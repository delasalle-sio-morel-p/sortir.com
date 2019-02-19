package bo;

public class Participant {

	private int idParticipant;
	private String nom;
	private String prenom;
	private String pseudo;
	private String motDePasse;
	private String telephone;
	private String email;
	private boolean administrateur;
	private boolean actif;
	private Site siteRattachement;

	public Participant() {
		
	}
	
	public Participant(int idParticipant, String nom, String prenom, String pseudo, String motDePasse, String telephone, String email, Site siteRattachement) {
		super();
		this.idParticipant = idParticipant;
		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.telephone = telephone;
		this.email = email;
		this.siteRattachement = siteRattachement;
	}

	public Participant(String nom, String prenom, String pseudo, String motDePasse, String telephone, String email, Site siteRattachement) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.telephone = telephone;
		this.email = email;
		this.siteRattachement = siteRattachement;
	}

	public Participant(String nom, String prenom, String pseudo, String motDePasse, String telephone, String email, boolean administrateur, boolean actif) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.telephone = telephone;
		this.email = email;
		this.administrateur = administrateur;
		this.actif = actif;
	}

	public Participant (Site siteRattachement) {
		this.siteRattachement = siteRattachement;
	}
	
	

	public int getIdparticipant() {
		return idParticipant;
	}

	public void setIdparticipant(int idParticipant) {
		this.idParticipant = idParticipant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public Site getSiteRattachement() {
		return siteRattachement;
	}

	public void setSiteRattachement(Site siteRattachement) {
		this.siteRattachement = siteRattachement;
	}

	
	
	@Override
	public String toString() {
		return "Participant [idParticipant=" + idParticipant + ", nom=" + nom + ", prenom=" + prenom + ", pseudo="
				+ pseudo + ", motDePasse=" + motDePasse + ", telephone=" + telephone + ", email=" + email
				+ ", administrateur=" + administrateur + ", actif=" + actif + ", siteRattachement=" + siteRattachement
				+ "]";
	}

}