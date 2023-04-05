package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Persistance;

/**
 * Servlet implementation class AjoutPersonne
 */
@WebServlet("/AjoutPersonne")
public class AjoutPersonne extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Persistance p=new Persistance();
	java.sql.Connection c=null;
	ResultSet result=null;
	String[] trow=null;//la partie dynamique du tableau
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjoutPersonne() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		p.encodage(request);
		HttpSession session = request.getSession();
		if(session.getAttribute("code_pers")==null){
			this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);	
		}else{
			try {
				c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String profile=(String)session.getAttribute("profil");
			String[] acces=profile.split("__");
			String btn=(String)request.getParameter("operation");
			if(!acces[25].equals("<td></td>")){
				
				String codePersonne=(String) request.getParameter("codePersonne");
				//System.out.println("le code transmis: "+code_pers);
				String nom=(String) request.getParameter("nom");
				
				String prenom=(String) request.getParameter("prenom");
				String email=(String) request.getParameter("email");
				String tel=(String) request.getParameter("tel");
				String pseudo=(String) request.getParameter("pseudo");
				String password=(String) request.getParameter("password");
				String id=(String) request.getParameter("id");
				String[] profil=new String[35];
				String[] profilEdit=new String[35];
				
				profil[0]=(String) request.getParameter("axProg");
				profil[1]=(String) request.getParameter("axProg1");
				profil[2]=(String) request.getParameter("axProg2");
				profil[3]=(String) request.getParameter("axProg3");
				profil[4]=(String) request.getParameter("axAction");
				profil[5]=(String) request.getParameter("axAction1");
				profil[6]=(String) request.getParameter("axAction2");
				profil[7]=(String) request.getParameter("axAction3");
				profil[8]=(String) request.getParameter("axProj");
				profil[9]=(String) request.getParameter("axProj1");
				profil[10]=(String) request.getParameter("axProj2");
				profil[11]=(String) request.getParameter("axProj3");
				profil[12]=(String) request.getParameter("axProj4");
				profil[13]=(String) request.getParameter("axProj5");
				profil[14]=(String) request.getParameter("axProj6");
				profil[15]=(String) request.getParameter("axProj7");
				profil[16]=(String) request.getParameter("axPub");
				profil[17]=(String) request.getParameter("axPub1");
				profil[18]=(String) request.getParameter("axPub2");
				profil[19]=(String) request.getParameter("axPub3");
				profil[20]=(String) request.getParameter("axUser");
				profil[21]=(String) request.getParameter("axUser1");
				profil[22]=(String) request.getParameter("axUser2");
				profil[23]=(String) request.getParameter("axUser3");
				profil[24]=(String) request.getParameter("axUser4");
				profil[25]=(String) request.getParameter("axUser5");
				profil[26]=(String) request.getParameter("axStat");
				profil[27]=(String) request.getParameter("axMedia");
				profil[28]=(String) request.getParameter("axMedia1");
				profil[29]=(String) request.getParameter("axMedia2");
				profil[30]=(String) request.getParameter("axParam");
				profil[31]=(String) request.getParameter("axParam1");
				profil[32]=(String) request.getParameter("axParam2");
				profil[33]=(String) request.getParameter("axParam3");
				profil[34]=(String) request.getParameter("axAide");
				
				request.setAttribute("edit", acces[25]);
				request.setAttribute("add", acces[24]);
				request.setAttribute("bloq", acces[26]);
				request.setAttribute("dbloq", acces[27]);
				request.setAttribute("del", acces[28]);
				request.setAttribute("top1", acces[35]);
				request.setAttribute("top2", acces[36]);
				request.setAttribute("top3", acces[37]);
				request.setAttribute("top4", acces[38]);
				request.setAttribute("top5", acces[39]);
				request.setAttribute("top6", acces[40]);
				
				for(int i=0;i<35;i++){
					if(profil[i]==null)
						profilEdit[i]="0";
					else
						profilEdit[i]="1";
				}
				
				String prof;
				prof=profilEdit[0]+"_"+profilEdit[1]+"_"+profilEdit[2]+"_"+profilEdit[3]+"-";
				prof+=profilEdit[4]+"_"+profilEdit[5]+"_"+profilEdit[6]+"_"+profilEdit[7]+"-";
				prof+=profilEdit[8]+"_"+profilEdit[9]+"_"+profilEdit[10]+"_"+profilEdit[11]+"_"+profilEdit[12]+"_"+profilEdit[13]+"_"+profilEdit[14]+"_"+profilEdit[15]+"-";
				prof+=profilEdit[27]+"_"+profilEdit[28]+"_"+profilEdit[29]+"-";
				prof+=profilEdit[16]+"_"+profilEdit[17]+"_"+profilEdit[18]+"_"+profilEdit[19]+"-";
				prof+=profilEdit[20]+"_"+profilEdit[21]+"_"+profilEdit[22]+"_"+profilEdit[23]+"_"+profilEdit[24]+"_"+profilEdit[25]+"-";
				prof+=profilEdit[30]+"_"+profilEdit[31]+"_"+profilEdit[32]+"_"+profilEdit[33]+"-";
				prof+=profilEdit[26]+"-";
				prof+=profilEdit[34];
				
				if(btn==null) btn="enregistrer";
				if(btn.equals("save") || btn.equals("saveAdd")){
					System.out.println("enregistrement d'une personne");
					String queryInsert="INSERT INTO personne (noms, email, contacts, " +
					"code_role, password, pseudo, prenoms, profil, bloque)" +
					"VALUES (" +
					"'"+p.getStringCode(nom.replaceAll("'", "`"),request)+"', " +
					"'"+p.getStringCode(email.replaceAll("'", ""),request)+"', " +
					"'"+tel+"', " +
					"'admin', " +
					"MD5('"+password+pseudo+"'), " +
					"'"+p.getStringCode(pseudo.replaceAll("'", "`"),request)+"', " +
					"'"+p.getStringCode(prenom.replaceAll("'", "`"),request)+"', " +
					"'"+prof+"', " +
					"'1' )";
					try {
						p.execReqEcriture(c, queryInsert);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(btn.equals("save")){
						try {
							c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						trow=p.rechargerTab(c,"personne","code_personne code_role pseudo noms email contacts bloque","code_personne",null);
						request.setAttribute("tr", trow[0]);
						request.setAttribute("nb", trow[1]);
						try {
							p.fermerConnexion(c);
							this.getServletContext().getRequestDispatcher("/WEB-INF/admin/utilisateurs.jsp").forward(request,response);
					  		
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							System.out.println("Erreur de fermeture de la connection à la BD.");
							this.getServletContext().getRequestDispatcher("/WEB-INF/admin/utilisateurs.jsp").forward(request,response);
					  		
							e.printStackTrace();
						}
					}else{
						response.setContentType("text/html"); 
						response.setCharacterEncoding("UTF-8");
						response.setHeader("Access-Control-Allow-Origin", "*");
				        //PrintWriter out = response.getWriter();
				        response.getWriter().write("Utilisateur enregitré avec success");
						try {
							p.fermerConnexion(c);
					  		
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							System.out.println("Erreur de fermeture de la connection à la BD.");
							e.printStackTrace();
						}
						return;
					}
				}else if(btn.equals("update")){
					String update="UPDATE personne SET " +
						"noms = '"+p.getStringCode(nom.replaceAll("'", "`"),request)+"', " +
						"prenoms = '"+p.getStringCode(prenom.replaceAll("'", "`"),request)+"', " +
						"pseudo = '"+p.getStringCode(pseudo.replaceAll("'", "`"),request)+"', " +
						"contacts = '"+tel+"', " +
						"email = '"+email+"', " +
						"code_role = 'admin', " +
						"profil = '"+prof+"' " +
						"WHERE code_personne = '"+id+"'";
					try {
						p.execReqEcriture(c, update);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					trow=p.rechargerTab(c,"personne","code_personne code_role pseudo noms email contacts bloque","code_personne",null);
					request.setAttribute("tr", trow[0]);
					request.setAttribute("nb", trow[1]);
					try {
						p.fermerConnexion(c);
						this.getServletContext().getRequestDispatcher("/WEB-INF/admin/utilisateurs.jsp").forward(request,response);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						this.getServletContext().getRequestDispatcher("/WEB-INF/admin/utilisateurs.jsp").forward(request,response);
						
						e.printStackTrace();
					}
				}
			}else{
				this.getServletContext().getRequestDispatcher("/WEB-INF/admin/access_interdit.jsp").forward(request,response);
			}
		}
	}
	
	
}
