package Printers;

import java.io.PrintWriter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hallgeir
 */
public class PersonPrinter implements StringConstants {
    
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    
 
 public void printPersonForm(int pnr, String lastName, String firstName, String email, PrintWriter out)
    {
        skrivHtmlHeader(out, "Persons");
        out.format(FORM, "personStore");           
        // -------------- SNR  ------------------------------ 
        out.format(DIV, "ledetekst");
        out.println ("Person number: " +"</div>");
        
        out.format(DIV, "inn");
        out.format(INPPROT, "text", "pnr", pnr);
        out.println("</div>");

        // --------------   ------------------------------ 
        out.format(DIV, "ledetekst");
        out.println ("LastName: " +"</div>");
        
        out.format(DIV, "inn");
        out.format(INP, "text", "lastName", lastName);
        out.println("</div>"); 
        
        // --------------Fornavn ------------------------------- 
        out.format(DIV, "ledetekst");
        out.println ("fornavn: " +"</div>");
        
        
        
        out.format(DIV, "inn");
        out.format(INP, "text", "firstName", firstName);
        out.println("</div>"); 
    
        // -------------- email ------------------------------- 
        out.format(DIV, "ledetekst");
        out.println ("Epost: " +"</div>");
        
        out.format(DIV, "inn");
        out.format(INP, "text", "email", email);
        out.println("</div>"); 
    
        
        // -------------- Operatorer -------------------------------
          
        out.format(INPSUB, "Submit", "valg", "Lagre");   // type navn ledetekst
        
        out.format(INPSUB,"Submit", "valg", "List alle");
        
        out.println("</div>");
        
         out.println("</form>");  
        // slutt
        
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

    
    

