package pojo;

public class Programme {
	private String designation ;
	private String intitule ;
	private String objectifs ;
	private String indicateurs ;
	private String valeurReference ;
	private String valeurCible ;
	private String path ;
	
	public Programme() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Programme(String designation, String intitule, String objectifs,
			String indicateurs, String valeurReference, String valeurCible , String path) {
		super();
		this.designation = designation;
		this.intitule = intitule;
		this.objectifs = objectifs;
		this.indicateurs = indicateurs;
		this.valeurReference = valeurReference;
		this.valeurCible = valeurCible;
		this.path = path ;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
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

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "Programme [designation=" + designation + ", intitule="
				+ intitule + ", objectifs=" + objectifs + ", indicateurs="
				+ indicateurs + ", valeurReference=" + valeurReference + "path=" + path 
				+ ", valeurCible=" + valeurCible + "]";
	}
	

}
