/*
 * Come here when we have selected ONE student
 * From the link, data is sent over, through the Request object. 
 * This servlet will 
    1: Get data from the Request object
    2: Establish a Session, and store relevant data in the 
       Session object. 
    3: Get a Connection to the database
    4: StudentPrinter object, and call the methos to print the 
        form for one student. 
    5: Print all Approvals for this student
    6: Close the database
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
import javax.servlet.http.HttpSession;
import klasser.DBTool;
import klasser.StudentTool;
import printers.StudentPrinter;

/**
 *
 * @author hallgeir
 */
@WebServlet(name = "studentDetail", urlPatterns = {"/studentDetail"})
public class StudentDetail extends HttpServlet {

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
            out.println("<title>Servlet studentDetail</title>");   
            out.println("<link rel='stylesheet' type='text/css' href='css/css.css'>"); 
            out.println("</head>");
            out.println("<body>");
            
            out.println("<h1> Inni StudentDetail </h1>");
            String snrS  = request.getParameter("snr");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            // out.println("<h1>Servlet studentDetail at " + request.getContextPath() + "</h1>");
            
            // If we come here because we have selected New Student
            if (snrS ==null)
            {   snrS ="0";
                firstName="";
                lastName="";
            }
                
            int snr = Integer.parseInt(snrS);
            
            /*
            Session: Maybe this is not necessary, but let it be
            like this by now.         
            */
            HttpSession session = request.getSession();
            session.setAttribute("snr", snr);
            session.setAttribute("firstName", firstName);
            session.setAttribute("lastName", lastName);
            
            StudentTool studentTool = new StudentTool();        
            DBTool dbVerktoy = new DBTool();
            Connection conn; 
            
            conn = dbVerktoy.loggInn(out);
            StudentPrinter studentPrinter = new StudentPrinter();
            
            // Skriver en student:
            studentPrinter.skrivStudentForm(snr, firstName, lastName, out);
            // og alle modulene til en student    
            studentTool.printOneStudentsModules(snr,out, conn);
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
