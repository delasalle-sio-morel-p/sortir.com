package bll;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {
	
	/**
	 * Echec quand la liste des partcipants  ne respecte pas les regles definies
	 */
	public static final int REGLE_PASSWORD_NON_IDENTIQUE=20000;
	/**
	 * Echec quand la liste des partcipants  ne respecte pas les regles definies
	 */
	public static final int REGLE_PSEUDO_NON_UNIQUE=20001;
	
}