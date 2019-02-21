package bll;

import java.util.List;

import bo.Inscription;
import dal.DAOFactory;
import dal.InscriptionDAO;
import exceptions.BusinessException;

public final class InscriptionManager {

	private InscriptionDAO inscriptionDAO;

	public InscriptionManager() {
		this.inscriptionDAO = DAOFactory.getInscriptionDAO();
	}

	public List<Inscription> selectAll() throws BusinessException {
		return this.inscriptionDAO.selectAll();
	}

	public List<Inscription> selectById(int idSortie) throws BusinessException {
		return this.inscriptionDAO.selectByIdSortie(idSortie);
	}
}
