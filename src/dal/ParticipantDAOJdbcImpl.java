package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import bo.Participant;
import bo.Site;
import exceptions.BusinessException;

public class ParticipantDAOJdbcImpl implements  ParticipantDAO {

	private static final String INSERT="INSERT INTO PARTICIPANTS (nom, prenom, pseudo, mot_de_passe, telephone, mail, administrateur, actif) VALUES (?,?,?,?,?,?,?,?)";

	private static final String UPDATE = "UPDATE PARTICIPANTS SET nom=?, prenom=?, pseudo=?, mot_de_passe=?, telephone=?, mail=?, sites_no_site=? WHERE no_participant=?";

	private static final String UPDATE_WITHOUT_MDP = "UPDATE PARTICIPANTS SET nom=?, prenom=?, pseudo=?, telephone=?, mail=?, sites_no_site=? WHERE no_participant=?";
	
	private static final String DELETE="DELETE FROM PARTICIPANTS WHERE no_participant=?";

	private static final String SELECT_ALL="SELECT no_participant, nom, prenom, pseudo, telephone, mail, administrateur, actif, sites_no_site FROM PARTICIPANTS" ;

	private static final String SELECT_ONE_BY_PSEUDO="SELECT no_participant, nom, prenom, pseudo, telephone, mail, administrateur, actif, sites_no_site, mot_de_passe FROM PARTICIPANTS WHERE pseudo=?";
	
	private static final String SELECT_ONE_BY_ID="SELECT no_participant, nom, prenom, pseudo, telephone, mail, administrateur, actif, sites_no_site, mot_de_passe FROM PARTICIPANTS WHERE no_participant=?";

	private static final String SELECT_LOGIN="SELECT no_participant, nom, prenom, pseudo, telephone, mail, administrateur, actif, sites_no_site, mot_de_passe FROM PARTICIPANTS WHERE pseudo=? ";

	
	/**
	 * Méthode qui permet d'ajouter un participant à la table PARTICIPANTS
	 */
	@Override
	public void insert(Participant participant) throws BusinessException {
		if (participant == null) 
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, participant.getNom());
			pstmt.setString(2, participant.getPrenom());
			pstmt.setString(3, participant.getPseudo());
			pstmt.setString(4, hashPassword(participant.getMotDePasse()));
			pstmt.setString(5, participant.getTelephone());
			pstmt.setString(6, participant.getEmail());
			pstmt.setBoolean(7, participant.isAdministrateur());
			pstmt.setBoolean(8, participant.isActif());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next())
			{
				participant.setIdparticipant(rs.getInt(1));
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
	 * Méthode qui permet de modifier un participant existant dans la table PARTICIPANTS
	 */
	@Override
	public Participant update(Participant participant) throws SQLException {
		try (Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
			pstmt.setString(1, participant.getNom());
			pstmt.setString(2, participant.getPrenom());
			pstmt.setString(3, participant.getPseudo());
			pstmt.setString(4, hashPassword(participant.getMotDePasse()));
			pstmt.setString(5, participant.getTelephone());
			pstmt.setString(6, participant.getEmail());
			pstmt.setInt(7, participant.getSiteRattachement().getIdSite());
			pstmt.setInt(8, participant.getIdparticipant());
			pstmt.executeUpdate();
		} catch (SQLException e)
		{
			throw new SQLException(e);
		}
		return participant;
	}
	
	
	
	/**
	 * Méthode appelée lorsque l'on souhaite mettre à jour le participant sans toucher à son mot de passe
	 */
	@Override
	public Participant updateWithoutMDP(Participant participant) throws SQLException {
		try (Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_WITHOUT_MDP);
			pstmt.setString(1, participant.getNom());
			pstmt.setString(2, participant.getPrenom());
			pstmt.setString(3, participant.getPseudo());
			pstmt.setString(4, participant.getTelephone());
			pstmt.setString(5, participant.getEmail());
			pstmt.setInt(6, participant.getSiteRattachement().getIdSite());
			pstmt.setInt(7, participant.getIdparticipant());
			pstmt.executeUpdate();
		} catch (SQLException e)
		{
			throw new SQLException(e);
		}
		return participant;
	}


	/**
	 * Méthode qui permet de supprimer un élément de la table PARTICIPANTS
	 */
	@Override
	public void delete(int idParticipant) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1, idParticipant);
			pstmt.executeUpdate();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * Méthode qui sélectionne tous les éléments de la table PARTICIPANTS
	 */
	@Override
	public List<Participant> selectAll() throws BusinessException {
		List<Participant> listesParticipants = new ArrayList<Participant>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			Statement pstmt = cnx.createStatement();
			ResultSet rs = pstmt.executeQuery(SELECT_ALL);

			while(rs.next())
			{
				listesParticipants.add(this.participantBuilder(rs));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return listesParticipants;
	}


	/**
	 * Méthode qui récupère tous les éléments de la table PARTICIPANTS pour un pseudo donné
	 */
	@Override
	public Participant selectByPseudo(String pseudo) throws SQLException {
		Participant participant = null;

		try (Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ONE_BY_PSEUDO);
			pstmt.setString(1, pseudo);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
			{
				participant = this.participantBuilder(rs);
			}
		} catch (SQLException e)
		{
			throw new SQLException(e);
		}
		return participant;
	}
	

	/**
	 * Méthode qui récupère tous les éléments de la table PARTICIPANTS pour un ID donné
	 */
	@Override
	public Participant selectById(int idParticipant) throws SQLException {
		Participant participant = null;

		try (Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ONE_BY_ID);
			pstmt.setInt(1, idParticipant);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
			{
				participant = this.participantBuilder(rs);
			}
		} catch (SQLException e)
		{
			throw new SQLException(e);
		}
		return participant;
	}


	/**
	 * Méthode qui permet de récupérer le Pseudo et le MdP pour se connecter à l'application
	 */
	@Override
	public Participant login(String pseudo) throws SQLException {
		Participant participant = null;
		try (Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_LOGIN);
			pstmt.setString(1,pseudo);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next())
			{
				participant = this.participantBuilder(rs);

			} else {
				throw new IllegalArgumentException("User/Password inconnu");
			}
		} catch (SQLException e)
		{
			throw new SQLException(e);
		}
		return participant;
	}

	
	/**
	 * Méthode qui permet de crypter le mot de passe avec BCrypt
	 * 
	 * @param plainPassword
	 * @return
	 */
	private String hashPassword(String plainPassword) {
		return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
	}

	

	/**
	 * 
	 * @param rs
	 * @return participantCourant
	 * @throws SQLException
	 */
	public Participant participantBuilder(ResultSet rs) throws SQLException {
		Participant participantCourant;
		participantCourant = new Participant();
		participantCourant.setIdparticipant(rs.getInt("idParticipant"));
		participantCourant.setNom(rs.getString("nom"));
		participantCourant.setPrenom(rs.getString("prenom"));
		participantCourant.setPseudo(rs.getString("pseudo"));
		participantCourant.setMotDePasse(rs.getString("motDePasse"));
		participantCourant.setTelephone(rs.getString("telephone"));
		participantCourant.setEmail(rs.getString("email"));
		participantCourant.setAdministrateur(rs.getBoolean("administrateur"));
		participantCourant.setActif(rs.getBoolean("actif"));

		Site siteRattachement = new Site();
		siteRattachement.setIdSite(rs.getInt("sites_idSite"));
		participantCourant.setSiteRattachement(siteRattachement);

		return participantCourant;
	}

}