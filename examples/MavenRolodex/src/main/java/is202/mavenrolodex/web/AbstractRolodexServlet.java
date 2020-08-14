package rolodex.web;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rolodex.data.Rolodex;


/**
 * This is the common superclass for the servlets in the application. It exists
 * mainly as a place to put shared methods, to avoid code duplication
 *
 * @author evenal
 */
public abstract class AbstractRolodexServlet extends HttpServlet {

    // This is the method that subclasses must implement to do anything
    protected abstract void doRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    // This method returns the database object
    // The one and only Rolodex object is stored as a context attribute
    // If there is no rolodex a new one will be created and stored
    // as a context attribute, before it is returned.
    protected Rolodex getRolodex() {
        ServletContext context = this.getServletContext();
        Rolodex rolodex = (Rolodex) (context.getAttribute("rolodex"));

        if (rolodex == null) {
            rolodex = new Rolodex();
            context.setAttribute("rolodex", rolodex);
        }
        return rolodex;
    }

    /**
     * Write some information about the request to a PrintWriter. The info
     * printed is: - ContextPath the part of the url that identifies the
     * application - ServletPath the part of the url that identifies the servlet
     * - Parameter name-value pairs
     *
     * This method is suitable for including the info in a page (it should be
     * printed inside a
     * <pre> tag
     * @param req
     * @param out
     */
    public static void writeRequestInfo(HttpServletRequest req, PrintWriter out) {
        out.append("\n\nRequest Info\n");
        out.format("%s=%s\n", "ContextPath", req.getContextPath());
        out.format("%s=%s\n", "ServletPath", req.getServletPath());
        Map<String, String[]> params = req.getParameterMap();
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            out.append(key).append("=[ ");
            for (String val : values) {
                out.append(val).append(" ");
            }
            out.append("]\n");
            out.flush();
        }
    }

    /**
     * This method will write debug info to a PrintStream. Suitable for writing
     * debug info to a PrintStream, for example to System.out
     *
     * @param req
     * @param out
     */
    public static void writeRequestInfo(HttpServletRequest req, PrintStream out) {
        PrintWriter outWriter = new PrintWriter(new OutputStreamWriter(out));
        writeRequestInfo(req, outWriter);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method. Overrides the method inherited
     * from HttpSevlet.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // make sure norwegian (and other european) special characters
        // are handled correctly
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        writeRequestInfo(request, System.out);
        doRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method. See comments in doGet()
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        writeRequestInfo(request, System.out);
        doRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
