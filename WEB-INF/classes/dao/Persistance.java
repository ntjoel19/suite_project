package dao;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Persistance {
	private final String driver = "org.postgresql.Driver";
	private String url ;
	private String user ;
	private String password ;
	
	public static String agentMozilla = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:35.0) Gecko/20100101 Firefox/35.0";
	public static String agentOpera = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36 OPR/33.0.1990.115 (Edition Campaign 34)";
	public static String agentChrome = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.73 Safari/537.36";
	public static String agentIE = "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.3)";
	HttpSession session;
	
	public Persistance(){
		try {
			Class.forName(driver);
			System.out.println("Pilote charg�");
		} catch (ClassNotFoundException e) {
			System.out.println("Echec de chargement du pilote postgres");
			e.printStackTrace();
		}
	}
	
	public Connection etablirConnexion(String url,String user,String password) throws SQLException{
		Connection connection = DriverManager.getConnection(url,user,password);
		if(connection == null) System.out.println("erreur de connection");
		else System.out.println("connection reussie");
		return connection ;
	}
	
	public void fermerConnexion(Connection c) throws SQLException{
		System.out.println("Fermeture de la connection a la BD");
		c.close();
	}

	public ResultSet execReqLecture(Connection connection ,String query) throws SQLException {
		ResultSet resultSet ;
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		return resultSet ;
	}
	
	public void execReqEcriture(Connection connection, String query) throws SQLException{
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.executeUpdate();
	}
	
	/**
	 * FONCTION rechargerTab
	 * cette fonctions permet de charger les colonnes d'une table en BD dans le but de 
	 * la renvoyer à la vue en charge de l'affichage des informations relatives à cette table
	 * @param table : c'est le nom d'une table en base de données
	 * @param colonnes: la liste des colonnes à selectionner dans la BD de la forme "col1 col2 ... colN"
	 * @return trow : c'est la partie du tableau à mettre à jour dans la vue (<tr><td>****</td></tr>)
	 */
	public String[] rechargerTab(java.sql.Connection c,String table, String colonnes,String pk, String[] critere){
		//colonnes est une liste de colonnes separee par des espaces
		//de la forme "col1 col2 ... colsN"
		String[] cols=colonnes.split(" ");
		int taille = cols.length;
		String colonnesTraitee="";
		//ici j'effectue un traitement sur la liste en y rajoutant les virgule de mani�re � 
		//rendre la liste de colonnes exploitable par mon SELECT
		for(int i=0;i<taille-1;i++)
			colonnesTraitee+=cols[i]+", ";
		colonnesTraitee+=cols[taille-1];
		ResultSet result=null;
		String requete = "";
		String where=where(critere);
		if(where != null){
			requete = "SELECT "+colonnesTraitee+" FROM "+table+" "+where+" ORDER BY "+pk+" ASC";
		}else{
			where="";
			requete = "SELECT "+colonnesTraitee+" FROM "+table+" "+where+" ORDER BY "+pk+" ASC";
		}
		//String requete = "SELECT code_personne FROM projet";
		String[] trow = {"","","",""};
		String tabGroup[];
		Integer nbRecord=0;
		try {
			int nb=0;
			result= this.execReqLecture(c, requete);
			trow[0]="";
			trow[1]="";
			Integer num=0;
				while(result.next()){
					  if(table.equals("action")){
						  trow[0] += "<tr class='ligne' style='background : #e5e5e5;' align='center'><td colspan="+taille+1+">Programme: " +result.getString("code_programme")+
								   "</td></tr>";
					  }
					  trow[0] += "<tr class='ligne' id='"+num+"'><td class='corpsprogrcheckBox'>" +
				  			"<input type='checkbox' id='check"+num+"' name='"+num+"' /><label for='check"+num+"'></label></td>";
					  trow[2]=num.toString();
					  String q="UPDATE "+table+" SET cocher = "+num+" WHERE "+pk+" = '"+result.getString(pk)+"';";
					  execReqEcriture(c , q);
					  for(int i=0;i<taille;i++){
						  if(cols[i].equals("progression")){
							  int percent=Integer.parseInt(result.getString(cols[i]));
							  trow[0]+="<td><progress id='progressMiniature' max='100' value='"+percent+"'></progress></td>";
						  }else if(cols[i].equals("intitule")){
							  trow[0]+="<td id='projet"+num+"'>"+result.getString(cols[i])+"</td>";
							  //System.out.println(result.getString(cols[i])+" et le code projet"+num);
						  }else if(cols[i].equals("code_personne")){
							  trow[0]+="<td id='personne"+num+"'>"+result.getString(cols[i])+"</td>";
							  //System.out.println(result.getString(cols[i])+" et le code projet"+num);
						  }else if(cols[i].equals("bloque")){
							  if(Integer.parseInt(result.getString(cols[i]))==0)
								  trow[0]+="<td id='bloque"+num+"' align='center'><img title='bloquer un utilisateur' width='20px' height='20px' src='././ressources/bloquer.png'/></td>";
							  else
								  trow[0]+="<td id='bloque"+num+"' align='center'><img title='debloquer un utilisateur' width='20px' height='20px' src='././ressources/debloquer1.png'/></td>";
						  
						  }else{
							  if(i==1){
								  //System.out.println(cols[i]);
								  trow[0]+="<td id='intitule'>"+result.getString(cols[i])+"</td>";
							  }else{
								  trow[0]+="<td>"+result.getString(cols[i])+"</td>";
							  }
						  }
					  }
					  trow[0]+="</tr>";
					  num++;
					  nbRecord++;
				 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		trow[1]=nbRecord.toString();
		return trow;
	}
	
	/**
	 * FONCTION rechargerTabMore
	 * cette fonctions permet de charger les colonnes d'une table en BD dans le but de 
	 * la renvoyer à la vue en charge de l'affichage des informations relatives à cette table
	 * @param table : c'est le nom d'une table en base de données
	 * @param colonnes: la liste des colonnes à selectionner dans la BD de la forme "col1 col2 ... colN"
	 * @return trow : c'est la partie du tableau à mettre à jour dans la vue (<tr><td>****</td></tr>)
	 */
	public String[] rechargerTabMore(java.sql.Connection c,String table, String colonnes,String pk,String[] tab2_col,String comonColSource,String comonColTarget,String[] critere){
		//colonnes est une liste de colonnes separee par des espaces
		//de la forme "col1 col2 ... colsN"
		String[] cols=colonnes.split(" ");
		int taille = cols.length;
		String colonnesTraitee="";
		//ici j'effectue un traitement sur la liste en y rajoutant les virgule de mani�re � 
		//rendre la liste de colonnes exploitable par mon SELECT
		for(int i=0;i<taille-1;i++)
			colonnesTraitee+=cols[i]+", ";
		colonnesTraitee+=cols[taille-1];
		ResultSet result=null;
		ResultSet result2=null;
		
		String requete="";
		String requete2 = "SELECT "+tab2_col[1]+" FROM "+tab2_col[0];
		//String requete = "SELECT code_personne FROM projet";
		String[] trow={"","",""};
		Integer nbRecord=0;
		try {
			result2= this.execReqLecture(c, requete2);
			trow[0]="";
			trow[1]="";
			Integer num=0;
				while(result2.next()){
					  //System.out.println(result2.getString(tab2_col[1]));
					  trow[0] += "<tr class='ligne' style='background : #e5e5e5;'><td colspan="+taille+1+"><b>"+tab2_col[0]+": " +result2.getString(tab2_col[1])+
					             "</b></td></tr>";
					  String where=where(critere);
					  //System.out.println("where = "+where);
					  if(where==null){
						  requete = "SELECT "+colonnesTraitee+" FROM "+table+" WHERE "+comonColSource+" = '"+result2.getString(comonColTarget)+"' ORDER BY "+pk+" ASC";
						  //System.out.println("requet = "+requete);
					  }else{
						  requete = "SELECT "+colonnesTraitee+" FROM "+table+" "+where+" AND ( "+comonColSource+" = '"+result2.getString(comonColTarget)+"' )";
					  }
					  result= this.execReqLecture(c, requete);
					  while(result.next()){
						  //System.out.println("code action = "+result.getString("designation"));
						  trow[0] += "<tr class='ligne' id='"+num+"'><td class='corpsprogrcheckBox'>" +
				  			"<input type='checkbox' id='check"+num+"' name='"+num+"' /><label for='check"+num+"'></label></td>";
						  trow[2]=num.toString();
						  
						  String q="UPDATE "+table+" SET cocher = "+num+" WHERE "+pk+" = '"+result.getString(pk)+"' AND code_programme='"+result2.getString(comonColTarget)+"';";
						  execReqEcriture(c , q);
						  num++;
						  for(int i=0;i<taille;i++){
							  if(cols[i].equals("progression")){
								  int percent=Integer.parseInt(result.getString(cols[i]));
								  trow[0]+="<td><progress id='progressMiniature' max='100' value='"+percent+"'></progress></td>";
							  }else{
								  trow[0]+="<td>"+result.getString(cols[i])+"</td>";
							  }
						  }
						  trow[0]+="</tr>";
						  nbRecord++;
					  }
				 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		trow[1]=nbRecord.toString();
		return trow;
	}
	
	/**
	 * La fontion suprimerLigneTable supprime les lignes d'un tableau HTML
	 * dont les champs sont remplis via la BD
	 * @param c la viriable de connection à la base de donnée
	 * @param table le nom de la table source en BD
	 * @param colonnes la liste des colonnes de la BD qui sont affichees dans la tableau HTML
	 * @param pk La clé primaire de la table en BD
	 * @param request la requette qui a été envoyee demandant la suppression de ligne et dans
	 * laquelle se trouve les numero des lignes cochees
	 * @return 0 si aucune ligne à supprimer, 1 si une ligne à supprimer et 2 si plusieurs ligne à supprimer
	 */
	public int suprimerLigneTable(java.sql.Connection c,String table, String colonnes,String pk, HttpServletRequest request,String[] tab2_col,String comonColSource,String comonColTarget){
		int resultat=0;
		//L'appel à cette fonction a juste pour but ici de recueillir le nombre de ligne du tableau
		String[] trow={"",""};
		if(tab2_col==null && comonColSource==null && comonColTarget==null){
			trow=this.rechargerTab(c,table,colonnes,pk,null);
		}else{
			trow=this.rechargerTabMore(c,table,colonnes,pk,tab2_col,comonColSource,comonColTarget,null);
		}
		int nb=Integer.parseInt(trow[1]);//nombre de lignes du tableau
		int[] n=new int[nb+2];//tableau qui conservera les numéros des lignes cochées
		Integer I;//pas indispensable mais utile de façon temporelle
		String coch;//"on" ou "null" pour dire que la ligne est cochée ou non
		/*
		 * On compte le nombre de ligne selectionnées
		 */
		n=nombreLignesCoche(request,nb);
		//System.out.println("result = "+n[nb]+"; ligne cochees = "+n[nb+1]);
		for(int i=0;i<n[nb+1];i++){//n[nb]=result et n[nb+1]=nombre de lignes cochees
			String requete = "DELETE FROM "+table+" WHERE cocher = "+n[i]+";";
			try {
				//c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_bd","postgres","infoday");
				this.execReqEcriture(c, requete);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n[nb];
	}
	
	public int[] nombreLignesCoche(HttpServletRequest request,int nb){
		int resultat=0;
		//L'appel à cette fonction a juste pour but ici de recueillir le nombre de ligne du tableau
		int nbCoch=0;//variable qui contiendra le nombre de cases cochées
		int[] n=new int[nb+2];//tableau qui conservera les numéros des lignes cochées
		Integer I;//pas indispensable mais utile de façon temporelle
		String coch;//"on" ou "null" pour dire que la ligne est cochée ou non
		/*
		 * On compte le nombre de ligne selectionnées
		 */
		for(int i=0;i<nb;i++){
			I=i;
			coch=request.getParameter(I.toString());
			//System.out.println(coch);
			if(coch != null){
				if(coch.equals("on")){
					n[nbCoch]=i;
					nbCoch++;
					//System.out.println("case cochee : "+n[nbCoch-1]);
				}
			}
		}
		n[nb+1]=nbCoch;
		if(nbCoch==0) {//Aucune ligne n'a ete sélectionnée n'a été selectionné
			resultat = 0; 
			n[nb]=0; 
		}
		if(nbCoch == 1){
			n[nb]=1;
			resultat = 1;
		}
		if(nbCoch > 1){
			n[nb]=2;
			resultat = 2;
		}
		return n;
	}
	
	// Gestion de tous les types de filtres et de recherches en BD
	public ResultSet filtre(Connection connection , String query ,List<Object> lCritere){
		PreparedStatement preparedstatement = null;
		ResultSet result = null;
		try {
			preparedstatement = connection.prepareStatement(query);
			for(int i=1;i<=lCritere.size();i++){
				preparedstatement.setString(i, (String)lCritere.get(i-1));
			}
			result = preparedstatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//deuxieme methode de filtre
	public ResultSet filtre(Connection connection , String table ,String[] lCritere) throws SQLException{
		PreparedStatement preparedstatement = null;
		ResultSet result = null;
		String where=where(lCritere);
		String query = "SELECT * FROM "+table+" "+where;
		//System.out.println("requete de filtre = "+ query);
		
		result = execReqLecture(connection, query);
		
		return result;
	}
	
	public String where(String[] critere){
		String where="";
		int i=0;
		if(critere==null){
			where = null;
		}else{
			where="WHERE (";
			for(i=0;i<critere.length-1;i++){
				if(!critere[i].equals("")){
					where+=critere[i];
					if(i+1<=critere.length-1 && !critere[i+1].equals("")){ 
						where+=" AND ";
					}
				}
			}
			where+=critere[i]+" )";
		}
		//System.out.println("WHERE = "+where);
		return where;
	}
	
	public String bdCoteClient(java.sql.Connection c,String table,int nbColonnes) throws SQLException{
		String bd="";
		
		ResultSet result = this.execReqLecture(c, "SELECT * FROM "+table);
		if(result.next()) ;
		while(result.next()){
			bd+=result.getString(1);
			for(int i=2;i<nbColonnes;i++){
				bd+="-||-"+result.getString(i);
			}
			bd+="-endLn-</br>";
		}
		bd="<div id='bd' style='display:none;'>" +
				""+bd+"" +
			"</div>";
		return bd;
	}
	
	public String listeProgramme(){
		String option="";
		Connection c=null;
		try {
			c = etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//Connection c=  etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
			ResultSet result = execReqLecture(c, "SELECT * FROM programme");
			
			while(result.next()){
				option+="<option value='"+result.getString("designation")+"'>"+result.getString("designation")+"</option>";
				//System.out.println("option = "+option);
			}
			fermerConnexion(c);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return option;
	}
	
	public String listeProgramme(String defaultSelected){
		String option="";
		Connection c=null;
		try {
			c = etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ResultSet result = execReqLecture(c, "SELECT * FROM programme");
			
			while(result.next()){
				if(result.getString("designation").equals(defaultSelected))
					option+="<option value='"+result.getString("designation")+"' selected>"+result.getString("designation")+"</option>";
				else
					option+="<option value='"+result.getString("designation")+"'>"+result.getString("designation")+"</option>";
				//System.out.println("option = "+option);
			}
			fermerConnexion(c);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return option;
	}
	
	public String listeAction(String programme){
		String requeteAction="SELECT designation FROM action where code_programme='"+programme+"'";
		//String query2="SELECT * FROM projet where code_programme='"+request.getParameter("programme")+"'";
		//System.out.println(";lsalkasd");
		String list="";
		String[] act=new String[100];
		int i=0;
		Connection c=null;
		try {
			c = etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//Connection c = etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
			ResultSet result = execReqLecture(c, requeteAction);
			
			while(result.next()){
				list+="<option value='"+result.getString("designation")+"'>"+result.getString("designation")+"</option>";
				act[i]=result.getString("designation");
				i++;
			}
			fermerConnexion(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public String listeProjet(String nomColonne,String valeur){
		String requeteProjet="SELECT "+nomColonne+" FROM projet where "+nomColonne+"='"+valeur+"'";
		
		String option="";
		Connection c=null;
		try {
			c = etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ResultSet result = execReqLecture(c, requeteProjet);
			
			while(result.next()){
				option+="<option value='"+result.getString("designation")+"'>"+result.getString("designation")+"</option>";
				//System.out.println("option = "+option);
			}
			fermerConnexion(c);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return option;
	}
	
	public String listeAction(String programme,String selectedAction){
		String requeteAction="SELECT designation FROM action where code_programme='"+programme+"'";
		//String query2="SELECT * FROM projet where code_programme='"+request.getParameter("programme")+"'";
		//System.out.println(";lsalkasd");
		String list="";
		String[] act=new String[100];
		int i=0;
		Connection c=null;
		try {
			c = etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			ResultSet result = execReqLecture(c, requeteAction);
			
			while(result.next()){
				if(result.getString("designation").equals(selectedAction))
					list+="<option value='"+result.getString("designation")+"' selected>"+result.getString("designation")+"</option>";
				else
					list+="<option value='"+result.getString("designation")+"'>"+result.getString("designation")+"</option>";
				
				act[i]=result.getString("designation");
				i++;
			}
			fermerConnexion(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public String getStringCode(String chaine,HttpServletRequest request){
		// si le navigateur c'est op�ra navigateur = 0 sinon nav egal 1
		session=request.getSession(true);
		String user_agent=(String)session.getAttribute("User-Agent");
		int navigateur = user_agent.contains("OPR")?0:1; 
		String Reschaine="";
		
		String encoding = request.getCharacterEncoding();
		if (encoding == null || !encoding.equals("UTF-8"))
		{
			try
			{
				request.setCharacterEncoding("UTF-8");
			}
			catch (UnsupportedEncodingException e)
			{
				System.err.println("HttpMultipartRequest - : " + e);
			}
		}
		Reschaine=new String((chaine.replaceAll("'", "`")).getBytes());
		return Reschaine;
	}
	
	public void encodage(HttpServletRequest request){
		String encoding = request.getCharacterEncoding();
		if (encoding == null || !encoding.equals("UTF-8"))
		{
			try
			{
				request.setCharacterEncoding("UTF-8");
			}
			catch (UnsupportedEncodingException e)
			{
				System.err.println("HttpMultipartRequest - : " + e);
			}
		}
	}
	
	public String listeProjet(String nomColonne){
		String dataList1="";
		Connection c=null;
		try {
			c = etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			//Connection c = etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
			String query1="SELECT DISTINCT "+nomColonne+" FROM projet";
			
			ResultSet result=execReqLecture(c, query1);
			while(result.next()){
					dataList1+=result.getString(nomColonne)+"??";
			}
			fermerConnexion(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataList1;
	}
	
	public String listePublications(String nomColonne){
		String dataList1="";
		Connection c=null;
		try {
			c = etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			String query1="SELECT DISTINCT "+nomColonne+" FROM information";
			
			ResultSet result=execReqLecture(c, query1);
			while(result.next()){
					dataList1+=result.getString(nomColonne)+"??";
			}
			fermerConnexion(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataList1;
	}
	
	public String notificationTache(String user) throws SQLException{
		Calendar dateActuelle=Calendar.getInstance();
		String dateToday = dateActuelle.get(Calendar.YEAR)+"-"+(dateActuelle.get(Calendar.MONTH)+1)+"-"+dateActuelle.get(Calendar.DAY_OF_MONTH);
		String query1="SELECT * FROM tache WHERE (date_de_debut < '"+dateToday+"') AND (statut != 0 AND statut != 1 AND statut != 2)";//taches non-encore demarrees
		String query2="SELECT * FROM tache WHERE (date_de_fin < '"+dateToday+"') AND (statut != 2)";//taches excedant les delais
		Connection c = etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
		String notification="<table id='listeTaches'><tr><th>Projet</th><th>Tache</th><th>Date</th></tr>";
		
		ResultSet requete1=execReqLecture(c, query1);
		ResultSet requete2=execReqLecture(c, query2);
		int i=0;
		//notification="";
		while(requete1.next()){
			notification+="<tr><td>"+requete1.getString("code_projet")+"</td><td>"+requete1.getString("nom_etape")+"</td><td>"+requete1.getString("date_de_debut")+"</td></tr>";
			i++;
		}
		while(requete2.next()){
			notification+="<tr><td>"+requete2.getString("code_projet")+"</td><td>"+requete2.getString("nom_etape")+"</td><td>"+requete2.getString("date_de_debut")+"</td></tr>";
			i++;
		}
		notification+="</table>";
		notification+="??"+i;
		if(i==0) notification="";
		fermerConnexion(c);
		return notification;
	}
	
	public String notificationUtilisateur(String user) throws SQLException{
		String query="Select * from personne where bloque="+0+"";
		Connection c = etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
		String notification="<table id='listeUser'><tr><th colspan='3'>En attente d'activation de compte</th></tr>";
		int i=0;
		ResultSet requete=execReqLecture(c, query);
		while(requete.next()){
			notification+="<tr><td>"+requete.getString("noms")+"</td><td>"+requete.getString("prenoms")+"</td><td>("+requete.getString("pseudo")+")</td></tr>";
			i++;
		}
		notification+="</table>";
		notification+="??"+i;
		if(i==0) notification="";
		fermerConnexion(c);
		return notification;
	}
	
	
	public String dateActuelle(){
		Calendar date=Calendar.getInstance();
		String dateToday = date.get(Calendar.HOUR)+"H"+date.get(Calendar.MINUTE)+" "+date.get(Calendar.YEAR)+"-"+(date.get(Calendar.MONTH)+1)+"-"+date.get(Calendar.DAY_OF_MONTH);
		return dateToday;
	}
	
	public boolean dateAfter(String dateD,String dateF){
		
		Calendar dateDeb=Calendar.getInstance();
		Calendar dateEnd=Calendar.getInstance();
		String date1[]=new String[3];
		String date2[]=new String[3];
		
		String[] tmp1=dateD.split("/");
		//System.out.println("la date est : "+dateD);
		if(tmp1[0].equals(dateD)){
			dateD=tmp1[0];
			String[] date = dateD.split("-");
			date1[0] = date[2];
			date1[1] = date[1];
			date1[2] = date[0];
			String datetemp[] = dateF.split("-");
			date2[0] = datetemp[2];
			date2[1] = datetemp[1];
			date2[2] = datetemp[0];
			
		}else{
			dateD=tmp1[0];
			String[] date = dateD.split("/");
			date1[0] = date[0];
			date1[1] = date[1];
			date1[2] = date[2];
			String datetemp[] = dateF.split("/");
			date2[0] = datetemp[0];
			date2[1] = datetemp[1];
			date2[2] = datetemp[2];
			
		}
		
		dateDeb.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date1[0]));
		dateDeb.set(Calendar.MONTH, Integer.parseInt(date1[1]));
		dateDeb.set(Calendar.YEAR, Integer.parseInt(date1[2]));
		
		dateEnd.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date2[0]));
		dateEnd.set(Calendar.MONTH, Integer.parseInt(date2[1]));
		dateEnd.set(Calendar.YEAR, Integer.parseInt(date2[2]));
		
		
		
		return dateDeb.after(dateEnd);
	}
	
	public boolean alertRetartProjet(String designation) throws SQLException{
		String query="SELECT * FROM tache WHERE code_projet='"+designation+"'";
		Connection c = etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
		
		ResultSet r=execReqLecture(c, query);
		int statut=0;
		int nb=0;
		boolean retour=false;
		while(r.next()){
			nb++;
			statut=Integer.parseInt((String)r.getString("statut"));
			Calendar dateActuelle=Calendar.getInstance();
			
			String dateToday = dateActuelle.get(Calendar.YEAR)+"-"+(dateActuelle.get(Calendar.MONTH)+1)+"-"+dateActuelle.get(Calendar.DAY_OF_MONTH);
			String dateDbut=(String)r.getString("date_de_debut");
			String dateFin=(String)r.getString("date_de_fin");
			
			
			String styleCSS1="";
			String styleCSS2="";
			//styleCSS2="style='background:green;'";
			if(statut == 0 || statut == 1){
				if(dateAfter(dateToday, dateFin)){
					retour = true;
					break;
				}
			}else if(statut == 3){
				if(dateAfter(dateToday, dateDbut)){
					retour = true;
					break;
				}
			}
			//System.out.println(""+nb+" = "+retour+"// fin = "+dateFin+" today = "+dateToday);
		}
		fermerConnexion(c);
		return retour;
	}
	
	public void recursifDelete(File path) throws IOException { 
		if (!path.exists()) throw new IOException( 
		"File not found '" + path.getAbsolutePath() + "'"); 
		if (path.isDirectory()) { 
		File[] children = path.listFiles(); 
		for (int i=0; children != null && i<children.length; i++) 
			recursifDelete(children[i]); 
		if (!path.delete()) throw new IOException( 
				"No delete path '" + path.getAbsolutePath() + "'"); 
		} 
		else if (!path.delete()) throw new IOException("No delete file '" + path.getAbsolutePath() + "'"); 
	} 
}
