package bo;

public class Etat {

	int idEtat;
	String libelle;

	public Etat() {

	}

	public Etat(int idEtat) {
		super();
		this.idEtat = idEtat;
	}

	public Etat(int idEtat, String libelle) {
		super();
		this.libelle = libelle;
	}

	public int getIdEtat() {
		return idEtat;
	}

	public void setIdEtat(int idEtat) {
		this.idEtat = idEtat;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Etat [idEtat=" + idEtat + ", libelle=" + libelle + "]";
	}
}