/*
 * Logically three things is done in this servlet:  
 *  1: Retrieve data from the form
 *  2: Do the calculations
 *  3: 3: Produce output
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hallgeir
 */
@WebServlet(name = "calculate", urlPatterns = {"/calculate"})
public class Calculate extends HttpServlet {

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
            String n1_string = request.getParameter("n1");
            String n2_string = request.getParameter("n2");
            String calc = request.getParameter("calc");

           
            int n1 = Integer.valueOf(n1_string);
            int n2 = Integer.valueOf(n2_string);
   
            String operator = "";   // will be used for output. 
 
            // 2: Do the calculations
            int result=0;  
            if (calc.contains("Add"))
            {   result = n1+n2;
                operator = "Sum of ";
             }
            else if (calc.contains("Subtract"))
            {   result=n1-n2;
                operator = "Subtraction between ";
            }
            
            // 3: Produce output 
            out.println("<body>");
            out.println("<h1>Calculator </h1>");
            
            out.println("<p>" +operator  + n1 + " and " +n2 + " is " +result + "</p>");
            out.println("This went fine! Selected  " + calc);
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    public int subtraher(int tall1, int tall2) {
        return tall1-tall2;
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
