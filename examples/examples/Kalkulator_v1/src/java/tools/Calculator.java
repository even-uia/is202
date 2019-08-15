package tools;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hallgeir
 */
public class Calculator {

   public int calculate(int n1, int n2, String calc)
    {
        // 2: Do the calculations
        int result=0;  
        if (calc.contains("Add"))
         {   result = n1+n2;
         }
         else if (calc.contains("Subtract"))
         {   result=n1-n2;
         }
        return result; 
    }
         
}
