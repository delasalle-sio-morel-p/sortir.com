package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Etat;
import bo.Inscription;
import bo.Participant;
import bo.Sortie;
import exceptions.BusinessException;

public class InscriptionDAOJdbcImpl implements InscriptionDAO {

	private static final String SELECT_ALL = "SELECT * FROM INSCRIPTIONS";
	private static final String SELECT_BY_ID_SORTIE = "SELECT INSCRIPTIONS.date_inscription, no_sortie, organisateur, PARTICIPANTS.nom as nomOrganisateur, prenom, pseudo "
			+ "FROM INSCRIPTIONS " + "INNER JOIN SORTIES ON sorties_no_sortie=no_sortie "
			+ "INNER JOIN PARTICIPANTS ON participants_no_participant=no_participant " + "WHERE no_sortie=?";

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
		sortie.setIdSortie(rs.getInt("no_sortie"));

		inscription.setIdSortie(sortie);
		Participant participant = new Participant();
		participant.setIdparticipant(rs.getInt("organisateur"));
		participant.setNom(rs.getString("nomOrganisateur"));
		participant.setPrenom(rs.getString("prenom"));
		participant.setPseudo(rs.getString("pseudo"));

		inscription.setIdParticipant(participant);

		return inscription;
	}

	@Override
	public List<Inscription> selectAll() throws BusinessException {
		List<Inscription> listeInscriptions = new ArrayList<Inscription>();

		try(Connection cnx = ConnectionProvider.getConnection()) 
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) 
			{
				listeInscriptions.add(this.map(rs));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return listeInscriptions;
	}
}
