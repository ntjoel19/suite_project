package pojo;

public class Role {
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String codeRole ;
	private String description ;
	
	public Role(String codeRole, String description) {
		super();
		this.codeRole = codeRole;
		this.description = description;
	}
	public String getCodeRole() {
		return codeRole;
	}
	public void setCodeRole(String codeRole) {
		this.codeRole = codeRole;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Role [codeRole=" + codeRole + ", description=" + description
				+ "]";
	}
	
}
