package dal;

public abstract class CodesResultatDAL {
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int INSERT_OBJET_NULL=10000;
	
	/**
	 * Echec général quand erreur non générér à l'insertion 
	 */
	public static final int INSERT_OBJET_ECHEC=10001;
	
	/**
	 * Echec général quand erreur non général la selection
	 */
	public static final int SELECT_OBJET_ECHEC=10002;
	
	/**
	 * Echec de la lecture des participants
	 */
	public static final int LECTURE_PARTICIPANTS_ECHEC=10002;
	
}
