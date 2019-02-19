package dal;

public abstract class DAOFactory {
	public static EtatDAO getEtatDAO()
	{
		return new EtatDAOJdbcImpl();
	}
}
