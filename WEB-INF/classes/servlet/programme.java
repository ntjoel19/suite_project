package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
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

//import json.JSONArray;
//import json.JSONObject;

import dao.Persistance;

/**
 * Servlet implementation class programme
 * @author Ntepp jean joel FOR INFODAY ENTREPRISE
 * @version 1.0
 */
@WebServlet("/programme")
public class programme extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Persistance p=new Persistance();
	java.sql.Connection c=null;
	ResultSet result=null;
	String[] trow=null;//la partie dynamique du tableau  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public programme() {
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
			
			String profil=(String)session.getAttribute("profil");
			System.out.println(profil);
			String[] acces=profil.split("__");
			//System.out.println("blablablablablablablabab = "+acces[1]);
			request.setAttribute("edit", acces[1]);
			request.setAttribute("add", acces[2]);
			request.setAttribute("del", acces[3]);
			if(!acces[0].equals("<img title='Acces interdit' class='img' src='./ressources/accesBloq.png' />Programmes")){
				request.setAttribute("top1", acces[35]);
				request.setAttribute("top2", acces[36]);
				request.setAttribute("top3", acces[37]);
				request.setAttribute("top4", acces[38]);
				request.setAttribute("top5", acces[39]);
				request.setAttribute("top6", acces[40]);
				java.sql.Connection c=null;
				try {
					c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String btn=request.getParameter("btn");
				String progrEdit=request.getParameter("programmepere");
				System.out.println(btn+" "+progrEdit);
				
				trow=p.rechargerTab(c,"programme","designation intitule objectifs indicateur valeur_ref valeur_cible","designation",null);
				
				int[] nb=p.nombreLignesCoche(request, Integer.parseInt(trow[1]));
				progrEdit=""+nb[0];
				
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
				request.setAttribute("sousSecteur", "");
				request.setAttribute("respoProgramme", "");
				request.setAttribute("cadre", "");
				request.setAttribute("strategie", "");
				request.setAttribute("axe_strategique", "");
				if("creerprogr".equals(btn) && !acces[1].equals("<td></td>")){
					request.setAttribute("value", "save");
					request.setAttribute("display1", "");
					request.setAttribute("display2", "");
					request.setAttribute("display3", "<!--");
					request.setAttribute("display4", "-->");
					request.setAttribute("data", " ");
					request.setAttribute("display", "input");
					request.setAttribute("ajout", "1");
					try {
						p.fermerConnexion(c);
						this.getServletContext().getRequestDispatcher("/WEB-INF/admin/programmes.jsp").forward(request,response);
						
					} catch (SQLException e) {
						this.getServletContext().getRequestDispatcher("/WEB-INF/admin/programmes.jsp").forward(request,response);
						
						e.printStackTrace();
					}
					
				}else if("progr".equals(btn)){
					//trow=p.rechargerTab(c,"programme","designation intitule objectifs indicateur");
					request.setAttribute("display1", "<!--");
					request.setAttribute("display2", "-->");
					request.setAttribute("display3", "");
					request.setAttribute("display4", "");
					request.setAttribute("value", "save");
					request.setAttribute("data", " ");
					request.setAttribute("display", "input");
				}else if("editerTop".equals(btn) && !acces[2].equals("<td></td>")){
					request.setAttribute("display1", "");
					request.setAttribute("display2", "");
					request.setAttribute("display3", "<!--");
					request.setAttribute("display4", "-->");
					request.setAttribute("value", "edit");
					
					String query1="SELECT designation FROM programme";
					String data="<select name='designation' id='designationSelect' type='text'>";
					try {
						result=p.execReqLecture(c, query1);
						while(result.next()){
							data+="<option value='"+result.getString("designation")+"'>"+result.getString("designation")+"</option>";
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					data+="</select>";
					request.setAttribute("data", data);
					request.setAttribute("display", "hidden");
				}
				if(!"non".equals(progrEdit) && !"action".equals(btn) && !"creeraction".equals(btn) && !"progr".equals(btn)){//si on voudrait editer un programme
					System.out.println("edition de programmes");
					System.out.println("nombre de ligne selectionnees "+nb[Integer.parseInt(trow[1])+1]);
					
					if(nb[Integer.parseInt(trow[1])+1]==0 || nb[Integer.parseInt(trow[1])+1]>1){
						request.setAttribute("impossibleDetiter", "vous devez selectionner un seul programme!");
						request.setAttribute("tr", trow[0]);
						request.setAttribute("nb", trow[1]);
						request.setAttribute("display3", "");
						request.setAttribute("display4", "");
						request.setAttribute("display1", "<!--");
						request.setAttribute("display2", "-->");
						
						//this.getServletContext().getRequestDispatcher("/WEB-INF/admin/programme.jsp").forward(request,response);
					}else{
						request.setAttribute("edition", "1");
						try {
							result = p.execReqLecture(c, "SELECT * FROM programme WHERE cocher ='"+progrEdit+"'");
							request.setAttribute("display1", "");
							request.setAttribute("display2", "");
							request.setAttribute("display3", "<!--");
							request.setAttribute("display4", "-->");
							while(result.next()){
								request.setAttribute("designation", result.getString("designation"));
								request.setAttribute("intitule", result.getString("intitule"));
								request.setAttribute("objectifs", result.getString("objectifs"));
								request.setAttribute("indicateur", result.getString("indicateur"));
								request.setAttribute("valRef", result.getString("valeur_ref"));
								request.setAttribute("valCible", result.getString("valeur_cible"));
								request.setAttribute("sousSecteur", result.getString("sous_secteur"));
								request.setAttribute("respoProgramme", result.getString("responsable"));
								request.setAttribute("cadre", result.getString("cadre_de_mise_en_oeuvre"));
								request.setAttribute("strategie", result.getString("strategie"));
								request.setAttribute("axe_strategique", result.getString("axe_strategique"));
								request.setAttribute("value", "editer");
								request.setAttribute("data", " ");
								request.setAttribute("display", "input");
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				
					trow=p.rechargerTab(c,"programme","designation intitule objectifs indicateur valeur_ref valeur_cible","designation",null);
					request.setAttribute("tr", trow[0]);
					request.setAttribute("nb", trow[1]);
				}
	
				try {
					String query1="SELECT * FROM programme";
					String option1="";
					String option2="";
					result=p.execReqLecture(c, query1);
					while(result.next()){
						option1+="<option value='"+result.getString("designation")+"'>";
						option2+="<option value='"+result.getString("intitule")+"'>";
					}
					request.setAttribute("option1", option1);
					request.setAttribute("option2", option2);
	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					String bd = p.bdCoteClient(c, "programme", 14);
					request.setAttribute("bd", bd);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				trow=p.rechargerTab(c,"programme","designation intitule objectifs indicateur valeur_ref valeur_cible","designation",null);
				request.setAttribute("tr", trow[0]);
				request.setAttribute("nb", trow[1]);
				try {
					p.fermerConnexion(c);
					this.getServletContext().getRequestDispatcher("/WEB-INF/admin/programmes.jsp").forward(request,response);
				} catch (SQLException e) {
					this.getServletContext().getRequestDispatcher("/WEB-INF/admin/programmes.jsp").forward(request,response);
					e.printStackTrace();
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
		java.sql.Connection c=null;
		String profil=(String)session.getAttribute("profil");
		System.out.println(profil);
		String[] acces=profil.split("__");
		request.setAttribute("edit", acces[1]);
		request.setAttribute("add", acces[2]);
		request.setAttribute("del", acces[3]);
		
		request.setAttribute("top1", acces[35]);
		request.setAttribute("top2", acces[36]);
		request.setAttribute("top3", acces[37]);
		request.setAttribute("top4", acces[38]);
		request.setAttribute("top5", acces[39]);
		request.setAttribute("top6", acces[40]);
		try {
			c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
			String designation=(String) request.getParameter("designation");
			String intitule=(String) request.getParameter("intitule");
			String objectifs=(String) request.getParameter("objectifs");
			String indicateur=(String) request.getParameter("indicateurs");
			String valeur_cible=(String) request.getParameter("valCible");
			String valeur_ref=(String) request.getParameter("valRef");
			
			String ss=(String) request.getParameter("sousSecteur");
			String rp=(String) request.getParameter("respoProgr");
			String as=(String) request.getParameter("axeStrategiq");
			String ci=(String) request.getParameter("cadreInstitutionel");
			String sp=(String) request.getParameter("strategiProgr");
			
			
			/*Inscription des donnees dans la BD*/
			String btn = (String)request.getParameter("enregistrerProgr");
			
			System.out.println("le bouton est "+btn);
			if(btn.equals("save")){
				Calendar d=Calendar.getInstance();
				
				int annee=d.get(Calendar.YEAR);
				trow=p.rechargerTab(c,"programme","designation intitule objectifs indicateur valeur_ref valeur_cible","designation",null);int nb=Integer.parseInt(trow[1]);
				String queryInsert="INSERT INTO programme (designation, intitule, objectifs, indicateur," +
						" valeur_cible, valeur_ref, sous_secteur, responsable, axe_strategique, cadre_de_mise_en_oeuvre, strategie, annee, cocher) " +
						"VALUES (" +
						"'"+p.getStringCode(designation,request)+"', " +
						"'"+p.getStringCode(intitule,request)+"', " +
						"'"+p.getStringCode(objectifs,request)+"', " +
						"'"+p.getStringCode(indicateur,request)+"', " +
						"'"+p.getStringCode(valeur_cible,request)+"', " +
						"'"+p.getStringCode(valeur_ref,request)+"', " +
						"'"+p.getStringCode(ss,request)+"', " +
						"'"+p.getStringCode(rp,request)+"', " +
						"'"+p.getStringCode(as,request)+"', " +
						"'"+p.getStringCode(ci,request)+"', " +
						"'"+p.getStringCode(sp,request)+"', " +
						"'"+annee+"', " +
						"'"+nb+"' )";
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
			}else if(btn.equals("editer")){
				//trow=p.rechargerTab(c,"programme","designation intitule objectifs indicateur","designation");
				//int nb=Integer.parseInt(trow[1]);
				String editInsert="UPDATE programme SET " +
						"intitule = '"+p.getStringCode(intitule,request)+"', " +
						"objectifs = '"+p.getStringCode(objectifs,request)+"', " +
						"indicateur = '"+p.getStringCode(indicateur,request)+"', " +
						"valeur_cible = '"+p.getStringCode(valeur_cible,request)+"', " +
						"valeur_ref = '"+p.getStringCode(valeur_ref,request)+"', " +
						"sous_secteur = '"+p.getStringCode(ss,request)+"', " +
						"responsable = '"+p.getStringCode(rp,request)+"', " +
						"axe_strategique = '"+p.getStringCode(as,request)+"', " +
						"cadre_de_mise_en_oeuvre = '"+p.getStringCode(as,request)+"', " +
						"strategie =  '"+p.getStringCode(sp,request)+"'" +
						"WHERE DESIGNATION = '"+p.getStringCode(designation,request)+"'";
				try {
					p.execReqEcriture(c, editInsert);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("display1", "<!--");
				request.setAttribute("display2", "-->");
				request.setAttribute("display3", "");
				request.setAttribute("display4", "");
			}else if(btn.equals("edit")){
				request.setAttribute("display1", "");
				request.setAttribute("display2", "");
				request.setAttribute("data", " ");
				request.setAttribute("display", "input");
				request.setAttribute("edition", "1");
				try {
					String[] trow=p.rechargerTab(c,"programme","designation intitule objectifs indicateur valeur_ref valeur_cible","designation",null);
					int nb=Integer.parseInt(trow[1]);//nombre de lignes du tableau
					int[] n=p.nombreLignesCoche(request,nb);
					System.out.println("numero de la ligne cochee : "+n[0]);
					result = p.execReqLecture(c, "SELECT * FROM programme WHERE cocher = "+n[0]);
					while(result.next()){
						request.setAttribute("designation", result.getString("designation"));
						request.setAttribute("intitule", result.getString("intitule"));
						request.setAttribute("objectifs", result.getString("objectifs"));
						request.setAttribute("indicateur", result.getString("indicateur"));
						request.setAttribute("valRef", result.getString("valeur_ref"));
						request.setAttribute("valCible", result.getString("valeur_cible"));
						request.setAttribute("sousSecteur", result.getString("sous_secteur"));
						request.setAttribute("respoProgramme", result.getString("responsable"));
						request.setAttribute("cadre", result.getString("cadre_de_mise_en_oeuvre"));
						request.setAttribute("strategie", result.getString("strategie"));
						request.setAttribute("axe_strategique", result.getString("axe_strategique"));
						request.setAttribute("value", "editer");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(btn.equals("delete")){
				int delete=p.suprimerLigneTable(c,"programme", "designation intitule objectifs indicateur","designation", request,null,null,null);	
				//request.setAttribute("display1", "<!--");
				//request.setAttribute("display2", "-->");
				
				trow=p.rechargerTab(c,"programme","designation intitule objectifs indicateur valeur_ref valeur_cible","designation",null);
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
			}else if("editerTop1".equals(btn)){//appel ajax
				String designat=(String)request.getParameter("designation");
				System.out.println(designat);
				String resultat="";
				try {
					result = p.execReqLecture(c, "SELECT * FROM programme WHERE designation ='"+designat+"'");
					while(result.next()){
				        resultat+=result.getString("intitule")+";";
				        resultat+=result.getString("objectifs")+";";
				        resultat+=result.getString("indicateur")+";";
				        resultat+=result.getString("valeur_ref")+";";
				        resultat+=result.getString("valeur_cible")+";";
				        resultat+=result.getString("sous_secteur")+";";
				        resultat+=result.getString("responsable")+";";
				        resultat+=result.getString("cadre_de_mise_en_oeuvre")+";";
				        resultat+=result.getString("strategie")+";";
				        resultat+=result.getString("axe_strategique")+";";
				        //System.out.println(resultat);
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
			}else if(btn.equals("filtrer")){;
				String annee=(String)(request.getParameter("annee"));	
			    String aee="";
				if(!designation.equals("")) designation="designation LIKE ('%"+designation+"%')";
				if(!intitule.equals("")) intitule="intitule LIKE ('%"+intitule+"%')";
				if(!annee.equals("")) annee="annee = "+annee+"";
				String[] critere={designation,intitule,annee};
				if(designation.equals("") && intitule.equals("") && annee.equals(""))
					trow=p.rechargerTab(c,"programme","designation intitule objectifs indicateur valeur_ref valeur_cible","designation",null);
				else
					trow=p.rechargerTab(c,"programme","designation intitule objectifs indicateur valeur_ref valeur_cible","designation",critere);
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
			}else if(btn.equals("reload")){;
			
			trow=p.rechargerTab(c,"programme","designation intitule objectifs indicateur valeur_ref valeur_cible","designation",null);//request.setAttribute("tr", "");
				
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
			
			/*Rechargement de la page*/
			trow=p.rechargerTab(c,"programme","designation intitule objectifs indicateur valeur_ref valeur_cible","designation",null);
			request.setAttribute("tr", trow[0]);
			request.setAttribute("nb", trow[1]);
			try {
				p.fermerConnexion(c);
				this.getServletContext().getRequestDispatcher("/WEB-INF/admin/programmes.jsp").forward(request,response);
			} catch (SQLException e) {
				this.getServletContext().getRequestDispatcher("/WEB-INF/admin/programmes.jsp").forward(request,response);
				e.printStackTrace();
			}	
		return;
	}

}
