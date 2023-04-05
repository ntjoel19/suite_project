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
 * Servlet implementation class utilisateur
 */
@WebServlet("/utilisateur")
public class utilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Persistance p=new Persistance();
	java.sql.Connection c=null;
	ResultSet result=null;
	String[] trow=null;//la partie dynamique du tableau
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public utilisateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String[] trow=null;
		p.encodage(request);
		if(session.getAttribute("code_pers")==null){
			String erreur="<label align='center' id='welcomeMsg'>Bienvenu dans Suite Project</label>";
			request.setAttribute("erreurPassw", erreur);
			this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
		}else{
			String profil=(String)session.getAttribute("profil");
			System.out.println(profil);
			String[] acces=profil.split("__");
			request.setAttribute("add", acces[24]);
			request.setAttribute("edit", acces[25]);
			request.setAttribute("bloq", acces[26]);
			request.setAttribute("dbloq", acces[27]);
			request.setAttribute("del", acces[28]);
			
			request.setAttribute("top1", acces[35]);
			request.setAttribute("top2", acces[36]);
			request.setAttribute("top3", acces[37]);
			request.setAttribute("top4", acces[38]);
			request.setAttribute("top5", acces[39]);
			request.setAttribute("top6", acces[40]);
			if(!acces[23].equals("<img title='Acces interdit' class='img' src='./ressources/accesBloq.png' />Gestion utilisateurs")){
				String btn=request.getParameter("btn");
				if("creeruser".equals(btn) && !acces[24].equals("<td></td>")){
					request.setAttribute("nom", "");
					request.setAttribute("prenom", "");
					request.setAttribute("contacts", "");
					request.setAttribute("email", "");
					request.setAttribute("pseudo", "");
					request.setAttribute("type", "save");
					request.setAttribute("type2", "saveAdd");
					request.setAttribute("id", "");
					request.setAttribute("passw", "");
					
					String[] aRow = null;
					int l=0;
					String[] anElement;
					int[] iValues=new int[9];
					iValues[0]=4;//0
					iValues[1]=4;//4
					iValues[2]=8;//8
					iValues[3]=3;//16
					iValues[4]=4;//19
					iValues[5]=6;//23
					iValues[6]=4;//29
					iValues[7]=1;//33
					iValues[8]=1;//34
					for(int i=0;i<9;i++){
						//System.out.println(aRow[i]);
						for(int j=0;j<iValues[i];j++){
							if(j==0) 
								request.setAttribute(""+l, " ");
							else
								request.setAttribute(""+l, "disabled='disabled'");
							l++;
						}
					}
					this.getServletContext().getRequestDispatcher("/WEB-INF/admin/add_pers&role.jsp").forward(request,response);
				}else if("gereruser".equals(btn)){
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
						this.getServletContext().getRequestDispatcher("/WEB-INF/admin/utilisateurs.jsp").forward(request,response);
						e.printStackTrace();
					}
					
				}
			}else{
				this.getServletContext().getRequestDispatcher("/WEB-INF/admin/access_interdit.jsp").forward(request,response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		p.encodage(request);
		if(session.getAttribute("code_pers")==null){
			this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);	
		}else{
			String profil=(String)session.getAttribute("profil");
			System.out.println(profil);
			String[] acces=profil.split("__");
			request.setAttribute("add", acces[24]);
			request.setAttribute("edit", acces[25]);
			request.setAttribute("bloq", acces[26]);
			request.setAttribute("dbloq", acces[27]);
			request.setAttribute("del", acces[28]);
			request.setAttribute("top1", acces[35]);
			request.setAttribute("top2", acces[36]);
			request.setAttribute("top3", acces[37]);
			request.setAttribute("top4", acces[38]);
			request.setAttribute("top5", acces[39]);
			request.setAttribute("top6", acces[40]);
			
			request.setAttribute("nom", "");
			request.setAttribute("prenom", "");
			request.setAttribute("contacts", "");
			request.setAttribute("email", "");
			request.setAttribute("pseudo", "");
			request.setAttribute("type", "save");
			request.setAttribute("type2", "saveAdd");
			request.setAttribute("id", "");
			request.setAttribute("passw", "");
			try {
				c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String btn=request.getParameter("operation");
			System.out.println(btn);
			if(btn==null) btn="editer";
			String codePersonne=(String) request.getParameter("codePersonne");
			//System.out.println("le code transmis: "+code_pers);
			String nom=(String) request.getParameter("noms");
			
			String prenom=(String) request.getParameter("prenoms");
			String email=(String) request.getParameter("email");
			String contact=(String) request.getParameter("contact");
			String adresse=(String) request.getParameter("adresse");
			//this.getServletContext().getRequestDispatcher("/WEB-INF/admin/utilisateurs.jsp").forward(request,response);
			if(btn.equals("delete")){
				int delete=p.suprimerLigneTable(c,"personne", "code_personne code_role pseudo noms email contacts bloque","code_personne", request,null,null,null);	
				trow=p.rechargerTab(c,"personne","code_personne code_role pseudo noms email contacts bloque","code_personne",null);
				response.setContentType("text/html"); 
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Access-Control-Allow-Origin", "*");
		        //PrintWriter out = response.getWriter();
				String resultat=trow[0]+"??"+trow[1]+"??"+delete;
		        response.getWriter().write(resultat);
		        try {
					p.fermerConnexion(c);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        return;
			}else if(btn.equals("editer")){
				trow=p.rechargerTab(c,"personne","code_personne code_role pseudo noms email contacts bloque","code_personne",null);
				int[] nb = p.nombreLignesCoche(request, Integer.parseInt(trow[1]));
				System.out.println("nombre de ligne selectionnees "+nb[Integer.parseInt(trow[1])+1]);
				if(nb[Integer.parseInt(trow[1])+1]==0 || nb[Integer.parseInt(trow[1])+1]>1){
					request.setAttribute("impossibleDetiter", "vous devez selectionner un seul utilisateur!");
					request.setAttribute("tr", trow[0]);
					request.setAttribute("nb", trow[1]);
					this.getServletContext().getRequestDispatcher("/WEB-INF/admin/utilisateurs.jsp").forward(request,response);
				}else{
					String chargerUser="SELECT * FROM personne WHERE cocher = "+nb[0];
					try {
						result = p.execReqLecture(c, chargerUser);
						result.next();
						String pseudo=result.getString("pseudo");
						String passw=result.getString("password");
						passw=passw.substring(0,passw.length()-pseudo.length());
						System.out.println("password = "+passw);
						request.setAttribute("nom", result.getString("noms"));
						request.setAttribute("prenom", result.getString("prenoms"));
						request.setAttribute("contacts", result.getString("contacts"));
						request.setAttribute("email", result.getString("email"));
						request.setAttribute("pseudo", pseudo);
						request.setAttribute("id", result.getString("code_personne"));
						request.setAttribute("type", "update");
						request.setAttribute("type2", "update");
						request.setAttribute("passw", "");
						
						profil=result.getString("profil");
						
						String[] aRow = profil.split("-");
						int l=0;
						String[] anElement;
						int[] iValues=new int[9];
						iValues[0]=4;
						iValues[1]=4;
						iValues[2]=8;
						iValues[3]=3;
						iValues[4]=4;
						iValues[5]=6;
						iValues[6]=4;
						iValues[7]=1;
						iValues[8]=1;
						for(int i=0;i<9;i++){
							System.out.print(aRow[i]+" ");
							anElement = aRow[i].split("_");
							if(anElement[0].equals("0")){
								System.out.println(iValues[i]);
								l++;
								request.setAttribute(""+l, "");
								for(int j=1;j<iValues[i];j++){
									request.setAttribute(""+l, "disabled='disabled'");
									l++;
								}
							}else{
								System.out.println(iValues[i]);
								for(int j=0;j<iValues[i];j++){
									if(anElement[j].equals("1")){
										request.setAttribute(""+l, "checked");
									}else{
										request.setAttribute(""+l, "");
									}
									
									l++;
								}
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						p.fermerConnexion(c);
						this.getServletContext().getRequestDispatcher("/WEB-INF/admin/add_pers&role.jsp").forward(request,response);
						
					} catch (SQLException e) {
						this.getServletContext().getRequestDispatcher("/WEB-INF/admin/add_pers&role.jsp").forward(request,response);
						
						e.printStackTrace();
					}
				}
			}else if(btn.equals("update")){
				
			}else if(btn.equals("bloquer")){
				String personne=request.getParameter("code_pers");
				System.out.println("bloquer "+personne);
				String query1="UPDATE personne SET " +
				"bloque = '0'" +
				"WHERE code_personne = '"+personne+"'";
				
				try {
					p.execReqEcriture(c, query1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				response.setContentType("text/html"); 
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Access-Control-Allow-Origin", "*");
		        //PrintWriter out = response.getWriter();
				String resultat="Utilisateur bloque";
		        response.getWriter().write(resultat);
		        try {
					p.fermerConnexion(c);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        return;
			}else if(btn.equals("debloquer")){
				String personne=request.getParameter("code_pers");
				System.out.println("bloquer "+personne);
				String query1="UPDATE personne SET " +
				"bloque = '1'" +
				"WHERE code_personne = '"+personne+"'";
				
				try {
					p.execReqEcriture(c, query1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				response.setContentType("text/html"); 
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Access-Control-Allow-Origin", "*");
		        //PrintWriter out = response.getWriter();
				String resultat="Utilisateur debloque";
		        response.getWriter().write(resultat);
		        try {
					p.fermerConnexion(c);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        return;
			}else if(btn.equals("reload")){;
			
				trow=p.rechargerTab(c,"personne","code_personne code_role pseudo noms email contacts bloque","code_personne",null);
				//request.setAttribute("tr", "");
				
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
			}
			
		}
	}
}
