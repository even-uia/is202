/*
 * Purpose of this class is to have a class for printing. 
 * Other classes will inherit from this class. 
 * Notice how the variables from the Interface StringConstants are 
 * "implemeted" here in this class. 
 */
package Printers;


import java.io.PrintWriter;

/**
 *
 * @author hallgeir
 */
public class GeneralPrinter  implements StringConstants {
    
      
    public void printMenuButtons(PrintWriter out) 
    {
        out.println("<form>");
        out.format(INPSUB5, "Submit", "index.html", "Index Page");
        out.format(INPSUB5, "Submit", "Menu", "Home");
        out.format(INPSUB5, "Submit", "NewStudent", "New Student");
        out.format(INPSUB5, "Submit", "retrieveStudents.html", "List Students");
        out.println("</form>");     
               
    }
    
     public void skrivHtmlHeader(PrintWriter out,  String tittel)
 {
       out.println("<!DOCTYPE html>");
       out.println("<html>");
       out.println("<head>");
       out.println("<title>" +tittel +"</title>");  
       //out.println("<meta charset=\"UTF-8\">");    
       out.println("<link rel=\"stylesheet\" href=\"css.css\">");            
       out.println("</head>");
 }
}
