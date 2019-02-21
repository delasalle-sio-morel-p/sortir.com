package bo;

public class Lieu {

	int idLieu;
	String nom;
	String rue;
	Float latitude;
	Float longitude;
	Ville idVille;

	public Lieu() {

	}

	public Lieu(int idLieu) {
		super();
		this.idLieu = idLieu;
	}

	public Lieu(int idLieu, String nom) {
		super();
		this.nom = nom;
	}

	public Lieu(int idLieu, String nom, String rue) {
		super();
		this.rue = rue;
	}

	public Lieu(int idLieu, String nom, String rue, Float latitude) {
		super();
		this.latitude = latitude;
	}

	public Lieu(int idLieu, String nom, String rue, Float latitude, Float longitude) {
		super();
		this.longitude = longitude;
	}

	public Lieu(int idLieu, String nom, String rue, Float latitude, Float longitude, Ville idVille) {
		super();
		this.idVille = idVille;
	}

	public Lieu(String nom, String rue, Float latitude, Float longitude, Ville idVille) {
		super();
		this.nom = nom;
		this.rue = rue;
		this.latitude = latitude;
		this.longitude = longitude;
		this.idVille = idVille;
	}

	public int getIdLieu() {
		return idLieu;
	}

	public void setIdLieu(int idLieu) {
		this.idLieu = idLieu;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Ville getIdVille() {
		return idVille;
	}

	public void setIdVille(Ville idVille) {
		this.idVille = idVille;
	}

	@Override
	public String toString() {
		return "Lieu [idLieu=" + idLieu + ", nom=" + nom + ", rue=" + rue + ", latitude=" + latitude + ", longitude="
				+ longitude + ", idVille=" + idVille + "]";
	}

}