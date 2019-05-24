package Printers;

import java.io.PrintWriter;
import java.util.ArrayList;
import student.Student;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hallgeir
 */
public class StudentPrinter implements StringConstants {
    
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    
 public void printStudentForm(int snr, String firstName, String lastName, PrintWriter out)
    {
        GeneralPrinter generalPrinter = new GeneralPrinter();
        generalPrinter.printMenuButtons(out);   

        skrivHtmlHeader(out, "Forsikringsavtaler");
        
        out.format(FORM, "StudentStore");           
        // -------------- SNR  ------------------------------ 
        out.format(DIV, "ledetekst");
        out.println ("Student number: " +"</div>");
        
        out.format(DIV, "inn");
        out.format(INPPROT, "text", "snr", snr);
        out.println("</div>");

        // --------------FirstName   ------------------------------ 
        out.format(DIV, "ledetekst");
        out.println ("FirstName: " +"</div>");
        
        out.format(DIV, "inn");
        out.format(INP, "text", "firstName", firstName);
        out.println("</div>"); 
        
        // --------------LastName ------------------------------- 
        out.format(DIV, "ledetekst");
        out.println ("LastName: " +"</div>");
        
        out.format(DIV, "inn");
        out.format(INP, "text", "lastName", lastName);
        out.println("</div>"); 
        
        // -------------- Operatorer -------------------------------
        out.format(DIV, "inn");
         
        out.format(INPSUB, "Submit", "valg", "Lagre");   // type navn ledetekst
        //out.format(INPSUB,"Submit", "valg", "List alle");
        // out.format(INPSUB,"Submit", "valg", "Ny");
        
        // out.format(INPSUB,"reset", "valg", "Clear");

        out.println("</div>");
        
        out.println(" <br>");        
        
        
        // start
         
        // out.format(INPSUB, "Submit", "valg", "New Asset");   
        // out.format(INPSUB,"Submit", "valg", "Generate 5 assets");         
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
       //out.println("<meta charset=\"UTF-8\">");    
       out.println("<link rel=\"stylesheet\" href=\"css.css\">");
            
       out.println("</head>");
 }
    
    
    public void printStudents(PrintWriter out, ArrayList<Student> students)
    {
        skrivHtmlHeader(out, "Students");
        GeneralPrinter generalPrinter = new GeneralPrinter();
        generalPrinter.printMenuButtons(out);
        String STUDENT  = "<li><a href='StudentDetail?snr=%s&firstName=%s&lastName=%s'>%s %s %s</a> </li>"; 

        int snr;
        String firstName;
        String lastName;
        
        for (Student oneStudent : students)
        {
            // oneStudent.print(out);
            snr = oneStudent.getSnr();
            firstName = oneStudent.getFirstName();
            lastName = oneStudent.getLastName();

            out.format(STUDENT,snr,firstName,lastName,snr,firstName,lastName );

            
            
        }
        
        
    }
}

    
    

