package bll;

import java.sql.SQLException;
import java.util.List;

import bo.Sortie;
import dal.DAOFactory;
import dal.SortieDAO;
import exceptions.BusinessException;
import util.ObjectUtil;

public final class SortieManager {

	private SortieDAO sortieDAO;
	private ObjectUtil objectUtil = new ObjectUtil();

	public SortieManager() {
		this.sortieDAO = DAOFactory.getSortieDAO();
	}

	public List<Sortie> selectAll() throws BusinessException {
		return this.sortieDAO.selectAll();
	}

	public Sortie selectAllById(int idSortie) throws BusinessException {
		if (!this.VerificationIdSortie(idSortie)) {
			return this.sortieDAO.selectOneById(idSortie);
		} else {
			return null;
		}
	}

	public Sortie update(Sortie sortie) throws BusinessException, SQLException {
		if (!this.VerificationSortie(sortie)) {
			return this.sortieDAO.update(sortie);
		} else {
			return null;
		}
	}

	public Sortie updateEtat(Sortie sortie) throws BusinessException, SQLException {
		if (!this.VerificationSortie(sortie)) {
			return this.sortieDAO.updateEtatSortie(sortie);
		} else {
			return null;
		}

	}

	public void insert(Sortie sortie) throws BusinessException {
		try {
			if (!this.VerificationSortie(sortie)) {
			}
			this.sortieDAO.insert(sortie);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	public void supprimer(int idSortie) throws BusinessException {
		try {
			if (!this.VerificationIdSortie(idSortie)) {
				this.sortieDAO.delete(idSortie);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	private boolean VerificationSortie(Sortie sortie) {
		return this.objectUtil.IsNull(sortie);
	}

	private boolean VerificationIdSortie(int idSortie) {
		return this.objectUtil.IsNull(idSortie);
	}
}
