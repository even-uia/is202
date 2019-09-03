/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import klasser.DbTools;

/**
 *
 * @author hallgeir
 */
@WebServlet(name = "OrdBehandler", urlPatterns = {"/OrdBehandler"})
public class OrdBehandler extends HttpServlet {

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
            out.println("<title>Servlet OrdBehandler</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Velkommen til ordbehandler</h1>");
           
            // 1: hente data
            String ord;
            String action;
            ord = request.getParameter("ord");
            action = request.getParameter("action");
            
            // 2: Hvis skriv, skrivut, hvis statistikk, finn lengde og skriv baklengs
            if (action.contains("Skriv"))
                out.println("Ordet er " +ord );
            else if (action.contains("Statistikk"))
            {
                out.println("Statistikk ");
                int ordLengde; 
                ordLengde = ord.length();
                out.println("Lengden av " +ord + " er :  " +ordLengde);
                
                int loper;  //skal representere hvor vi er i stringen
                char ch;
                
                loper =ordLengde -1;
                out.println("<br>");
                while (loper >= 0)
                {
                    ch = ord.charAt(loper);
                    out.print(ch);
                    loper--;
                }
                
            }
            else
            {
                
                DbTools dbtools = new DbTools();
                Connection conn = dbtools.loggInn(out);
                out.println("Connection: " +conn);
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
