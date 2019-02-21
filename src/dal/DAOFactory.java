package dal;

public abstract class DAOFactory {

	public static ParticipantDAO getParticipantDAO() {
		return new ParticipantDAOJdbcImpl();
	}

	public static SiteDAO getSiteDAO() {
		return new SiteDAOJdbcImpl();
	}

	public static LieuDAO getLieuDAO() {
		return new LieuDAOJdbcImpl();
	}

	public static VilleDAO getVilleDAO() {
		return new VilleDAOJdbcImpl();
	}

	public static EtatDAO getEtatDAO() {
		return new EtatDAOJdbcImpl();
	}

	public static SortieDAO getSortieDAO() {
		return new SortieDAOJdbcImpl();
	}

	public static InscriptionDAO getInscriptionDAO() {
		return new InscriptionDAOJdbcImpl();
	}
}
