/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is202.web.payara.maventest;

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
@WebServlet(name = "ReceiveUsername", urlPatterns = {"/ReceiveUsername"})
public class ReceiveUsername extends AbstractServlet {

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
        String username = request.getParameter("username");
        String greeting = "Welcome " + username;
        request.setAttribute("greeting", greeting);
        writeResponse(request, response, greeting);
    }

    protected void writeBody(HttpServletRequest req,
                             PrintWriter out) {
        String greeting = (String) req.getAttribute("greeting");
        out.println("<h2>" + greeting + "</h2>");

    }
}
