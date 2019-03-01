package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bo.Inscription;
import bo.Participant;
import bo.Sortie;
import exceptions.BusinessException;

public class InscriptionDAOJdbcImpl implements InscriptionDAO {

	private static final String SELECT_ALL = "SELECT date_inscription, sorties_no_sortie, participants_no_participant, "
			+ "no_sortie, nom, datedebut, duree, datecloture, nbinscriptionsmax, "
			+ "descriptioninfos, urlPhoto, organisateur, lieux_no_lieu, etats_no_etat "
			+ "FROM INSCRIPTIONS INNER JOIN SORTIES "
			+ "ON sorties_no_sortie = no_sortie "
			+ "ORDER BY datedebut ASC";
	private static final String SELECT_BY_ID_SORTIE = "SELECT INSCRIPTIONS.date_inscription, no_sortie, organisateur, PARTICIPANTS.nom as nomOrganisateur, prenom, pseudo "
			+ "FROM INSCRIPTIONS " + "INNER JOIN SORTIES ON sorties_no_sortie=no_sortie "
			+ "INNER JOIN PARTICIPANTS ON participants_no_participant=no_participant " + "WHERE no_sortie=?";
	private static final String INSERT = "INSERT INTO INSCRIPTIONS(date_inscription, sorties_no_sortie, participants_no_participant)"
			+ "VALUES(?,?,?)";
	/**
	 * Méthode qui sélectionne tous les éléments de la table INSCRIPTIONS
	 */
	@Override
	public List<Inscription> selectByIdSortie(int idSortie) throws BusinessException {
		List<Inscription> listesInscriptions = new ArrayList<Inscription>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID_SORTIE);
			pstmt.setInt(1, idSortie);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				listesInscriptions.add(this.map(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listesInscriptions;

	}

	private Inscription map(ResultSet rs) throws SQLException {
		Inscription inscription = new Inscription();
		inscription.setDateInscription(rs.getTimestamp("date_inscription"));

		Sortie sortie = new Sortie();
		sortie.setIdSortie(rs.getInt("sorties_no_sortie"));
		Participant participant = new Participant();
		participant.setIdparticipant(rs.getInt("participants_no_participant"));

		inscription.setIdParticipant(participant);
		inscription.setIdSortie(sortie);

		return inscription;
	}

	@Override
	public List<Inscription> selectAll() throws BusinessException {
		List<Inscription> listeInscriptions = new ArrayList<Inscription>();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				listeInscriptions.add(this.map(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listeInscriptions;
	}
	
	/**
	 * Méthode qui permet d'ajouter une ville à la table VILLES
	 */
	@Override
	public void insert(Inscription inscription) throws BusinessException {
		if (inscription == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setTimestamp(1, new Timestamp(inscription.getDateInscription().getTime()));
			pstmt.setInt(2, inscription.getIdSortie().getIdSortie());
			pstmt.setInt(3, inscription.getIdParticipant().getIdparticipant());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
	}
}
