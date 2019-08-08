/*
 * Purpose: To see, update one module, for one student, 
 * that is, one module approval. 
 * Come from one student, selected one module 
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
import printers.ApprovalPrinter;

/**
 *
 * @author hallgeir
 */
@WebServlet(name = "approvalDetail", urlPatterns = {"/approvalDetail"})
public class ApprovalDetail extends HttpServlet {

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
            out.println("<title>Servlet ApprovalDetail</title>");
            out.println("<link rel='stylesheet' type='text/css' href='css/css.css'>");             
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ApprovalDetail at " + request.getContextPath() + "</h1>");

            out.println("Inni ApprovalDetail");
            String snrS  = request.getParameter("snr");
            String mnrS =  request.getParameter("mnr");
            
                   
            int snr = Integer.parseInt(snrS);
            int mnr = Integer.parseInt(mnrS);
            
           /*
                From this servlet we got to a POJO, ApprovalPrinter
                To store more information than there is in the form, 
                we use session variables. 
           */
           
           // HN session  
            HttpSession session = request.getSession();
            session.setAttribute("snr", snr);
            session.setAttribute("mnr", mnr);
    //        session.setAttribute("firstName", firstName);
    //        session.setAttribute("lastName", lastName);
            
            out.println("<br><br> In ApprovalDetail, for student no " +snr +", module no :" + "modul" +mnr);
                    
            ApprovalTool approvalTool = new ApprovalTool();        
            DBTool dbTool = new DBTool();
            Connection conn; 
                        
            conn = dbTool.loggInn(out);
            ApprovalPrinter approvalPrinter = new ApprovalPrinter();
            // out.println("<br><br> Rett før ApprovalPrinter.skrivApprovalForm");
            approvalPrinter.skrivApprovalForm(snr, mnr, out, request);
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
