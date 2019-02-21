package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bo.Etat;
import bo.Lieu;
import bo.Participant;
import bo.Site;
import bo.Sortie;
import bo.Ville;
import exceptions.BusinessException;

public class SortieDAOJdbcImpl implements SortieDAO {

	private static final String SELECT_ALL = "SELECT no_Sortie, SORTIES.nom as nomSortie, datedebut, duree, datecloture, nbinscriptionsmax, descriptioninfos, urlPhoto, "
			+ "organisateur, PARTICIPANTS.nom as nomOrganisateur, prenom, pseudo, sites_no_Site,  "
			+ "lieux_no_Lieu, nom_lieu, rue, latitude, longitude, villes_no_Ville, "
			+ "etats_no_Etat, libelle FROM SORTIES " + "INNER JOIN PARTICIPANTS ON organisateur = no_Participant "
			+ "INNER JOIN SITES ON sites_no_Site = no_Site " + "INNER JOIN LIEUX ON lieux_no_Lieu = no_Lieu "
			+ "INNER JOIN ETATS ON etats_no_Etat = no_Etat " + "INNER JOIN VILLES ON villes_no_Ville = no_Ville "
			+ "ORDER BY datedebut ASC ";

	private static final String SELECT_ONE_BY_ID = "SELECT no_Sortie, SORTIES.nom as nomSortie, datedebut, duree, datecloture, nbinscriptionsmax, descriptioninfos, urlPhoto, "
			+ "organisateur, PARTICIPANTS.nom as nomOrganisateur, prenom, pseudo, sites_no_Site,  "
			+ "lieux_no_Lieu, nom_lieu, rue, latitude, longitude, villes_no_Ville, "
			+ "etats_no_Etat, libelle FROM SORTIES " + "INNER JOIN PARTICIPANTS ON organisateur = no_Participant "
			+ "INNER JOIN SITES ON sites_no_Site = no_Site " + "INNER JOIN LIEUX ON lieux_no_Lieu = no_Lieu "
			+ "INNER JOIN ETATS ON etats_no_Etat = no_Etat " + "INNER JOIN VILLES ON villes_no_Ville = no_Ville "
			+ "WHERE no_Sortie=?";

	private static final String INSERT = "INSERT INTO SORTIES(nom, datedebut, duree, datecloture, nbinscriptionsmax, descriptioninfos, etatsortie, urlPhoto, organisateur, lieux_no_Lieu, etats_no_Etat)"
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

	private static final String UPDATE = "UPDATE SORTIES SET nom=?, datedebut=?, duree=?, datecloture=?, nbinscriptionsmax=?, descriptioninfos=?,urlPhoto=?, organisateur=?, lieux_no_Lieu=?, etats_no_Etat=? WHERE no_Sortie=?";

	private static final String UPDATE_ANNULER = "UPDATE SORTIES SET descriptioninfos=?, etats_no_Etat=? WHERE no_Sortie=?";

	private static final String DELETE = "DELETE FROM SORTIES WHERE no_Sortie=?";

	/**
	 * Méthode qui sélectionne tous les éléments de la table SORTIES
	 */
	@Override
	public List<Sortie> selectAll() throws BusinessException {
		List<Sortie> listeSorties = new ArrayList<Sortie>();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				listeSorties.add(this.map(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeSorties;
	}

	/**
	 * Méthode qui récupère tous les éléments de la table SORTIES pour un ID donné
	 */
	@Override
	public Sortie selectOneById(int idSortie) throws BusinessException {
		Sortie sortie = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ONE_BY_ID);
			pstmt.setInt(1, idSortie);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				sortie = this.map(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sortie;
	}

	/**
	 * Méthode qui permet d'ajouter une sorties à la table SORTIES
	 */
	@Override
	public void insert(Sortie sortie) throws BusinessException {
		if (sortie == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, sortie.getNom());
			pstmt.setTimestamp(2, new Timestamp(sortie.getDateHeureDebut().getTime()));
			pstmt.setInt(3, sortie.getDuree());
			pstmt.setTimestamp(4, new Timestamp(sortie.getDateHeureFin().getTime()));
			pstmt.setInt(5, sortie.getNbParticipantMax());
			pstmt.setString(6, sortie.getDescription());
			pstmt.setString(7, sortie.getUrlPhoto());
			pstmt.setInt(8, sortie.getOrganisateur().getIdparticipant());
			pstmt.setInt(9, sortie.getIdLieu().getIdLieu());
			pstmt.setInt(10, sortie.getIdEtat().getIdEtat());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next()) {
				sortie.setIdSortie(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

	}

	/**
	 * Méthode qui permet de modifier une sortie
	 */
	@Override
	public Sortie update(Sortie sortie) throws SQLException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
			pstmt.setString(1, sortie.getNom());
			pstmt.setTimestamp(2, new Timestamp(sortie.getDateHeureDebut().getTime()));
			pstmt.setInt(3, sortie.getDuree());
			pstmt.setTimestamp(4, new Timestamp(sortie.getDateHeureFin().getTime()));
			pstmt.setInt(5, sortie.getNbParticipantMax());
			pstmt.setString(6, sortie.getDescription());
			pstmt.setString(7, sortie.getUrlPhoto());
			pstmt.setInt(8, sortie.getOrganisateur().getIdparticipant());
			pstmt.setInt(9, sortie.getIdLieu().getIdLieu());
			pstmt.setInt(10, sortie.getIdEtat().getIdEtat());
			pstmt.setInt(11, sortie.getIdSortie());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		}
		return sortie;
	}

	/**
	 * Méthode qui permet de un état annulée d'une sortie existant
	 */
	@Override
	public Sortie updateEtatSortie(Sortie sortie) throws SQLException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ANNULER);
			pstmt.setString(1, sortie.getDescription());
			pstmt.setInt(2, sortie.getIdEtat().getIdEtat());
			pstmt.setInt(3, sortie.getIdSortie());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		}
		return sortie;
	}

	/**
	 * Méthode qui permet de supprimer un élément de la table SORTIES
	 */
	@Override
	public void delete(int idSortie) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1, idSortie);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param rs
	 * @return sortie
	 * @throws SQLException
	 */
	public Sortie map(ResultSet rs) throws SQLException {
		Sortie sortie = new Sortie();

		sortie.setIdSortie(rs.getInt("no_sortie"));
		sortie.setNom(rs.getString("nomSortie"));
		sortie.setDateHeureDebut(rs.getTimestamp("datedebut"));
		sortie.setDuree(rs.getInt("duree"));
		sortie.setDateHeureFin(rs.getTimestamp("datecloture"));
		sortie.setNbParticipantMax(rs.getInt("nbinscriptionsmax"));
		sortie.setDescription(rs.getString("descriptioninfos"));
		sortie.setUrlPhoto(rs.getString("urlPhoto"));

		Participant participant = new Participant();
		participant.setIdparticipant(rs.getInt("organisateur"));
		participant.setNom(rs.getString("nomOrganisateur"));
		participant.setPrenom(rs.getString("prenom"));
		participant.setPseudo(rs.getString("pseudo"));
		Site siteRattachement = new Site();
		siteRattachement.setIdSite(rs.getInt("sites_no_site"));
		participant.setSiteRattachement(siteRattachement);
		sortie.setOrganisateur(participant);

		Lieu lieu = new Lieu();
		lieu.setIdLieu(rs.getInt("lieux_no_lieu"));
		lieu.setNom(rs.getString("nom_lieu"));
		lieu.setRue(rs.getString("rue"));
		lieu.setLatitude(rs.getFloat("latitude"));
		lieu.setLongitude(rs.getFloat("longitude"));
		Ville ville = new Ville();
		ville.setIdVille(rs.getInt("villes_no_ville"));
		lieu.setIdVille(ville);
		sortie.setIdLieu(lieu);

		Etat etat = new Etat();
		etat.setIdEtat(rs.getInt("etats_no_etat"));
		etat.setLibelle(rs.getString("libelle"));
		sortie.setIdEtat(etat);

		return sortie;
	}

}