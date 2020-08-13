package rolodex.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rolodex.data.Rolodex;


/**
 * This method will display the Contact form page. It uses a page object to
 * write the page (in the same way as ShowList does. See the ContatctPage class
 * for explanation of the page.
 *
 * @author evenal
 */
@WebServlet(name = "ShowContact", urlPatterns = {"/ShowContact"})
public class ShowContact extends AbstractRolodexServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String name = request.getParameter("name");
            Rolodex rolodex = getRolodex();
            new ContactPage(rolodex, name).writePage(request, out);
        }
    }
}
