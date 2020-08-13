/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package output;

import java.io.PrintWriter;
import output.StringConstants;

/**
 *
 * @author hallgeir
 */
public class Printer implements StringConstants {
      
     /*     printForm will generate the form. . 
            PS: notice that the form is connected to the Servlet calculate.  
            PS2: By generating the form in amethod the form can show both input  
            and results. The methos is called after a calculation.  
     */
     
   
    public void printForm(PrintWriter out, int v1, int v2,int result, String operator)
    {
        printHtmlHeader(out, "UiA");
        out.println("<h1> Calculator from form </h1>");
        String fn = "\"";
        out.println("<div class= "+fn +"form" +fn +">");
        
        out.println("<h1> Calculator: Give two numbers and an opareator </h1>");
        
        
        out.println("<form action= "+fn +"calculate" +fn +"method=" +fn +"post" +fn +">");
        out.println("<div class = \"ledetekst\"> Number 1: </div>");
        
        out.println("<div class = \"inn\"> <input type=\"text\" name = \"v1\" value = \"");
        out.println(v1);
        out.println("\"> </div>"); 
        
        out.println("<div class = \"ledetekst\"> Number 2: </div>");
        out.println("<div class = \"inn\"> <input type=\"text\" name = \"v2\" value = \"");  
        out.println(v2);
        out.println("\"> </div> <br><br>");
        
        out.println("<div class = \"resultat\"> <input type=\"text\" name = \"result\" value = \"");  
        out.println(result);
        out.println("\"> </div>");   
        
        out.println("<div class = \"ledetekst\"> Operator </div>");
        out.println("<br>");
        out.println("<div class = \"inn\">");

        String opadd ="normal";
        String opsub="normal";
        String opmul="normal";
        String opdiv="normal";
              
        if (operator.contains("+"))
            opadd="chosen";
        else if (operator.contains("-"))
            opsub="chosen";
        else if (operator.contains("*"))
            opmul="chosen";
        else if (operator.contains("/"))
            opdiv="chosen";    
           
        
        out.println("<input type=\"Submit\" name=\"operator\" value=\"+\" "
                + "class=\"" + opadd +"\">");        
        
        out.println("<input type=\"Submit\" name=\"operator\" value=\"-\""
                 + "class=\"" + opsub +"\">" );        
        
        out.println("<input type=\"Submit\" name=\"operator\" value=\"*\" "
                + "class=\" " + opmul +"\"> ");        
        out.println("  <input type=\"Submit\" name=\"operator\" value=\"/\""
                + "class=\"" + opdiv +"\"> ");         
        
        out.println("                   ");
        out.println("  <input type=\"Submit\" name=\"operator\" value=\"Clear\">  ");        
        out.println("</div>");  
        out.format(" <input type = \"Submit\" name=\"%s\" value=\"%s\">","operator", "Test");        
        out.println(" <br>");        
        out.println("</form>");  
        out.println("");  
        out.println("</div>");  
    }
    
    //start
  public void printForm2(PrintWriter out, int v1, int v2,int result, String operator) {
          
    out.println("<h1> Calculator: Give two numbers and an operator </h1>");
    
    printHtmlHeader(out, "Calculate PrintForm2");
    out.format(FORM, "calculate");      
   
     out.format(DIV, "form");
   
     
        // -------------- first number  ------------------------------ 
      out.format(DIV, "ledetekst");
      out.println ("Number 1: " +"</div>");
        
      out.format(DIV, "inn");
      out.format(INP, "text", "v1", v1);
      out.println("</div>"); 
        
        // --------------second number ------------------------------- 
       out.format(DIV, "ledetekst");
       out.println ("Number 2: " +"</div>");
        
       out.format(DIV, "inn");
       out.format(INP, "text", "v2", v2);
       out.println("</div>"); 
        
       
       // -------------Result -------------------------------------
       out.println("<br>");
       out.format(DIV, "resultat");
       
       out.format(INP, "text", "result", result);
       out.println("</div>");   

        // -------------- Operatorer -------------------------------       
        String opadd ="normal";
        String opsub="normal";
        String opmul="normal";
        String opdiv="normal";
              
        if (operator.contains("+"))
            opadd="chosen";
        else if (operator.contains("-"))
            opsub="chosen";
        else if (operator.contains("*"))
            opmul="chosen";
        else if (operator.contains("/"))
            opdiv="chosen";    
        
        out.format(INPSUB2, "Submit", "operator", "+", opadd); 
        out.format(INPSUB2, "Submit", "operator", "-", opsub); 
        out.format(INPSUB2, "Submit", "operator", "*", opmul); 
        out.format(INPSUB2, "Submit", "operator", "/", opdiv); 
                
        out.format(INPSUB, "Submit", "operator",  "Clear");        
        
        out.println("</div>");  
        out.println(" <br>");        
        out.println("</form>");  
     }
       
    
    public void printHtmlHeader(PrintWriter out,  String tittel)
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
