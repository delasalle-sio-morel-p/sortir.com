package bo;

public class Site {

	private int idSite;
	private String nom;

	public Site() {

	}

	public Site(int idSite) {
		this.idSite = idSite;
	}

	public Site(int idSite, String nom) {
		this(idSite);
		this.nom = nom;
	}

	public int getIdSite() {
		return idSite;
	}

	public void setIdSite(int idSite) {
		this.idSite = idSite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Site [idSite=" + idSite + ", nom=" + nom + "]";
	}

}