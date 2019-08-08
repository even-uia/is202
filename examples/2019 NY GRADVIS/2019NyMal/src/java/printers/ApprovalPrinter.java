/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printers;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import klasser.DBTool;
//import static org.apache.openejb.client.Client.request;
/**
 *
 * @author hallgeir
 */
public class ApprovalPrinter implements StringConstants {
    
    public void skrivApprovalForm(int snr,int  mnr,PrintWriter out,HttpServletRequest request) {
 
        DBTool dbVerktoy = new DBTool();
        Connection conn = dbVerktoy.loggInn(out);
        String firstName =" ";
        String lastName =" ";
        String points = " ";
        String description = " ";
     try {
        PreparedStatement getStudent;
        PreparedStatement getApproval;
        
        getStudent = conn.prepareStatement("select * from studenthn where snr = ?");
        getStudent.setInt(1,snr);
        
         ResultSet rset;
         rset = getStudent.executeQuery();

        
        if  (rset.next()) {
            firstName = rset.getString("firstName");
            lastName = rset.getString("lastName");
        }
        
        out.println("<h1> Firstname " +firstName +"</h1>");
        out.println("<h1> Lastname " +lastName +"</h1>");
        
        
        getApproval =  conn.prepareStatement("select * from approval where snr = ? and mnr =?");
        getApproval.setInt(1,snr);
        getApproval.setInt(2, mnr);
        rset = getApproval.executeQuery();

         if  (rset.next()) {
            points = rset.getString("points");
            description = rset.getString("description");
        }
        
        skrivHtmlHeader(out,  "One module");
        out.println("Module for studentnumber " + snr + "  " + firstName + "   " +lastName);
        out.println("<h1> Module " +mnr + "</h1>");
         
        out.format(FORM, "approvalStore");           

        /*  HN: We arrive here, in a POJO, from a servlet. 
            From the calling Servlet, ApprovalDetail, we used session to 
            store snr and mnr. 
        */
                      
        // -------------- points  ------------------------------- 
        out.format(DIV, "ledetekst");       //ledetekst is a class defined in the css
        out.println ("Points " +"</div>");
        
        out.format(DIV, "inn");             //inn defined in CSS
        out.format(INP, "text", "points", points);
        out.println("</div>"); 
        
        // -------------- description  ------------------------------- 
        out.format(DIV, "ledetekst");
        out.println ("Description " +"</div>");
        
        out.format(DIV, "inn");
        out.format(INP, "text", "description", description);
        out.println("</div>"); 

        // -------------- Operatorer -------------------------------
        out.format(DIV, "inn");
         
        out.format(INPSUB, "Submit", "valg", "Lagre");   // type navn ledetekst
        out.format(INPSUB,"Submit", "valg", "List alle");
        out.format(INPSUB,"Submit", "valg", "Clear");

        out.println("</div>");
        
        out.println(" <br>");        
        out.println("</form>");  
        out.println("");  
        out.println("</div>");
        
        } // end try      // end try     
         catch (SQLException ex) {
                out.println("Ikke hentet fra DB " +ex);
         }
    }
   
 
   public void skrivHtmlHeader(PrintWriter out,  String tittel)
 {
      out.println("<!DOCTYPE html>");
       out.println("<html>");
       out.println("<head>");
       out.println("<title>" +tittel +"</title>");  
       out.println("<meta charset=\"UTF-8\">");    
       out.println("<link rel='stylesheet' href='css/css.css'>");
       out.println("</head>");
 }
 
}
