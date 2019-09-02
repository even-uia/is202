/*
 * All funksjonalitet som har med ord å gjøre, skrive ut, baklengs, lengde....
 */
package klasser;

import java.io.PrintWriter;

/**
 *
 * @author hallgeir
 */
public class WordFunctionality {
    
    /*
        Skriver ordet 
    */
    public void printWord(String ord, PrintWriter out)
    {
        out.println("Ordet er " + ord);
    }
    
    /*
        Skriver ut lengden på ordet, og deretter skrives ordet, teksten baklengs. 
    */
    public void wordStatistics(String ord, PrintWriter out)
    {
        out.println("Statistikk ");
        int ordLengde; 
        ordLengde = ord.length();
        out.println("Lengden av " +ord + " er :  " +ordLengde);
                
        int loper;  //skal representere hvor vi er i stringen
        char ch;
                
        loper =ordLengde -1;
        out.println("<br>");
        while (loper >= 0)
            {
                ch = ord.charAt(loper);
                out.print(ch);
                loper--;
            }              
    }
}
