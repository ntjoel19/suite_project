package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Persistance;

/**
 * Servlet implementation class details
 */
@WebServlet("/details")
public class details extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String requeteProjet="SELECT * FROM PROJET WHERE INTITULE ='";
	String requeteTache="SELECT * FROM TACHE WHERE DESIGNATION ='";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String typeDeDetail = request.getParameter("type");
		String id_de_element_pour_detail = request.getParameter("valeur");
		ResultSet rs = null;
		HttpSession session = request.getSession(true);
		if(session.getAttribute("code_pers")==null){
			String erreur="<label align='center' id='welcomeMsg'>Bienvenu dans Suite Project</label>";
			request.setAttribute("erreurPassw", erreur);
			this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
		}else{
			Persistance p=new Persistance();
			p.encodage(request);
			Connection c=null;
			try {
				c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String profil=(String)session.getAttribute("profil");
			System.out.println("profil utilisateur"+profil);
			String[] acces=profil.split("__");
			request.setAttribute("top1", acces[35]);
			request.setAttribute("top2", acces[36]);
			request.setAttribute("top3", acces[37]);
			request.setAttribute("top4", acces[38]);
			request.setAttribute("top5", acces[39]);
			request.setAttribute("top6", acces[40]);
			if(typeDeDetail.equals("projet")){
				try {
					rs=p.execReqLecture(c, requeteProjet+id_de_element_pour_detail+"';");
					if(rs.next()){
						request.setAttribute("path", rs.getString("path"));
						request.setAttribute("designation", rs.getString("designation"));
						request.setAttribute("appartenance", rs.getString("appartenance"));
						request.setAttribute("intitule", rs.getString("intitule"));
						request.setAttribute("porteur", rs.getString("porteur"));
						request.setAttribute("contexte", rs.getString("contexte_de_mise_en_oeuvre"));
						request.setAttribute("description", rs.getString("description_projet"));
						String[] objectifs=(String[])rs.getString("objectifs_vises").split("-/");
						request.setAttribute("objectif1", objectifs[0]);
						request.setAttribute("objectif2", objectifs[1]);
						String[] promoteurs=(String[])rs.getString("ministere").split("-/");
						request.setAttribute("promot1", promoteurs[0]);
						request.setAttribute("promot2", promoteurs[1]);
						request.setAttribute("promot3", promoteurs[2]);

						request.setAttribute("dataList1", p.listeProgramme(rs.getString("code_programme")));
						request.setAttribute("dataListAction", p.listeAction(rs.getString("code_programme"),rs.getString("code_action")));
						String[] ancrage11=(String[])rs.getString("ancrage_du_projet").split("-/");
						request.setAttribute("ancrage1", ancrage11[0]);
						request.setAttribute("ancrage2", ancrage11[1]);
						request.setAttribute("effet", rs.getString("effets_attendus"));
						request.setAttribute("impact", rs.getString("impactes"));
						String justification = (String)rs.getString("justification_des_besoins");
						String[] justification11=justification.split("-/");
						request.setAttribute("justification1", justification11[0]);
						request.setAttribute("justification2", justification11[1]);
						request.setAttribute("justification3", justification11[2]);
						request.setAttribute("cout", rs.getString("cout_du_projet"));
						request.setAttribute("population", rs.getString("population"));
						request.setAttribute("service", rs.getString("service_responsable"));
						request.setAttribute("typologie", rs.getString("typologie_du_projet"));
						request.setAttribute("extrants", rs.getString("extrants_escomptes"));
						request.setAttribute("nature_finance", rs.getString("nature_du_financement"));
						request.setAttribute("maitre_Ovr", rs.getString("maitre_doeuvre"));
						request.setAttribute("maitre_Ovg", rs.getString("maitre_douvrage"));
						request.setAttribute("partenaire", rs.getString("partenaires"));
						request.setAttribute("programme", rs.getString("code_programme"));
						request.setAttribute("action", rs.getString("code_action"));
						try {
							p.fermerConnexion(c);
							this.getServletContext().getRequestDispatcher("/WEB-INF/admin/detailsProjet.jsp").forward(request,response);
						} catch (SQLException e) {
							this.getServletContext().getRequestDispatcher("/WEB-INF/admin/detailsProjet.jsp").forward(request,response);
							e.printStackTrace();
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
