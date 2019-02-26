package bo;

public class Ville {

	int idVille;
	String nomVille;
	String codePostal;

	public Ville() {

	}

	public Ville(int idVille) {
		super();
		this.idVille = idVille;
	}

	public Ville(int idVille, String nomVille) {
		super();
		this.nomVille = nomVille;
	}

	public Ville(int idVille, String nomVille, String codePostal) {
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
		return nomVille;
	}

	public void setNom(String nomVille) {
		this.nomVille = nomVille;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	@Override
	public String toString() {
		return "Ville [idVille=" + idVille + ", nomVille=" + nomVille + ", codePostal=" + codePostal + "]";
	}
}