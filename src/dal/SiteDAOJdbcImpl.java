package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Site;
import exceptions.BusinessException;

public class SiteDAOJdbcImpl implements SiteDAO {

	private static final String INSERT = "INSERT INTO SITES (nom_site) VALUES (?)";

	private static final String UPDATE = "UPDATE SITES SET nom_site=? WHERE no_site=?";

	private static final String DELETE = "DELETE FROM SITES WHERE no_site=?";

	private static final String SELECT_ALL = "SELECT * FROM SITES";

	private static final String SELECT_ONE_BY_ID = "SELECT nom_site FROM SITES WHERE no_site=?";

	/**
	 * Méthode qui sélectionne tous les éléments de la table SITES
	 */
	@Override
	public List<Site> selectAll() throws BusinessException {
		List<Site> listeSites = new ArrayList<Site>();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				listeSites.add(this.map(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeSites;
	}

	/**
	 * Méthode qui permet d'ajouter un site à la table SITES
	 */
	@Override
	public void insert(Site site) throws BusinessException {
		if (site == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, site.getNom());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next()) {
				site.setIdSite(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
	}

	/**
	 * Méthode qui permet de modifier un site existant dans la table SITES
	 */
	@Override
	public Site update(Site site) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
			pstmt.setString(1, site.getNom());
			pstmt.setInt(2, site.getIdSite());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return site;
	}

	/**
	 * Méthode qui permet de supprimer un élément de la table SITES
	 */
	@Override
	public void delete(int idSite) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1, idSite);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode qui récupère tous les éléments de la table SITES pour un ID donné
	 */
	@Override
	public Site selectById(int idSite) throws SQLException {
		Site site = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ONE_BY_ID);

			pstmt.setInt(1, idSite);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				site = new Site(idSite);
				site.setNom(rs.getString("nom_site"));
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
		return site;
	}

	/**
	 * 
	 * @param rs
	 * @return site
	 * @throws SQLException
	 */
	private Site map(ResultSet rs) throws SQLException {
		Site site;
		site = new Site();

		site.setIdSite(rs.getInt("no_site"));
		site.setNom(rs.getString("nom_site"));

		return site;
	}

}