package Printers;

import java.io.PrintWriter;
import java.util.ArrayList;
import student.Student;

/*
 * Purpose of this class is to handle all printing related to students. 
 * The class extends the GeneralPrinter. 
 * It may print a form, f.ex when we list many students, and select one. 
 * Or list many students, and create a link, so selected student will 
 * be shown in a form when selected. 
 */

/**
 *
 * @author hallgeir
 */
public class StudentPrinter extends GeneralPrinter implements StringConstants {
   
/*
    When we for example have selcted one student, where each line
    represents a student, as a link, this method will be executed to show 
    information. 
    */    
 public void printStudentForm(int snr, String firstName, String lastName, PrintWriter out)
    {
        // skrivHtmlHeader(out, "Forsikringsavtaler");
        
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
        
         out.println("</form>");  
        
        out.println("");  
        out.println("</div>");  
    }
    

 /*
    Based on an incoming ArrayList of Students, a list of students
    will be printed. Each student is represented by a link to this particular student. 
 */
    public void printStudents(PrintWriter out, ArrayList<Student> students)
    {   // Notice how declararation and use of this String is, 
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

    
    

