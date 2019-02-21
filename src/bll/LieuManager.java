package bll;

import java.util.List;

import bo.Lieu;
import dal.DAOFactory;
import dal.LieuDAO;
import exceptions.BusinessException;

public final class LieuManager {

	private LieuDAO lieuDAO;

	public LieuManager() {
		this.lieuDAO = DAOFactory.getLieuDAO();
	}

	public List<Lieu> selectAll() throws BusinessException {
		return this.lieuDAO.selectAll();
	}

	public Lieu selectAllById(int idLieu) throws BusinessException {
		return this.lieuDAO.selectOneById(idLieu);
	}

	public void supprimer(int id) throws BusinessException {
		this.lieuDAO.delete(id);
	}

	public void ajouter(Lieu lieu) throws BusinessException {
		this.lieuDAO.insert(lieu);
	}

}
