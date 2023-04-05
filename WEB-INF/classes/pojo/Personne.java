package pojo;

public class Personne {
	private String codePersonne ;
	private String noms ;
	private String emails ;
	private String contacts ;
	private String codeRole ;

	public Personne() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Personne(String codePersonne, String noms, String emails,
			String contacts, String codeRole) {
		super();
		this.codePersonne = codePersonne;
		this.noms = noms;
		this.emails = emails;
		this.contacts = contacts;
		this.codeRole = codeRole;
	}
	public String getCodePersonne() {
		return codePersonne;
	}
	public void setCodePersonne(String codePersonne) {
		this.codePersonne = codePersonne;
	}
	public String getNoms() {
		return noms;
	}
	public void setNoms(String noms) {
		this.noms = noms;
	}
	public String getEmails() {
		return emails;
	}
	public void setEmails(String emails) {
		this.emails = emails;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getCodeRole() {
		return codeRole;
	}
	public void setCodeRole(String codeRole) {
		this.codeRole = codeRole;
	}
	@Override
	public String toString() {
		return "Personne [codePersonne=" + codePersonne + ", noms=" + noms
				+ ", emails=" + emails + ", contacts=" + contacts
				+ ", codeRole=" + codeRole + "]";
	}


}
