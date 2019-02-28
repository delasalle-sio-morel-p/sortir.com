package bll;

import java.sql.SQLException;
import java.util.List;

import bo.Participant;
import dal.DAOFactory;
import dal.ParticipantDAO;
import exceptions.BusinessException;
import util.ObjectUtil;

public final class ParticipantManager {

	private ParticipantDAO participantDAO;
	private ObjectUtil objectUtil = new ObjectUtil();

	public ParticipantManager() {
		this.participantDAO = DAOFactory.getParticipantDAO();
	}

	public List<Participant> selectAllNotes() throws BusinessException {
		return this.participantDAO.selectAll();
	}

	public Participant selectOne(String pseudo) throws BusinessException, SQLException {
		if (!this.objectUtil.IsNull(pseudo)) {
			return this.participantDAO.selectByPseudo(pseudo);
		} else {
			return null;
		}
	}

	public Participant selectById(int idParticipant) throws BusinessException, SQLException {
		if (!this.VerificationIdParticipant(idParticipant)) {
			return this.participantDAO.selectById(idParticipant);
		} else {
			return null;
		}
	}

	public Participant ajouter(Participant participantAjout) throws BusinessException {
		BusinessException exception = new BusinessException();

		Participant participant = new Participant(participantAjout);

		if (!exception.hasErreurs() && !this.VerificationParticipant(participant)) {
			this.participantDAO.insert(participant);
		}

		if (exception.hasErreurs()) {
			throw exception;
		}
		return participant;
	}

	public Participant modifier(Participant participant) throws BusinessException, SQLException {
		if (!this.VerificationParticipant(participant)) {
			return this.participantDAO.update(participant);
		} else {
			return null;
		}
	}

	public Participant modifierSansMDP(Participant participant) throws BusinessException, SQLException {
		if (!this.VerificationParticipant(participant)) {
			return this.participantDAO.updateWithoutMDP(participant);
		} else {
			return null;
		}
	}

	public void supprimer(int idParticipant) throws BusinessException {
		try {
			if (!this.VerificationIdParticipant(idParticipant)) {
				this.participantDAO.delete(idParticipant);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
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

	private boolean VerificationParticipant(Participant participant) {
		return this.objectUtil.IsNull(participant);
	}

	private boolean VerificationIdParticipant(int idParticipant) {
		return this.objectUtil.IsNull(idParticipant);
	}

}
