package servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Download
 */

public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10 ko
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Download() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 /* Lecture du param�tre 'chemin' pass� � la servlet via la d�claration dans le web.xml */
	    String chemin = this.getServletConfig().getInitParameter( "chemin" );
	    
	    /* R�cup�ration du chemin du fichier demand� au sein de l'URL de la requ�te */
	    String fichierRequis = request.getPathInfo();
	    fichierRequis = fichierRequis.substring(1, fichierRequis.length());
	    
	    /* V�rifie qu'un fichier a bien �t� fourni */
	    if ( fichierRequis == null || "/".equals( fichierRequis ) ) {
	        /* Si non, alors on envoie une erreur 404, qui signifie que la ressource demand�e n'existe pas */
	        response.sendError(HttpServletResponse.SC_NOT_FOUND);
	        return;
	    }
	    /* D�code le nom de fichier r�cup�r�, susceptible de contenir des espaces et autres caract�res sp�ciaux, et pr�pare l'objet File */
	    fichierRequis = URLDecoder.decode( fichierRequis, "UTF-8");
	    File fichier = new File( chemin, fichierRequis );
	            
	    /* V�rifie que le fichier existe bien */
	    if ( !fichier.exists() ) {
	    	System.out.println("le chemin est = "+chemin+fichierRequis);
	        /* Si non, alors on envoie une erreur 404, qui signifie que la ressource demand�e n'existe pas */
	        response.sendError(HttpServletResponse.SC_NOT_FOUND);
	        return;
	    }
	    /* R�cup�re le type du fichier */
	    String type = getServletContext().getMimeType( fichier.getName() );

	    /* Si le type de fichier est inconnu, alors on initialise un type par d�faut */
	    if ( type == null ) {
	        type = "application/octet-stream";
	    }
		
	    /* Initialise la r�ponse HTTP */
	    response.reset();
	    response.setBufferSize( DEFAULT_BUFFER_SIZE );
	    response.setContentType( type );
	    response.setHeader( "Content-Length", String.valueOf( fichier.length() ) );
	    response.setHeader( "Content-Disposition", "attachment; filename=\"" + fichier.getName() + "\"" );
	    
	    /* Pr�pare les flux */
	    BufferedInputStream entree = null;
	    BufferedOutputStream sortie = null;
	    try {
	        /* Ouvre les flux */
	        entree = new BufferedInputStream( new FileInputStream( fichier ), DEFAULT_BUFFER_SIZE );
	        sortie = new BufferedOutputStream( response.getOutputStream(), DEFAULT_BUFFER_SIZE );
	     
	        /* Lit le fichier et �crit son contenu dans la r�ponse HTTP */
	        byte[] tampon = new byte[DEFAULT_BUFFER_SIZE];
	        int longueur;
	        while ( ( longueur= entree.read( tampon ) ) > 0 ) {
	            sortie.write( tampon, 0, longueur );
	        }
	    } finally {
	        try {
	            sortie.close();
	        } catch ( IOException ignore ) {
	        }
	        try {
	            entree.close();
	        } catch ( IOException ignore ) {
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
