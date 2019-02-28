package bll;

import java.sql.SQLException;
import java.util.List;

import bo.Site;
import bo.Sortie;
import dal.DAOFactory;
import dal.SiteDAO;
import exceptions.BusinessException;
import util.ObjectUtil;

public final class SiteManager {

	private SiteDAO siteDAO;
	private ObjectUtil objectUtil = new ObjectUtil();

	public SiteManager() {
		this.siteDAO = DAOFactory.getSiteDAO();
	}

	public List<Site> selectAll() throws BusinessException {
		return this.siteDAO.selectAll();
	}

	public Site selectById(int idSite) throws BusinessException, SQLException {
		if (!this.VerificationIdSite(idSite)) {
			return this.siteDAO.selectById(idSite);
		} else {
			return null;
		}
	}

	public Site update(Site site) throws BusinessException, SQLException {
		if (!this.VerificationSite(site)) {
			return this.siteDAO.update(site);
		} else {
			return null;
		}
	}

	public void insert(Site siteNew) throws BusinessException {
		try {
			if (!this.VerificationSite(siteNew)) {
				this.siteDAO.insert(siteNew);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}

	}

	public void delete(int idSite) throws BusinessException {
		try {
			if (!this.VerificationIdSite(idSite)) {
				System.out.println(idSite);
				this.siteDAO.delete(idSite);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	private boolean VerificationSite(Site site) {
		return this.objectUtil.IsNull(site);
	}

	private boolean VerificationIdSite(int idSite) {
		return this.objectUtil.IsNull(idSite);
	}
}
