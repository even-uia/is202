/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import klasser.DBTool;
import printers.ModulePrinter;

/**
 *
 * @author hallgeir
 */
@WebServlet(name = "moduleDetail", urlPatterns = {"/moduleDetail"})
public class ModuleDetail extends HttpServlet {

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
          request.setCharacterEncoding("utf-8");      // HN for norske tegn. 
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet moduleDetail</title>"); 
            out.println("<link rel='stylesheet' type='text/css' href='css/css.css'>"); 
            out.println("</head>");
            out.println("<body>");
            
                       
            String mnrS  = request.getParameter("mnr");
            
            // We can come here if we have chosen new Module, then mnr == null, 
            // we have to check for this. 
            if (mnrS == null)
                mnrS = "0";
            
            out.println("<h1>Servlet moduleDetail at " + request.getContextPath() + "</h1>");
            out.println("Valgt er " +mnrS);
            int mnr = Integer.parseInt(mnrS);
            out.println("mnrInt er  " +mnr);

            String moduleName = request.getParameter("moduleName");
            String moduleDescription = request.getParameter("moduleDescription");

            
            ModulePrinter modulSkriver = new ModulePrinter();        
            DBTool dbVerktoy = new DBTool();
            Connection conn; 
            
            conn = dbVerktoy.loggInn(out);
            
            modulSkriver.skrivModulForm(mnr, moduleName, moduleDescription, out);
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
