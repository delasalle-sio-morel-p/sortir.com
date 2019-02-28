package bll;

import java.sql.SQLException;
import java.util.List;

import bo.Ville;
import util.ObjectUtil;
import dal.DAOFactory;
import dal.VilleDAO;
import exceptions.BusinessException;

public class VilleManager {

	private VilleDAO villeDAO;
	private ObjectUtil objectUtil = new ObjectUtil();

	public VilleManager() {
		this.villeDAO = DAOFactory.getVilleDAO();
	}

	public List<Ville> selectAll() throws BusinessException {
		return this.villeDAO.selectAll();
	}

	public Ville selectById(int idVille) throws BusinessException, SQLException {
		if (!this.VerifivationIdVille(idVille)) {
			return this.villeDAO.selectOneById(idVille);
		} else {
			return null;
		}
	}

	public void insert(Ville ville) throws BusinessException {
		try {
			if (!this.VerificationVille(ville)) {
				this.villeDAO.insert(ville);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	public void delete(int idVille) throws SQLException, BusinessException {
		try {
			if (!this.VerifivationIdVille(idVille)) {
				this.villeDAO.delete(idVille);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	public Ville update(Ville ville) throws BusinessException, SQLException {
		if (!this.VerificationVille(ville)) {
			return this.villeDAO.update(ville);
		}
		return null;
	}

	private boolean VerificationVille(Ville ville) {
		return this.objectUtil.IsNull(ville);
	}

	private boolean VerifivationIdVille(int idVille) {
		return this.objectUtil.IsNull(idVille);
	}
}
