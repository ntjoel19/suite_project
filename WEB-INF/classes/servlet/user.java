package servlet;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojo.Action;
import pojo.Log;
import pojo.Personne;
import pojo.Programme;
import pojo.Projet;

import dao.Persistance;

/**
 * Servlet implementation class user
 */
@WebServlet("/user")
public class user extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String code_personne;
	Persistance p=new Persistance();
	Log log=new Log();
	HttpSession session;
	Personne currentUser=new Personne();
	java.sql.Connection c=null;
	ResultSet result=null;
	String query=null;
	String[] trow=null;//la partie dynamique du tableau  
	String erreur="<label align='center' id='welcomeMsg'>Bienvenu dans Suite Project</label>";
	admin servletAdmin=new admin();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public user() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("erreurPassw", erreur);
		p=new Persistance();
		p.encodage(request);
		String action = request.getParameter("action");
		String codePersonne=request.getParameter("pseudo")+request.getParameter("tel");
		
		String option=request.getParameter("action")==null?"":request.getParameter("action");
		System.out.println("get?");
		session = request.getSession(true);
		String nomUser = (String)session.getAttribute("nom")==null?"VISITEUR":(String)session.getAttribute("nom");
		String userCode = (String)session.getAttribute("code_pers")==null?"0":(String)session.getAttribute("code_pers");
		String userInfo = "Nom = VISITEUR IP = " +request.getRemoteHost();
		String dateOpt = (String)p.dateActuelle();
		if(option.equals("enregistrerPers")){
			String q="INSERT INTO personne (noms, prenoms, email, bloque, contacts, " +
					"code_role, password, profil, pseudo" +
					") " +
					"VALUES (" +
					"'"+p.getStringCode(request.getParameter("nom"),request)+"', " +
					"'"+p.getStringCode(request.getParameter("prenom"),request)+"', " +
					"'"+p.getStringCode(request.getParameter("email"),request)+"', " +
					"'0', " +//0 signifie que l'utilisateur est par defaut bloqué
					"'"+request.getParameter("tel")+"', " +
					"'user', " +
					"MD5('"+request.getParameter("password")+request.getParameter("pseudo")+"'), " +
					"'0_0_0_0-0_0_0_0-0_0_0_0_0_0_0_0-0_0_0-0_0_0_0-0_0_0_0_0_0-0_0_0_0-0-0', " +
					"'"+p.getStringCode(request.getParameter("pseudo"),request)+"' )" ;
			try {
				c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
				p.execReqEcriture(c, q);
				
				log = new Log(userCode , userInfo , dateOpt , "Crée un compte via la page de connection");
				log.write();
				
				response.setContentType("text/html"); 
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Access-Control-Allow-Origin", "*");
		        //PrintWriter out = response.getWriter();
		        response.getWriter().write("votre compte a bien ete enregistre");
		        try {
					p.fermerConnexion(c);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Erreur de fermeture de la connection à la BD.");
					e.printStackTrace();
				}
		        return;
			} catch (SQLException e) {
				log = new Log(userCode , userInfo , dateOpt , "erreur de creation d'un compte via la page de connection");
				log.write();
				e.printStackTrace();
				response.setContentType("text/html"); 
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Access-Control-Allow-Origin", "*");
		        //PrintWriter out = response.getWriter();
		        response.getWriter().write("Erreur! verifiez que les champs sont correctement renseignes.");
		        try {
					p.fermerConnexion(c);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("Erreur de fermeture de la connection à la BD.");
					e1.printStackTrace();
				}
		        return;
			}
		}else if(option.equals("modifierPass")){
			String pseudo=request.getParameter("pseudo");
			String passwActuel=request.getParameter("password_A");
			String passw=request.getParameter("password");
			
			String r="select * from personne where pseudo='"+pseudo+"' AND password=MD5('"+passwActuel+pseudo+"')";
			try {
				p=new Persistance();
				c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
				ResultSet requete=p.execReqLecture(c, r);
				log = new Log(userCode , userInfo , dateOpt , "Modifie le mot de passe de "+pseudo);
				log.write();
				while(requete.next()){
					p.execReqEcriture(c, "update personne set password=md5('"+passw+pseudo+"') where pseudo='"+pseudo+"';");
					response.setContentType("text/html"); 
					response.setCharacterEncoding("UTF-8");
					response.setHeader("Access-Control-Allow-Origin", "*");
			        //PrintWriter out = response.getWriter();
			        response.getWriter().write("Mot de passe changé, veuiller vous connecter!!!");
			        try {
						p.fermerConnexion(c);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("Erreur de fermeture de la connection à la BD.");
						e.printStackTrace();
					}
			        return;
				}
				response.setContentType("text/html"); 
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Access-Control-Allow-Origin", "*");
				response.getWriter().write("Utilisateur non-existant!!!");
				try {
					p.fermerConnexion(c);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Erreur de fermeture de la connection à la BD.");
					e.printStackTrace();
				}
		        return;
			} catch (SQLException e) {
				log = new Log(userCode , userInfo , dateOpt , "echec de modification du mot de passe de "+pseudo);
				log.write();
				e.printStackTrace();
				response.setContentType("text/html"); 
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Access-Control-Allow-Origin", "*");
				response.getWriter().write("Probleme de modification en BD!!!");
				try {
					p.fermerConnexion(c);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("Erreur de fermeture de la connection à la BD.");
					e1.printStackTrace();
				}
		        return;
			}
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nom=request.getParameter("login");
		String password=request.getParameter("password");
		p.encodage(request);
		
		String requete="SELECT * FROM  personne WHERE " +
				" pseudo = '"+nom+"' AND password = MD5('"+password+nom+"')";
		
		String requete2="SELECT * FROM  personne WHERE " +
		" code_role = 'vide'";
		Integer i=0;
		String roles="";
		String user_roles="";
		String requete3="SELECT * FROM  role";
		
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
		
		
			try {
				p=new Persistance();
				c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
				result = p.execReqLecture(c, requete);
				//on entre dans le while si il existe un utilisateur ayant le mot de passe et le login fournis
				if(result.next()){
					if(((String)result.getString("bloque")).equals("1")){
						session=request.getSession(true);
						currentUser=new Personne(result.getString("code_personne"),
								   result.getString("noms"),
								   result.getString("email"),
								   result.getString("contacts"),
								   result.getString("code_role"));
					  session.setAttribute("code_pers", currentUser.getCodePersonne());
					  session.setAttribute("nom", currentUser.getNoms());
					  session.setAttribute("email", currentUser.getEmails());
					  session.setAttribute("contacts", currentUser.getContacts());
					  session.setAttribute("code_role", currentUser.getCodeRole());
					  InetAddress IP = InetAddress.getLocalHost();
					  String MonIP = IP.getHostAddress();
					  session.setAttribute("IP", MonIP);
					  session.setAttribute("User-Agent", request.getHeader("User-Agent"));
					  
					  String userCode = (String) session.getAttribute("code_pers");
					  String userInfo = "Nom = " + (String) session.getAttribute("nom") + " IP = " +request.getRemoteHost();
					  String dateOpt = (String)p.dateActuelle();
					  
					  log = new Log(userCode , userInfo , dateOpt , "Connection en tant qu`administrateur");
					  log.write();
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
						result = p.execReqLecture(c,requete3);
						roles+="<select>";
						while(result.next()){
							roles+="<option value='"+result.getString("code_role")+"'>"+result.getString("code_role")+"</option>";;
						}
						roles+="</select>";
						user_roles="<table>";
						result = p.execReqLecture(c,requete2);
						while(result.next()){
							i++;
							user_roles+="<tr><td>";
							user_roles+=result.getString("noms")+"</td><td style=''>"+roles+"</td>";
							user_roles+="</tr>";
						}
						user_roles+="</table>";	
						System.out.println("code personne = "+session.getAttribute("code_pers"));
						String requete0="SELECT code_role FROM  personne WHERE " +
						" code_personne = '"+session.getAttribute("code_pers")+"'";
						result = p.execReqLecture(c,requete0);
						while(result.next()){
							codeR=result.getString("code_role");
						}
						result = p.execReqLecture(c,requete4+session.getAttribute("code_pers")+"'");
						while(result.next()){
							servletAdmin.setAcces(result.getString("profil"));
						}
						
						request.setAttribute("dataList1", dataList1);
						request.setAttribute("dataList2", dataList2);
						request.setAttribute("dataList3", dataList3);
						request.setAttribute("dataList4", dataList4);
						  //Ceci jouera le rÃ´le d'une variable de session, connue par toutes les vues et servlets
						  
						  
						  if(currentUser.getCodeRole().equals("user")){
							 
					  	  }else if(currentUser.getCodeRole().equals("admin")){
								String[] acc=servletAdmin.acces(servletAdmin.getAcces());
								
								String profil=acc[0];
								for(int i1=1;i1<41;i1++)
									profil+="__"+acc[i1];
								System.out.println(profil);
								session.setAttribute("profil", profil);
								
								request.setAttribute("btn0", acc[0]);
								request.setAttribute("btn1", acc[4]);
								request.setAttribute("btn2", acc[8]);
								request.setAttribute("btn3", acc[16]);
								request.setAttribute("btn4", acc[19]);
								request.setAttribute("btn5", acc[23]);
								request.setAttribute("btn6", acc[29]);
								request.setAttribute("btn7", acc[33]);
								request.setAttribute("btn8", acc[34]);
								
								request.setAttribute("top1", acc[35]);
								request.setAttribute("top2", acc[36]);
								request.setAttribute("top3", acc[37]);
								request.setAttribute("top4", acc[38]);
								request.setAttribute("top5", acc[39]);
								request.setAttribute("top6", acc[40]);
								
								String ii=i.toString();
								request.setAttribute("nbCmptAValider", ii);
								request.setAttribute("roles", user_roles);
								System.out.println(user_roles);
								try {
									p.fermerConnexion(c);
									this.getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request,response);
							  		
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									System.out.println("Erreur de fermeture de la connection à la BD.");
									this.getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request,response);
							  		
									e.printStackTrace();
								}
					  		}
						  index.nbUser++;
						  System.out.println("il y a "+index.nbUser+" utilisateurs connectes");
					}else{
						erreur="<label align='center' id='erreurPassw'>Votre compte n'est pas encore active</label>";
						request.setAttribute("erreurPassw", erreur);
						
						session=request.getSession(true);
					  
					  String userCode = (String) session.getAttribute("code_pers");
					  String userInfo = "Nom = " + (String) session.getAttribute("nom") + " IP = " +request.getRemoteHost();
					  String dateOpt = (String)p.dateActuelle();
						try {
							p.fermerConnexion(c);
							log = new Log(userCode , userInfo , dateOpt , "Connection interdite pour raison de compte nom encore active");
							log.write();
							this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
					  		
						} catch (SQLException e) {
							log = new Log(userCode , userInfo , dateOpt , "Connection interdite pour raison de compte nom encore active");
							log.write();

							System.out.println("Erreur de fermeture de la connection à la BD.");
							this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
					  		
							e.printStackTrace();
						}
					}
				}else{
					session=request.getSession(true);
					  
					  String userCode = (String) session.getAttribute("code_pers");
					  String userInfo = "Nom = " + (String) session.getAttribute("nom") + " IP = " +request.getRemoteHost();
					  String dateOpt = (String)p.dateActuelle();
					erreur="<label align='center' id='erreurPassw'>Login ou mot de passe incorrect</label>";
					request.setAttribute("erreurPassw", erreur);
					try {
						p.fermerConnexion(c);
						log = new Log(userCode , userInfo , dateOpt , "Echec de mot de passe lors de la connection");
						log.write();
						this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
				  		
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("Erreur de fermeture de la connection à la BD.");
						this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
				  		
						e.printStackTrace();
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		
	}

}
