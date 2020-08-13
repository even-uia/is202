package printers;

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
public class ModulePrinter implements StringConstants {
    
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
  
 public void skrivModulForm(int mnr, String moduleName, String moduleDescription, PrintWriter out)
    {
         
        skrivHtmlHeader(out, "Modul");
        out.println("<h1> Modul </h1>");
        out.println("<h1> En Modul </h1>");
        
        out.format(FORM, "moduleStore");           
        // -------------- MNR  ------------------------------ 
        out.format(DIV, "ledetekst");
        out.println ("Modulnummer: " +"</div>");
        
        out.format(DIV, "inn");
        out.format(INP, "text", "mnr", mnr);
        out.println("</div>");

        // -------------- moduleName  ------------------------------ 
        out.format(DIV, "ledetekst");
        out.println ("Modulnavn: " +"</div>");
        
        out.format(DIV, "inn");
        out.format(INP, "text", "moduleName", moduleName);
        out.println("</div>"); 
           
        // --------------moduleDescription ------------------------------- 
        out.format(DIV, "ledetekst");
        out.println ("Module description: " +"</div>");
        
        out.format(DIV, "inn");
        out.format(INP, "text", "moduleDescription", moduleDescription);
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

    
    

