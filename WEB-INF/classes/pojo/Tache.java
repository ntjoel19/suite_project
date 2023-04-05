package pojo;

import java.util.Date;

public class Tache {
	private String designation ;
	private String description ;
	private long cout ;
	private int quantite ;
	private Date dateDebut ;
	private Date dateFin ;
	private String indicateurSuivi ;
	private int statut ;
	private String codeProjet ;
	
	public Tache() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tache(String designation, String description, long cout,
			int quantite, Date dateDebut, Date dateFin, String indicateurSuivi,
			int statut, String codeProjet) {
		super();
		this.designation = designation;
		this.description = description;
		this.cout = cout;
		this.quantite = quantite;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.indicateurSuivi = indicateurSuivi;
		this.statut = statut;
		this.codeProjet = codeProjet;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getCout() {
		return cout;
	}

	public void setCout(long cout) {
		this.cout = cout;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getIndicateurSuivi() {
		return indicateurSuivi;
	}

	public void setIndicateurSuivi(String indicateurSuivi) {
		this.indicateurSuivi = indicateurSuivi;
	}

	public int getStatut() {
		return statut;
	}

	public void setStatut(int statut) {
		this.statut = statut;
	}

	public String getCodeProjet() {
		return codeProjet;
	}

	public void setCodeProjet(String codeProjet) {
		this.codeProjet = codeProjet;
	}

	@Override
	public String toString() {
		return "Tache [designation=" + designation + ", description="
				+ description + ", cout=" + cout + ", quantite=" + quantite
				+ ", dateDebut=" + dateDebut + ", dateFin=" + dateFin
				+ ", indicateurSuivi=" + indicateurSuivi + ", statut=" + statut
				+ ", codeProjet=" + codeProjet + "]";
	}

	


}
