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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import klasser.ToolBox;
import student.StudentTools;

/**
 *
 * @author hallgeir
 */
@WebServlet(name = "RetrieveAllStudents", urlPatterns = {"/RetrieveAllStudents"})
public class RetrieveAllStudents extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Retrieve Data</title>");            
            out.println("</head>");
            
            out.println("<body>");
            // out.println("<h1>Retrieve data  </h1>");
            // out.println("<h1> <a href = index.html> Home </a> </h1>");
            String name = request.getParameter("name");
            String numberStr = request.getParameter("number");
            /*
                We are using the same servlet to retrieve all students, 
                select some students by name, and to test if we can run 
                a selection many times. Checking connections to DB
            */            
            if (name==null)
                name="";
            if (numberStr==null)
                numberStr="1";

            int number = Integer.parseInt(numberStr);
            
            //out.println("name" +name +"Number " +number);
            
            StudentTools studentTools = new StudentTools();
            studentTools.studentRetrieve(out,name,number);
           
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
