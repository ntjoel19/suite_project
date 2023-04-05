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
 * Servlet implementation class admin
 */
@WebServlet("/admin")
public class admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Persistance p=new Persistance();
	java.sql.Connection c=null;
	ResultSet result=null;
	String acces="";
	String[] topMenuAllow={
			"<a id='progr' href='/suite_project/programme?btn=progr'>Programmes</a>",
			
			"<a id='action' href='/suite_project/action?btn=action'>Actions</a>",
			
			"<a id='projet' href='/suite_project/projet?btn=suiviprojet'>Projets</a>",
			"<a href='/suite_project/projet?btn=creerprojet'>Nouveau</a>",
			"<a href='/suite_project/projet?btn=gerertache'>Taches</a>",
			
			"<a id='pub' href='/suite_project/publication?btn=pub'>Publications</a>"
	};
	String[] mainAccessAllow={
			"<a href='/suite_project/programme?btn=progr'><img class='img' src='./ressources/newProg.jpg'/>Programmes</a>",
			"<td class='menu_gestiontitrebtn'><input id='btnEdit' class='btnImgEdit' type='submit' src='././ressources/modifier.png' name='btn' value='edit' /></td>",
			"<td class='menu_gestiontitrebtn'><a href='/suite_project/programme?btn=creerprogr'><img class='img' src='ressources/add.jpg'/></a></td>",
			"<td class='menu_gestiontitrebtn'><input id='btnDelete' type='image' src='././ressources/del.png' name=enregistrerProgr' value='delete'/></td>",
			
			"<a href='/suite_project/action?btn=action'><img class='img' src='./ressources/newAction.jpg' />Actions</a>",
			"<td class='menu_gestiontitrebtn'><input title='éditer un élément' id='btnEdit' class='btnImgEdit' type='submit' src='././ressources/modifier.png' name='enregistrerAction' value='edit' /></td>",
			"<td class='menu_gestiontitrebtn'><a title='Ajouter un élément' href='/suite_project/action?btn=creeraction'><img class='img' src='ressources/add.jpg'/></a></td>",
			"<td class='menu_gestiontitrebtn'><input title='Supprimer un élément' id='btnDelete' type='image' src='././ressources/del.png' name='enregistrerAction' value='delete'/></td>",
			
			"<a href='/suite_project/projet?btn=suiviprojet'><img class='img' src='./ressources/suiviProject.jpg' />Projets</a>",
			"<td class='menu_gestiontitrebtn'><a title='Ajouter un élément' href='/suite_project/projet?btn=creerprojet'><img class='img' src='ressources/add.jpg'/></a></td>",
			"<td class='menu_gestiontitrebtn'><input title='éditer un élément' id='btnEdit' class='btnImgEdit' type='submit' src='././ressources/modifier.png' name='enregistrerProj' value='editer' /></td>",
			"<td class='menu_gestiontitrebtn' align='center'><img title='Generer fiche' id='btnPrint'  src='././ressources/optFicheTDR.png'/></td>",
			"",
			"<td class='menu_gestiontitrebtn' align='center'><a href='/suite_project/projet?btn=gerertache' title='calandrier' type='image' ><img id='btnTache' src='././ressources/optTask.png'/></a></td>",
			"<td class='menu_gestiontitrebtn'><input title='Supprimer un élément' id='btnDelete' type='image' src='././ressources/del.png' name='enregistrerProj' value='delete'/></td>",
			"",
			
			"<a href='#'><img class='img' src='./ressources/gestionMedia.jpg' class='optionBloquee'/>gestion des medias</a>",
			" ",
			" ",
			
			"<a href='/suite_project/publication?btn=pub'><img class='img' src='./ressources/newPubl.png' />Publications</a>",
			"<td class='menu_gestiontitrebtn'><input title='éditer un élément' id='btnEdit' class='btnImgEdit' type='submit' src='././ressources/modifier.png' name='btn' value='editerPub' /></td>",
			"<td class='menu_gestiontitrebtn'><a href='/suite_project/publication?btn=creerpub'><img class='img' src='ressources/add.jpg'/></a></td>",
			"<td class='menu_gestiontitrebtn'><input id='btnDelete' type='image' src='././ressources/del.png' name='btn' value='deletePub'/></td>",
		
			
			"<a href='/suite_project/utilisateur?btn=gereruser'><img class='img' src='./ressources/editUser.jpg' />Gestion utilisateurs</a>",
			"<td class='menu_gestiontitrebtn'><input title='éditer un élément' id='btnEdit' class='btnImgEdit' type='submit' src='././ressources/modifier.png' name='operation' value='editer' /></td>",
			"<td class='menu_gestiontitrebtn'><a title='Ajouter un élément' href='/suite_project/utilisateur?btn=creeruser'><img class='img' src='ressources/add.jpg'/></a></td>",
			"<td class='menu_gestiontitrebtn' align='center'><input title='Bloquer l'utilisateur' id='btnBloquer' type='image' src='././ressources/bloquer.png' name='enregistrerProj' value='bloquer' /></td>",
			"<td class='menu_gestiontitrebtn' align='center'><input title='Debloquer l'utilisateur' id='btnDebloquer' type='image' src='././ressources/debloquer1.png' name='enregistrerProj' value='debloquer' /></td>",
			"<td class='menu_gestiontitrebtn'><input title='Supprimer un élément' id='btnDelete' type='image' src='././ressources/del.png' name='enregistrerProj' value='save1'/></td>",
			
			"<a href='#'><img class='img' src='./ressources/parametrage.PNG' class='optionBloquee' />Parametrage</a>",
			" ",
			" ",
			" ",
			
			"<a href=''><img class='img' src='./ressources/statistique.jpg' />statistiques</a>",
			
			"<a href='/suite_project/helpContent'><img class='img' src='./ressources/help.jpg' />aide</a>"
	};
	String[] mainAccessBloq={
			"<img title='Acces interdit' class='img' src='./ressources/accesBloq.png' />Programmes",
			"<td></td>",
			"<td></td>",
			"<td></td>",
			
			"<img title='Acces interdit' class='img' src='./ressources/accesBloq.png' />Actions",
			"<td></td>",
			"<td></td>",
			"<td></td>",
			
			"<img title='Acces interdit' class='img' src='./ressources/accesBloq.png' />Projets",
			"<td></td>",
			"<td></td>",
			"<td></td>",
			"<td></td>",
			"<td></td>",
			"<td></td>",
			"<td></td>",
			
			"<img title='Acces interdit' class='img' src='./ressources/accesBloq.png' />gestion des medias",
			"<td></td>",
			"<td></td>",
			
			"<img title='Acces interdit' class='img' src='./ressources/accesBloq.png' />Publications",
			"<td></td>",
			"<td></td>",
			"<td></td>",
			
			"<img title='Acces interdit' class='img' src='./ressources/accesBloq.png' />Gestion utilisateurs",
			"<td></td>",
			"<td></td>",
			"<td></td>",
			"<td></td>",
			"<td></td>",
			
			"<img title='Acces interdit' class='img' src='./ressources/accesBloq.png' />Parametrage",
			"<td></td>",
			"<td></td>",
			"<td></td>",
			
			"<img title='Acces interdit' class='img' src='./ressources/accesBloq.png' />statistiques",
			
			"<img title='Acces interdit' class='img' src='./ressources/accesBloq.png' />aide"
	};
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		p.encodage(request);
		HttpSession session = request.getSession();
		try {
			c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
			try {
				p.fermerConnexion(c);
				this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
		  		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Erreur de fermeture de la connection à la BD.");
				this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
		  		
				e.printStackTrace();
			}
		}else{
			
			System.out.println("utilisateur "+session.getAttribute("code_pers"));
			String query="SELECT designation, intitule FROM programme";
			String dataList="";
			String query1="SELECT designation, intitule FROM programme";
			String dataList1="";
			String query2="SELECT designation, intitule FROM action";
			String dataList2="";
			String query3="SELECT titre FROM information";
			String dataList3="";
			String query4="SELECT designation FROM projet";
			String dataList4="";
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
				result = p.execReqLecture(c,requete2);
				while(result.next()){
					i++;
				}
				
				String codeR="";
				result = p.execReqLecture(c,requete3);
				while(result.next()){
					codeR=result.getString("code_role");
				}
				result = p.execReqLecture(c,requete4+session.getAttribute("code_pers")+"'");
				while(result.next()){
					acces=result.getString("profil");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String profil=(String)session.getAttribute("profil");
			//System.out.println(profil);
			String[] acc=profil.split("__");

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
			
			request.setAttribute("dataList1", dataList1);
			request.setAttribute("dataList2", dataList2);
			request.setAttribute("dataList3", dataList3);
			request.setAttribute("dataList4", dataList4);
			String ii=i.toString();
			request.setAttribute("nbCmptAValider", ii);
			request.setAttribute("dataList", dataList);
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	public String[] acces(String accesFromBD){
		//System.out.println(accesFromBD);
		String[] access=new String[100];//36 pour le menu principal //6 pour le menu superieur
		String[] aRow = accesFromBD.split("-");
		int l=0;
		String[] anElement;
		int[] iValues=new int[9];
		iValues[0]=4;//programme 0
		iValues[1]=4;//action 4
		iValues[2]=8;//projet 8
		iValues[3]=3;//media 16
		iValues[4]=4;//publication 19
		iValues[5]=6;//user 23
		iValues[6]=4;//parametre 29
		iValues[7]=1;//statistique 33
		iValues[8]=1;//aide 34
		
		for(int i=0;i<9;i++){
			//System.out.println(aRow[i]);
			int value=0;
			anElement = aRow[i].split("_");
			for(int j=0;j<iValues[i];j++){
				//System.out.println(anElement[j]);
				if(anElement[j].equals("1")){
					access[l]=mainAccessAllow[l];
					switch (l){
						case 0 : access[35]=topMenuAllow[0];
						case 4 : access[36]=topMenuAllow[1];
						case 8 : access[37]=topMenuAllow[2];
						case 9 : access[38]=topMenuAllow[3];
						case 10 : access[39]=topMenuAllow[4];
						case 19 : access[40]=topMenuAllow[5];
					}
				}else{
					access[l]=mainAccessBloq[l];
					switch (l){
						case 0 : access[35]=" ";
						case 4 : access[36]=" ";
						case 8 : access[37]=" ";
						case 9 : access[38]=" ";
						case 10 : access[39]=" ";
						case 19 : access[40]=" ";
					}
				}
				l++;
			}
		}
		return access;
	}

	/**
	 * @return the acces
	 */
	public String getAcces() {
		return acces;
	}

	/**
	 * @param acces the acces to set
	 */
	public void setAcces(String acces) {
		this.acces = acces;
	}

	/**
	 * @return the mainAccessAllow
	 */
	public String[] getMainAccessAllow() {
		return mainAccessAllow;
	}

	/**
	 * @param mainAccessAllow the mainAccessAllow to set
	 */
	public void setMainAccessAllow(String[] mainAccessAllow) {
		this.mainAccessAllow = mainAccessAllow;
	}

	/**
	 * @return the mainAccessBloq
	 */
	public String[] getMainAccessBloq() {
		return mainAccessBloq;
	}

	/**
	 * @param mainAccessBloq the mainAccessBloq to set
	 */
	public void setMainAccessBloq(String[] mainAccessBloq) {
		this.mainAccessBloq = mainAccessBloq;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the topMenuAllow
	 */
	public String[] getTopMenuAllow() {
		return topMenuAllow;
	}

	/**
	 * @param topMenuAllow the topMenuAllow to set
	 */
	public void setTopMenuAllow(String[] topMenuAllow) {
		this.topMenuAllow = topMenuAllow;
	}

}
