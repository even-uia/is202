package rolodex.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rolodex.data.Rolodex;
import static rolodex.web.AbstractRolodexServlet.writeRequestInfo;


/**
 *
 * @author evenal
 */
@WebServlet(name = "AdminDeleteContact", urlPatterns = {"/AdminDeleteContact"})
public class AdminDeleteContact extends AbstractRolodexServlet {

    protected void doRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            // write some debug info to the log file
            writeRequestInfo(request, System.out);
            Rolodex rolodex = getRolodex();
            // get the request parameters (values from the contact form)
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            rolodex.deleteContact(name);
            new ListPage(rolodex).writePage(request, out);
        } catch (NamingException ne) {
            throw new ServletException(ne);
//        } catch (SQLException se) {
//            throw new ServletException(se);
//        }
        }
    }
}
