package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Etat;
import exceptions.BusinessException;

public class EtatDAOJdbcImpl implements EtatDAO {
	
	/* REQUETES */
	private static final String SELECT_ALL = "SELECT * FROM ETATS";
	
	private static final String SELECT_ONE_BY_ID = SELECT_ALL + "WHERE no_Etat=?";
	
	private static final String INSERT = "INSERT INTO ETATS(libelle) VALUES (?)";
	
	private static final String UPDATE = "UPDATE ETATS SET libelle=? WHERE no_Etat=?";
	
	private static final String DELETE = "DELETE FROM ETATS WHERE no_Etat=?";
	
	
	/**
	 * Méthode qui sélectionne tous les éléments de la table ETATS
	 */
	@Override
	public List<Etat> selectAll() throws BusinessException {
		List<Etat> listeEtats = new ArrayList<Etat>();

		try(Connection cnx = ConnectionProvider.getConnection()) 
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) 
			{
				listeEtats.add(this.map(rs));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return listeEtats;
	}
	
	
	/**
	 * Méthode qui récupère tous les éléments de la table ETATS pour un ID donné
	 */
	@Override
	public Etat selectById(int idEtat) {
		Etat etat = null;

		try (Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ONE_BY_ID);

			pstmt.setInt(1, idEtat);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				etat = this.map(rs);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return etat;
	}
	
	
	/**
	 * Méthode qui permet d'ajouter un état à la table ETATS
	 */
	@Override
	public void insert(Etat etat) throws BusinessException {
		if (etat == null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		try (Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, etat.getLibelle());
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next())
			{
				etat.setIdEtat(rs.getInt(1));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
	}
	
	
	/**
	 * Méthode qui permet de modifier un état existant dans la table ETATS
	 */
	@Override
	public Etat update(Etat etat) throws SQLException {
		try (Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
			pstmt.setString(1, etat.getLibelle());
			pstmt.setInt(2, etat.getIdEtat());
			pstmt.executeUpdate();
			
		} catch (SQLException e)
		{
			throw new SQLException(e);
		}
		return etat; 
	}
	
	
	/**
	 * Méthode qui permet de supprimer un élément de la table ETATS
	 */
	@Override
	public void delete(int idEtat) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1, idEtat);
			pstmt.executeUpdate();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @param rs
	 * @return etat
	 * @throws SQLException
	 */
	private Etat map (ResultSet rs) throws SQLException {
		Etat etat = new Etat();
		etat.setIdEtat(rs.getInt("idEtat"));
		etat.setLibelle(rs.getString("libelle"));
		return etat;		
	}

}
