/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import klasser.HttpServletProject;
import student.StudentTools;

/**
 *
 * @author hallgeir
 */
@WebServlet(name = "RetrieveAllStudents", urlPatterns = {"/RetrieveAllStudents"})
public class RetrieveAllStudents extends HttpServletProject {

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
        request.setCharacterEncoding("UTF-8");   // will interpret input in UTF-8 format
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
               printPage(out, request,"RetrieveAll");      
            
        }
    }
    
    @Override
    public void printBody(PrintWriter out,HttpServletRequest request )
           {
            super.printBody(out, request);
            String name = request.getParameter("name");
            String numberStr = request.getParameter("number");
            
            if (name==null)
                name="";
            if (numberStr==null)
                numberStr="1";

            int number = Integer.parseInt(numberStr);
            
            //out.println("name" +name +"Number " +number);
            
            StudentTools studentTools = new StudentTools();
            studentTools.studentRetrieve(out,name,number);           
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
