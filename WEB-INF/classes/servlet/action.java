package servlet;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Persistance;

/**
 * Servlet implementation class programme
 * @author Ntepp jean joel FOR INFODAY ENTREPRISE
 * @version 1.0
 */
@WebServlet("/action")
public class action extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Persistance p=new Persistance();
	java.sql.Connection c=null;
	ResultSet result=null;
	String[] trow=null;//la partie dynamique du tableau 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public action() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		p.encodage(request);
		HttpSession session = request.getSession();
		InetAddress IP = InetAddress.getLocalHost();
		String MonIP = IP.getHostAddress();
		if(session.getAttribute("code_pers")==null){
			String erreur="<label align='center' id='welcomeMsg'>Bienvenu dans Suite Project</label>";
			request.setAttribute("erreurPassw", erreur);
			
			System.out.println("L'utilisateur d'adresse IP "+MonIP+" a raté son mot de passe");
			this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
		}else{
			
			java.sql.Connection c=null;
			try {
				c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String[] tab={"programme","designation"};
			trow=p.rechargerTabMore(c,"action","designation intitule objectif code_programme","designation",tab,"code_programme","designation",null);
			
			int[] nb=p.nombreLignesCoche(request, Integer.parseInt(trow[1]));
			String progrEdit=""+nb[0];
			
			String btn=request.getParameter("btn");
			
			//System.out.println(btn+" "+progrEdit);
			//System.out.println("User IP = "+request.getRemoteHost()+"\n User header = "+request.getHeader("User-Agent"));
			request.setAttribute("display1", "");
			request.setAttribute("display2", "");
			request.setAttribute("display3", "");
			request.setAttribute("display4", "");
			request.setAttribute("designation", "");
			request.setAttribute("intitule", "");
			request.setAttribute("objectifs", "");
			request.setAttribute("indicateur", "");
			request.setAttribute("valRef", "");
			request.setAttribute("valCible", "");
			request.setAttribute("codeProgr", "");
			request.setAttribute("respoProgramme", "");//stop ici
			
			String profil=(String)session.getAttribute("profil");
			//System.out.println(profil);
			String[] acces=profil.split("__");
			request.setAttribute("edit", acces[5]);
			request.setAttribute("add", acces[6]);
			request.setAttribute("del", acces[7]);
			
			request.setAttribute("top1", acces[35]);
			request.setAttribute("top2", acces[36]);
			request.setAttribute("top3", acces[37]);
			request.setAttribute("top4", acces[38]);
			request.setAttribute("top5", acces[39]);
			request.setAttribute("top6", acces[40]);
			
			request.setAttribute("display1", "<!--");
			request.setAttribute("display2", "-->");
			request.setAttribute("display3", "");
			request.setAttribute("display4", "");
			if(!acces[4].equals("<img title='Acces interdit' class='img' src='./ressources/accesBloq.png' />Actions")){
				if("creeraction".equals(btn) && !acces[5].equals("<td></td>")){
					System.out.println("L'utilisateur"+session.getAttribute("nom")+" d'adresse IP "+request.getRemoteHost()+" essaie de creer une action");
					
					//System.out.println(btn+" voyons");
					request.setAttribute("value", "save");
					request.setAttribute("display1", "");
					request.setAttribute("display2", "");
					request.setAttribute("display3", "<!--");
					request.setAttribute("display4", "-->");
					request.setAttribute("data", " ");
					request.setAttribute("display", "input");
					request.setAttribute("ajout", "1");
				}else if("action".equals(btn)){
					System.out.println("L'utilisateur"+session.getAttribute("nom")+" d'adresse IP "+request.getRemoteHost()+" a accedé à l'interface de gestion des action");
					try {
						result = p.execReqLecture(c, "SELECT * FROM programme");
						String option="";
						while(result.next()){
							option+="<option value="+result.getString("designation")+">"+result.getString("designation")+"</option>";
							//System.out.println("option = "+option);
						}
						request.setAttribute("option", option);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
					request.setAttribute("display1", "<!--");
					request.setAttribute("display2", "-->");
					request.setAttribute("display3", "");
					request.setAttribute("display4", "");
					request.setAttribute("value", "save");
					request.setAttribute("data", " ");
					request.setAttribute("display", "input");
				}else if("editerTop".equals(btn) && !acces[6].equals("<td></td>")){
					request.setAttribute("display1", "");
					request.setAttribute("display2", "");
					request.setAttribute("display3", "<!--");
					request.setAttribute("display4", "-->");
					request.setAttribute("value", "edit");
					
					String query1="SELECT designation FROM action";
					String data="<select name='designation' id='designationSelect' type='text'>";
					try {
						result=p.execReqLecture(c, query1);
						while(result.next()){
							data+="<option value='"+result.getString("designation")+"'>"+result.getString("designation")+"</option>";
							request.setAttribute("intitule", result.getString("intitule"));
							request.setAttribute("objectifs", result.getString("objectif"));
							request.setAttribute("indicateur", result.getString("indicateur"));
							request.setAttribute("valRef", result.getString("valeur_ref"));
							request.setAttribute("valCible", result.getString("valeur_cible"));
							request.setAttribute("codeProgr", result.getString("code_programme"));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					data+="</select>";
					request.setAttribute("data", data);
					request.setAttribute("display", "hidden");
				}
				
				String query="SELECT designation, intitule FROM programme";
				String dataList="";
				try {
					result=p.execReqLecture(c, query);
					while(result.next()){
						dataList+="<option value='"+result.getString("designation")+"'>"+result.getString("designation")+"</option>";
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!"non".equals(progrEdit) && !"action".equals(btn) && !"creeraction".equals(btn)){//si on voudrait editer un programme
					if(nb[Integer.parseInt(trow[1])+1]==0 || nb[Integer.parseInt(trow[1])+1]>1){
						request.setAttribute("impossibleDetiter", "vous devez selectionner une seul action!");
						request.setAttribute("tr", trow[0]);
						request.setAttribute("nb", trow[1]);
						//this.getServletContext().getRequestDispatcher("/WEB-INF/admin/action.jsp").forward(request,response);
					}else{
						request.setAttribute("edition", "1");
						if(!acces[6].equals("<td></td>")){
							try {
								//System.out.println(progrEdit);
								String requete="SELECT * FROM action WHERE cocher ='"+progrEdit+"'";
								result = p.execReqLecture(c, requete);
								request.setAttribute("display1", "");
								request.setAttribute("display2", "");
								request.setAttribute("display3", "<!--");
								request.setAttribute("display4", "-->");
								request.setAttribute("value", "editer");
								request.setAttribute("data", " ");
								request.setAttribute("display", "input");
								System.out.println(requete);
								while(result.next()){
									request.setAttribute("progrPere", result.getString("code_programme"));
									request.setAttribute("designation", result.getString("designation"));
									request.setAttribute("intitule", result.getString("intitule"));
									request.setAttribute("objectif", result.getString("objectif"));
									request.setAttribute("indicateur", result.getString("indicateur"));
									request.setAttribute("valRef", result.getString("valeur_ref"));
									request.setAttribute("valCible", result.getString("valeur_cible"));
									request.setAttribute("respoProgramme", result.getString("code_programme"));	
								}		
								System.out.println("L'utilisateur"+session.getAttribute("nom")+" d'adresse IP "+request.getRemoteHost()+" essaie d'editer l'action "+result.getString("designation"));
								
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					//tab={"programme","designation"};
					trow=p.rechargerTabMore(c,"action","designation intitule objectif code_programme","designation",tab,"code_programme","designation",null);
					request.setAttribute("tr", trow[0]);
					request.setAttribute("nb", trow[1]);
					request.setAttribute("dataList", dataList);
				}
				
				try {
					String query1="SELECT * FROM action";
					String option1="";
					String option2="";
					String option3="";
					result=p.execReqLecture(c, query1);
					while(result.next()){
						option1+="<option value='"+result.getString("designation")+"'>";
						option2+="<option value='"+result.getString("intitule")+"'>";
						option3+="<option value='"+result.getString("objectif")+"'>";
					}
					request.setAttribute("option1", option1);
					request.setAttribute("option2", option2);
					request.setAttribute("option3", option3);
	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				trow=p.rechargerTabMore(c,"action","designation intitule objectif code_programme","designation",tab,"code_programme","designation",null);
				request.setAttribute("tr", trow[0]);
				request.setAttribute("nb", trow[1]);
				request.setAttribute("dataList", dataList);
				this.getServletContext().getRequestDispatcher("/WEB-INF/admin/actions.jsp").forward(request,response);
			}else{
				this.getServletContext().getRequestDispatcher("/WEB-INF/admin/access_interdit.jsp").forward(request,response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		p.encodage(request);
		HttpSession session = request.getSession();
		if(session.getAttribute("code_pers")==null){
			String erreur="<label align='center' id='welcomeMsg'>Bienvenu dans Suite Project</label>";
			request.setAttribute("erreurPassw", erreur);
			this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
		}else{
			String code_pers=(String)session.getAttribute("code_pers");
			String nomUser=(String)session.getAttribute("nom");
			String emailUser=(String)session.getAttribute("email");
			String contactUser=(String)session.getAttribute("contacts");
			String code_role_user=(String)session.getAttribute("code_role");
		
			String[] tab = {"programme","designation"};
			java.sql.Connection c=null;
			try {
				c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
				result = p.execReqLecture(c, "SELECT * FROM programme");
				String option="";
				while(result.next()){
					option+="<option value="+result.getString("designation")+">"+result.getString("designation")+"</option>";
					System.out.println("option = "+option);
				}
				request.setAttribute("option", option);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			String designation=(String) request.getParameter("designation");
			String intitule=(String) request.getParameter("intitule");
			String objectifs=(String) request.getParameter("objectifs");
			//System.out.println(" voici le premier objectif :"+request.getParameter("objectifs"));
			String indicateur=(String) request.getParameter("indicateur");
			String valeur_cible=(String) request.getParameter("valCible");
			String valeur_ref=(String) request.getParameter("valRef");
			String programme=(String) request.getParameter("programmepere");
			//System.out.println("Indicateur = "+indicateur);
			
			String profil=(String)session.getAttribute("profil");
			//System.out.println(profil);
			String[] acces=profil.split("__");
			request.setAttribute("edit", acces[5]);
			request.setAttribute("add", acces[6]);
			request.setAttribute("del", acces[7]);
		
			request.setAttribute("top1", acces[35]);
			request.setAttribute("top2", acces[36]);
			request.setAttribute("top3", acces[37]);
			request.setAttribute("top4", acces[38]);
			request.setAttribute("top5", acces[39]);
			request.setAttribute("top6", acces[40]);
				
			/*Inscription des donnÃ©es dans la BD*/
			String btn = (String)request.getParameter("enregistrerAction");
			//System.out.println("le bouton est "+btn);
			if(btn.equals("save")){
				System.out.println("L'utilisateur"+session.getAttribute("nom")+" d'adresse IP "+request.getRemoteHost()+" essaie d'editer l'action "+designation);
				
				trow=p.rechargerTabMore(c,"action","designation intitule objectif code_programme","designation",tab,"code_programme","designation",null);
				int nb=Integer.parseInt(trow[1]);
				Calendar d=Calendar.getInstance();
				int annee=d.get(Calendar.YEAR);
				String queryInsert="INSERT INTO action (designation, intitule, objectif, indicateur," +
						" valeur_cible, valeur_ref, annee, code_programme) " +
						"VALUES (" +
						"'"+p.getStringCode(designation,request)+"', " +
						"'"+p.getStringCode(intitule,request)+"', " +
						"'"+p.getStringCode(objectifs,request)+"', " +
						"'"+p.getStringCode(indicateur,request)+"', " +
						"'"+p.getStringCode(valeur_cible,request)+"', " +
						"'"+p.getStringCode(valeur_ref,request)+"', " +
						"'"+annee+"', " +
						"'"+p.getStringCode(programme,request)+"' )";
					    try {
						     c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
						     p.execReqEcriture(c, queryInsert);
					    } catch (SQLException e) {
						     e.printStackTrace();
					    }
					request.setAttribute("display1", "<!--");
					request.setAttribute("display2", "-->");
					request.setAttribute("display3", "");
					request.setAttribute("display4", "");
					System.out.println("d'enregistrement action = "+queryInsert);
				}else if(btn.equals("editer")){
					//trow=p.rechargerTab(c,"programme","designation intitule objectifs indicateur","designation");
					//int nb=Integer.parseInt(trow[1]);
					request.getAttribute("designation");
					String editInsert="UPDATE action SET " +
							"intitule = '"+p.getStringCode(intitule,request)+"', " +
							"objectif = '"+p.getStringCode(objectifs,request)+"', " +
							"indicateur = '"+p.getStringCode(indicateur,request)+"', " +
							"valeur_cible = '"+p.getStringCode(valeur_cible,request)+"', " +
							"valeur_ref = '"+p.getStringCode(valeur_ref,request)+"' " +
							"WHERE DESIGNATION = '"+(String)request.getParameter("designatHidden")+"' AND code_programme = '"+(String)request.getParameter("progrPere")+"'";
					//System.out.println("Voici l'objectif defini :"+objectifs);
					try {
						c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
						p.execReqEcriture(c, editInsert);
						System.out.println("L'utilisateur"+session.getAttribute("nom")+" d'adresse IP "+request.getRemoteHost()+" a modifié l'action: "+designation);
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					//System.out.println("d'enregistrement action = "+editInsert);
					request.setAttribute("display1", "<!--");
					request.setAttribute("display2", "-->");
					request.setAttribute("display3", "");
					request.setAttribute("display4", "");
				}else if(btn.equals("edit")){//si le bouton editer du formulaire des cases a cocher du tableau est valide
					try {
						String[] trow=p.rechargerTabMore(c,"action","designation intitule objectif code_programme","designation",tab,"code_programme","designation",null);
						int nb=Integer.parseInt(trow[1]);//nombre de lignes du tableau
						int[] n=p.nombreLignesCoche(request,nb);
						//System.out.println("numero de la ligne cochee : "+n[0]);
						result = p.execReqLecture(c, "SELECT * FROM action WHERE cocher = "+n[0]);
						while(result.next()){
							request.setAttribute("designation", result.getString("designation"));
							request.setAttribute("intitule", result.getString("intitule"));
							request.setAttribute("objectifs", result.getString("objectif"));
							request.setAttribute("indicateur", result.getString("indicateur"));
							request.setAttribute("valRef", result.getString("valeur_ref"));
							request.setAttribute("valCible", result.getString("valeur_cible"));
							request.setAttribute("programme", result.getString("code_programme"));
							System.out.println("L'utilisateur"+session.getAttribute("nom")+" d'adresse IP "+request.getRemoteHost()+" essaie d'editer l'action "+result.getString("designation"));
							
	
							request.setAttribute("value", "editer");
						}
						String query="SELECT designation, intitule FROM programme";
						String dataList="";
						result=p.execReqLecture(c, query);
						while(result.next()){
							dataList+="<option value='"+result.getString("designation")+"'>"+result.getString("designation")+"</option>";
						}
						request.setAttribute("display1", "");
						request.setAttribute("display2", "");
						request.setAttribute("display3", "<!-");
						request.setAttribute("display4", "-->");
						request.setAttribute("data", " ");
						request.setAttribute("dataList", dataList);
						request.setAttribute("display", "input");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(btn.equals("delete")){
					trow=p.rechargerTabMore(c,"action","designation intitule objectif code_programme","designation",tab,"code_programme","designation",null);
					
					int nb[]=p.nombreLignesCoche(request, Integer.parseInt(trow[1]));
					//String projet=(String)request.getParameter("projetpere");
					int ligneCochee=nb[0];
					int nbLigneCochee = nb[Integer.parseInt(trow[1])+1];
					System.out.println("L'utilisateur de code "+code_pers+" a coché l'action "+ligneCochee+" pour Suppression");
					
					try {
						for(int i=0;i<nbLigneCochee;i++){
							p.execReqEcriture(c, "DELETE FROM action WHERE cocher ='"+nb[i]+"'");
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("L'utilisateur "+session.getAttribute("nom")+" d'adresse IP "+request.getRemoteHost()+" a supprimé une action");
					
					trow=p.rechargerTabMore(c,"action","designation intitule objectif code_programme","designation",tab,"code_programme","designation",null);
					//request.setAttribute("tr", "");
					
					response.setContentType("text/html"); 
					response.setCharacterEncoding("UTF-8");
					response.setHeader("Access-Control-Allow-Origin", "*");
			        //PrintWriter out = response.getWriter();
					String resultat=trow[0]+"??"+trow[1];
			        response.getWriter().write(resultat);
			        
			        return;
				}else if(btn.equals("filtrer")){;
					designation=(String) request.getParameter("designation");
					intitule=(String)(request.getParameter("intitule"));
					programme=(String)(request.getParameter("programme"));
					String[] critere={"","",""};
					if(!designation.equals("")) designation="designation LIKE ('"+designation+"%')";
					if(!intitule.equals("")) intitule="intitule LIKE ('"+intitule+"%')";
					if(programme.equals("--All--")){
							critere[1]=designation;
							critere[2]=intitule;
							if(designation.equals("") && intitule.equals("")){
								critere=null;
							}
					}else{
							critere[0]="code_programme = ('"+programme+"')";
							critere[1]=designation;
							critere[2]=intitule;
					}
					
					trow=p.rechargerTabMore(c,"action","designation intitule objectif code_programme","designation",tab,"code_programme","designation",critere);
					
					response.setContentType("text/html"); 
					response.setCharacterEncoding("UTF-8");
					response.setHeader("Access-Control-Allow-Origin", "*");
			        //PrintWriter out = response.getWriter();
					String resultat=trow[0]+"??"+trow[1];
			        response.getWriter().write(resultat);
			        
			        return;
				}else if(btn.equals("reload")){;
				
					trow=p.rechargerTabMore(c,"action","designation intitule objectif code_programme","designation",tab,"code_programme","designation",null);
					//request.setAttribute("tr", "");
					
					response.setContentType("text/html"); 
					response.setCharacterEncoding("UTF-8");
					response.setHeader("Access-Control-Allow-Origin", "*");
			        //PrintWriter out = response.getWriter();
					String resultat=trow[0]+"??"+trow[1];
			        response.getWriter().write(resultat);
			        
			        return;
				}
				
				/*Rechargement de la page*/
				trow=p.rechargerTabMore(c,"action","designation intitule objectif code_programme","designation",tab,"code_programme","designation",null);
				request.setAttribute("tr", trow[0]);
				request.setAttribute("nb", trow[1]);
			this.getServletContext().getRequestDispatcher("/WEB-INF/admin/actions.jsp").forward(request,response);
		}
	}

}
