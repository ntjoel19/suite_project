package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojo.Action;
import pojo.Information;
import pojo.Log;
import pojo.Personne;
import pojo.Programme;
import pojo.Projet;

import dao.Persistance;

/**
 * Servlet implementation class index
 */
public class index extends HttpServlet {
	private static final String LISTE_PROGRAMMES = "SELECT designation, intitule, objectifs, indicateur FROM Programme ORDER BY designation" ;
	private static final String LISTE_ACTIONS = "SELECT code_programme, designation, intitule, objectif, indicateur FROM Action ORDER BY designation" ;
	private static final String LISTE_PROJETS = "SELECT * FROM Projet ORDER BY intitule";
	private static final String LISTE_PROJETS_TEL = "SELECT designation, intitule, objectifs_vises, extrants_escomptes, cout_du_projet, path, path_tdr FROM Projet";
	private static final String LISTE_PROGRAMMES_TEL = "SELECT designation,intitule,objectifs,indicateur,path FROM Programme" ;
	private static final String LISTE_ACTIONS_TEL = "SELECT designation,intitule,objectif,indicateur,path FROM Action" ;
	private static final String LISTE_PUBLICATION = "SELECT * FROM information ORDER BY date_de_publication" ;
	
	public static String code_personne;
	Personne currentUser=new Personne();
	Log log=new Log();
	private HttpSession session;
	//java.sql.Connection c=null;
	//ResultSet result=null;
	String query=null;
	//String[] trow=null;//la partie dynamique du tableau  
	//String erreur="<label align='center' id='welcomeMsg'>Bienvenu dans Suite Project</label>";
	admin servletAdmin=new admin();
	private static final long serialVersionUID = 1L;
	Persistance p=new Persistance();
	java.sql.Connection c=null;
	ResultSet result=null;
	String[] trow=null;//la partie dynamique du tableau 
	String erreur="<label align='center' id='welcomeMsg'>Bienvenu dans Suite Project</label>";
	public static int nbUser=0;
	public static String connexion="nav";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public index() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		p.encodage(request);
		String attribut = request.getQueryString();
		System.out.println("attribut = "+attribut);
		if(attribut == null)
			attribut="nav";
			//pageAppelant=(String)request.getAttribute("connexion");
		String[] attr2=attribut.split("=");
		String attr="";
		if(attr2.length>1) attr=attr2[1];
		if(attribut.equals("user") || attr.equals("decnx")){
			
			erreur="<label align='center' id='welcomeMsg'>Bienvenu dans Suite Project</label>";
			request.setAttribute("erreurPassw", erreur);
			//System.out.println("connecte depuis "+session.getLastAccessedTime());
			
			String deconex=(String)request.getParameter("deconnexion");
			System.out.println(deconex);
			if("decnx".equals(deconex)){
				
				session = request.getSession(true);

				String userCode = (String) session.getAttribute("code_pers");
				String userInfo = "Nom = " + (String) session.getAttribute("nom") + " IP = " +request.getRemoteHost();
				String dateOpt = (String)p.dateActuelle();
				
				log = new Log(userCode , userInfo , dateOpt , "Deconnexion");
				log.write();
				
				session.setAttribute("code_pers", "");
				session.setAttribute("nom", "");
				session.setAttribute("email", "");
				session.setAttribute("contacts", "");
				session.setAttribute("code_role", "");
				session.invalidate();
				nbUser--;
				
				System.out.println("il y a "+nbUser+" utilisateurs connectes");
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);	
		}else{
			Integer i=0;
			session = request.getSession(true);
			String nomUser = (String)session.getAttribute("nom")==null?"VISITEUR":(String)session.getAttribute("nom");
			String userCode = (String)session.getAttribute("code_pers")==null?"0":(String)session.getAttribute("code_pers");
			String userInfo = "Nom = VISITEUR IP = " +request.getRemoteHost();
			String dateOpt = (String)p.dateActuelle();
			
			log = new Log(userCode , userInfo , dateOpt , "Connection a l`interface utilisateur");
			log.write();
			String query1="SELECT designation, intitule FROM programme";
			String dataList1="";
			String query2="SELECT designation, intitule FROM action";
			String dataList2="";
			String query3="SELECT titre FROM information";
			String dataList3="";
			String query4="SELECT designation FROM projet";
			String dataList4="";
			String codeR="";
			//String requete4="SELECT acces_main_acces FROM  role WHERE  code_role = '";
			String requete4="SELECT profil FROM  personne WHERE  code_personne = '";
			p=new Persistance();
			try {
				c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				
				result=p.execReqLecture(c, query1);
				while(result.next()){
					dataList1+="<option value='"+result.getString("designation")+"'>"+result.getString("designation")+"</option>";
				}
				result=p.execReqLecture(c, query2);
				while(result.next()){
					dataList2+="<option value='"+result.getString("designation")+"'>"+result.getString("designation")+"</option>";
				}
				result=p.execReqLecture(c, query3);
				while(result.next()){
					dataList3+="<option value='"+result.getString("titre")+"'>"+result.getString("titre")+"</option>";
				}
				result=p.execReqLecture(c, query4);
				while(result.next()){
					dataList4+="<option value='"+result.getString("designation")+"'>"+result.getString("designation")+"</option>";
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("dataList1", dataList1);
			request.setAttribute("dataList2", dataList2);
			request.setAttribute("dataList3", dataList3);
			request.setAttribute("dataList4", dataList4);
			
			try {
				result = p.execReqLecture(c,LISTE_PUBLICATION);
				String informations1="";
				String informations2="";
				String informations3="";
				String informations4="";
				String informations5="";
				String informations6="";
				String informations7="";
				
				while(result.next()){
					String titre_tronque = "";
					titre_tronque = result.getString("titre").split(" ").length>1?result.getString("titre").split(" ")[0]+" "+result.getString("titre").split(" ")[1]+"...":result.getString("titre").split(" ")[0];
					System.out.println("contenu ="+titre_tronque);
					informations1+="<ul><li align='center'><img width='100px height='50px' src='/suite_project/LoadExternalRessources?ImageName="+result.getString("path")+"'/></br><span>"+titre_tronque+"</span></li>";
					informations2+="<td><span>"+titre_tronque+" ...</span></td>";
					informations6+=result.getString("titre")+"---";
					informations3+=result.getString("contenu_publication")+"---";
					informations4+=result.getString("auteur")+"---";
					informations5+=result.getString("date_de_publication")+"---";
					informations7+="/suite_project/LoadExternalRessources?ImageName="+result.getString("path")+"---";
				}
				request.setAttribute("lPublicationImg", informations1);
				request.setAttribute("lPublicationLink", informations2);
				request.setAttribute("lPublicationContent", informations3);
				request.setAttribute("lPublicationAutor", informations4);
				request.setAttribute("lPublicationDate", informations5);
				request.setAttribute("lPublicationTitle", informations6);
				request.setAttribute("lPublicationSlideImg", informations7);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				result = p.execReqLecture(c,LISTE_PROGRAMMES);
				List<Programme>  lp = new ArrayList<Programme>();
				
				while(result.next()){
					Programme progTemp = new Programme();
					progTemp.setDesignation(result.getString("designation"));
					progTemp.setIntitule(result.getString("intitule"));
					progTemp.setObjectifs(result.getString("objectifs"));
					progTemp.setIndicateurs(result.getString("indicateur"));
					lp.add(progTemp);
				}
				request.setAttribute("lProg", lp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				result = p.execReqLecture(c,LISTE_PROGRAMMES);
				List<Programme>  lp = new ArrayList<Programme>();
					
				while(result.next()){
					Programme progTemp = new Programme();
					progTemp.setDesignation(result.getString("designation"));
					progTemp.setIntitule(result.getString("intitule"));
					progTemp.setObjectifs(result.getString("objectifs"));
					progTemp.setIndicateurs(result.getString("indicateur"));
					lp.add(progTemp);
				}
				request.setAttribute("lProg", lp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				result = p.execReqLecture(c,LISTE_ACTIONS);
				List<Action> la = new ArrayList<Action>();
				
				while(result.next()){
					Action actTemp = new Action();
					actTemp.setCode_programme(result.getString("code_programme"));
					actTemp.setDesignation(result.getString("designation"));
					actTemp.setIntitule(result.getString("intitule"));
					actTemp.setObjectifs(result.getString("objectif"));
					actTemp.setIndicateurs(result.getString("indicateur"));
					la.add(actTemp);
				}
				request.setAttribute("lAct", la);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				result = p.execReqLecture(c, LISTE_PROJETS);
				List<Projet> lj = new ArrayList<Projet>();
				
				while(result.next()){
					Projet projTemp = new Projet();
					projTemp.setCode_programme(result.getString("code_programme"));
					projTemp.setCode_action(result.getString("code_action"));
					projTemp.setDesignation(result.getString("designation"));
					projTemp.setIntitule(result.getString("intitule"));
					projTemp.setObjectifsVises(result.getString("objectifs_vises"));
					projTemp.setExtrantsEscomptes(result.getString("extrants_escomptes"));
					projTemp.setCoutDuProjet(result.getString("cout_du_projet"));
					projTemp.setPath(result.getString("path"));
					projTemp.setCocher(result.getString("cocher"));
					lj.add(projTemp);
				} 
				request.setAttribute("lProj", lj);
			} catch (SQLException e) {
				e.printStackTrace();
			}
					
			try {
				result = p.execReqLecture(c, LISTE_PROJETS_TEL);
				List<Projet> lprojTel = new ArrayList<Projet>();
				
				while(result.next()){  
					Projet projTemp = new Projet();
					projTemp.setDesignation(result.getString("designation"));
					projTemp.setIntitule(result.getString("intitule"));
					projTemp.setObjectifsVises(result.getString("objectifs_vises"));
					projTemp.setExtrantsEscomptes(result.getString("extrants_escomptes"));
					projTemp.setCoutDuProjet(result.getString("cout_du_projet"));
					projTemp.setPath(result.getString("path"));
					projTemp.setTDRPath(result.getString("path_tdr"));
					lprojTel.add(projTemp);
				} 
				request.setAttribute("lProjTel", lprojTel);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				result = p.execReqLecture(c,LISTE_PROGRAMMES_TEL);
				List<Programme>  lprogTel = new ArrayList<Programme>();
						
				while(result.next()){
					Programme progTemp = new Programme();
					progTemp.setDesignation(result.getString("designation"));
					progTemp.setIntitule(result.getString("intitule"));
					progTemp.setObjectifs(result.getString("objectifs"));
					progTemp.setIndicateurs(result.getString("indicateur"));
					progTemp.setPath(result.getString("path"));
					lprogTel.add(progTemp);
				}
					request.setAttribute("lProgTel", lprogTel);
			} catch (SQLException e) {
				e.printStackTrace();
			}
						
			try {
				result = p.execReqLecture(c,LISTE_ACTIONS_TEL);
				List<Action>  lactTel = new ArrayList<Action>();
						
				while(result.next()){
					Action actTemp = new Action();
					actTemp.setDesignation(result.getString("designation"));
					actTemp.setIntitule(result.getString("intitule"));
					actTemp.setObjectifs(result.getString("objectif"));
					actTemp.setIndicateurs(result.getString("indicateur"));
					actTemp.setPath(result.getString("path"));
					lactTel.add(actTemp);
				}
				request.setAttribute("lActTel", lactTel);
			} catch (SQLException e) {
				e.printStackTrace();
			}
					
			//Traitements des filtres 
			query = "SELECT * FROM action WHERE action.code_programme=?" ;
			List<Object> lCritere = new ArrayList<Object>();
			lCritere.add("cle1prog");
			try {
				result = p.filtre(c,query,lCritere);
				List<Action> laf = new ArrayList<Action>();
							
				while(result.next()){
					Action actTemp = new Action();
					actTemp.setDesignation(result.getString("designation"));
					actTemp.setIntitule(result.getString("intitule"));
					actTemp.setObjectifs(result.getString("objectif"));
					actTemp.setIndicateurs(result.getString("indicateur"));
					laf.add(actTemp);
				}
				request.setAttribute("lActFil",laf);				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				p.fermerConnexion(c);
				this.getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(request,response);
			} catch (SQLException e) {
				this.getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(request,response);
				e.printStackTrace();
			}
			
		}
		
	}
}
