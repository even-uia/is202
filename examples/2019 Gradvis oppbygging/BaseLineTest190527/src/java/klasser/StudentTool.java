
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

JDBC JNDI DataSource Servlet
 */
package klasser;

import java.io.PrintWriter;
import java.sql.*; 
/**
 *
 * @author hallgeir
 * When we retrieve we do this without using Prepared Statement
 * We directly send the sql string for execution. 
 */
public class StudentTool {
   Statement stmt;
   DBTools dbtools;
    
    public void skrivStudenter(PrintWriter out)
    { 
        
        String strSelect = "select * from student";

         out. println("The SQL query is: " + strSelect);
         out.println("<br>");
 
         try {            
             Connection conn;
             dbtools = new DBTools();
             conn = dbtools.loggInn(out); 
             out.println("Conn er " +conn);
             // Step 2: Allocate a 'Statement' object in the Connection
             out.println("før stmt ");
             stmt = conn.createStatement();
             out.println("etter stmt");
             ResultSet rset = stmt.executeQuery(strSelect);
             out.println("rset er" +rset);
             // Step 4: Process the ResultSet by scrolling the cursor forward via next().
             //  For each row, retrieve the contents of the cells with getXxx(columnName).
             out.println("The records selected are:" +"<br>");
             int rowCount = 0;
             while(rset.next()) {   // Move the cursor to the next row, return false if no more row
                    String studentFname = rset.getString("firstName");
                    String studentLname  = rset.getString("lastName");
                    out.println(rowCount +": " + studentFname + ", " + studentLname +"<br>");
                    ++rowCount;
             }  // end while
             out.println("Total number of records = " + rowCount);
             //conn.close();  //PS! Må explisitt lukke DB-connection
         } // end catch     
         catch (SQLException ex) {
                out.println("Ikke hentet fra DB " +ex);
         } 
   }
   

/*
    Lagre en student, med bruk av Prepared Statement
 */    
     public void lagreStudentPP(PrintWriter out, String firstName, String lastName)
    {         
        PreparedStatement newStudent; 
        out.println("Inni lagreStudentPP");
       
        try {
            Connection conn; 
            dbtools = new DBTools();
            conn = dbtools.loggInn(out); 
            
            String ins ="insert into hallgeir.student (firstName,lastName)  values (?, ?)";
          
            newStudent = conn.prepareStatement(ins);
     
            
             newStudent.setString(1,firstName);             
             newStudent.setString(2,lastName);  
                
             out.println(newStudent);
             newStudent.executeUpdate();
             conn.close();
      } // end try     
         catch (SQLException ex) {
                out.println("Ikke fått opprettet NY Student " +ex);
         }
        
  }
   
}// slutt 
    
