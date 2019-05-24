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

/**
 *
 * @author hallgeir
 */
@WebServlet(name = "personRetrieve", urlPatterns = {"/personRetrieve"})
public class PersonRetrieve extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");   
        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>All Persons:  </title>");            
            out.println("</head>");
            
            out.println("<body>");
            
            PersonTools personTools = new PersonTools();
            DBTools dbVerktoy = new DBTools();
             /*
            Establish the connection object in a 
            TRY with Resources structure 
            */
            try (
                Connection conn = dbVerktoy.getConnection(out);
            )      
            
            { // Start try
                      
                String hent = request.getParameter("hent");
                String numberStr = request.getParameter("number");
                int toInsert;
                out.println("Number str " +numberStr +"Number int er");
                toInsert = Integer.parseInt(numberStr);
                //number = Integer.parseInt(numberStr);
                //number =35;
                out.println("Number str " +numberStr +"Number int er" +toInsert);
                if (hent !=null) 
                    out.println("hent is true"); 
                if (hent.contains("Personel"))
                    personTools.printClients(out,conn);
                else if (hent.contains("TestData"))     
                    personTools.createTestData(toInsert, out,conn);
                else if (hent.contains("Delete"))
                    personTools.deleteTestData(out, conn);
                       
             } // end try           // end try          
            catch (SQLException ex) {
                out.println("Not OK" +ex);
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
