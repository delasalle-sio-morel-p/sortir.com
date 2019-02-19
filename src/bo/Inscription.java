package bo;

import java.util.Date;

public class Inscription {
	
	Date dateInscription;
	Sortie idSortie;
	Participant idParticipant;
	
	public Inscription() {
		
	}
	
	public Inscription (Date dateInscription) {
		super();
		this.dateInscription = dateInscription;
	}
	
	public Inscription (Date dateInscription, Sortie idSortie) {
		super();
		this.idSortie = idSortie;
	}
	
	public Inscription (Date dateInscription, Participant idParticipant) {
		super();
		this.idParticipant = idParticipant;
	}
	
	
	
	
	public Date getDateInscription() {
		return dateInscription;
	}
	
	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}
	
	public Sortie getIdSortie() {
		return idSortie;
	}
	
	public void setIdSortie(Sortie idSortie) {
		this.idSortie = idSortie;
	}
	
	public Participant getIdParticipant() {
		return idParticipant;
	}
	
	public void setIdParticipant(Participant idParticipant) {
		this.idParticipant = idParticipant;
	}
	
	

	@Override
	public String toString() {
		return "Inscription [dateInscription=" + dateInscription + ", idSortie=" + idSortie + ", idParticipant="
				+ idParticipant + "]";
	}
}