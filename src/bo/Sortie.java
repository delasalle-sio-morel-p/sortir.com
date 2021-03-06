package bo;

import java.util.Date;

public class Sortie {

	int idSortie;
	String nom;
	Date dateHeureDebut;
	int duree;
	Date dateHeureFin;
	int nbParticipantMax;
	String description;
	String urlPhoto;
	Participant organisateur;
	Lieu lieu;
	Etat etat;

	public Sortie() {

	}

	public Sortie(int idSortie) {
		super();
		this.idSortie = idSortie;
	}

	public Sortie(int idSortie, String nom) {
		super();
		this.nom = nom;
	}

	public Sortie(int idSortie, String nom, Date dateHeureDebut) {
		super();
		this.dateHeureDebut = dateHeureDebut;
	}

	public Sortie(int idSortie, String nom, Date dateHeureDebut, int duree) {
		super();
		this.duree = duree;
	}

	public Sortie(int idSortie, String nom, Date dateHeureDebut, int duree, Date dateHeureFin) {
		super();
		this.dateHeureFin = dateHeureFin;
	}

	public Sortie(int idSortie, String nom, Date dateHeureDebut, int duree, Date dateHeureFin, int nbParticipantMax) {
		super();
		this.nbParticipantMax = nbParticipantMax;
	}

	public Sortie(int idSortie, String nom, Date dateHeureDebut, int duree, Date dateHeureFin, int nbParticipantMax,
			String description) {
		super();
		this.description = description;
	}

	public Sortie(int idSortie, String nom, Date dateHeureDebut, int duree, Date dateHeureFin, int nbParticipantMax,
			String description, String urlPhoto) {
		super();
		this.urlPhoto = urlPhoto;
	}

	public Sortie(int idSortie, String nom, Date dateHeureDebut, int duree, Date dateHeureFin, int nbParticipantMax,
			String description, String urlPhoto, Participant organisateur) {
		super();
		this.organisateur = organisateur;
	}

	public Sortie(int idSortie, String nom, Date dateHeureDebut, int duree, Date dateHeureFin, int nbParticipantMax,
			String description, String urlPhoto, Participant organisateur, Lieu lieu) {
		super();
		this.lieu = lieu;
	}

	public Sortie(int idSortie, String nom, Date dateHeureDebut, int duree, Date dateHeureFin, int nbParticipantMax,
			String description, String urlPhoto, Participant organisateur, Lieu lieu, Etat etat) {
		super();
		this.etat = etat;
	}

	public int getIdSortie() {
		return idSortie;
	}

	public void setIdSortie(int idSortie) {
		this.idSortie = idSortie;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateHeureDebut() {
		return dateHeureDebut;
	}

	public void setDateHeureDebut(Date dateHeureDebut) {
		this.dateHeureDebut = dateHeureDebut;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public Date getDateHeureFin() {
		return dateHeureFin;
	}

	public void setDateHeureFin(Date dateHeureFin) {
		this.dateHeureFin = dateHeureFin;
	}

	public int getNbParticipantMax() {
		return nbParticipantMax;
	}

	public void setNbParticipantMax(int nbParticipantMax) {
		this.nbParticipantMax = nbParticipantMax;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrlPhoto() {
		return urlPhoto;
	}

	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}

	public Participant getOrganisateur() {
		return organisateur;
	}

	public void setOrganisateur(Participant organisateur) {
		this.organisateur = organisateur;
	}

	public Lieu getLieu() {
		return lieu;
	}

	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	@Override
	public String toString() {
		return "Sortie [idSortie=" + idSortie + ", nom=" + nom + ", dateHeureDebut=" + dateHeureDebut + ", duree="
				+ duree + ", dateHeureFin=" + dateHeureFin + ", nbParticipantMax=" + nbParticipantMax + ", description="
				+ description + ", urlPhoto=" + urlPhoto + ", organisateur=" + organisateur + ", lieu=" + lieu
				+ ", etat=" + etat + "]";
	}
}