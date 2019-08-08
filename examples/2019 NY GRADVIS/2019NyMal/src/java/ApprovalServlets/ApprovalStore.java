/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApprovalServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import klasser.ApprovalTool;
import klasser.DBTool;
import klasser.StudentTool;
import printers.StudentPrinter;

/**
 *
 * @author hallgeir
 */
@WebServlet(name = "approvalStore", urlPatterns = {"/approvalStore"})
public class ApprovalStore extends HttpServlet {

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
        request.setCharacterEncoding("utf-8");      // HN for norwegian in request object
        response.setCharacterEncoding("utf-8");     // 
        // response.setContentType("text/html;charset=UTF-8");   //Trenger ikke denne

        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ApprovalLagre</title>");   
            out.println("<link rel='stylesheet' type='text/css' href='css/css.css'>"); 
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>One student and the modules </h1>");
            out.println("We are in: " + request.getContextPath() +"<br>" );
            
            String description;
            String pointsS;
            String snrS; 
            String mnrS;
            String valg; 
            String firstName;
            String lastName;
            
            int points;
            int snr=0; 
            int mnr;
                                
            //Get snr and mnr from Session variables
             HttpSession session = request.getSession();
             
             snr = (Integer) session.getAttribute("snr");            
             //out.println("br><br> Hentet fra Session  snr " +snr);
             mnr = (Integer) session.getAttribute("mnr");      
             //out.println("<br><br> Hentet fra Session  mnr " +mnr);
             
             firstName = (String) session.getAttribute("firstName");      
             //out.println("<br><br> Hentet fra Session  firstName " +firstName);
             
             lastName = (String) session.getAttribute("lastName");      
             //out.println("<br><br> Hentet fra Session  lastName " +lastName);
             
            
            pointsS = request.getParameter("points").trim();
            description = request.getParameter("description");
            valg = request.getParameter("valg");
   
             points = Integer.parseInt(pointsS);
             
            //StudentSkriver studentSkriver  = new StudentPrinter(); 
            ApprovalTool approvalVerktoy = new ApprovalTool();  
                     
            DBTool dbVerktoy = new DBTool();
            Connection conn; 
            
            conn = dbVerktoy.loggInn(out);
                        
            
            if (valg.contains("List"))   
            {
                oneSudentAllModules(snr, firstName, lastName, out, conn);
            }
            
            if (valg.contains("Lagre")) {
           
            approvalVerktoy.updateApproval(snr, mnr, points, description, out, conn);
            oneSudentAllModules(snr, firstName, lastName, out, conn);
            
          }

            dbVerktoy.commit();
            dbVerktoy.close();                     
           
            
            out.println("</body>");
            
            out.println("</html>");
        }
    }
    
        public void oneSudentAllModules(int snr, String firstName, String lastName, PrintWriter out, Connection conn)
         {
              StudentTool studentVerktoy = new StudentTool();        
              StudentPrinter studentSkriver = new StudentPrinter();
              studentSkriver.skrivStudentForm(snr, firstName, lastName, out);
              studentVerktoy.printOneStudentsModules(snr,out, conn);
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
