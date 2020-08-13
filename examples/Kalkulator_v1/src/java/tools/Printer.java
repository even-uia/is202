/*
 * Purpose: To produce output for calculations
 */
package tools;

import java.io.PrintWriter;

/**
 * @author hallgeir
 */
public class Printer {
    
    public void printCalc(int n1, int n2, String operator, int result, PrintWriter out)
    {           
        out.println("<body>");
        out.println("<h1>Calculator </h1>");
        String printLine=""; 
         
        switch(operator) 
        { 
            case "Add": 
                printLine = (n1 + " + " +n2 +" equals  " +result +"<br>");
                break; 
            case "Subtract": 
                printLine = (n1 + " - " +n2 +" equals  " +result +"<br>" );
                break; 
            default: 
                System.out.println("no match"); 
        } 

        out.println("<h2>" + printLine +"</h2>");
        
           
       out.println("</body>");
       out.println("</html>");
    }
}
