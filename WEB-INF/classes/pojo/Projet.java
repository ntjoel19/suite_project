package pojo;

public class Projet {
    private String designation;
    private String code_action;
    private String code_programme;
	private String codePersonne;
	private String intitule;
	private String contextMiseEnOeuvre;
	private String codeAction;
	private String maitreDoeuvre;
	private String maitreDouvrage;
	private String partenaires;
	private String serviceResponsable;
	private String objectifsVises;
	private String justificationDesBesoins;
	private String ancrageDuProjet;
	private String coutDuProjet;
	private String natureDuFinancement;
	private String extrantsEscomptes;
	private String effetsAttendus;
	private String impactes;
	private String typologieDuProjet;
	private String path ;
	private String tDRPath ;
	private String cocher ;
	
	public Projet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Projet(String designation, String codePersonne, String intitule,
			String contextMiseEnOeuvre, String codeAction,
			String maitreDoeuvre, String maitreDouvrage, String partenaires,
			String serviceResponsable, String objectifsVises,
			String justificationDesBesoins, String ancrageDuProjet,
			String coutDuProjet, String natureDuFinancement,
			String extrantsEscomptes, String effetsAttendus, String impactes,
			String typologieDuProjet, String path, String tDRPath, String code_action,String code_programme) {
			super();
			this.designation = designation;
			this.codePersonne = codePersonne;
			this.intitule = intitule;
			this.contextMiseEnOeuvre = contextMiseEnOeuvre;
			this.codeAction = codeAction;
			this.maitreDoeuvre = maitreDoeuvre;
			this.maitreDouvrage = maitreDouvrage;
			this.partenaires = partenaires;
			this.serviceResponsable = serviceResponsable;
			this.objectifsVises = objectifsVises;
			this.justificationDesBesoins = justificationDesBesoins;
			this.ancrageDuProjet = ancrageDuProjet;
			this.coutDuProjet = coutDuProjet;
			this.natureDuFinancement = natureDuFinancement;
			this.extrantsEscomptes = extrantsEscomptes;
			this.effetsAttendus = effetsAttendus;
			this.impactes = impactes;
			this.typologieDuProjet = typologieDuProjet;
			this.path = path;
			this.tDRPath = tDRPath;
			this.code_programme = code_programme;
			this.code_action = code_action;
	}


	
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getCodePersonne() {
		return codePersonne;
	}
	public void setCodePersonne(String codePersonne) {
		this.codePersonne = codePersonne;
	}
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	public String getContextMiseEnOeuvre() {
		return contextMiseEnOeuvre;
	}
	public void setContextMiseEnOeuvre(String contextMiseEnOeuvre) {
		this.contextMiseEnOeuvre = contextMiseEnOeuvre;
	}
	public String getCodeAction() {
		return codeAction;
	}
	public void setCodeAction(String codeAction) {
		this.codeAction = codeAction;
	}
	public String getMaitreDoeuvre() {
		return maitreDoeuvre;
	}
	public void setMaitreDoeuvre(String maitreDoeuvre) {
		this.maitreDoeuvre = maitreDoeuvre;
	}
	public String getMaitreDouvrage() {
		return maitreDouvrage;
	}
	public void setMaitreDouvrage(String maitreDouvrage) {
		this.maitreDouvrage = maitreDouvrage;
	}
	public String getPartenaires() {
		return partenaires;
	}
	public void setPartenaires(String partenaires) {
		this.partenaires = partenaires;
	}
	public String getServiceResponsable() {
		return serviceResponsable;
	}
	public void setServiceResponsable(String serviceResponsable) {
		this.serviceResponsable = serviceResponsable;
	}
	public String getObjectifsVises() {
		return objectifsVises;
	}
	public void setObjectifsVises(String objectifsVises) {
		this.objectifsVises = objectifsVises;
	}
	public String getJustificationDesBesoins() {
		return justificationDesBesoins;
	}
	public void setJustificationDesBesoins(String justificationDesBesoins) {
		this.justificationDesBesoins = justificationDesBesoins;
	}
	public String getAncrageDuProjet() {
		return ancrageDuProjet;
	}
	public void setAncrageDuProjet(String ancrageDuProjet) {
		this.ancrageDuProjet = ancrageDuProjet;
	}
	public String getCoutDuProjet() {
		return coutDuProjet;
	}
	public void setCoutDuProjet(String coutDuProjet) {
		this.coutDuProjet = coutDuProjet;
	}
	public String getNatureDuFinancement() {
		return natureDuFinancement;
	}
	public void setNatureDuFinancement(String natureDuFinancement) {
		this.natureDuFinancement = natureDuFinancement;
	}
	public String getExtrantsEscomptes() {
		return extrantsEscomptes;
	}
	public void setExtrantsEscomptes(String extrantsEscomptes) {
		this.extrantsEscomptes = extrantsEscomptes;
	}
	public String getEffetsAttendus() {
		return effetsAttendus;
	}
	public void setEffetsAttendus(String effetsAttendus) {
		this.effetsAttendus = effetsAttendus;
	}
	public String getImpactes() {
		return impactes;
	}
	public void setImpactes(String impactes) {
		this.impactes = impactes;
	}
	public String getTypologieDuProjet() {
		return typologieDuProjet;
	}
	public void setTypologieDuProjet(String typologieDuProjet) {
		this.typologieDuProjet = typologieDuProjet;
	}
	

	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		this.path = path;
	}



	public String getTDRPath() {
		return tDRPath;
	}



	public void setTDRPath(String tDRPath) {
		this.tDRPath = tDRPath;
	}



	@Override
	public String toString() {
		return "Projet [designation=" + designation + ", codePersonne="
				+ codePersonne + ", intitule=" + intitule
				+ ", contextMiseEnOeuvre=" + contextMiseEnOeuvre
				+ ", codeAction=" + codeAction + ", maitreDoeuvre="
				+ maitreDoeuvre + ", maitreDouvrage=" + maitreDouvrage
				+ ", partenaires=" + partenaires + ", serviceResponsable="
				+ serviceResponsable + ", objectifsVises=" + objectifsVises
				+ ", justificationDesBesoins=" + justificationDesBesoins
				+ ", ancrageDuProjet=" + ancrageDuProjet + ", coutDuProjet="
				+ coutDuProjet + ", natureDuFinancement=" + natureDuFinancement
				+ ", extrantsEscomptes=" + extrantsEscomptes
				+ ", effetsAttendus=" + effetsAttendus + ", impactes="
				+ impactes + ", typologieDuProjet=" + typologieDuProjet
				+ ", path=" + path + ", tDRPath=" + tDRPath + "]";
	}



	/**
	 * @return the code_action
	 */
	public String getCode_action() {
		return code_action;
	}



	/**
	 * @param code_action the code_action to set
	 */
	public void setCode_action(String code_action) {
		this.code_action = code_action;
	}



	/**
	 * @return the tDRPath
	 */
	public String gettDRPath() {
		return tDRPath;
	}



	/**
	 * @param tDRPath the tDRPath to set
	 */
	public void settDRPath(String tDRPath) {
		this.tDRPath = tDRPath;
	}



	/**
	 * @return the code_programme
	 */
	public String getCode_programme() {
		return code_programme;
	}



	/**
	 * @param code_programme the code_programme to set
	 */
	public void setCode_programme(String code_programme) {
		this.code_programme = code_programme;
	}



	public String getCocher() {
		return cocher;
	}



	public void setCocher(String cocher) {
		this.cocher = cocher;
	}





}

