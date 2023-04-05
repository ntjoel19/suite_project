package pojo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import dao.Persistance;

public class Log {
	private String codePersonne; //fk: liste deroulante
	private String infoPersonne;
	private String codeLog;
	private String dateEcriture;
	private String operationEffectuee;
	public Log() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Log(String codePersonne, String infoPersonne, String dateEcriture,
			String operationEffectuee) {
		super();
		this.codePersonne = codePersonne;
		this.infoPersonne = infoPersonne;
		this.dateEcriture = dateEcriture;
		this.operationEffectuee = operationEffectuee;
	}
	
	public void write(){
		Persistance p= new Persistance();
		Connection c= null;
		try {
			c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			p.execReqEcriture(c, "insert into log (code_personne, info_personne, date_ecriture, operation) values ('"+this.codePersonne+"', '"+infoPersonne+"','"+this.dateEcriture+"','"+this.operationEffectuee+"')");
			p.fermerConnexion(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getCodePersonne() {
		return codePersonne;
	}
	
	public void setCodePersonne(String codePersonne) {
		this.codePersonne = codePersonne;
	}
	public String getCodeLog() {
		return codeLog;
	}
	public void setCodeLog(String codeLog) {
		this.codeLog = codeLog;
	}
	public String getDateEcriture() {
		return dateEcriture;
	}
	public void setDateEcriture(String  dateEcriture) {
		this.dateEcriture = dateEcriture;
	}
	public String getOperationEffectuee() {
		return operationEffectuee;
	}
	public void setOperationEffectuee(String operationEffectuee) {
		this.operationEffectuee = operationEffectuee;
	}
	@Override
	public String toString() {
		return "Log [codePersonne=" + codePersonne + ", codeLog=" + codeLog
				+ ", dateEcriture=" + dateEcriture + ", operationEffectuee="
				+ operationEffectuee + ", infoPersonne =" + infoPersonne + "]";
	}
	public String getInfoPersonne() {
		return infoPersonne;
	}
	public void setInfoPersonne(String infoPersonne) {
		this.infoPersonne = infoPersonne;
	}
	
	
}
