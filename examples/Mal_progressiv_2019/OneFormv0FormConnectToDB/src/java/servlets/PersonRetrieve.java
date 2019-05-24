/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Classes.DBTools;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import static java.sql.DriverManager.println;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hallgeir
 */
@WebServlet(name = "personRetrieve", urlPatterns = {"/personRetrieve"})
public class PersonRetrieve extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         
         response.setCharacterEncoding("UTF-8");   //HN
         request.setCharacterEncoding("UTF-8");   // will interpret input in UTF-8 format
        
        Connection conn;
        Statement stmt;
         
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>All Persons:  </title>");            
            out.println("</head>");
            
            
            out.println("<body>");
            out.println("<h1>");
            String hent = request.getParameter("hent");
            String numberStr = request.getParameter("pnr");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            out.println("<br>PersonNr  " +numberStr );
            out.println("<br>FirstName " +firstName );
            out.println("<br>LastName " +lastName );
            out.println("<br>Email    " +email);
            
            DBTools tool = new DBTools();
           if (hent.contains("DataSource"))
           {
               out.println("Connecting using DataSource");
               conn = tool.getConnection(out);
           }
           else if (hent.contains("Straight"))
           {
               out.println("Connecting Straight forward");
               conn = tool.getConnection(out);
           }
           else
               conn = tool.getConnection(out);
            
                      
            if (conn==null)
                out.println("<br>Connection NOT ok");
            else
            {   out.println("<br>Connection ok");
                 out.println("Connection: " +conn);
            }
           
            String strSelect = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE='BASE TABLE'";
         
            System.out.println("The SQL query is: " + strSelect); // Echo For debugging
            out. println("The SQL query is: " + strSelect);
         
            System.out.println();
            out.println();
 
            try {
                stmt = conn.createStatement();
                ResultSet rset = stmt.executeQuery(strSelect);
 
                // Step 4: Process the ResultSet by scrolling the cursor forward via next().
                //  For each row, retrieve the contents of the cells with getXxx(columnName).
                out.println("The records selected are:" +"<br>");
                int rowCount = 0;
                while(rset.next()) {   // Move the cursor to the next row, return false if no more row
                    String table_Name = rset.getString("TABLE_NAME");
                    out.println(rowCount +": " + table_Name +"<br>");
                    ++rowCount;
                 }  // end while
                 out.println("Total number of records = " + rowCount);
         } // end catch     
         catch (SQLException ex) {
                out.println("Ikke hentet fra DB " +ex);
         }

            //Slutt
            
            
            out.println("</h1>");
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
