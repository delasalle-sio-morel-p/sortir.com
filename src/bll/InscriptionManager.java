package bll;

import java.util.List;

import bo.Inscription;
import bo.Ville;
import dal.DAOFactory;
import dal.InscriptionDAO;
import exceptions.BusinessException;
import util.ObjectUtil;

public final class InscriptionManager {

	private InscriptionDAO inscriptionDAO;
	private ObjectUtil objectUtil = new ObjectUtil();

	public InscriptionManager() {
		this.inscriptionDAO = DAOFactory.getInscriptionDAO();
	}

	public List<Inscription> selectAll() throws BusinessException {
		return this.inscriptionDAO.selectAll();
	}

	public List<Inscription> selectById(int idSortie) throws BusinessException {
		if (!this.objectUtil.IsNull(idSortie)) {
			return this.inscriptionDAO.selectByIdSortie(idSortie);
		} else {
			return null;
		}
	}
	public void insert(Inscription inscription) throws BusinessException {
		try {
			this.inscriptionDAO.insert(inscription);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}
