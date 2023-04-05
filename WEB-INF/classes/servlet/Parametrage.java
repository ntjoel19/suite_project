package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Parametrage
 */
@WebServlet(name = "parametrage", urlPatterns = { "/parametrage" })
public class Parametrage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Parametrage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String requete2="SELECT * FROM  personne WHERE " +
		" code_role = 'vide'";
		String requete3="SELECT code_role FROM  personne WHERE " +
		" code_personne = '"+session.getAttribute("code_pers")+"'";
		//String requete4="SELECT acces_main_acces FROM  role WHERE  code_role = '";
		String requete4="SELECT profil FROM  personne WHERE  code_personne = '";
		Integer i=0;
		if(session.getAttribute("code_pers")==null){
			String erreur="<label align='center' id='welcomeMsg'>Bienvenu dans Suite Project</label>";
			request.setAttribute("erreurPassw", erreur);
			this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
		}else{
			//p.encodage(request);
			String profil=(String)session.getAttribute("profil");
			System.out.println("profil utilisateur"+profil);
			String[] acces=profil.split("__");
			request.setAttribute("top1", acces[35]);
			request.setAttribute("top2", acces[36]);
			request.setAttribute("top3", acces[37]);
			request.setAttribute("top4", acces[38]);
			request.setAttribute("top5", acces[39]);
			request.setAttribute("top6", acces[40]);
			this.getServletContext().getRequestDispatcher("/WEB-INF/admin/parametrage.jsp").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
