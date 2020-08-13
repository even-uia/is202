/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import klasser.DbTool;
import klasser.DbFunctionality;
import klasser.WordFunctionality;

/**
 *
 * @author hallgeir
 */
@WebServlet(name = "Ord", urlPatterns = {"/Ord"})
public class Ord extends HttpServlet {

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
            out.println("<title>Servlet LagreOrd</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Diverse om ord </h1>");
            
            // 1: HEND DATA FRA FORMEN
            String ord;
            String action;
            ord = request.getParameter("ord");
            action = request.getParameter("action");
            
            // 2: CONNECTION TIL DATABASEN
            Connection conn;           
            DbTool dbtool = new DbTool();
            conn = dbtool.loggInn(out);
            // out.println("Tebage fra logginn");

            // SJEKK VALG, OG UTFØR I HENHOLD TIL DET
            DbFunctionality dbfunctionality = new DbFunctionality();
            WordFunctionality wordfunctionality = new WordFunctionality(); 

            if (action.contains("Skriv"))
                wordfunctionality.printWord(ord, out);
            else if (action.contains("Statistikk"))
            {
                wordfunctionality.wordStatistics(ord, out);
            }    
            else if (action.contains("Lagre"))
            {
                dbfunctionality.newWord(ord, out, conn);
            }
            else if (action.contains("tabeller"))
            {
                dbfunctionality.showTables(conn, out);
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
