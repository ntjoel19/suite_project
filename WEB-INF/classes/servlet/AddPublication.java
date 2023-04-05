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
 * Servlet implementation class AddPublication
 */
@WebServlet(name = "addPublication", urlPatterns = { "/addPublication" })
public class AddPublication extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Persistance p=new Persistance();
	java.sql.Connection c=null;
	ResultSet result=null;
	String[] trow=null;//la partie dynamique du tableau    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPublication() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			
			String btn=request.getParameter("btn");
			request.setAttribute("code_pers", code_pers);
			java.sql.Connection c=null;
			
			try {
				c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			request.setAttribute("type", "save");
			request.setAttribute("type2", "saveAdd");
			
			String profil=(String)session.getAttribute("profil");
			System.out.println("profil utilisateur"+profil);
			String[] acces=profil.split("__");
			request.setAttribute("top1", acces[35]);
			request.setAttribute("top2", acces[36]);
			request.setAttribute("top3", acces[37 ]);
			request.setAttribute("top4", acces[38]);
			request.setAttribute("top5", acces[39]);
			request.setAttribute("top6", acces[40]);
			if(!acces[19].equals("<img title='Acces interdit' class='img' src='./ressources/accesBloq.png' />Publications")){
			
			}else{
				this.getServletContext().getRequestDispatcher("/WEB-INF/admin/access_interdit.jsp").forward(request,response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		p.encodage(request);
		doGet(request, response);
	}

}
