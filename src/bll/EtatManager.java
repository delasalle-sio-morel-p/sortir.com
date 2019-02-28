package bll;

import java.sql.SQLException;
import java.util.List;

import bo.Etat;
import dal.DAOFactory;
import dal.EtatDAO;
import util.ObjectUtil;
import exceptions.BusinessException;

public final class EtatManager {

	private EtatDAO etatDAO;
	private ObjectUtil objectUtil = new ObjectUtil();

	public EtatManager() {
		this.etatDAO = DAOFactory.getEtatDAO();
	}

	public List<Etat> selectAll() throws BusinessException {
		return this.etatDAO.selectAll();
	}

	public Etat selectAllById(int idEtat) throws BusinessException, SQLException {
		if (!this.VerificationEtat(idEtat)) {
			return this.etatDAO.selectById(idEtat);
		}
		return new Etat();
	}

	public void supprimer(int idEtat) throws BusinessException {
		if (!this.VerificationEtat(idEtat)) {
			this.etatDAO.delete(idEtat);
		}

	}

	private boolean VerificationEtat(int idEtat) throws BusinessException {
		return this.objectUtil.IsNull(idEtat);
	}

}
