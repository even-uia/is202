/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import Classes.DBTools;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author hallgeir
 *  
 */
@WebServlet(name = "personStore", urlPatterns = {"/personStore"})
public class PersonStore extends HttpServlet {

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
        response.setCharacterEncoding("UTF-8");   //Use utf-8 
        request.setCharacterEncoding("UTF-8");   
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AvtaleLagrer </title>");            
            out.println("</head>");
            out.println("<body>");
            // out.println("<h1>Servlet AvtaleLagrer at " + request.getContextPath() + "</h1>");
            
            String firstName; 
            String lastName; 
            String email;
            String pnrStr;
            String valg;
            
            int pnr;
            pnrStr = request.getParameter("pnr");
            
            pnr = Integer.parseInt(pnrStr);
            
            firstName = request.getParameter("firstName"); 
            lastName = request.getParameter("lastName");
            email = request.getParameter("email");
            valg = request.getParameter("valg");
            
            
            DBTools dbVerktoy = new DBTools();
            PersonTools personTools = new PersonTools();
            
             /*
            Establish the connection object in a 
            TRY with Resources structure 
            */
            try (
                Connection conn = dbVerktoy.getConnection(out);
            )      
            
            { // Start try
            
            if (valg.contains("Lagre"))
                {   // out.println("I PersonStore");
                    boolean finnes;
                    finnes = personTools.checkPersonExist (pnr, out, conn);
                    // If we have selected new, pnr will be 0 (zero)
                    if (pnr == 0)
                        { finnes=false;
                           out.println("NY, finnes settes lik false");
                        }
                    if (finnes)
                    {  // out.println("<br>FINNES OPPDATER <br>");
                        personTools.updatePerson(pnr, lastName, firstName, email, out, conn);
                    }
                    else
                    {   
                        // out.println("<br>------ NY ------- <br>");    
                        personTools.newPerson(pnr, firstName, lastName,email, out, conn);                        
                        //avtaleVerktoy.newAvtale2(pnr, firstName, lastName, out);                        
                    }
                   
                    //HN 181002
                     personTools.printClients(out,conn); 
                }
                   
            else if (valg.contains("List"))
            {
                // Se https://stackoverflow.com/questions/20947806/how-can-i-call-from-one-servlet-file-to-another-servlet-file 
                personTools.printClients(out,conn); 
                //RequestDispatcher rd = request.getRequestDispatcher("hentAvtaler");
                //rd.forward(request,response);
            }
            
            } // end try           // end try           // end try           // end try           // end try           // end try           // end try           // end try           // end try           // end try           // end try           // end try           // end try           // end try           // end try           // end try          
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
