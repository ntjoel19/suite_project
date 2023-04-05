package pojo;

public class Action {
	private String designation ;
	private String intitule ;
	private String objectifs ;
	private String indicateurs ;
	private String valeurReference ;
	private String valeurCible ;
	private String code_programme ;
	private String path ;
	

	public Action() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Action(String designation, String intitule, String objectifs,
			String indicateurs, String valeurReference, String valeurCible,
			String code_programme , String path) {
		super();
		this.designation = designation;
		this.intitule = intitule;
		this.objectifs = objectifs;
		this.indicateurs = indicateurs;
		this.valeurReference = valeurReference;
		this.valeurCible = valeurCible;
		this.code_programme = code_programme;
		this.path = path ;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}



	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}

	
	public String getIntitule() {
		return intitule;
	}


	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}


	public String getObjectifs() {
		return objectifs;
	}


	public void setObjectifs(String objectifs) {
		this.objectifs = objectifs;
	}


	public String getIndicateurs() {
		return indicateurs;
	}


	public void setIndicateurs(String indicateurs) {
		this.indicateurs = indicateurs;
	}


	public String getValeurReference() {
		return valeurReference;
	}


	public void setValeurReference(String valeurReference) {
		this.valeurReference = valeurReference;
	}


	public String getValeurCible() {
		return valeurCible;
	}


	public void setValeurCible(String valeurCible) {
		this.valeurCible = valeurCible;
	}


	public String getCode_programme() {
		return code_programme;
	}


	public void setCode_programme(String code_programme) {
		this.code_programme = code_programme;
	}


	@Override
	public String toString() {
		return "Action [designation=" + designation + ", intitule=" + intitule
				+ ", objectifs=" + objectifs + ", indicateurs=" + indicateurs
				+ ", valeurReference=" + valeurReference + ", valeurCible="
				+ valeurCible + ", code_programme=" + code_programme
				+ ", path=" + path + "]";
	}


	
	
	
}