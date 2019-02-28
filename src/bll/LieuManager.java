package bll;

import java.util.List;

import bo.Lieu;
import dal.DAOFactory;
import dal.LieuDAO;
import exceptions.BusinessException;
import util.ObjectUtil;

public final class LieuManager {

	private LieuDAO lieuDAO;
	private ObjectUtil objectUtil = new ObjectUtil();

	public LieuManager() {
		this.lieuDAO = DAOFactory.getLieuDAO();
	}

	public List<Lieu> selectAll() throws BusinessException {
		return this.lieuDAO.selectAll();
	}

	public Lieu selectAllById(int idLieu) throws BusinessException {
		if (!this.VerificationIdLieu(idLieu)) {
			return this.lieuDAO.selectOneById(idLieu);
		} else {
			return null;
		}
	}

	public void supprimer(int idLieu) throws BusinessException {
		try {
			if (!this.VerificationIdLieu(idLieu)) {
				this.lieuDAO.delete(idLieu);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}

	}

	public void ajouter(Lieu lieu) throws BusinessException {
		try {
			if (!this.objectUtil.IsNull(lieu)) {
				this.lieuDAO.insert(lieu);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}

	}

	private boolean VerificationIdLieu(int idLieu) {
		return this.objectUtil.IsNull(idLieu);
	}
}
