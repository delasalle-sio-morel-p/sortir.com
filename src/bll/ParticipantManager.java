package bll;

import java.sql.SQLException;
import java.util.List;

import bo.Participant;
import dal.DAOFactory;
import dal.ParticipantDAO;
import exceptions.BusinessException;

public final class ParticipantManager {

	private ParticipantDAO participantDAO;

	public ParticipantManager() {
		this.participantDAO = DAOFactory.getParticipantDAO();
	}

	public List<Participant> selectAllNotes() throws BusinessException {
		return this.participantDAO.selectAll();
	}

	public Participant selectOne(String pseudo) throws BusinessException, SQLException {
		return this.participantDAO.selectByPseudo(pseudo);
	}

	public Participant selectById(int id) throws BusinessException, SQLException {
		return this.participantDAO.selectById(id);
	}

	public Participant ajouter(String nom, String prenom, String pseudo, String motDePasse, String telephone,
			String email, boolean administrateur, boolean actif) throws BusinessException {
		BusinessException exception = new BusinessException();

		Participant participant = new Participant(nom, prenom, pseudo, motDePasse, telephone, email, administrateur,
				actif);

		if (!exception.hasErreurs()) {
			this.participantDAO.insert(participant);
		}

		if (exception.hasErreurs()) {
			throw exception;
		}
		return participant;
	}

	public Participant modifier(Participant participant) throws BusinessException, SQLException {
		return this.participantDAO.update(participant);
	}

	public Participant modifierSansMDP(Participant participant) throws BusinessException, SQLException {
		return this.participantDAO.updateWithoutMDP(participant);
	}

	public void supprimer(int id) throws BusinessException {
		this.participantDAO.delete(id);
	}

	public Participant login(String pseudo) throws BusinessException, SQLException {
		Participant participant = null;
		BusinessException exception = new BusinessException();

		if (!exception.hasErreurs()) {
			participant = this.participantDAO.login(pseudo);

		}
		if (exception.hasErreurs()) {
			throw exception;
		}
		return participant;
	}

	public void verifPseudo(String pseudo) throws BusinessException, SQLException {
		BusinessException businessException = new BusinessException();
		if (participantDAO.selectByPseudo(pseudo) == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_PSEUDO_NON_UNIQUE);
		}
	}

}
