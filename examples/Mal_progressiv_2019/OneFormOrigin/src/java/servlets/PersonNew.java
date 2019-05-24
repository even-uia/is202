/*
 * When we have a list of client, there is one possible action, new client
 * If new client is selected this Servlet is activated. 
 * Will activate the class that can print a client form. 
 */
package servlets;

import Classes.DBTools;
import Person.PersonTools;
import Printers.PersonPrinter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hallgeir
 */
@WebServlet(name = "personNew", urlPatterns = {"/personNew"})
public class PersonNew extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");   //HN
        request.setCharacterEncoding("UTF-8");    //
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PersonNew</title>");            
            out.println("</head>");
            out.println("<body>");
           
            DBTools dbVerktoy = new DBTools();
            PersonTools clientTool = new PersonTools();
            
            /*
            Establish the connection object in a 
            TRY with Resources structure 
            */
            try (
                Connection conn = dbVerktoy.getConnection(out);
            )      
            
            { // Start try
            
            int newId = 0;
            
            PersonPrinter personPrinter = new PersonPrinter();
            personPrinter.printPersonForm(newId, "","","", out);
            
            } // end try           // end try          
         catch (SQLException ex) {
                out.println("Not OK" +ex);
         }
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
