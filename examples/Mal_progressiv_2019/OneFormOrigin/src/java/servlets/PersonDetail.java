/*
 * HN: activated when one client is selected from the list.  
 * Then everything from this client will be shown, in a form.  
 *  
 */
package servlets;

import Person.PersonTools;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;   
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Classes.DBTools;
import Printers.PersonPrinter;
import java.sql.SQLException;

/**
 *
 * @author hallgeir
 */
@WebServlet(name = "personDetail", urlPatterns = {"/personDetail"})
public class PersonDetail extends HttpServlet {

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
          request.setCharacterEncoding("UTF-8");      // HN for norske tegn. 
          response.setCharacterEncoding("UTF-8");
           response.setContentType("text/html;charset=UTF-8");
          
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>personDetail</title>");   
            out.println("<link rel='stylesheet' type='text/css' href='css/css.css'>"); 
            out.println("</head>");
            out.println("<body>");
            
                        
            // REQUEST OBJECT DATA
            // out.println("Inni PersonDetail");
            String pnrStr  = request.getParameter("pnr");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            out.println("<h1>Details for Person : " +lastName +" " +firstName + "email" +email +"</h1>");
          
            int pnr;
            pnr = Integer.parseInt(pnrStr);

            PersonTools clientTool = new PersonTools();        
            DBTools dbVerktoy = new DBTools();
            
            /*
            Establish the connection object in a 
            TRY with Resources structure 
            */
            try (
                Connection conn = dbVerktoy.getConnection(out);
            )      
            
            { // Start try
            
            
            PersonPrinter personPrinter = new PersonPrinter();
            
            // print one client
            personPrinter.printPersonForm(pnr, firstName, lastName, email, out);
            
         } // end try           // end try           // end try           // end try           // end try           // end try           // end try           // end try           // end try           // end try           // end try           // end try           // end try           // end try           // end try           // end try          
         catch (SQLException ex) {
                out.println("Not retrieved from table CLIENT" +ex);
         }        
            dbVerktoy.close();
           
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
