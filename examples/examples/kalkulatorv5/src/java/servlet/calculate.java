package servlet;

/*      This Servlet is called from index.html. This is the  
 *      "engine" in the calculator
 */

import funksjonalitet.toolbox;               //klassen toolbox har metoder som kalkulatoren, dvs calculate bruker. 
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.*;

/**
 *
 * @author hallgeir
 */
@WebServlet(urlPatterns = {"/calculate"})      //angir url til denne servlet'en
public class calculate extends HttpServlet {   

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
 PS: Usually this Servlet is activated from the calculator form.  
 BUT the first time the servlet is activated is from a direct link from the index page. The form have not been shown.  
     */
    
     private toolbox kalkis;        // toolbox for calculations 
     private int result;          // resultatet av beregningene
    
              
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<Calculator Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            kalkis=new toolbox();
            
            String number1Str;      // retrieved from the form as a string
            String number2Str;
            
            
            /*  First time we are here, we do not come from the form, that is NO INPUT.  
                Then both numbers are equal to null, that is, no String. 
                 
            
            */
            
            number1Str = request.getParameter("tall1");          // number1Str er navnet på input feltet i formen
            number2Str = request.getParameter("tall2");          // 
            String operator = request.getParameter("operator");
            int t1;
            int t2; 
            
    
            if (number1Str==null)
            {
                 t1=0;
                 t2=0; 
                 operator=" ";   
            }
            else
            {
                    t1 = Integer.parseInt(number1Str);
                    t2 = Integer.parseInt(number2Str);
            }
  
            //out.println("Skal regne ut "+operator +number1Str +number2Str);
            
            result = kalkis.regnUt(t1,t2,operator);
            
            /**     Dersom submit knappen Clear er valgt, skal 
             *      alt nullstilles
            */
            
            if (operator.contains("Clear"))
            {
                result=0;
                t1=0;
                t2=0;
            }
            
            kalkis.printForm(out, t1, t2, result, operator);
                   
            
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
