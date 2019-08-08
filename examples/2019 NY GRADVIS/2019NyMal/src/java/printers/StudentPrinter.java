/*
    For printing everything related to students

*/
package printers;

import java.io.PrintWriter;

/**
 *
 * @author hallgeir
 */
public class StudentPrinter implements StringConstants {
    
    
/*  Will print form for one student. Data is coming by parameters. 
    Could also have used Session variables that is set earlier.     
    From here, control is passed to StudentStore
*/
 
 public void skrivStudentForm(int snr, String firstName, String lastName, PrintWriter out)
    {       
        skrivHtmlHeader(out, "UiA-Studenter");
        // out.println("<h1> Student </h1>");
        
        out.format(FORM, "studentStore");           
        // -------------- SNR  ------------------------------ 
        out.format(DIV, "ledetekst");
        out.println ("Studentnummer: " +"</div>");
        
        out.format(DIV, "inn");
        out.format(INP, "text", "snr", snr);
        out.println("</div>");

        // -------------- FIRSTNAME  ------------------------------ 
        out.format(DIV, "ledetekst");
        out.println ("Fornavn: " +"</div>");
        
        out.format(DIV, "inn");
        out.format(INP, "text", "firstName", firstName);
        out.println("</div>"); 
        
        // --------------LASTNAME ------------------------------- 
        out.format(DIV, "ledetekst");
        out.println ("Etternavn: " +"</div>");
        
        out.format(DIV, "inn");
        out.format(INP, "text", "lastName", lastName);
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
    }
    
    public void skrivHtmlHeader(PrintWriter out,  String tittel)
 {
      out.println("<!DOCTYPE html>");
       out.println("<html>");
       out.println("<head>");
       out.println("<title>" +tittel +"</title>");  
       out.println("<meta charset=\"UTF-8\">");    
       out.println("<link rel=\"stylesheet\" href=\"css.css\">");
            
       out.println("</head>");
 }    
}

    
    

