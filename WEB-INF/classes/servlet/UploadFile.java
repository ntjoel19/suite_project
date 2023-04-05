package servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;


@WebServlet("/UploadFile")
public class UploadFile extends HttpServlet {
        private static final long serialVersionUID = 1L;
        private final String UPLOAD_DIRECTORY = "/suite_project/projet/";

        protected void doPost(HttpServletRequest request,
             HttpServletResponse response) throws ServletException, IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        // process only if it is multipart content
        if (isMultipart) {
                // Create a factory for disk-based file items
                FileItemFactory factory = new DiskFileItemFactory();

                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);
                try {
                // Parse the request
                List<FileItem> multiparts = upload.parseRequest((RequestContext) request);

                for (FileItem item : multiparts) {
                if (!item.isFormField()) {
                String name = new File(item.getName()).getName();
                item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                }
                }
                } 
                catch (Exception e) 
                {
                  e.printStackTrace();
                }
        }
}
}