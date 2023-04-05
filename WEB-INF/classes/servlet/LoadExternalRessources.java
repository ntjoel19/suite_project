package servlet;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


/**
 * Servlet implementation class LoadExternalRessources
 */
@WebServlet("/LoadExternalRessources")
public class LoadExternalRessources extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(LoadExternalRessources.class.getName());  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadExternalRessources() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try 
		{
 
			DataOutput output = new DataOutputStream( response.getOutputStream() );
			response.setContentType("image/jpeg");
 
			String nomImage = request.getParameter("ImageName");
			System.out.println("chargement de l'image ... "+nomImage);
			File file = null;
 
			FileInputStream in = null;			
 
			String filePath = "/suite_project/article/"+nomImage;
 
 
			file = new File(filePath);				
 
			/*
			 * Dans le cas ou l'image n'est pas pr�sente dans le r�pertoire
			 * On affiche une image par defaut 'Image Introuuvable'
			 */
			HttpSession session = request.getSession();
			if(!file.exists())
			{
				String path = session.getServletContext().getRealPath("")+"/ressource/imageIntrouvable.jpg" ;	
				file = new File(path);
				path = path + file;
			}
 
			in = new FileInputStream(file);
 
			response.setContentLength((int)file.length());
 
			byte buffer[]=new byte[4096];
			int nbLecture;
 
			while( (nbLecture = in.read(buffer)) != -1 )
			{				
				output.write(buffer,0,nbLecture);					
			}	
 
			in.close();
 
		}
		catch (IOException e)
		{
		    logger.error("erreur lors du renvoie du fichier jpg",e);		
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
