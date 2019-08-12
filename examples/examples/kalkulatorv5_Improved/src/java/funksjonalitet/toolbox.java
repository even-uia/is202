/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funksjonalitet;

import java.io.PrintWriter;
import output.StringConstants;

/**
 *
 * @author hallgeir
 */
public class toolbox implements StringConstants {
    
     public int regnUt(int tall1, int tall2, String operator)
    {
        if (operator.contains("+"))
            return (tall1+tall2);
        else if (operator.contains("-"))
            return (tall1-tall2);
        else if (operator.contains("*"))
            return (tall1*tall2);
        else if (operator.contains("/"))
            return (tall1/tall2);
        else return -99;
        
    }
     
     /*     skrivForm2 er metoden i verktøykassa som skriver formen. 
            PS: legg merke til at formen er knyttet til servleten 
            beregn, 
            PS2:  Ved å generere formen i en metode, kan formen vise både input 
            og resultat. Denne kalles når en beregning er gjort. (kalt metoden over, 
            dvs regnUt(). 
     */
     
   
    public void printForm(PrintWriter out, int v1, int v2,int resultat, String operator)
    {
        skrivHtmlHeader(out, "UiA");
        out.println("<h1> Calculator from form3 </h1>");
        String fn = "\"";
        out.println("<div class= "+fn +"form" +fn +">");
        
        out.println("<h1> Kalkulator: Oppgi to tall og en operator </h1>");
        
        
        out.println("<form action= "+fn +"calculate" +fn +"method=" +fn +"post" +fn +">");
        out.println("<div class = \"ledetekst\"> Tall1: </div>");
        

//skriver ut ett input felt, angir først div, har og med value. 
        out.println("<div class = \"inn\"> <input type=\"text\" name = \"tall1\" value = \"");
        out.println(v1);
        out.println("\"> </div>"); 
        
        out.println("<div class = \"ledetekst\"> Tall2 </div>");
        out.println("<div class = \"inn\"> <input type=\"text\" name = \"tall2\" value = \"");  
        out.println(v2);
        out.println("\"> </div> <br><br>");
        
        out.println("<div class = \"resultat\"> <input type=\"text\" name = \"resultat\" value = \"");  
        out.println(resultat);
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
  public void printForm2(PrintWriter out, int v1, int v2,int resultat, String operator) {
          
    out.println("<h1> Calculator: Give two numbers and an operator </h1>");
    
     skrivHtmlHeader(out, "Calculate PrintForm2");
    out.format(FORM, "calculate");      
   
     out.format(DIV, "form");
   
     
        // -------------- first number  ------------------------------ 
      out.format(DIV, "ledetekst");
      out.println ("Tall 1: " +"</div>");
        
      String v1S;
      v1S = Integer.toString(v1);
      out.format(DIV, "inn");
      out.format(INP, "text", "tall1", v1S);
      out.println("</div>"); 
        
        // --------------second number ------------------------------- 
       out.format(DIV, "ledetekst");
       out.println ("Tall 2: " +"</div>");
        
       out.format(DIV, "inn");
       out.format(INP, "text", "tall2", v2);
       out.println("</div>"); 
        
       
       // -------------Result -------------------------------------
       out.println("<br>");
       out.format(DIV, "resultat");
       
       out.format(INP, "text", "resultat", resultat);
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
