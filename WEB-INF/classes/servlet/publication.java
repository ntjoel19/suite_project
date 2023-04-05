package servlet;

import java.io.IOException;
import java.sql.Connection;
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
 * Servlet implementation class publication
 */
@WebServlet("/publication")
public class publication extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Persistance p=new Persistance();
	java.sql.Connection c=null;
	ResultSet result=null;
	String[] trow=null;//la partie dynamique du tableau  
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public publication() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("code_pers")==null){
			String erreur="<label align='center' id='welcomeMsg'>Bienvenu dans Suite Project</label>";
			request.setAttribute("erreurPassw", erreur);
			this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
		}else{
			p.encodage(request);
			String code_pers=(String)session.getAttribute("code_pers");
			String nomUser=(String)session.getAttribute("nom");
			String emailUser=(String)session.getAttribute("email");
			String contactUser=(String)session.getAttribute("contacts");
			String code_role_user=(String)session.getAttribute("code_role");
			
			String btn=request.getParameter("btn");
			request.setAttribute("code_pers", code_pers);
			java.sql.Connection c=null;
			System.out.println("L'utilisateur de code "+code_pers+" appui sur le bouton "+btn);
			request.setAttribute("listAuteurs", p.listePublications("auteur"));
			request.setAttribute("listTitres", p.listePublications("titre"));
			
			try {
				c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String profil=(String)session.getAttribute("profil");
			String[] acces=profil.split("__");
			request.setAttribute("top1", acces[35]);
			request.setAttribute("top2", acces[36]);
			request.setAttribute("top3", acces[37 ]);
			request.setAttribute("top4", acces[38]);
			request.setAttribute("top5", acces[39]);
			request.setAttribute("top6", acces[40]);
			if(!acces[19].equals("<img title='Acces interdit' class='img' src='./ressources/accesBloq.png' />Publications")){
				
				if("creerpub".equals(btn) && !acces[21].equals("<td></td>")){
					request.setAttribute("save", "save");
					request.setAttribute("saveAdd", "saveAdd");
					System.out.println("Utilisateur "+code_pers+" est sur le point de creer une nouvelle publication");
					try {
						p.fermerConnexion(c);
						this.getServletContext().getRequestDispatcher("/WEB-INF/admin/add_Publication.jsp").forward(request,response);
						
					} catch (SQLException e) {
						this.getServletContext().getRequestDispatcher("/WEB-INF/admin/add_Publication.jsp").forward(request,response);
						
						e.printStackTrace();
					}
				}else if("pub".equals(btn)){
					System.out.println("Utilisateur "+code_pers+" viens d'acceder a l'interface de gestion de publication");
					
					request.setAttribute("display1", "<!--");
					request.setAttribute("display2", "-->");
				
					String[] trow=p.rechargerTab(c,"information","auteur titre contenu_publication date_de_publication","contenu_publication",null);request.setAttribute("tr", trow[0]);
					
					request.setAttribute("nb", trow[1]);
					request.setAttribute("tr", trow[0]);
					request.setAttribute("edit", acces[20]);
					request.setAttribute("add", acces[21]);
					request.setAttribute("suppr", acces[22]);
					try {
						p.fermerConnexion(c);
						this.getServletContext().getRequestDispatcher("/WEB-INF/admin/publications.jsp").forward(request,response);
						
					} catch (SQLException e) {
						this.getServletContext().getRequestDispatcher("/WEB-INF/admin/publications.jsp").forward(request,response);
						
						e.printStackTrace();
					}
				}else if("editerPub".equals(btn) && !acces[20].equals("<td></td>")){
					trow=p.rechargerTab(c,"information","auteur titre contenu_publication date_de_publication","contenu_publication",null);
					
					int nb[]=p.nombreLignesCoche(request, Integer.parseInt(trow[1]));
					//String projet=(String)request.getParameter("projetpere");
					int ligneCochee=nb[0];
					if(nb[Integer.parseInt(trow[1])+1]==0 || nb[Integer.parseInt(trow[1])+1]>1){
						request.setAttribute("impossibleDetiter", "vous devez selectionner une seul action!");
						request.setAttribute("tr", trow[0]);
						request.setAttribute("nb", trow[1]);
						request.setAttribute("edit", acces[20]);
						request.setAttribute("add", acces[21]);
						request.setAttribute("suppr", acces[22]);
						System.out.println("L'utilisateur de code "+code_pers+" a voulu editer des publications sans en selectionner aucune");
						try {
							p.fermerConnexion(c);
							this.getServletContext().getRequestDispatcher("/WEB-INF/admin/publications.jsp").forward(request,response);
							
						} catch (SQLException e) {
							this.getServletContext().getRequestDispatcher("/WEB-INF/admin/publications.jsp").forward(request,response);
							
							e.printStackTrace();
						}
					}else{
						try {
							result = p.execReqLecture(c, "SELECT * FROM information WHERE cocher ='"+ligneCochee+"'");
							while(result.next()){
								request.setAttribute("titre", result.getString("titre"));
								request.setAttribute("auteur", result.getString("auteur"));
								request.setAttribute("contenu", result.getString("contenu_publication"));
								request.setAttribute("fichier", result.getString("path"));
								request.setAttribute("idPub", "<input type='hidden' name='idPub' value='"+result.getString("id")+"'/>");
								request.setAttribute("Editer", "Editer");
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						request.setAttribute("save", "edit");
						request.setAttribute("saveAdd", "saveAdd");
						System.out.println("L'utilisateur de code "+code_pers+" a coché la publication "+ligneCochee+" pour édition");
						try {
							p.fermerConnexion(c);
							this.getServletContext().getRequestDispatcher("/WEB-INF/admin/add_Publication.jsp").forward(request,response);
							
						} catch (SQLException e) {
							this.getServletContext().getRequestDispatcher("/WEB-INF/admin/add_Publication.jsp").forward(request,response);
							
							e.printStackTrace();
						}
					}
				}else if("deletePub".equals(btn)){
					trow=p.rechargerTab(c,"information","auteur titre contenu_publication date_de_publication","contenu_publication",null);
					
					int nb[]=p.nombreLignesCoche(request, Integer.parseInt(trow[1]));
					//String projet=(String)request.getParameter("projetpere");
					int ligneCochee=nb[0];
					int nbLigneCochee = nb[Integer.parseInt(trow[1])+1];
					System.out.println("L'utilisateur de code "+code_pers+" a coché la publication "+ligneCochee+" pour Suppression");
					
					try {
						for(int i=0;i<nbLigneCochee;i++){
							p.execReqEcriture(c, "DELETE FROM information WHERE cocher ='"+nb[i]+"'");
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					trow=p.rechargerTab(c,"information","auteur titre contenu_publication date_de_publication","contenu_publication",null);
					
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
				}else if("filtrerPub".equals(btn)){
					System.out.println("Utilisateur "+code_pers+" viens de filtrer les publication");
					String auteur=(String)request.getParameter("auteur");
					String titre=(String)request.getParameter("titre");
					String date=(String)request.getParameter("date");
					
					if(!auteur.equals("")) auteur="auteur LIKE ('"+auteur+"%')";
					if(!titre.equals("")) titre="titre LIKE ('"+titre+"%')";
					if(!date.equals("")) date="date_de_publication LIKE ('"+date+"%')";
					
					String[] critere={auteur,titre,date};
		
					trow=p.rechargerTab(c,"information","auteur titre contenu_publication date_de_publication","contenu_publication",critere);
					
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
		String code_pers=(String)session.getAttribute("code_pers");
		String nomUser=(String)session.getAttribute("nom");
		String emailUser=(String)session.getAttribute("email");
		String contactUser=(String)session.getAttribute("contacts");
		String code_role_user=(String)session.getAttribute("code_role");
		
		java.sql.Connection c=null;
		try {
			c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		p.encodage(request);
		String profil=(String)session.getAttribute("profil");
		System.out.println("profil utilisateur"+profil);
		String[] acces=profil.split("__");
		request.setAttribute("top1", acces[35]);
		request.setAttribute("top2", acces[36]);
		request.setAttribute("top3", acces[37]);
		request.setAttribute("top4", acces[38]);
		request.setAttribute("top5", acces[39]);
		request.setAttribute("top6", acces[40]);
		
		String auteur=(String)request.getAttribute("auteur");
		String description=(String)request.getAttribute("description");
		String titre=(String)request.getAttribute("titre");
			//String valeur_ref=(String) request.getParameter("valeur_ref");
			
			
			/*Inscription des donnÃ©es dans la BD*/
			Integer id=0;
			try {
				result = p.execReqLecture(c ,"SELECT id FROM information");
				while(result.next()) id++;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String btn = (String)request.getParameter("operation");
			System.out.println("le bouton est "+btn);
			if(btn.equals("save")){
				
			}else{
				int delete=p.suprimerLigneTable(c,"information","code_personne titre contenu_publication date_de_publication","contenu_publication", request,null,null,null);					
			}
			
			/*Rechargement de la page*/
			request.setAttribute("display1", "<!--");
			request.setAttribute("display2", "-->");
			//trow=p.rechargerTab(c,"information","code_personne titre contenu_publication date_de_publication","contenu_publication");
			//request.setAttribute("tr", trow[0]);
			//request.setAttribute("nb", trow[1]);
		this.getServletContext().getRequestDispatcher("/WEB-INF/admin/publications.jsp").forward(request,response);
	}

}
