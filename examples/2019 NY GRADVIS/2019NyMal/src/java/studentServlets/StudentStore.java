/*
 * This Servlet is called when Student is going to be updated. 
 * 1: Retrieve data from the Request object
 * 2: Create Connection, DBTool, StudentTool objects
 * 3: Check if student exist, update or new student
 *          - call studentTool.updateStudent(....);
              or   studentTool.newStudent
 *  
 */
package studentServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import klasser.DBTool;
import klasser.StudentTool;
import printers.StudentPrinter;

/**
 *
 * @author hallgeir
 */
    @WebServlet(name = "studentStore", urlPatterns = {"/studentStore"})
public class StudentStore extends HttpServlet {

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
            out.println("<title>Servlet ModulLagrer</title>");   
            out.println("<link rel='stylesheet' type='text/css' href='css/css.css'>"); 
            out.println("</head>");
            out.println("<body>");
            // out.println("<h1>Servlet ModulLagrer at " + request.getContextPath() + "</h1>");
            
            String firstName; 
            String lastName; 
            String snrS;
            String valg; 
              
            snrS = request.getParameter("snr");
            valg = request.getParameter("valg");
            int snr;
            
            if ((snrS ==null) || (valg.contains("Clear")))
            {   snr =0;
                firstName = " ";
                lastName = " ";
            }
            else
                {snr = Integer.parseInt(snrS);
                firstName = request.getParameter("firstName"); 
                lastName = request.getParameter("lastName");
                 }     

            StudentPrinter studentPrinter  = new StudentPrinter(); 
            StudentTool studentTool = new StudentTool();  
           
            DBTool dbTool = new DBTool();
            Connection conn; 
            
            boolean finnes;
            
            conn = dbTool.loggInn(out);
            finnes = studentTool.sjekkomStudentfinnes (snr,out, conn);            

            if (valg.contains("List")) 
                studentTool.printStudents(out, conn); 
            
            else if (valg.contains("Lagre")) {
                if (finnes)
                    studentTool.updateStudent(snr, firstName, lastName, out, conn);
                else 
                    studentTool.newStudent(snr, firstName, lastName, out, conn); 
                // After new or update, will print student and approvals
                studentPrinter.skrivStudentForm(snr, firstName, lastName, out); 
                studentTool.printOneStudentsModules(snr,out, conn);
            }
            dbTool.commit();
            dbTool.close();                     
                        
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
