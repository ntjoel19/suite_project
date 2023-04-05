package servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.Persistance;


/**
 * Servlet implementation class Upload
 */
@WebServlet(name = "upload", urlPatterns = { "/upload" })
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
				maxFileSize=1024*1024*50,      // 50MB
				maxRequestSize=1024*1024*50)   // 50MB
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

public static final String CHAMP_DESCRIPTION = "description";
public static final String CHAMP_FICHIER     = "fichier";
//public static final String CHEMIN        = "/";
public static final int TAILLE_TAMPON = 10240; // 10 ko
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Persistance p = new Persistance();
		Connection c=null;
		HttpSession session = request.getSession();
		try {
			c = p.etablirConnexion("jdbc:postgresql://localhost:5432/suite_project_test_bd","postgres","infoday");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String chemin = "";
		
	    System.out.println("Le chemin est = "+chemin);
	    System.out.println("l'operation est = "+request.getParameter("operation")+" |||| id = "+request.getParameter("idPub"));
		/* Récupération du contenu du champ de description */
	    String description = request.getParameter("description");
	    String titre = request.getParameter("titre");
	    String auteur = request.getParameter("auteur");
	    String dateActuelle = p.dateActuelle();
	    
	    //request.setAttribute( "description", description );
	    /*
	     * Les données reçues sont multipart, on doit donc utiliser la méthode
	     * getPart() pour traiter le champ d'envoi de fichiers.
	     */
	    Part part = request.getPart( CHAMP_FICHIER );
	    System.out.println("le part est : "+part);
	    /*
	     * Il faut déterminer s'il s'agit d'un champ classique 
	     * ou d'un champ de type fichier : on délègue cette opération 
	     * à la méthode utilitaire getNomFichier().
	     */
	    String nomFichier = getNomFichier( part );
	    

	    /*
	     * Si la méthode a renvoyé quelque chose, il s'agit donc d'un champ
	     * de type fichier (input type="file").
	     */
	    if ( nomFichier != null && !nomFichier.isEmpty() ) {
	        String nomChamp = part.getName();
	        
	        /*
	         * Antibug pour Internet Explorer, qui transmet pour une raison
	         * mystique le chemin du fichier local à la machine du client...
	         * 
	         * Ex : C:/dossier/sous-dossier/fichier.ext
	         * 
	         * On doit donc faire en sorte de ne sélectionner que le nom et
	         * l'extension du fichier, et de se débarrasser du superflu.
	         */
	         nomFichier = nomFichier.substring( nomFichier.lastIndexOf( '/' ) + 1 )
	                .substring( nomFichier.lastIndexOf( '\\' ) + 1 );
	         nomFichier = nomFichier.substring(1, nomFichier.length()-1);
	         
	         if(titre!=null){
	        	 nomFichier = dateActuelle+"."+nomFichier.substring( nomFichier.indexOf( '.' ) + 1 ,nomFichier.length());
	        	 System.out.println(nomFichier);
	        	 chemin = "/suite_project/article/";
	 	    	 new File(chemin).mkdirs();
	 	    	String queryInsert="";
	 	    	if(request.getParameter("operation").equals("save")){
		 	    	 queryInsert="INSERT INTO information (code_personne,auteur, type_information, titre, path, contenu_publication, date_de_publication) " +
							"VALUES ("+
							"'"+session.getAttribute("code_pers")+"', "+
							"'"+p.getStringCode(auteur.replaceAll("'", "`"),request)+"', 'publication', "+
							"'"+p.getStringCode(titre.replaceAll("'", "`"),request)+"', "+
							"'"+nomFichier+"', "+
							"'"+description.replaceAll("'", "`")+"', "+
							"'"+p.dateActuelle()+"' )";
	 	    	}else{
	 	    		queryInsert="UPDATE  information " +
							"SET "+
							"code_personne = '"+session.getAttribute("code_pers")+"', "+
							"auteur = '"+p.getStringCode(auteur,request)+"', type_information = 'publication', "+
							"titre = '"+p.getStringCode(titre.replaceAll("'", "`"),request)+"', "+
							"path = '"+nomFichier+"', "+
							"contenu_publication = '"+p.getStringCode(description.replaceAll("'", "`"),request)+"', "+
							"date_de_publication = '"+p.dateActuelle()+"' "
									+ "WHERE id = '"+request.getParameter("idPub")+"'";
	 	    	}
				try {
					p.execReqEcriture(c, queryInsert);
				} catch (SQLException e) {
					e.printStackTrace();
				}
	         }else{//si on voudrais plutot stocker des pieces de projets
	        	 String projet = request.getParameter("projet");
	        	 nomFichier = nomFichier+"."+nomFichier.substring( nomFichier.indexOf( '.' ) + 1 ,nomFichier.length());
	        	 System.out.println(nomFichier);
	        	 chemin = "/suite_project/projet/"+projet+"/";
	 	    	 new File(chemin).mkdirs();
	 	    	 
	         }
	         System.out.println("nomFichier");
	        /* Écriture du fichier sur le disque */
	        ecrireFichier( part, nomFichier, chemin );
	        request.setAttribute( nomChamp, nomFichier );
	    }
	    
	    if ( nomFichier == null ) {
	        /* La méthode a renvoyé null, il s'agit donc d'un champ classique ici (input type="text|radio|checkbox|etc", select, etc). */
	        String nomChamp = part.getName();
	        /* Récupération du contenu du champ à l'aide de notre nouvelle méthode */
	        String valeurChamp = getValeur( part );
	        request.setAttribute( nomChamp, valeurChamp );
	    } else if ( !nomFichier.isEmpty() ) {
	        /* La méthode a renvoyé quelque chose, il s'agit donc d'un champ de type fichier (input type="file"). */
	        
	    }
		if(titre!=null){
			
			String profil=(String)session.getAttribute("profil");
			System.out.println("profil utilisateur"+profil);
			String[] acces=profil.split("__");
			request.setAttribute("top1", acces[35]);
			request.setAttribute("top2", acces[36]);
			request.setAttribute("top3", acces[37]);
			request.setAttribute("top4", acces[38]);
			request.setAttribute("top5", acces[39]);
			request.setAttribute("top6", acces[40]);
			
			request.setAttribute("edit", acces[20]);
			request.setAttribute("add", acces[21]);
			request.setAttribute("suppr", acces[22]);
			
			String[] trow=p.rechargerTab(c,"information","auteur titre contenu_publication date_de_publication","contenu_publication",null);
			int nb=Integer.parseInt(trow[1]);
			request.setAttribute("tr", trow[0]);
			request.setAttribute("nb", trow[1]);
			try {
				p.fermerConnexion(c);
				this.getServletContext().getRequestDispatcher( "/WEB-INF/admin/publications.jsp" ).forward( request, response );
				
			} catch (SQLException e) {
				this.getServletContext().getRequestDispatcher( "/WEB-INF/admin/publications.jsp" ).forward( request, response );
				
				e.printStackTrace();
			}
		}
	}
	
	/* 
	 * Méthode utilitaire qui a pour unique but d'analyser l'en-tête "content-disposition",
	 * et de vérifier si le paramètre "filename"  y est présent. Si oui, alors le champ traité
	 * est de type File et la méthode retourne son nom, sinon il s'agit d'un champ de formulaire 
	 * classique et la méthode retourne null. 
	 */
	private static String getNomFichier( Part part ) {
	    /* Boucle sur chacun des paramètres de l'en-tête "content-disposition". */
		System.out.println(part.getHeader( "content-disposition" ));
	    for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
	    	/* Recherche de l'éventuelle présence du paramètre "filename". */
	        if ( contentDisposition.trim().startsWith("filename") ) {
	            /* Si "filename" est présent, alors renvoi de sa valeur, c'est-à-dire du nom de fichier. */
	            return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 );
	        }
	    }
	    /* Et pour terminer, si rien n'a été trouvé... */
	    return null;
	}
	
	/*
	 * Méthode utilitaire qui a pour but d'écrire le fichier passé en paramètre
	 * sur le disque, dans le répertoire donné et avec le nom donné.
	 */
	private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
		 /* Prépare les flux. */
	    BufferedInputStream entree = null;
	    BufferedOutputStream sortie = null;
	    try {
	        /* Ouvre les flux. */
	        entree = new BufferedInputStream( part.getInputStream(), TAILLE_TAMPON );
	        sortie = new BufferedOutputStream( new FileOutputStream( new File( chemin + nomFichier ) ),TAILLE_TAMPON );

	        /*
	         * Lit le fichier reçu et écrit son contenu dans un fichier sur le
	         * disque.
	         */
	        byte[] tampon = new byte[TAILLE_TAMPON];
	        int longueur;
	        while ( ( longueur = entree.read( tampon ) ) > 0 ) {
	            sortie.write( tampon, 0, longueur );
	        }
	    } finally {
	        try {
	        	System.out.println("sortie = "+sortie);
	            sortie.close();
	        } catch ( IOException ignore ) {
	        }
	        try {
	            entree.close();
	        } catch ( IOException ignore ) {
	        }
	    }
	}
	
	/*
	 * Méthode utilitaire qui a pour unique but de lire l'InputStream contenu
	 * dans l'objet part, et de le convertir en une banale chaîne de caractères.
	 */
	private String getValeur( Part part ) throws IOException {
	    BufferedReader reader = new BufferedReader( new InputStreamReader( part.getInputStream(), "UTF-8" ) );
	    StringBuilder valeur = new StringBuilder();
	    char[] buffer = new char[1024];
	    int longueur = 0;
	    while ( ( longueur = reader.read( buffer ) ) > 0 ) {
	        valeur.append( buffer, 0, longueur );
	    }
	    return valeur.toString();
	}

}
