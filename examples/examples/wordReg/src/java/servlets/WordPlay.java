/*
 * Logically three things is done in this servlet:  
 *  1: Retrieve data from the form
 *  2: Do the calculations
 *  3: 3: Produce output
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hallgeir
 */
@WebServlet(name = "wordPlay", urlPatterns = {"/wordPlay"})
public class WordPlay extends HttpServlet {

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
            out.println("<title>Welcome </title>");            
            out.println("</head>");
            
            
            // 1: Retrieve data
            String word = request.getParameter("word");
            String doAction = request.getParameter("doAction");

            
            // 2: Act

            if (doAction.contains("Print"))
            {  printWord(word,out);
             }
            else if (doAction.contains("stats"))
            {    
                wordStatistics(word,out);
            }
            
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    public void printWord(String word, PrintWriter out) {
        out.println("Word is : " +word);
    }


    public void wordStatistics(String word, PrintWriter out) {
        out.println("<h1> Word Statistics </h1>");
        int length;
        length = word.length();
        out.println("Length of " +word + " is " +length + "<br>");
        
        // Print backwords
        int runner = length-1; 
        out.println(word +" backwords is:  <br>");
        while (runner >= 0)
        {
            char ch = word.charAt(runner);        
            out.print(ch);
            runner--;
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
