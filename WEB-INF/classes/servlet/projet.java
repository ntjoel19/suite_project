package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfWriter;

import dao.Persistance;
import pojo.Log;

/**
 * Servlet implementation class projet
 */
@WebServlet("/projet")
public class projet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String out = "contents2.pdf";
	
	Persistance p=new Persistance();
	Log log = new Log();
	java.sql.Connection c=null;
	ResultSet result=null;
	String[] trow=null;//la partie dynamique du tableau
	
	String[] champs=new String[28];
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public projet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int progression=0;
		HttpSession session = request.getSession(true);
		String paramHTTP=(String)request.getParameter("btn")==null?"":(String)request.getParameter("btn");
		//System.out.println("Le test d'entree est = "+session.getAttribute("code_pers")==null && (!paramHTTP.equals("listActions") || !paramHTTP.equals("filtreActProgr")));
		if(session.getAttribute("code_pers")==null && (!paramHTTP.equals("listActions") || !paramHTTP.equals("filtreActProgr"))){
			String erreur="<label align='center' id='welcomeMsg'>Bienvenu dans Suite Project</label>";
			request.setAttribute("erreurPassw", erreur);
			this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
		}else{
			p.encodage(request);
			String userCode = (String) session.getAttribute("code_pers");
			String userInfo = "Nom = " + (String) session.getAttribute("nom") + " IP = " +request.getRemoteHost();
			String dateOpt = (String)p.dateActuelle();
			Connection c=null;
			request.setAttribute("dataList1", p.listeProjet("intitule"));
			request.setAttribute("dataList2", p.listeProjet("porteur"));
			//String chemin_ressource = request.getRealPath("/ressources/projet");
			//System.out.println("repertoire du projet cree= "+chemin_ressource);
			try {
				c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String profil=(String)session.getAttribute("profil");
			//System.out.println("profil utilisateur"+profil);
			String[] acces=profil.split("__");
			request.setAttribute("top1", acces[35]);
			request.setAttribute("top2", acces[36]);
			request.setAttribute("top3", acces[37]);
			request.setAttribute("top4", acces[38]);
			request.setAttribute("top5", acces[39]);
			request.setAttribute("top6", acces[40]);
			if(!acces[8].equals("<img title='Acces interdit' class='img' src='./ressources/accesBloq.png' />Projets")){
				request.setAttribute("intitule", "");
				request.setAttribute("porteur", "");
				request.setAttribute("contexte", "");
				request.setAttribute("description", "");
				request.setAttribute("objectif1","");
				request.setAttribute("objectif2", "");;
				request.setAttribute("promot1", "");
				request.setAttribute("promot2", "");
				request.setAttribute("promot3", "");
				request.setAttribute("action", "");
				request.setAttribute("ancrage1", "");
				request.setAttribute("ancrage2", "");
				request.setAttribute("effet", "");
				request.setAttribute("impact", "");
				request.setAttribute("justification1", "");
				request.setAttribute("justification2", "");
				request.setAttribute("justification3", "");
				request.setAttribute("cout", "");
				request.setAttribute("population", "");
				request.setAttribute("service", "");
				request.setAttribute("typologie", "");
				request.setAttribute("extrants", "");
				request.setAttribute("nature_finance", "");
				request.setAttribute("maitre_Ovr", "");
				request.setAttribute("maitre_Ovg", "");
				request.setAttribute("partenaire", "");
				request.setAttribute("designation", "");
				request.setAttribute("name", "enregistrer");
				request.setAttribute("appartenance", "");
				String btn=request.getParameter("btn");
				request.setAttribute("option", p.listeProgramme());//chargement de la liste des programmes
				if("creerprojet".equals(btn)){
					if(!acces[9].equals("<td></td>")){
						String dataList="";
						request.setAttribute("dataList1", p.listeProgramme());//chargement de la liste des programmes
						
						request.setAttribute("dataList", dataList);
						try {
							p.fermerConnexion(c);
							log = new Log(userCode , userInfo , dateOpt , "acces interface creation projet");
							log.write();
							System.out.println("J'ai ecrit dans le log");
							this.getServletContext().getRequestDispatcher("/WEB-INF/admin/add_project&stap.jsp").forward(request,response);
							
						} catch (SQLException e) {
							log = new Log(userCode , userInfo , dateOpt , "acces interface creation projet: echec de fermeture de connection");
							log.write();
							this.getServletContext().getRequestDispatcher("/WEB-INF/admin/add_project&stap.jsp").forward(request,response);
							e.printStackTrace();
						}
					}else{
						try {
							p.fermerConnexion(c);
							log = new Log(userCode , userInfo , dateOpt , "tentative interdite de creer un projet");
							log.write();
							this.getServletContext().getRequestDispatcher("/WEB-INF/admin/access_interdit.jsp").forward(request,response);
							
						} catch (SQLException e) {
							log = new Log(userCode , userInfo , dateOpt , "tentative interdite de creer un projet");
							log.write();
							this.getServletContext().getRequestDispatcher("/WEB-INF/admin/access_interdit.jsp").forward(request,response);
							e.printStackTrace();
						}
					}
				}else if("listActions".equals(btn)){

					response.setContentType("text/html"); 
					response.setCharacterEncoding("UTF-8");
					response.setHeader("Access-Control-Allow-Origin", "*");
			        //PrintWriter out = response.getWriter();
					//on charge la liste des actions qui seront utilisées dans les champs auto-completables
			        response.getWriter().write(p.listeAction(request.getParameter("programme")));
			        
			        return;
				}else if("editerUnique".equals(btn)){
						int ligneCochee=Integer.parseInt((String)request.getParameter("projet"));
						try {
							result = p.execReqLecture(c, "SELECT * FROM projet WHERE cocher ='"+ligneCochee+"'");
							while(result.next()){
								String actions="";
								request.setAttribute("designation", result.getString("designation"));
								request.setAttribute("appartenance", result.getString("appartenance"));
								request.setAttribute("intitule", result.getString("intitule"));
								request.setAttribute("porteur", result.getString("porteur"));
								request.setAttribute("contexte", result.getString("contexte_de_mise_en_oeuvre"));
								request.setAttribute("description", result.getString("description_projet"));
								String[] objectifs=(String[])result.getString("objectifs_vises").split("-/");
								request.setAttribute("objectif1", objectifs[0]);
								request.setAttribute("objectif2", objectifs[1]);
								String[] promoteurs=(String[])result.getString("ministere").split("-/");
								request.setAttribute("promot1", promoteurs[0]);
								request.setAttribute("promot2", promoteurs[1]);
								request.setAttribute("promot3", promoteurs[2]);
		
								request.setAttribute("dataList1", p.listeProgramme(result.getString("code_programme")));
								request.setAttribute("dataListAction", p.listeAction(result.getString("code_programme"),result.getString("code_action")));
								String[] ancrage11=(String[])result.getString("ancrage_du_projet").split("-/");
								request.setAttribute("ancrage1", ancrage11[0]);
								request.setAttribute("ancrage2", ancrage11[1]);
								request.setAttribute("effet", result.getString("effets_attendus"));
								request.setAttribute("impact", result.getString("impactes"));
								String justification = (String)result.getString("justification_des_besoins");
								String[] justification11=justification.split("-/");request.setAttribute("justification1", justification11[0]);
								request.setAttribute("justification2", justification11[1]);
								request.setAttribute("justification3", justification11[2]);
								request.setAttribute("cout", result.getString("cout_du_projet"));
								request.setAttribute("population", result.getString("population"));
								request.setAttribute("service", result.getString("service_responsable"));
								request.setAttribute("typologie", result.getString("typologie_du_projet"));
								request.setAttribute("extrants", result.getString("extrants_escomptes"));
								request.setAttribute("nature_finance", result.getString("nature_du_financement"));
								request.setAttribute("maitre_Ovr", result.getString("maitre_doeuvre"));
								request.setAttribute("maitre_Ovg", result.getString("maitre_douvrage"));
								request.setAttribute("partenaire", result.getString("partenaires"));
								
								request.setAttribute("name", "editerProjet");
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						profil=(String)session.getAttribute("profil");
						request.setAttribute("top1", acces[35]);
						request.setAttribute("top2", acces[36]);
						request.setAttribute("top3", acces[37]);
						request.setAttribute("top4", acces[38]);
						request.setAttribute("top5", acces[39]);
						request.setAttribute("top6", acces[40]);
						log = new Log(userCode , userInfo , dateOpt , "acces interface edition projet");
						log.write();
						try {
							p.fermerConnexion(c);
							this.getServletContext().getRequestDispatcher("/WEB-INF/admin/add_project&stap.jsp").forward(request,response);
							return;
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							System.out.println("Erreur de fermeture de la connection à la BD.");
							this.getServletContext().getRequestDispatcher("/WEB-INF/admin/add_project&stap.jsp").forward(request,response);
							
							e.printStackTrace();
							return;
						}
				}else if("gerertache".equals(btn)){
					String dataList="";
					if(!acces[13].equals("<td></td>")){
						request.setAttribute("dataList1", p.listeProjet("intitule"));
						request.setAttribute("dataList", dataList);
						log = new Log(userCode , userInfo , dateOpt , "acces interface gestion des taches de projets");
						log.write();
						this.getServletContext().getRequestDispatcher("/WEB-INF/admin/editer_taches.jsp").forward(request,response);
					}else{
						log = new Log(userCode , userInfo , dateOpt , "acces interdit a l'espace de gestion des taches");
						log.write();
						try {
							p.fermerConnexion(c);
							this.getServletContext().getRequestDispatcher("/WEB-INF/admin/access_interdit.jsp").forward(request,response);
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							System.out.println("Erreur de fermeture de la connection à la BD.");
							this.getServletContext().getRequestDispatcher("/WEB-INF/admin/access_interdit.jsp").forward(request,response);
							
							e.printStackTrace();
						}
					}
				}else if("filtreActProgr".equals(btn)){
					String list="";
					
					try {
						ResultSet r=p.execReqLecture(c, "select * from projet where ( code_programme='"+request.getParameter("programme")+"' AND code_action='"+request.getParameter("action")+"' )");
						
						while(r.next()){
							
							list+="<tr><td>"+r.getString("designation")+"</td><td>"+r.getString("intitule")+"</td><td>"+r.getString("objectifs_vises")+"</td><td>"+r.getString("extrants_escomptes")+"</td><td>"+r.getString("cout_du_projet")+"</td>";
							list+="<td align ='left'><span> Fiche projet </span>(<a href='"+r.getString("path")+"'>Télécharger</a>) <br/><span> TDR </span> (<a href='"+r.getString("path")+"'>Télécharger</a>)</td></tr>";
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("j'affiche : "+list);
					response.setContentType("text/html"); 
					response.setCharacterEncoding("UTF-8");
					response.setHeader("Access-Control-Allow-Origin", "*");
			        response.getWriter().write(list);
			        
			        try {
						p.fermerConnexion(c);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("Erreur de fermeture de la connection à la BD.");
						e.printStackTrace();
					}
			        
			        return;
				}else if("suiviprojet".equals(btn)){
					try {
						p.notificationTache("ntepp");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("acces a l'interface suivi projet ");
					String requete = "SELECT code_personne FROM projet";
					String[] trow={"",""};
					Integer nbRecord=0;
					try {
						c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("apres ouverture ...");
					trow=rechargerTabProjet(c,"code_programme code_action intitule objectifs_vises progression progr_BD porteur appartenance","designation",null);
					//System.out.println("liste des projet en html = "+trow[0]);
					request.setAttribute("tr", trow[0]);
					request.setAttribute("nb", trow[1]);
				
					
					request.setAttribute("edit", acces[9]);
					request.setAttribute("add", acces[10]);
					request.setAttribute("genererFichPr", acces[11]);
					request.setAttribute("etapeEdit", acces[12]);
					request.setAttribute("voirEtapes", acces[13]);
					request.setAttribute("suppr", acces[14]);
					request.setAttribute("budget", acces[15]);
					try {
						p.fermerConnexion(c);
						log = new Log(userCode , userInfo , dateOpt , "acces interface de gestion des projets");
						log.write();
						this.getServletContext().getRequestDispatcher("/WEB-INF/admin/suivi_projet.jsp").forward(request,response);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("Erreur de fermeture de la connection à la BD.");
						this.getServletContext().getRequestDispatcher("/WEB-INF/admin/suivi_projet.jsp").forward(request,response);
						
						e.printStackTrace();
					}
				}else if("gereruser".equals(btn)){
					try {
						p.fermerConnexion(c);
						this.getServletContext().getRequestDispatcher("/WEB-INF/admin/utilisateurs.jsp").forward(request,response);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("Erreur de fermeture de la connection à la BD.");
						this.getServletContext().getRequestDispatcher("/WEB-INF/admin/utilisateurs.jsp").forward(request,response);
						
						e.printStackTrace();
					}
				}
			}else{
				try {
					p.fermerConnexion(c);
					log = new Log(userCode , userInfo , dateOpt , "acces interface creation projet");
					this.getServletContext().getRequestDispatcher("/WEB-INF/admin/access_interdit.jsp").forward(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Erreur de fermeture de la connection à la BD.");
					this.getServletContext().getRequestDispatcher("/WEB-INF/admin/access_interdit.jsp").forward(request,response);
					e.printStackTrace();
				}
			}	
		}
	}

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathFiles = request.getServletContext().getRealPath("");
		HttpSession session = request.getSession();
		if(session.getAttribute("code_pers")==null){
			String erreur="<label align='center' id='welcomeMsg'>Bienvenu dans Suite Project</label>";
			request.setAttribute("erreurPassw", erreur);
			this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
		}else{
			String chemin_ressource = request.getRealPath("/ressources/projet");
			String userCode = (String) session.getAttribute("code_pers");
			String userInfo = "Nom = " + (String) session.getAttribute("nom") + " IP = " +request.getRemoteHost();
			String dateOpt = (String)p.dateActuelle();
			p.encodage(request);
			Connection c=null;
			try {
				c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
				result = p.execReqLecture(c, "SELECT * FROM programme");
				String option="";
				while(result.next()){
					option+="<option value="+result.getString("designation")+">"+result.getString("designation")+"</option>";
					//System.out.println("option = "+option);
				}
				request.setAttribute("option", option);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			request.setAttribute("dataList1", p.listeProjet("intitule"));
				int progression=0;	
			
				String neplusafficher=(String) request.getParameter("neplusafficher");
				String intitule=(String) request.getParameter("intitule")==null?"":(String) request.getParameter("intitule");
				champs[0]=intitule;
				System.out.println("l'intitule est "+champs[0]);
				String contexte=(String) request.getParameter("contexte")==null?"":(String) request.getParameter("contexte");
				champs[1]=contexte;
				String description=(String) request.getParameter("description")==null?"":(String) request.getParameter("description");
				champs[2]=description;
				String objectif1=(String) request.getParameter("objectif1");
				champs[3]=objectif1;
				String objectif2=(String) request.getParameter("objectif2");
				champs[4]=objectif2;
				String promoteur1=(String) request.	getParameter("promoteur1");
				champs[5]=promoteur1;
				String promoteur2=(String) request.getParameter("promoteur2");
				champs[6]=promoteur2;
				String promoteur3=(String) request.getParameter("promoteur3");
				champs[7]=promoteur3;
				String programme=(String) request.getParameter("programme")==null?"":(String) request.getParameter("programme");
				champs[8]=programme;
				String ancrage1=(String) request.getParameter("ancrage1");
				champs[9]=ancrage1;
				String ancrage2=(String) request.getParameter("ancrage2");
				champs[10]=ancrage2;
				String effets=(String) request.getParameter("effets")==null?"":(String) request.getParameter("effets");
				champs[11]=effets;
				String impacte=(String) request.getParameter("impacte")==null?"":(String) request.getParameter("impacte");
				champs[12]=impacte;
				String justification1=(String) request.getParameter("justification1");
				champs[13]=justification1;
				String justification2=(String) request.getParameter("justification2");
				champs[14]=justification2;
				String justification3=(String) request.getParameter("justification3");
				champs[15]=justification3;
				String cout=(String) request.getParameter("cout");
				champs[16]=cout;
				String population=(String) request.getParameter("population");
				champs[17]=population;
				String service=(String) request.getParameter("service");
				champs[18]=service;
				String typologie=(String) request.getParameter("typologie");
				champs[19]=typologie==null?"projet structurant":typologie;
				String extrant=(String) request.getParameter("extrant");
				champs[20]=extrant;
	
				String nature_financement=(String) request.getParameter("nature_financement");
				champs[21]=nature_financement;
				String maitredoeuvre=(String) request.getParameter("maitredoeuvre");
				champs[22]=maitredoeuvre;
				String maitredouvrage=(String) request.getParameter("maitredouvrage");
				champs[23]=maitredouvrage;
				String partenaire=(String) request.getParameter("partenaire");
				champs[24]=partenaire;
				String porteur=(String) request.getParameter("porteur");
				champs[25]=porteur;
				String progrPere=(String) request.getParameter("action");
				champs[26]=progrPere;
				String appartenance=(String) request.getParameter("appartenance");
				champs[27]=appartenance;
				//String typologie=(String) request.getParameter("typologie");
				//String extrant=(String) request.getParameter("extrant");
				
				for(int i=0;i<28;i++){
					//System.out.println(champs[i]);
					if(champs[i]==null){
						progression+=1;
					}else if(champs[i].equals("")){
						progression+=1;
					}
				}
				//System.out.println("la progression est : "+progression);
				progression=(progression/28)*100;
				progression = 100 - progression;
				//System.out.println("la progression est : "+progression);
				String btn="editer";
				//System.out.println("je suis la");
				//System.out.println("le bouton est "+request.getParameter("enregistrerProjet"));
				
				if((String)request.getParameter("enregistrerProjet")==null)
					btn = "valider";
				else
					btn = (String)request.getParameter("enregistrerProjet");
				
				if((String)request.getParameter("enregistrerProj")!=null)
					btn = (String)request.getParameter("enregistrerProj");
				
				String designation="B";
				System.out.println("le bouton est "+btn);
				String code_pers=(String)session.getAttribute("code_pers");
				String objectif = objectif1+"-/"+objectif2+"-";
				String ancrage = ancrage1+"-/"+ancrage2+"-";
				//designation=code_pers+"+"+intitule+" ";
				String justification = justification1+"-/"+justification2+"-/"+justification3+"-";
				String promoteur = promoteur1+"-/"+promoteur2+"-/"+promoteur3+"-";
				
				
				if(btn.equals("enregistrer")){
					String queryInsert="INSERT INTO projet (code_personne, intitule, contexte_de_mise_en_oeuvre, " +
							"code_action, maitre_doeuvre, maitre_douvrage, partenaires, service_responsable, " +
							"agence_dexecution, description_projet, objectifs_vises, " +
							"justification_des_besoins, ancrage_du_projet, cout_du_projet, nature_du_financement, neplusaffiche, " +
							"extrants_escomptes, effets_attendus, impactes, progression,  ministere, population, porteur, appartenance, code_programme, typologie_du_projet)" +
					"VALUES (" +
					"'"+code_pers+"', " +
					"'"+p.getStringCode(intitule,request)+"', " +
					"'"+p.getStringCode(contexte,request)+"', " +
					"'"+p.getStringCode(programme,request)+"', " +
					"'"+p.getStringCode(maitredoeuvre,request)+"', " +
					"'"+p.getStringCode(maitredouvrage,request)+"', " +
					"'"+p.getStringCode(partenaire,request)+"', " +
					"'"+p.getStringCode(service,request)+"', " +
					"'agence dexecution empty', " +
					"'"+p.getStringCode(description,request)+"', " +
					"'"+p.getStringCode(objectif,request)+"', " +
					"'"+p.getStringCode(justification,request)+"', " +
					"'"+p.getStringCode(ancrage,request)+"', " +
					"'"+cout+"', " +
					"'"+nature_financement+"', " +
					"'"+neplusafficher+"', " +
					"'"+p.getStringCode(extrant,request)+"', " +
					"'"+p.getStringCode(effets,request)+"', " +
					"'"+p.getStringCode(impacte,request)+"', " +
					"'"+progression+"', " +
					"'"+p.getStringCode(promoteur,request)+"', " +
					"'"+p.getStringCode(population,request)+"', " +
					"'"+request.getParameter("porteur")+"', " +
					"'"+p.getStringCode(appartenance,request)+"', " +
					"'"+progrPere+"', " +
					"'"+typologie+"' )";
					System.out.println(queryInsert);
					String designat="";
					try {
						c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
						p.execReqEcriture(c, queryInsert);

						log = new Log(userCode , userInfo , dateOpt , "Enregistrement d'un nouveau projet = "+intitule);
						log.write();
						
						result = p.execReqLecture(c, "SELECT code_personne, porteur,designation FROM projet WHERE intitule='"+intitule+"';");
						if(result.next()){
							designat=result.getString("designation");
							p.execReqEcriture(c, "UPDATE projet SET path='"+result.getString("porteur")+result.getString("code_personne")+".pdf' WHERE intitule='"+intitule+"';");
							
						}
					} catch (SQLException e) {
						log = new Log(userCode , userInfo , dateOpt , "Erreur d'enregistrement d'un nouveau projet");
						log.write();
						e.printStackTrace();
					}
					
					new File("/suite_project/projet/"+designat).mkdirs();
					System.out.println("repertoire du projet cree= "+chemin_ressource+"\\"+designat);
					trow=rechargerTabProjet(c,"code_programme code_action intitule objectifs_vises progression progr_BD porteur appartenance","designation",null);
					
					//generation de la fiche projet
					queryInsert="SELECT * FROM projet where intitule='"+intitule+"'";
					String[] selection=new String[34];
					try {
						//c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
						result = p.execReqLecture(c, queryInsert);
						while(result.next()){
							for(int i1=0;i1<32;i1++){
								selection[i1]=result.getString(i1+1);
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					String message = genererPdf(selection, chemin_ressource,"testPDF",chemin_ressource);
					if(message.equals("erreur")){
						message = "le chemin est incorrect";
					}
				}else if(btn.equals("editerProjet")){
					System.out.println("je vais editer");
					
					String editInsert="UPDATE projet SET " +
					"intitule = '"+p.getStringCode(intitule,request)+"', " +
					"contexte_de_mise_en_oeuvre = '"+p.getStringCode(contexte,request)+"', " +
					"code_action = '"+p.getStringCode(programme,request)+"', " +
					"maitre_douvrage = '"+p.getStringCode(maitredoeuvre,request)+"', " +
					"maitre_doeuvre = '"+p.getStringCode(maitredouvrage,request)+"', " +
					"partenaires = '"+p.getStringCode(partenaire,request)+"', " +
					"service_responsable ='"+p.getStringCode(service,request)+"', " +
					"description_projet = '"+p.getStringCode(description,request)+"', " +
					"objectifs_vises = '"+p.getStringCode(objectif,request)+"', " +
					"justification_des_besoins = '"+p.getStringCode(justification,request)+"', " +
					"ancrage_du_projet = '"+p.getStringCode(ancrage,request)+"', " +
					"cout_du_projet = '"+p.getStringCode(cout,request)+"', " +
					"nature_du_financement = '"+p.getStringCode(nature_financement,request)+"', " +
					"extrants_escomptes = '"+p.getStringCode(extrant,request)+"', " +
					"effets_attendus = '"+p.getStringCode(effets,request)+"', " +
					"impactes = '"+p.getStringCode(impacte,request)+"', " +
					"ministere = '"+p.getStringCode(promoteur,request)+"', "+
					"population = '"+p.getStringCode(population,request)+"', "+
					"appartenance = '"+p.getStringCode(appartenance,request)+"', "+
					"porteur = '"+p.getStringCode(porteur,request)+"', "+
					"typologie_du_projet = '"+typologie+"' "+
					"WHERE DESIGNATION = '"+request.getParameter("designation")+"'";
					
					try {
						p.execReqEcriture(c, editInsert);
						log = new Log(userCode , userInfo , dateOpt , "Modification du projet : "+intitule );
						log.write();
					} catch (SQLException e) {
						log = new Log(userCode , userInfo , dateOpt , "Erreur de modification du projet : "+intitule );
						log.write();
						e.printStackTrace();
					}
					//this.getServletContext().getRequestDispatcher("/WEB-INF/admin/suivi.jsp").forward(request,response);
					
					//generation de la fiche projet
					String querySelect="SELECT * FROM projet where intitule='"+intitule+"'";
					String[] selection=new String[34];
					try {
						//c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
						result = p.execReqLecture(c, querySelect);
						while(result.next()){
							for(int i1=0;i1<32;i1++){
								selection[i1]=result.getString(i1+1);
							}
							new File("/suite_project/projet/"+result.getString("designation")).mkdirs();
							System.out.println("repertoire du projet cree= "+chemin_ressource+"\\"+result.getString("designation"));
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}System.out.println(intitule);
					String message = genererPdf(selection, chemin_ressource,"testPDF",chemin_ressource);
					if(message.equals("erreur")){
						message = "le chemin est incorrect";
					}
					
				}else if("editer".equals(btn) || "editerUnique".equals(btn)){
					trow=rechargerTabProjet(c,"code_programme code_action intitule objectifs_vises progression progr_BD porteur appartenance","designation",null);
					int nb[]=p.nombreLignesCoche(request, Integer.parseInt(trow[1]));
					//String projet=(String)request.getParameter("projetpere");
					int ligneCochee=0;
					System.out.println("je viens de cliquer sur editerUnique");
					if("editerUnique".equals(btn))
						ligneCochee=Integer.parseInt((String)request.getParameter("projet"));
					else
						ligneCochee=nb[0];
					try {
						result = p.execReqLecture(c, "SELECT * FROM projet WHERE cocher ='"+ligneCochee+"'");
						while(result.next()){
							String actions="";
							request.setAttribute("designation", p.getStringCode(result.getString("designation"),request));
							request.setAttribute("appartenance", p.getStringCode(result.getString("appartenance"),request));
							request.setAttribute("intitule", p.getStringCode(result.getString("intitule"),request));
							request.setAttribute("porteur", p.getStringCode(result.getString("porteur"),request));
							request.setAttribute("contexte", p.getStringCode(result.getString("contexte_de_mise_en_oeuvre"),request));
							request.setAttribute("description", p.getStringCode(result.getString("description_projet"),request));
							String[] objectifs=(String[])p.getStringCode(result.getString("objectifs_vises"),request).split("-/");
							request.setAttribute("objectif1", p.getStringCode(objectifs[0],request));
							request.setAttribute("objectif2", p.getStringCode(objectifs[1],request));
							String[] promoteurs=(String[])p.getStringCode(result.getString("ministere"),request).split("-/");
							request.setAttribute("promot1", p.getStringCode(promoteurs[0],request));
							request.setAttribute("promot2", p.getStringCode(promoteurs[1],request));
							request.setAttribute("promot3", p.getStringCode(promoteurs[2],request));
	
							request.setAttribute("dataList1", p.listeProgramme(p.getStringCode(result.getString("code_programme"),request)));
							request.setAttribute("dataListAction", p.listeAction(p.getStringCode(result.getString("code_programme"),request),p.getStringCode(result.getString("code_action"),request)));
							String[] ancrage11=(String[])p.getStringCode(result.getString("ancrage_du_projet"),request).split("-/");
							request.setAttribute("ancrage1", p.getStringCode(ancrage11[0],request));
							request.setAttribute("ancrage2", p.getStringCode(ancrage11[1],request));
							request.setAttribute("effet", p.getStringCode(result.getString("effets_attendus"),request));
							request.setAttribute("impact", p.getStringCode(result.getString("impactes"),request));
							String[] justification11=(String[])p.getStringCode(result.getString("justification_des_besoins"),request).split("-/");
							request.setAttribute("justification1", p.getStringCode(justification11[0],request));
							request.setAttribute("justification2", p.getStringCode(justification11[1],request));
							request.setAttribute("justification3", p.getStringCode(justification11[2],request));
							request.setAttribute("cout", p.getStringCode(result.getString("cout_du_projet"),request));
							request.setAttribute("population", p.getStringCode(result.getString("population"),request));
							request.setAttribute("service", p.getStringCode(result.getString("service_responsable"),request));
							request.setAttribute("typologie", p.getStringCode(result.getString("typologie_du_projet"),request));
							request.setAttribute("extrants", p.getStringCode(result.getString("extrants_escomptes"),request));
							request.setAttribute("nature_finance", p.getStringCode(result.getString("nature_du_financement"),request));
							request.setAttribute("maitre_Ovr", p.getStringCode(result.getString("maitre_doeuvre"),request));
							request.setAttribute("maitre_Ovg", p.getStringCode(result.getString("maitre_douvrage"),request));
							request.setAttribute("partenaire", p.getStringCode(result.getString("partenaires"),request));
							
							request.setAttribute("name", "editerProjet");
						}
						log = new Log(userCode , userInfo , dateOpt , "acces interface edition projet : "+intitule);
						log.write();
					} catch (SQLException e) {
						log = new Log(userCode , userInfo , dateOpt , "Erreur lors de l'acces interface edition projet");
						log.write();
						e.printStackTrace();
					}
					String profil=(String)session.getAttribute("profil");
					//System.out.println("profil utilisateur"+profil);
					String[] acces=profil.split("__");
					request.setAttribute("top1", acces[35]);
					request.setAttribute("top2", acces[36]);
					request.setAttribute("top3", acces[37]);
					request.setAttribute("top4", acces[38]);
					request.setAttribute("top5", acces[39]);
					request.setAttribute("top6", acces[40]);
					try {
						p.fermerConnexion(c);
						this.getServletContext().getRequestDispatcher("/WEB-INF/admin/add_project&stap.jsp").forward(request,response);
						return;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						this.getServletContext().getRequestDispatcher("/WEB-INF/admin/add_project&stap.jsp").forward(request,response);
						return;
					}
					
				}else if(btn.equals("deleteUnique")){
					String projet=request.getParameter("projet");
					String queryDel="delete from projet where cocher='"+projet+"'";
					String repProjet="";
					String intituleASupprimer="";
					try {
						ResultSet r=p.execReqLecture(c, "Select designation,intitule from projet where cocher='"+projet+"'");
						intituleASupprimer = r.getString("intitule");
						//on supprime les taches associeesa ce projet
						if(r.next()) p.execReqEcriture(c,"delete from tache where code_projet='"+r.getString("designation")+"'");
						repProjet=r.getString("designation");
						//on supprime le projet lui meme
						log = new Log(userCode , userInfo , dateOpt , "Suppression du projet intitulé : "+r.getString("intitule"));
						log.write();
						p.execReqEcriture(c,queryDel);
					} catch (SQLException e) {
						log = new Log(userCode , userInfo , dateOpt , "Echec de suppression du projet intitulé : "+intituleASupprimer);
						log.write();
						e.printStackTrace();
					}
					//suppression du repertoire du projet
					System.out.println("Suppression du repertoire /suite_project/projet/"+repProjet+"/");
					File del =  new File("/suite_project/projet/"+repProjet+"/"); 
					try { 
					p.recursifDelete(del); 
					} catch (IOException e) { 
					// TODO Auto-generated catch block 
					e.printStackTrace(); 
					}
					trow=rechargerTabProjet(c,"code_programme code_action intitule objectifs_vises progression progr_BD porteur appartenance","designation",null);
					response.setContentType("text/html"); 
					response.setCharacterEncoding("UTF-8");
					response.setHeader("Access-Control-Allow-Origin", "*");
			        //PrintWriter out = response.getWriter();
					String resultat=trow[0]+"??"+trow[1]+"??";
			        response.getWriter().write(resultat);
			        try {
						p.fermerConnexion(c);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        return;
				}else if(btn.equals("afficheDossierProjet") || btn.equals("supprPieceProjet") || btn.equals("addPieceProjet")){
					String projet=request.getParameter("projet");
					String codeProjet=projet;
					System.out.println("blablablabl = "+projet);
					log = new Log(userCode , userInfo , dateOpt , "Affiche le dossier du projet : "+projet);
					log.write();
					if(btn.equals("supprPieceProjet")){
						String piece=request.getParameter("piece");
						try {
							log = new Log(userCode , userInfo , dateOpt , "Suppression d'une piece du projet : "+projet);
							log.write();
							p.execReqEcriture(c, "delete from dossier_projet where id_piece='"+piece+"'");
						} catch (SQLException e) {
							log = new Log(userCode , userInfo , dateOpt , "Erreur de suppression d'une piece du projet : "+projet);
							log.write();

							e.printStackTrace();
						}
					}
					
					if(btn.equals("addPieceProjet")){
						String nom_piece=request.getParameter("nom_piece");
						String fichier=request.getParameter("fichier");
						//String[] fichierName=fichier.split("fakepath");
						try {
							p.execReqEcriture(c, "INSERT INTO dossier_projet (nom_piece, code_projet, piece_jointe)  VALUES ('"+
						nom_piece+"', '"+projet+"', '"+fichier+ "');");
							log = new Log(userCode , userInfo , dateOpt , "Ajout d'une piece au projet : "+projet);
							log.write();
							result=p.execReqLecture(c, "select cocher from projet where intitule='"+projet+"'");
							if(result.next()) projet=result.getString("cocher");
						} catch (SQLException e) {
							log = new Log(userCode , userInfo , dateOpt , "Erreur d'ajout d'une piece au projet : "+projet);
							log.write();
							e.printStackTrace();
						}
					}
					//System.out.println("blablablabl = "+projet);
					String query="select * from projet where cocher='"+projet+"'";
					String code_proj="";
					String resultat="";
					resultat="<table id='tablePiecesProj' width='100%'><thead><tr><th></th><th width='100%' style='border-bottom:1px solid grey;'>Pieces</th><th></th></tr></thead><tbody>";
					try {
						result = p.execReqLecture(c,query);
						if(result.next())
							projet=result.getString("intitule");
						code_proj=result.getString("designation");
						result=p.execReqLecture(c,"select * from dossier_projet where code_projet='"+result.getString("intitule")+"'");
						int i=0;
						while(result.next()){
							i++;
							resultat+="<tr><td style='border-bottom:1px solid grey;'>"+i+"</td><td style='border-bottom:1px solid grey;'><a href='/suite_project/fichiers/projet/"+code_proj+"/"+result.getString("piece_jointe")+"'>"+result.getString("nom_piece")+"</a></td><td style='border-bottom:1px solid grey;'><img class='supprimerPiece' id='"+result.getString("id_piece")+"--"+codeProjet+"' title='supprimer' src='././ressources/optDel.PNG'/></td></tr>";
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					resultat+="<tr style='display:none;' align='center' id='formAddPieceTableRow'><td></td><td><input class='in' name='description' placeholder='Description de la piece' type='text' value='"+projet+"' id='projet_1'/>&nbsp;<input class='in' value='fichier' type='file' id='chemin' accept='.pdf,.doc,.docx,.xls,.xlsx,audio/*,video/*,image/*,media_type'/><input type='hidden' id='hidden' name='codeProjet' designation='"+code_proj+"' codeProjet='"+projet+"' value='"+codeProjet+"'/>&nbsp;<input type='submit' name='addPieceDossier' value='enregistrer'/><td colspan='2'></td></tr>";
					resultat+="<tr><td></td><td align='center'><a href='#' class='more' title='ajouter une piece' style='border-radius:100px;border:1px solid grey;background:white;margin:10px;padding:10px;' id='addPiece'>+</a></td></tr>";
					
					resultat+="</tbody></table>";
					response.setContentType("text/html"); 
					response.setCharacterEncoding("UTF-8");
					response.setHeader("Access-Control-Allow-Origin", "*");
			        //PrintWriter out = response.getWriter();
					
			        response.getWriter().write(resultat);
			        try {
						p.fermerConnexion(c);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        return;
				}else if(btn.equals("delete")){
					trow=rechargerTabProjet(c,"code_programme code_action intitule objectifs_vises progression progr_BD porteur appartenance","designation",null);
					int nb[]=p.nombreLignesCoche(request, Integer.parseInt(trow[1]));
					String repProjet="";
					for(int i=0;i<nb[Integer.parseInt(trow[1])+1];i++){	
						String projectTask="";
						try {
							ResultSet r=p.execReqLecture(c, "Select designation,intitule from projet where cocher="+nb[i]);
							if(r.next()) p.execReqEcriture(c,"delete from tache where code_projet='"+r.getString("designation")+"'");
							projectTask = r.getString("intitule");
							log = new Log(userCode , userInfo , dateOpt , "Suppression d''une tache au projet : "+projectTask);
							log.write();
							repProjet=r.getString("designation");
							//on supprime le projet lui meme
							p.execReqEcriture(c, "DELETE FROM projet WHERE cocher = "+nb[i]);
							//suppression du repertoire du projet
							System.out.println("Suppression du repertoire /suite_project/projet/"+repProjet+"/");
							File del =  new File("/suite_project/projet/"+repProjet+"/"); 
							try { 
								p.recursifDelete(del); 
							} catch (IOException e) { 
								// TODO Auto-generated catch block 
								e.printStackTrace(); 
							}
							
						} catch (SQLException e) {
							log = new Log(userCode , userInfo , dateOpt , "Erreur de suppression d''une tache au projet : "+projectTask);
							log.write();
							e.printStackTrace();
						}
					}
					
					trow=rechargerTabProjet(c,"code_programme code_action intitule objectifs_vises progression progr_BD porteur appartenance","designation",null);
					response.setContentType("text/html"); 
					response.setCharacterEncoding("UTF-8");
					response.setHeader("Access-Control-Allow-Origin", "*");
			        //PrintWriter out = response.getWriter();
					String resultat=trow[0]+"??"+trow[1]+"??";
			        response.getWriter().write(resultat);
			        try {
						p.fermerConnexion(c);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        return;
			        
				}else if(btn.equals("Fiche Projet utilisateur")){
					String projet=(String)request.getParameter("projet");
					String queryInsert="SELECT * FROM projet where cocher="+projet;
					String[] selection=new String[34];
					try {
						//c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
						result = p.execReqLecture(c, queryInsert);
						while(result.next()){
							for(int i1=0;i1<32;i1++){
								selection[i1]=result.getString(i1+1);
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					String message = genererPdf(selection, chemin_ressource,"testPDF",chemin_ressource);
					if(message.equals("erreur")){
						message = "le chemin est incorrect";
					}
					
					response.setContentType("text/html"); 
					response.setCharacterEncoding("UTF-8");
					response.setHeader("Access-Control-Allow-Origin", "*");
			        //PrintWriter out = response.getWriter();
			        response.getWriter().write(message);
			        
			        try {
						p.fermerConnexion(c);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        
			        return;
				}else if(btn.equals("Fiche projet")){
					String chemin=(String)request.getParameter("chemin");
					trow=rechargerTabProjet(c,"code_programme code_action intitule objectifs_vises progression progr_BD porteur appartenance","designation",null);
					int nb=Integer.parseInt(trow[1]);
					int[] ligneCochee=p.nombreLignesCoche(request, nb);
					String message="Les fichiers ont bien ete generes";
					for(int i=0;i<ligneCochee[nb+1];i++){
						String queryInsert="SELECT * FROM projet where cocher="+ligneCochee[i];
						String[] selection=new String[32];
						try {
							//c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
							result = p.execReqLecture(c, queryInsert);
							while(result.next()){
								for(int i1=0;i1<32;i1++){
									selection[i1]=result.getString(i1+1);
								}
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
						message = genererPdf(selection, chemin_ressource, "testPDF", chemin_ressource);
						if(message.equals("erreur")){
							message = "le chemin est incorrect";
							break;
						}
					}
					
					response.setContentType("text/html"); 
					response.setCharacterEncoding("UTF-8");
					response.setHeader("Access-Control-Allow-Origin", "*");
			        //PrintWriter out = response.getWriter();
			        response.getWriter().write(message);
			        try {
						p.fermerConnexion(c);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        return;
				}else if(btn.equals("notifTache")){
					String notif="";
					try {
						notif = p.notificationTache("");
						log = new Log(userCode , userInfo , dateOpt , "Notification systeme projet en retard ");
						log.write();
					} catch (SQLException e) {
						log = new Log(userCode , userInfo , dateOpt , "Erreur de notification systeme projet en retard ");
						log.write();
						e.printStackTrace();
					}
					
					response.setContentType("text/html"); 
					response.setCharacterEncoding("UTF-8");
					response.setHeader("Access-Control-Allow-Origin", "*");
			        //PrintWriter out = response.getWriter();
			        response.getWriter().write(notif);
			        try {
						p.fermerConnexion(c);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        return;
				}else if(btn.equals("notifUser")){
					String notif="";
					try {
						notif = p.notificationUtilisateur("");
						log = new Log(userCode , userInfo , dateOpt , "Notification systeme utilisateur en attente de deblocage de compte ");
						log.write();
					} catch (SQLException e) {
						log = new Log(userCode , userInfo , dateOpt , "Erreur de notification systeme utilisateur en attente de deblocage de compte ");
						log.write();
						e.printStackTrace();
					}
					
					response.setContentType("text/html"); 
					response.setCharacterEncoding("UTF-8");
					response.setHeader("Access-Control-Allow-Origin", "*");
			        //PrintWriter out = response.getWriter();
			        response.getWriter().write(notif);
			        return;
				}else if(btn.equals("loadStep") || btn.equals("delStep") || btn.equals("editStep") || btn.equals("valider") || btn.equals("editer la tache")){;
					String result4ajax="";
					String intitul=(String) request.getParameter("projet");
					String tache=(String) request.getParameter("tache");
					//System.out.println(intitul);
					String query1="";
					String codeProjet="";
					try {
						query1="SELECT designation FROM projet WHERE intitule='"+intitul+"'";
						result=p.execReqLecture(c, query1);
						while(result.next()){
								codeProjet = result.getString("designation");
						}
						if(!codeProjet.equals("")) result = p.execReqLecture(c, "SELECT * FROM tache WHERE code_projet = '"+codeProjet+"'");
						if(btn.equals("delStep")){
							try {
								log = new Log(userCode , userInfo , dateOpt , "Suppression d'une tache du projet : "+intitul);
								log.write();
								p.execReqEcriture(c, "DELETE FROM tache WHERE designation = '"+tache+"'");
							}catch(SQLException e){
								log = new Log(userCode , userInfo , dateOpt , "Erreur de suppression d'une tache du projet : "+intitul);
								log.write();
								e.printStackTrace();
							}
						}
						if(btn.equals("editStep")){
							result=p.execReqLecture(c, "SELECT * FROM tache WHERE designation = '"+tache+"'");
							result4ajax="";
							if(result.next()){
								result4ajax+=(String)result.getString("nom_etape")+"??";
								result4ajax+=(String)result.getString("date_de_debut")+"??";
								result4ajax+=(String)result.getString("date_de_fin")+"??";
								result4ajax+=(String)result.getString("cout")+"??";
								result4ajax+=(String)result.getString("description")+"??";
								result4ajax+=(String)result.getString("quantite")+"??";
								result4ajax+=(String)result.getString("statut");
								
								response.setContentType("text/html"); 
								response.setCharacterEncoding("UTF-8");
								response.setHeader("Access-Control-Allow-Origin", "*");
						        PrintWriter out = response.getWriter();
						        out.println(result4ajax);
						        try {
									p.fermerConnexion(c);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						        return;
							}
						}
						if(btn.equals("valider") || btn.equals("editer la tache")){
							String dateDebut=(String) request.getParameter("dateDebut");
							String dateFin=(String) request.getParameter("dateFin");
							String nomEtape=p.getStringCode(request.getParameter("nomEtape"),request);
							String coutEtape=(String) request.getParameter("coutEtape");
							String descriptionEtape=p.getStringCode(request.getParameter("descriptionEtape"),request);
							String quantite=(String) request.getParameter("quantite");
							String descriptionProjet=(String) request.getParameter("description") ;
							intitul=(String) request.getParameter("projet");
							
							//System.out.println("description etape = "+descriptionEtape+" descriptionProjet = "+descriptionProjet);
							
							query1="SELECT designation FROM projet WHERE intitule='"+intitul+"'";
							codeProjet="";
							try {
								ResultSet result1=p.execReqLecture(c, query1);
								while(result1.next()){
										codeProjet = result1.getString("designation");
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							Long temps=System.currentTimeMillis();
							session = request.getSession();
							//String designationEtape=
							String designationEtape = temps.toString();
							String statut=(String) request.getParameter("statut");
							String statutString = statut;
							int stat = 0;
							if(statut.equals("demarre")){
								stat=0;
							}else if(statut.equals("en_cours")){
								stat=1;
							}else if(statut.equals("terminee")){
								stat=2;
							}else if(statut.equals("non_demarree")){
								stat=3;
							}
							if(btn.equals("valider")){
								String q="INSERT INTO tache (designation, description, cout, quantite, " +
										"date_de_debut, date_de_fin, statut, nom_etape, code_projet" +
								") " +
								"VALUES (" +
								"'"+p.getStringCode(designationEtape.replaceAll("'", "`"),request)+"', " +
								"'"+p.getStringCode(descriptionEtape.replaceAll("'", "`"),request)+"', "+coutEtape+", "+quantite+", " +
								"'"+dateDebut+"', " +
								"'"+dateFin+"', "+stat+", " +
								"'"+p.getStringCode(nomEtape.replaceAll("'", "`"),request)+"', " +
								"'"+codeProjet+"') ";
								try{
									p.execReqEcriture(c, q);
									log = new Log(userCode , userInfo , dateOpt , "Ajout d'une tache du projet : "+intitul);
									log.write();
								}catch(SQLException e){
									log = new Log(userCode , userInfo , dateOpt , "Erreur d'ajout d'une tache du projet : "+intitul);
									log.write();
									e.printStackTrace();
								}
							}else{
								System.out.println("Mise a jour d'une tache de "+request.getParameter("idTache"));
								String q="UPDATE tache SET "+
								"description = '"+p.getStringCode(descriptionEtape,request)+"', "+
								"cout = '"+coutEtape+"', "+
								"quantite = '"+quantite+"', " +
								"date_de_debut = '"+dateDebut+"', " +
								"statut = '"+stat+"', "+
								"date_de_fin = '"+dateFin+"', "+
								"nom_etape = '"+p.getStringCode(nomEtape,request)+"', " +
								"code_projet = '"+codeProjet+"' "
										+ "WHERE designation = '"+p.getStringCode(request.getParameter("idTache"),request)+"'";
								try{
									p.execReqEcriture(c, q);
									log = new Log(userCode , userInfo , dateOpt , "Mise a jour d'une tache du projet : "+intitul);
									log.write();
								}catch(SQLException e){
									log = new Log(userCode , userInfo , dateOpt , "Mise a jour d'une tache du projet : "+intitul);
									log.write();
									e.printStackTrace();
								}
							}
						}
						int nb=0;
						int statut=0;
						
						result=p.execReqLecture(c, query1);
						if(result.next()) result = p.execReqLecture(c, "select * from tache where code_projet='"+result.getString("designation")+"'");
						result4ajax="<thead><tr><th></th><th>Nom</th><th>date debut</th><th>date fin</th><th>cout</th><th>description</th><th>Operations</th></tr></thead><tbody>";
						//System.out.println("select * from tache where code_projet='"+result.getString(designation)+"'");
						while(result.next()){
							nb++;
							statut=Integer.parseInt((String)result.getString("statut"));
							Calendar dateActuelle=Calendar.getInstance();
							
							String dateToday = dateActuelle.get(Calendar.YEAR)+"-"+(dateActuelle.get(Calendar.MONTH)+1)+"-"+dateActuelle.get(Calendar.DAY_OF_MONTH);
							String dateDbut=(String)result.getString("date_de_debut");
							String dateFin=(String)result.getString("date_de_fin");
							
							
							String styleCSS1="";
							String styleCSS2="";
							//styleCSS2="style='background:green;'";
							if(statut == 0 || statut == 1){
								if(p.dateAfter(dateToday, dateFin)){
									styleCSS1="style='background:red;' title='Tache en retard'";
								}
							}else if(statut == 3){
								if(p.dateAfter(dateToday, dateDbut)){
									styleCSS2="style='background:red;' title='Tache en retard'";
								}
							}
							String StatutFullName = "";
							if(statut==0){
								StatutFullName = "Demarrée";
							}else if(statut==1){
								StatutFullName = "En cours";
							}else if(statut==2){
								StatutFullName = "terminée";
							}else if(statut==3){
								StatutFullName = "non demarrée";
							}
							result4ajax+="<tr>";
							result4ajax+="<td>"+nb+"</td>";
							result4ajax+="<td>"+(String)result.getString("nom_etape")+"\n("+StatutFullName+")</td>";
							result4ajax+="<td "+styleCSS2+">"+dateDbut+"</td>";
							result4ajax+="<td "+styleCSS1+">"+dateFin+"</td>";
							result4ajax+="<td>"+(String)result.getString("cout")+"</td>";
							result4ajax+="<td>"+(String)result.getString("description")+"</td>";
							result4ajax+="<td><a href='#' class='btnDelet' id='"+(String)result.getString("designation")+"'>" +
									"<img title='supprimer' src='././ressources/optDel.PNG'/></a>" +
									"<a href='#' class='btnEdition' id='"+(String)result.getString("designation")+"'>" +
									"<img title='Modifier' src='././ressources/optEdit.png'/></a></td>";
							result4ajax+="</tr>";
						}
						result4ajax+="</tbody>";
					}catch (SQLException e) {
						response.setContentType("text/html"); 
						response.setCharacterEncoding("UTF-8");
						response.setHeader("Access-Control-Allow-Origin", "*");
				        PrintWriter out = response.getWriter();
				        out.println("Parametre incorrect");
				        e.printStackTrace();
				        try {
							p.fermerConnexion(c);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				        return;
					}
					
					response.setContentType("text/html"); 
					response.setCharacterEncoding("UTF-8");
					response.setHeader("Access-Control-Allow-Origin", "*");
			        //PrintWriter out = response.getWriter();
			        response.getWriter().write(result4ajax);
			        //System.out.println("fin de l'operation");
			        try {
						p.fermerConnexion(c);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        return;
				}else if(btn.equals("reload")){;
				
					trow=rechargerTabProjet(c,"code_programme code_action intitule objectifs_vises progression progr_BD porteur appartenance","designation",null);
					
					response.setContentType("text/html"); 
					response.setCharacterEncoding("UTF-8");
					response.setHeader("Access-Control-Allow-Origin", "*");
			        //PrintWriter out = response.getWriter();
					String resultat=trow[0]+"??"+trow[1];
			        response.getWriter().write(resultat);
			        log = new Log(userCode , userInfo , dateOpt , "Rechargement de la liste des projets ");
					log.write();
			        try {
						p.fermerConnexion(c);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        return;
				}else if(btn.equals("filtrer")){;
					intitule=(String) request.getParameter("intitule");
					String titulaire=(String) request.getParameter("porteur");
					String progr=(String) request.getParameter("programme");
		
					if(!intitule.equals("")) intitule="intitule LIKE ('"+intitule+"%')";
					if(!titulaire.equals("")) titulaire="code_personne LIKE ('"+titulaire+"%')";
					if(!progr.equals("")) progr="code_action LIKE ('"+progr+"%')";
					String[] critere={intitule,titulaire,progr};
					trow=rechargerTabProjet(c,"code_programme code_action intitule objectifs_vises progression progr_BD porteur appartenance","designation",critere);
					
					response.setContentType("text/html"); 
					response.setCharacterEncoding("UTF-8");
					response.setHeader("Access-Control-Allow-Origin", "*");
			        //PrintWriter out = response.getWriter();
					String resultat=trow[0]+"??"+trow[1];
			        response.getWriter().write(resultat);
			        try {
						p.fermerConnexion(c);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        return;
			}else if(btn.equals("filtrerAction")){
				String action=(String)request.getParameter("action");
				intitule=(String)request.getParameter("intitule");
				porteur=(String)request.getParameter("porteur");
				
				if(!intitule.equals("")) intitule="intitule LIKE ('"+intitule+"%')";
				if(!action.equals("--All--")) action="code_action LIKE ('"+action+"%')";
				else action="";
					
				if(!porteur.equals(""))  porteur="code_personne LIKE ('"+porteur+"%')";
				
				String[] critere={intitule,action,porteur};
				String where = critere[0] +" AND "+ critere[1] +" AND "+ critere[2];
				trow=rechargerTabProjet(c,"code_programme code_action intitule objectifs_vises progression progr_BD porteur appartenance","designation",critere);
				log = new Log(userCode , userInfo , dateOpt , "Filtre les projets selon le critere : "+where);
				log.write();
				
				response.setContentType("text/html"); 
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Access-Control-Allow-Origin", "*");
		        //PrintWriter out = response.getWriter();
		        response.getWriter().write(trow[1]+"??"+trow[0]);
		        try {
					p.fermerConnexion(c);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        return;
				
			}else if(btn.equals("filtrerProgr")){
				String progr=(String) request.getParameter("programme");
				//si aucune action n'a ete selectionne, on initialise la var action a ""
				String action=(String) request.getParameter("action")==null?"--All--":(String) request.getParameter("programme");
				programme=progr;
				System.out.println(progr);
				String titulaire=(String) request.getParameter("porteur");
				intitule=(String) request.getParameter("intitule");
				String query="SELECT designation FROM action where code_programme='"+request.getParameter("programme")+"'";
				String query2="SELECT * FROM projet where code_programme='"+request.getParameter("programme")+"'";
				
				String list="<option value='All'>--All--</option>";
				String[] act=new String[100];
				int i=0;
				try {
					c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
					result = p.execReqLecture(c, query);
					
					while(result.next()){
						list+="<option value='"+result.getString("designation")+"'>"+result.getString("designation")+"</option>";
						act[i]=result.getString("designation");
						i++;
					}
					//for(int i=0;i<30;i++) System.out.println(selection[i]);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				String critereProgramme="";
				String[] critere=null;
				String where="";
				if(progr.equals("--All--") && action.equals("--All--") && intitule.equals("") && titulaire.equals("")){
					critere = null;
					where = null;
				}else if(!progr.equals("--All--") && action.equals("--All--")){
						critereProgramme="((code_action = '"+act[0]+"' AND code_programme = '"+progr+"')";
						for(int j=1;j<i;j++){
							critereProgramme+=" OR (code_action  = '"+act[j]+"' AND code_programme = '"+progr+"')";
						}
						critereProgramme+=")";
						if(!intitule.equals("")) intitule="intitule LIKE ('"+intitule+"%')";
						if(!titulaire.equals("")) titulaire="code_personne LIKE ('"+titulaire+"%')";
						
						critere = new String[3];
						critere[0]=intitule;
						critere[1]=titulaire;
						critere[2]=critereProgramme;
						where = (critere[0] +" AND "+ critere[1] +" AND "+ critere[2]).replaceAll("'", "");;
				}else if(!progr.equals("--All--") && !action.equals("--All--")){
					action="code_action == '"+action+"'";
					if(!intitule.equals("")) intitule="intitule LIKE ('"+intitule+"%')";
					if(!titulaire.equals("")) titulaire="code_personne LIKE ('"+titulaire+"%')";
					critereProgramme="code_programme='"+progr+"'";
					
					critere = new String[4];
					critere[0]=intitule;
					critere[1]=titulaire;
					critere[2]=critereProgramme;
					critere[3]=action;
					where = (critere[0] +" AND "+ critere[1] +" AND "+ critere[2] +" AND "+ critere[3]).replaceAll("'", "");
				}
				System.out.println(where);
				trow=rechargerTabProjet(c,"code_programme code_action intitule objectifs_vises progression progr_BD porteur appartenance","designation",critere);
				log = new Log(userCode , userInfo , dateOpt , "Filtre les projets selon un critere : "+where);
				log.write();
				
				response.setContentType("text/html"); 
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Access-Control-Allow-Origin", "*");
		        //PrintWriter out = response.getWriter();
		        response.getWriter().write(list+"??"+trow[0]);
		        try {
					p.fermerConnexion(c);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        return;
			}else if(btn.equals("details")){
				//String intitule=(String) request.getParameter("intitule");
				trow=rechargerTabProjet(c,"code_programme code_action intitule objectifs_vises progression progr_BD porteur appartenance","designation",null);
				
				Integer nb=Integer.parseInt(trow[1]);//nombre de projets enregistres
				int[] ligneCoch=p.nombreLignesCoche(request, nb);
				//Integer.parseInt((String) request.getParameter("nb"));
				//for(int k=0;k<ligneCoch.length;k++)
					//System.out.print(ligneCoch[k]+",");
				//System.out.println("");
				for(int i=0;i<ligneCoch[nb+1];i++){
					//ligne = Integer.parseInt((String) request.getParameter("ligne"+1));
					//System.out.println(ligneCoch[i]);
					String query="SELECT * FROM projet where cocher="+ligneCoch[i];
					String[] selection=new String[34];
					try {
						c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
						result = p.execReqLecture(c, query);
						while(result.next()){
							for(int i1=0;i1<32;i1++){
								selection[i1]=result.getString(i1+1);
							}
						}
						String chemin = genererPdf(selection, pathFiles,"testPDF",chemin_ressource);
						chemin = chemin.replace('\\','/');
						//for(int i=0;i<30;i++) System.out.println(selection[i]);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				//System.out.println(chemin);
				response.setContentType("text/html"); 
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Access-Control-Allow-Origin", "*");
		        //En se servira de la suite plus tard
				//System.out.println("le chemin est = "+chemin);
		        //response.getWriter().write(chemin);
				try {
					p.fermerConnexion(c);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        return;
			}
			try {
				c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			trow=rechargerTabProjet(c,"code_programme code_action intitule objectifs_vises progression progr_BD porteur appartenance","designation",null);
			//System.out.println(trow[0]);
			request.setAttribute("tr", trow[0]);
			request.setAttribute("nb", trow[1]);
			String profil=(String)session.getAttribute("profil");
			//System.out.println(profil);
			String[] acces=profil.split("__");
			request.setAttribute("edit", acces[9]);
			request.setAttribute("add", acces[10]);
			request.setAttribute("genererFichPr", acces[11]);
			request.setAttribute("etapeEdit", acces[12]);
			request.setAttribute("voirEtapes", acces[13]);
			request.setAttribute("suppr", acces[14]);
			request.setAttribute("budget", acces[15]);
			request.setAttribute("top1", acces[35]);
			request.setAttribute("top2", acces[36]);
			request.setAttribute("top3", acces[37]);
			request.setAttribute("top4", acces[38]);
			request.setAttribute("top5", acces[39]);
			request.setAttribute("top6", acces[40]);
			try {
				p.fermerConnexion(c);
				this.getServletContext().getRequestDispatcher("/WEB-INF/admin/suivi_projet.jsp").forward(request,response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				this.getServletContext().getRequestDispatcher("/WEB-INF/admin/suivi_projet.jsp").forward(request,response);
				e.printStackTrace();
			}
		}
	}
	
	public String genererPdf(String[] champs, String pDestinationFolder,String pTargetFileNamePrefix,String destination){
		FileOutputStream fos = null;
		FontFactory.registerDirectories();
		//System.out.println("promoteur = "+champs[27]);
		String[] promoteur = champs[27].split("/");
		String[] justification = champs[12].split("/");
		String[] objectif = champs[11].split("/");
		String[] ancrage = champs[13].split("/");
		
		Font fuente = FontFactory.getFont("Times New Roman");
		/** Times Roman 18 Bold */
		Font CATFONT = new Font(Font.getFamily("Times New Roman"), 18, Font.BOLD);
		 
		/** Times Roman 12 Normal */
		Font REDFONT = new Font(Font.getFamily("Times New Roman"), 12, Font.NORMAL);
		 
		/** Times Roman 16 Bold */
		Font SUBFONT = new Font(Font.getFamily("Times New Roman"), 16, Font.BOLD);
		 
		/** Times Roman 12 Bold */
		Font SMALLBOLD = new Font(Font.getFamily("Times New Roman"), 12, Font.BOLD);
		
		/** Times Roman 10 Bold */
		Font SMALL_10 = new Font(Font.getFamily("Times New Roman"), 10, Font.NORMAL);
		String chemin="";
		try {
			//System.out.println(System.getProperties());
			//System.setProperty("user.dir", "C:/eclipse-jee-mars-1-win32/eclipse/workspace/suite_project/WebContent/ressources/projet");
			System.out.println("le chemin relatif est"+"/"+champs[1]);
			
			Document fiche_projet = creerDocument("/suite_project/projet/"+champs[1]+"/"+champs[31]+champs[0]+".pdf");
			
			addMetaData(fiche_projet);
			Paragraph paragraphe = new Paragraph();
			Paragraph paragraphe1 = new Paragraph();
			Image image = Image.getInstance("/suite_project/entete_fiche_projet.PNG");
			image.scaleToFit(fiche_projet.getPageSize().getWidth(), fiche_projet.getPageSize().getHeight());
			image.setAlignment(Image.ALIGN_CENTER);
			fiche_projet.add(image);
			
			// Creation d'une PdfPTable avec 2 colonnes
			float[] columnWidths = {2f,5f};
		    final PdfPTable table = new PdfPTable(columnWidths);
		    table.setWidthPercentage(100f);
		    
		    
		    // Creation d'une PdfPCell avec un paragraphe
		    final PdfPCell cell = new PdfPCell(new Paragraph("A. INFORMATIONS GENERALES",SMALLBOLD));
		    
		    // Changement du colspan de la PdfCell
		    cell.setColspan(2);
		 
		    // Ajout de la PdfCell custom à la PdfPTable
		    table.addCell(cell);
		 
		    //insert column headings 1 (intitule)
		    insertCell(table, "A.1 Intitulé du Projet :", Element.ALIGN_RIGHT, 1, SMALLBOLD);
		    insertCell(table, champs[2], Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, "A.2 Description : ", Element.ALIGN_RIGHT, 1, SMALLBOLD);
		    insertCell(table, champs[10], Element.ALIGN_CENTER, 1, SMALL_10);
		    
		    //insert an empty row
		    insertCell(table, "A.2 Promoteur du Projet", Element.ALIGN_LEFT, 4, SMALLBOLD);
		    insertCell(table, "Ministère :", Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, promoteur[0], Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, "Secteur :", Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, promoteur[1], Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, "Sous-secteur :", Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, promoteur[2], Element.ALIGN_CENTER, 1, SMALL_10);
		    
		    //insert an empty row
		    insertCell(table, "A.3 Justification du Projet ", Element.ALIGN_LEFT, 4, SMALLBOLD);
		    insertCell(table, "Justificatifs par rapport aux besoins identifiés ", Element.ALIGN_LEFT, 4, SMALLBOLD);
		    insertCell(table, "Besoins identifiés et à satisfaire par le projet :", Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, justification[0], Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, "Situation actuelle :", Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, justification[1], Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, "Situation desiree :", Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, justification[2], Element.ALIGN_CENTER, 1, SMALL_10);
		    
		    //insert an empty row
		    insertCell(table, "Objectifs du projet ", Element.ALIGN_LEFT, 4, SMALLBOLD);
		    insertCell(table, "Objectif global du projet :", Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, objectif[0], Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, "Situation actuelle :", Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, objectif[1], Element.ALIGN_CENTER, 1, SMALL_10);
		    
		    //insert an empty row
		    insertCell(table, "Ancrage du projet à la stratégie nationale, sectorielle, ministérielle ou thématique ", Element.ALIGN_LEFT, 4, SMALLBOLD);
		    insertCell(table, "Objectif Stratégique Global :", Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, ancrage[0], Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, "Objectif Stratégique Spécifique :", Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, ancrage[1], Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, "Programme Concerné  :", Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, champs[4], Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, "Variable d’action :", Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, "", Element.ALIGN_CENTER, 1, SMALL_10);
		    
		    //insert an empty row
		    insertCell(table, "A.4 Population cible du projet : "+champs[28], Element.ALIGN_LEFT, 4, SMALLBOLD);
		    
		    //insert an empty row
		    insertCell(table, "A.5 Extrant escompté/Produit attendu : \n"+champs[17], Element.ALIGN_LEFT, 4, SMALLBOLD);
		    
		    //insert an empty row
		    insertCell(table, "A.6 Typologie du projet : \n"+champs[19], Element.ALIGN_LEFT, 4, SMALLBOLD);
		    
		    //insert an empty row
		    insertCell(table, "A.7 Montage institutionnel : \n", Element.ALIGN_LEFT, 4, SMALLBOLD);
		    insertCell(table, "Maitre d'ouvrage :", Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, champs[6], Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, "Maitre d'oeuvre :", Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, champs[5], Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, "Partenaires  :", Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, champs[7], Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, "Service responsable :", Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, champs[8], Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, "Agence d'execution :", Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, champs[9], Element.ALIGN_CENTER, 1, SMALL_10);
		    
		    //insert an empty row
		    insertCell(table, "A.7 Montage financier : \n", Element.ALIGN_LEFT, 4, SMALLBOLD);
		    insertCell(table, "Cout :", Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, champs[14], Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, "Nature du financement :", Element.ALIGN_CENTER, 1, SMALL_10);
		    insertCell(table, champs[15], Element.ALIGN_CENTER, 1, SMALL_10);
		    
		    paragraphe.add(table);
		    fiche_projet.add(paragraphe);
		 //for(int i=0;i<25;i++){
			 //doc.add(new Paragraph(champs[i]));
			 //doc.newPage();
		 //}
		 ////doc.add(new Paragraph(new Date().toString()));
		 fiche_projet.close();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
		 
		 if (fos != null) {
		 try {
		  fos.close();
		 }
		 catch (Exception ex) {
			  ex.printStackTrace();
			 }
		 }
		}
		//System.load("C:/eclipse/workspace/suite_project/WebContent/ressources/pdf");
		/*String copierVers=destination+"/"+champs[31]+champs[0]+".pdf";
		File source=new File("/pdf/suite_project/"+champs[1]+"/"+champs[31]+champs[0]+".pdf");
		if(pDestinationFolder.charAt(pDestinationFolder.length()-1) != '/')
			pDestinationFolder+="/";
		File dest=new File(pDestinationFolder+"/"+champs[1]+champs[31]+champs[0]+".pdf");
		boolean test = CopierFichier(source, source);
		if(!test) return "erreur";*/
		return pDestinationFolder+champs[31]+champs[0]+".pdf";
	}
	
	/**
	 * Crée un Document vide.
	 *
	 * @param file Chemin du fichier à créer.
	 * @return Document s'il n'y a pas eu d'erreur.
	 * @throws IOException s'il y a eu une erreur avec le nom de fichier fourni.
	 * @throws DocumentException s'il y a eu une erreur côté iText.
	 */
	private Document creerDocument(String file) throws DocumentException, IOException {
	    Document document = new Document();
	 
	    PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file));
	    //pdfWriter.setPageEvent((PdfPageEvent) this);
	    // Préférence de lecture : 2 pages en colonne.
	    pdfWriter.setViewerPreferences(PdfWriter.PageLayoutTwoColumnLeft);
	    document.open();
	 
	    return document;
	}
	
	/**
	 * Ajout de données de type Metadata au document.
	 *
	 * @param document Document auquel il faut rajouter les metadatas.
	 */
	private void addMetaData(Document document) {
	    document.addTitle("Fiche projet autogénérée (MINT)");
	    document.addSubject("Gestion des numerique projets");
	    //document.addKeywords("Java, PDF, iText");
	    document.addAuthor("Ministere des Transport (SuiteProject)");
	    document.addCreator("Ministere des Transport (SuiteProject)");
	}
	
	/**
	 * Ajoute une ligne vide number fois dans le Paragraph passé en paramètre.
	 *
	 * @param paragraph A remplir avec les lignes vides.
	 * @param number Nombre de lignes vides à ajouter.
	 */
	private void ajouterLigneVide(Paragraph paragraph, int number) {
	    for (int i = 0; i < number; i++) {
	        paragraph.add(new Paragraph(" "));
	    }
	}
	
	/**
	 * Crée un tableau de 3 colonnes et l'ajoute à la Section passée en
	 * paramètre.
	 * 
	 * @param subCatPart Section à remplir avec le tableau.
	 * @param nbCols nombre de colonnes du tableau
	 * @throws BadElementException Si une erreur survient lors de l'ajout d'un
	 *             élément dans la PdfPTable.
	 */
	private void createTable(Section subCatPart,int nbCols) throws BadElementException {
	    // Creation d'une PdfPTable avec 3 colonnes
	    final PdfPTable table = new PdfPTable(nbCols);
	 
	    // Creation d'une PdfPCell avec un paragraphe
	    final PdfPCell cell = new PdfPCell(new Paragraph("header with colspan "+nbCols));
	 
	    // Changement du colspan de la PdfCell
	    cell.setColspan(nbCols);
	 
	    // Ajout de la PdfCell custom à la PdfPTable
	    table.addCell(cell);
	 
	    // Ajout d'objets String à la PdfPTable
	    table.addCell("1.1");
	    table.addCell("2.1");
	    table.addCell("3.1");
	    table.addCell("1.2");
	    table.addCell("2.2");
	    table.addCell("3.2");
	 
	    // Ajout d'un espace entre la PdfPTable et l'élément précédent.
	    //table.setSpacingBefore(15f);
	 
	    subCatPart.add(table);
	}
	
	/**
	 * Crée une liste et l'ajoute dans la Section passée en paramètre.
	 * 
	 * @param subCatPart Section à remplir avec la liste.
	 */
	private void createList(Section subCatPart) {
	    final List list = new List(true, false, 10);
	    list.add(new ListItem("Premier point"));
	    list.add(new ListItem("Deuxième point"));
	    list.add(new ListItem("Troisième point"));
	    subCatPart.add(list);
	}

	private void insertCell(PdfPTable table, String text, int align, int colspan, Font font){
		   
		  //create a new cell with the specified Text and Font
		  PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
		  //set the cell alignment
		  cell.setHorizontalAlignment(align);
		  //set the cell column span in case you want to merge two or more cells
		  cell.setColspan(colspan);
		  //in case there is no text and you wan to create an empty row
		  if(text.trim().equalsIgnoreCase("")){
		   cell.setMinimumHeight(10f);
		  }
		  //add the call to the table
		  table.addCell(cell);	   
	}

	private boolean CopierFichier(File source, File Destination){
		boolean resultat=false;
		FileInputStream filesource=null;
		FileOutputStream fileDestination=null;
		try{
			filesource=new FileInputStream(source);
			fileDestination=new FileOutputStream(Destination);
			byte buffer[]=new byte[512*1024];
			int nblecture;
			while((nblecture=filesource.read(buffer))!=-1){
				fileDestination.write(buffer,0,nblecture);
			}
			resultat=true;
		}catch(FileNotFoundException nf){
			nf.printStackTrace();
		}catch(IOException io){
			io.printStackTrace();
		}finally{
			try{
				filesource.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			try{
				fileDestination.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return resultat;
	}
	
	public String[] rechargerTabProjet(java.sql.Connection c, String colonnes,String pk,String critere[]){
		String[] retour={"","",""};
		ResultSet listeProjet=null;
		String[] programmes=new String[1000];
		String[] porteurProj=new String[1000];
		int[] listeProgressions=new int[1000];
		String requete;
		Integer comptProj=0;
		//colonnes est une liste de colonnes separee par des espaces
		//de la forme "col1 col2 ... colsN"
		String[] cols=colonnes.split(" ");
		int taille = cols.length;
		String colonnesUtiles="designation, code_personne, "+cols[0]+", "+cols[1]+", "+cols[2]+", "+cols[3]+", "+cols[4]+", "+cols[6]+", "+cols[7];
		
		String where=p.where(critere);
		if(critere != null){
			requete = "SELECT path, cocher, "+colonnesUtiles+" FROM projet "+where+" ORDER BY code_programme,code_action";
		}else{
			requete = "SELECT path,  cocher, "+colonnesUtiles+" FROM projet ORDER BY code_programme,code_action";
		}
		
		try{
			listeProjet = p.execReqLecture(c, requete);
			while(listeProjet.next()){
				
				//result = p.execReqLecture(c, "SELECT code_programme FROM action WHERE designation = '"+listeProjet.getString("code_action")+"'");
				//while(result.next()){
					//programmes[comptProj]=result.getString("code_programme");
				//}
				
				listeProgressions[comptProj]=progressionProjet(c,listeProjet.getString("designation"));
				comptProj++;
			}
			String requeteTache="Select * from tache where code_projet = ";
			ResultSet listeTache=null;
			//listeProjet.beforeFirst();
			listeProjet = p.execReqLecture(c, requete);
			//System.out.println("requete = "+requete);
			comptProj=0;
			//if(programmes[comptProj] != null){
			//retour[0]="<tbody id='tbody'></tbody>";
				String style="";
				boolean alert=false;
				while(listeProjet.next()){
					ResultSet tmp=p.execReqLecture(c, "SELECT COUNT (*) AS nbLignes FROM (SELECT * FROM TACHE WHERE CODE_PROJET='"+listeProjet.getString("designation")+"') AS t");
					 int nbLignes=0;
				     tmp.next();
				     nbLignes = tmp.getInt("nbLignes");
					
					if(p.alertRetartProjet(listeProjet.getString("designation"))) style="style='background:#DEF3CA;'";
					//if(nbLignes!=0){ retour[0] += "<tr  class='ligne' "+style+" id='"+comptProj+"'><td class='corpsprogrcheckBox'>" +
		  			//"<input type='checkbox' id='check"+comptProj+"' name='"+comptProj+"' /><label for='check"+comptProj+"'></label><a href='#' id='"+listeProjet.getString("designation")+"'>+</a></td>";
					//String q="UPDATE projet SET cocher = "+comptProj+" WHERE designation = '"+listeProjet.getString(pk)+"';";
					 //p.execReqEcriture(c , q);
					 //}else{
						retour[0] += "<tr  class='ligne' "+style+" id='"+comptProj+"'><td class='corpsprogrcheckBox'>" +
					  			"<input type='checkbox' code_projet='"+listeProjet.getString("designation")+"' path_tdr='"+listeProjet.getString("path")+"' id='check"+comptProj+"' name='"+comptProj+"' /><label for='check"+comptProj+"'></label></td>";
								String q="UPDATE projet SET cocher = "+comptProj+" WHERE designation = '"+listeProjet.getString(pk)+"';";
								 p.execReqEcriture(c , q);
					//}
					 retour[0]+="<td id='intitule' align='center'><a href='/suite_project/details?type=projet&&valeur="+listeProjet.getString("intitule")+"'>"+listeProjet.getString("intitule")+"</a></td>";
					 retour[0]+="<td id='action'>"+listeProjet.getString("code_action")+"</td>";
					 retour[0]+="<td id='programme'>"+listeProjet.getString("code_programme")+"</td>";
					 retour[0]+="<td id='objectifs'>"+listeProjet.getString("objectifs_vises")+"</td>";
					 int percent=Integer.parseInt(listeProjet.getString("progression"));
					 retour[0]+="<td><progress id='progressMiniature' max='100' value='"+percent+"'></progress>&nbsp;"+percent+"%</td>";
					 retour[0]+="<td><progress id='progressMiniature2' max='100' value='"+listeProgressions[comptProj]+"'></progress>&nbsp;"+listeProgressions[comptProj]+"%</td>";
					 retour[0]+="<td id='porteur'>"+listeProjet.getString("porteur")+"</td>";
					 retour[0]+="<td id='tutelle'>"+listeProjet.getString("appartenance")+"</td>";
					 retour[0]+="<td><a href='#operation' class='btnDelet' id='"+(String)listeProjet.getString("cocher")+"'>" +
								"<img title='supprimer' src='././ressources/optDel.PNG'/></a>" +
								"<a href='/suite_project/projet?projet="+(String)listeProjet.getString("cocher")+"&&btn=editerUnique' class='btnEdition' id='"+(String)listeProjet.getString("cocher")+"'>" +
								"<img title='Modifier' src='././ressources/optEdit.png'/></a>&nbsp;"+
								"<a href='#operation' class='btnDossier' id='"+(String)listeProjet.getString("cocher")+"'>" +
								"<img title='Dossier du projet' src='././ressources/dossier.png'/></a>"
								+ "</td>";
					 
				     //System.out.println("nb taches = "+nbLignes);
				     //if(nbLignes != 0){
				    	 //retour[0]+="<tr><td></td><td colspan='9'>";
				    	 //retour[0]+="<table width='100%' style='display:none;' class='"+listeProjet.getString("designation")+"'>"
				    	 	//	+ "<tr><td>No</td><td>Nom de l'etape</td><td>debut</td><td>fin</td><td>Cout</td><td>description</td></tr>";
				    	 //listeTache=p.execReqLecture(c, "SELECT * FROM TACHE WHERE CODE_PROJET='"+listeProjet.getString("designation")+"'");
				    	 //int nb=0;
				    	 /*while(listeTache.next()){
				    		 	int statut=Integer.parseInt((String)listeTache.getString("statut"));
								Calendar dateActuelle=Calendar.getInstance();
								
								String dateToday = dateActuelle.get(Calendar.YEAR)+"-"+(dateActuelle.get(Calendar.MONTH)+1)+"-"+dateActuelle.get(Calendar.DAY_OF_MONTH);
								String dateDbut=(String)listeTache.getString("date_de_debut");
								String dateFin=(String)listeTache.getString("date_de_fin");
								
								
								String styleCSS1="";
								String styleCSS2="";
								//styleCSS2="style='background:green;'";
								if(statut == 0 || statut == 1){
									if(p.dateAfter(dateToday, dateFin)){
										styleCSS1="style='background:red;' title='Tache en retard'";
									}
								}else if(statut == 3){
									if(p.dateAfter(dateToday, dateDbut)){
										styleCSS2="style='background:red;' title='Tache en retard'";
									}
								}
				    		 	retour[0]+="<tr style='border:1px solid white;'>";
				    		 	retour[0]+="<td>"+nb+"</td>";
				    		 	retour[0]+="<td>"+(String)listeTache.getString("nom_etape")+"</td>";
				    		 	retour[0]+="<td>"+dateDbut+"</td>";
				    		 	retour[0]+="<td>"+dateFin+"</td>";
				    		 	retour[0]+="<td>"+(String)listeTache.getString("cout")+"</td>";
				    		 	retour[0]+="<td>"+(String)listeTache.getString("description")+"</td>";
				    		 	retour[0]+="</tr>";
				    		 	nb++;
				    	 }*/
				    	 //retour[0]+="</table>";
				    	 //retour[0]+="</td>"; 
				    	 //retour[0]+="</tr>"; 
				     //}
					 //retour[0]+="<tr><td></td><td colspan='9'></td>";
					 //retour[0]+="</tr>";
					 style="";
					 comptProj++;
				}
			//}else{
				
			//}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		retour[1]=comptProj.toString();
		return retour;
	}
	
	int progressionProjet(java.sql.Connection c,String code_projet){
		int retour=0;
		int nombreEtape=1;
		
		try {
			result = p.execReqLecture(c, "SELECT statut FROM tache WHERE code_projet='"+code_projet+"'");
			
			while(result.next()) nombreEtape=nombreEtape+1;
			nombreEtape--;
			//System.out.println("nnombre de taches = "+nombreEtape);
			result = p.execReqLecture(c, "SELECT statut FROM tache WHERE code_projet='"+code_projet+"'");
			
			while(result.next()){
				if(result.getString("statut").equals("2")) retour+=2;//etape terminee
				else if(result.getString("statut").equals("1")) retour+=1;//etape encours
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		retour = (nombreEtape==0)?0:(100*retour) / (2*nombreEtape);
		//System.out.println("taux de realisation = "+retour);
		return retour; //ceci est le taux de realisation du projet
	}

}
