package bo;

public class Ville {
	
	int idVille;
	String nom_ville;
	String codePostal;
	
	
	public Ville() {
		
	}
	
	public Ville(int idVille) {
		super();
		this.idVille = idVille;
	}
	
	public Ville (int idVille, String nom_ville) {
		super();
		this.nom_ville = nom_ville;
	}
	
	public Ville (int idVille, String nom_ville, String codePostal) {
		super();
		this.codePostal = codePostal;
	}
	
	
	
	public int getIdVille() {
		return idVille;
	}
	public void setIdVille(int idVille) {
		this.idVille = idVille;
	}
	public String getNom() {
		return nom_ville;
	}
	public void setNom(String nom_ville) {
		this.nom_ville = nom_ville;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	
	
	@Override
	public String toString() {
		return "Ville [idVille=" + idVille + ", nom_ville=" + nom_ville + ", codePostal=" + codePostal + "]";
	}
}