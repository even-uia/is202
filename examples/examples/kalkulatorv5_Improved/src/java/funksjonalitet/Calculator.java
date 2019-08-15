/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funksjonalitet;

/**
 *
 * @author hallgeir
 */
public class Calculator {
    
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
  
    
}
