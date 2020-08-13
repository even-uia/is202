/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is202;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author evenal
 */
@WebServlet(name = "RequestUsername", urlPatterns = {"/RequestUsername"})
public class RequestUsername extends AbstractServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            writeResponse(request, response, "Who are you?");
        }
    }

    protected void writeBody(HttpServletRequest req, PrintWriter out) {
        out.println("<h3>Who are you?</h3>");
        out.println("<form action='ReceiveUsername'>");
        out.println("<p>Please Enter your name here:"
                + " <input type='text' name='username' />");
        out.println("<br/");
        out.println("<button type='submit' value='sendresponse'");
        out.println("</form>");
    }
}
