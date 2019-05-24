/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Printers.StudentPrinter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import klasser.ToolBox;

/**
 *
 * @author hallgeir
 */
@WebServlet(name = "StudentDetail", urlPatterns = {"/StudentDetail"})
public class StudentDetail extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StudentDetail</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StudentDetail at " + request.getContextPath() + "</h1>");
   // hn start ================================
               /*
                Two ways we may come here: 
                1: from the list of clients, we have selected one client. 
                2: from an update of one asset, for one client, return to
                   this overwiew of one client
            */
            
            /*
                IF DATA IN REQUEST OBJECT USE THESE, 
                ELSE USE SESSION OBJECT
            */
            
            // REQUEST OBJECT DATA
            // out.println("Inni ClientDetail");
            String snrS  = request.getParameter("snr");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            out.println("<h1>Details for client : " +firstName +" " +lastName +"</h1>");
            
            HttpSession session = request.getSession();
            int snr;
            
            // String avtalenrStr; 
            // If we come here because we have selected New Student
            // or from update of assets.  
            if (snrS == null)  // No data in request object 
            {
                out.println("henter fra session");
                snr = (Integer) session.getAttribute("snr");
                firstName = (String) session.getAttribute("firstName");
                lastName = (String) session.getAttribute("lastName");
                out.println(" session var " +snr +firstName + lastName);
            
            }
            else //Means data in request object
            {
                //out.println(" a");
                //out.println("nå er den " +avtalenr);
                snr = Integer.parseInt(snrS);
            }    

            // out.println(" b");

            // HN Session
            out.println("clientDetail ");
            
            //HttpSession session = request.getSession();
            session.setAttribute("snr", snr);
            session.setAttribute("firstName", firstName);
            session.setAttribute("lastName", lastName);
            
           // ClientTools clientTool = new ClientTools();        
            ToolBox toolBox = new ToolBox();
            
            /*
            Establish the connection object in a 
            TRY with Resources structure 
            */
            try (
                Connection conn = toolBox.loggInn(out);
            )      
            
            { // Start try
            
            
            StudentPrinter studentPrinter = new StudentPrinter();
            
            // print one client
            studentPrinter.printStudentForm(snr, firstName, lastName, out);
            
            
         } // end try          
         catch (SQLException ex) {
                out.println("Not retrieved from table student" +ex);
         }        
           // toolBox.close();
           
            
            
   //  hn slutt ===================================         
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
