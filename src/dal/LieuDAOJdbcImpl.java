package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Lieu;
import bo.Ville;
import exceptions.BusinessException;

public class LieuDAOJdbcImpl implements LieuDAO {

	/* REQUETES */
	private static final String INSERT = "INSERT INTO LIEUX (nom_lieu, rue, latitude, longitude, villes_no_ville) VALUES (?,?,?,?,?)";

	private static final String UPDATE = "UPDATE LIEUX SET nom_lieu=?, rue=?, latitude=?, longitude=?, villes_no_ville=? WHERE no_lieu=?";

	private static final String DELETE = "DELETE FROM LIEUX WHERE no_lieu=?";

	private static final String SELECT_ALL = "SELECT no_lieu, nom_lieu, rue, latitude, longitude, villes_no_ville, nom_ville, code_postal FROM LIEUX INNER JOIN VILLES ON villes_no_ville = no_ville";

	private static final String SELECT_ONE_BY_ID = SELECT_ALL + "WHERE no_lieu=?";

	/**
	 * Méthode qui permet d'ajouter un état à la table LIEUX
	 */
	@Override
	public void insert(Lieu lieu) throws BusinessException {
		if (lieu == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, lieu.getNom());
			pstmt.setString(2, lieu.getRue());
			pstmt.setFloat(3, lieu.getLatitude());
			pstmt.setFloat(4, lieu.getLongitude());
			pstmt.setInt(5, lieu.getVille().getIdVille());
			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				lieu.setIdLieu(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
	}

	/**
	 * Méthode qui sélectionne tous les éléments de la table LIEUX
	 */
	@Override
	public List<Lieu> selectAll() throws BusinessException {
		List<Lieu> listeLieux = new ArrayList<Lieu>();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				listeLieux.add(this.map(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeLieux;
	}

	/**
	 * Méthode qui récupère tous les éléments de la table LIEUX pour un ID
	 * donné
	 */
	@Override
	public Lieu selectOneById(int idLieu) throws BusinessException {
		Lieu lieu = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ONE_BY_ID);

			pstmt.setInt(1, idLieu);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				lieu = this.map(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lieu;
	}

	/**
	 * Méthode qui permet de modifier un lieu existant dans la table LIEUX
	 */
	@Override
	public void update(Lieu lieu) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
			pstmt.setString(1, lieu.getNom());
			pstmt.setString(2, lieu.getRue());
			pstmt.setFloat(3, lieu.getLatitude());
			pstmt.setFloat(4, lieu.getLongitude());
			pstmt.setObject(5, lieu.getVille());
			pstmt.setInt(6, lieu.getIdLieu());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode qui permet de supprimer un élément de la table LIEUX
	 */
	@Override
	public void delete(int idLieu) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1, idLieu);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param rs
	 * @return lieu
	 * @throws SQLException
	 */
	private Lieu map(ResultSet rs) throws SQLException {
		Lieu lieu = new Lieu();
		lieu.setIdLieu(rs.getInt("no_lieu"));
		lieu.setNom(rs.getString("nom_lieu"));
		lieu.setRue(rs.getString("rue"));
		lieu.setLatitude(rs.getFloat("latitude"));
		lieu.setLongitude(rs.getFloat("longitude"));
		Ville ville = new Ville();
		ville.setIdVille(rs.getInt("villes_no_ville"));
		lieu.setVille(ville);
		return lieu;
	}

}
