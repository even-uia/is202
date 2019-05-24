/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import Printers.GeneralPrinter;
import Printers.StudentPrinter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import klasser.ToolBox;

/**
 *
 * @author hallgeir
 */
public class StudentTools {
    
    ToolBox toolBox; 
    
    /*
    Now we will use Prepared Statement
    1: Create a PreparedStatement variable
    2: Create a PreparedStatement through the Connection object
    
    Notice how the Connection object is crated and given value,
    in a try with resources structure. 
    The purpose is that the connection should close and "die"
    when the job is done. To check this we send an parameter
    in, and we will do a full run of the method 
    studentRetrieve(out), the given number of times. 
    */

    /**
     *
     * @param out
     * @param name
     * @return
     */
   
    public ArrayList studentRetrieveAll(PrintWriter out, String name)
    {
        toolBox = new ToolBox();   
        PreparedStatement getStudents;
        ArrayList<Student> students;
        students = new ArrayList<>();
        
        out.println();
        // try with resources  see https://stackoverflow.com/questions/8066501/how-should-i-use-try-with-resources-with-jdbc
        try (Connection conn = toolBox.loggInn(out);
             Statement stmt = conn.createStatement();   
            ) 
        {
            getStudents = conn.prepareStatement("select * from student where firstName like ? or lastName like ? order by firstname");
            String search = "%" +name +"%";
            getStudents.setString(1,search);
            getStudents.setString(2,search);

            // out.println("PreparedStatement object is: " +getStudents);
            ResultSet rset = getStudents.executeQuery();
 
            // Step 4: Process the ResultSet by scrolling the cursor forward via next().
            //  For each row, retrieve the contents of the cells with getXxx(columnName).
            // out.println("The records selected are:" +"<br>");
            
            int rowCount = 0;
            int snr;
            String firstName;
            String lastName;
            Student newStudent;
            
            while(rset.next()) {   // Move the cursor to the next row, return false if no more row
                snr = rset.getInt("snr");
                firstName = rset.getString("firstName");
                lastName = rset.getString("lastName");
                // out.println(rowCount +": Snr er: " +snr +"   "  + firstName +" " +lastName +"<br>");
                newStudent = new Student(snr, firstName,lastName);
                students.add(newStudent);
                ++rowCount;
            }  // end while
            out.println("Total number of records = " + rowCount);
            toolBox.close(conn);
            return students; 
         } // end catch     
         catch (SQLException ex) {
          out.println("Ikke hentet fra DB " +ex);
         }   
        return null;
    }
   
    /*
    The purpose here is to be able to test that we have 
    enough connections. Notice that studetRetrieve uses 
    try with resources. 
    */
    public void studentRetrieve(PrintWriter out, String name, int number)
    {
        StudentPrinter studentPrinter = new StudentPrinter();
        int runner = 1;
        
        while (runner <= number)
        {
            ArrayList<Student> students = new ArrayList();
            // out.println("<h1> Loop index is " +runner +"<br> </h1>");
            students = studentRetrieveAll(out,name);    
            studentPrinter.printStudents(out,students);
            runner++; 
        }    
    }
    
    /*
    studentStore will update a student if it exists, and 
    insert a new if snr = 0; 
    */
    public void studentStore(int snr, String firstName, String lastName, PrintWriter out)
    {   // out.println("Inni StudentStore");
        if (snr==0)
            newStudent(snr,firstName,lastName,out);  
        else
            updateStudent(snr,firstName, lastName,out); 
        
       studentRetrieve(out, "", 1);
    }



 public void updateStudent(int snr, String firstName, String lastName, PrintWriter out) 
 {
        toolBox = new ToolBox();
        PreparedStatement updateStudent;
        // out.println("Inni updateAvtale");
        
         try   (Connection conn = toolBox.loggInn(out);
               )         
         {
            String upd ="update student set firstName =?, lastName =? WHERE snr =?";
                    
            updateStudent = conn.prepareStatement(upd);
       
             updateStudent.setString(1,firstName);
             updateStudent.setString(2,lastName);             
             updateStudent.setInt(3,snr);  
                
             // out.println(updateStudent);
             int ant = updateStudent.executeUpdate();
                
      } // end try     
         catch (SQLException ex) {
                out.println("Ikke fått oppdatert Student " +ex);
         }
  }
 
  
  /*
        Create new student. If this client exists a message is given. 
  */
  public void newStudent(int snr, String firstName, String lastName, PrintWriter out)
  {
      toolBox = new ToolBox();
      PreparedStatement newStudent; 
      // out.println("Inni newStudent:Snr " +snr);

       if (checkIfStudentExist (snr, out) == true)
                {out.println("Student " +snr +" already exist. "); 
                }
             else {
             try 
                 (Connection conn = toolBox.loggInn(out);                     
                 )
             {                       
             String ins ="insert into hallgeir.student (snr,firstName,lastName)  values (?, ?, ?)";        
             newStudent = conn.prepareStatement(ins);
     
             newStudent.setInt(1,snr);
             newStudent.setString(2,firstName);             
             newStudent.setString(3,lastName);  
                
             // out.println(newStudent);
             newStudent.executeUpdate();
            
      } // end try 
                       
         catch (SQLException ex) {
                out.println("Ikke fått opprettet NY Student " +ex);
         }
   } //end if
  }

     public boolean checkIfStudentExist (int snr, PrintWriter out) 
       {
           toolBox = new ToolBox(); 
           boolean ny = false;
           PreparedStatement getStudent; 
         
         try 
             (Connection conn = toolBox.loggInn(out);  
                 )         
         {
              getStudent = conn.prepareStatement("select * from student where snr =?");
              getStudent.setInt(1,snr);
               ResultSet rset;
               rset = getStudent.executeQuery();
           
               ny = rset.next();
               return ny;                 
         } // end try     
         catch (SQLException ex) {
                out.println("Ikke hentet EN fra DB " +ex);
         }
         return ny;
    }
           
}
 


