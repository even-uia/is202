package rolodex.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rolodex.data.Rolodex;


/**
 *
 * @author evenal
 */
@WebServlet(name = "ShowList", urlPatterns = {"/ShowList"})
public class ShowList extends AbstractRolodexServlet {

    /**
     * This servlet will display the contact list page, which is the main/home
     * page of the application. Before displaying the page, it can carry out
     * database operations (add, change, or remove contacts).
     *
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            // write some debug info to the log file
            writeRequestInfo(request, System.out);
            System.out.flush();
            Rolodex rolodex = getRolodex();
            System.out.println("rolo = " + rolodex);
            ListPage lp = new ListPage(rolodex);
            System.out.println("lp = " + lp);
            lp.writePage(request, out);
        }
    }
}
