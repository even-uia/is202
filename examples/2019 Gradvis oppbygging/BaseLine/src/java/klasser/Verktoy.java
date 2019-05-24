
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

JDBC JNDI DataSource Servlet
 */
package klasser;

import java.io.PrintWriter;
import java.sql.*; 
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 * @author hallgeir
 */
public class Verktoy {
    Connection conn; 
    Statement stmt;
    
    public void skrivStudenter(PrintWriter out)
    { 
         String strSelect = "select * from student";

         System.out.println("The SQL query is: " + strSelect); // Echo For debugging
         out. println("The SQL query is: " + strSelect);
         
         System.out.println();
         out.println();
 
         try {
             
                ResultSet rset = stmt.executeQuery(strSelect);
 
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
         } // end catch     
         catch (SQLException ex) {
                out.println("Ikke hentet fra DB " +ex);
         }
         
         
   }
      
    /*
    Lagre en student manuell
    */
    public void lagreStudent(PrintWriter out, String firstName, String lastName)
    {         
        String insStudent = "insert into hallgeir.student (firstName,lastName)  values (\"+firstName+\" ,lastName )";
        
        System.out.println("The SQL query is: " + insStudent); // Echo For debugging
        out. println("The SQL query is: " + insStudent);

         try {             
                ResultSet rset = stmt.executeQuery(insStudent);
                out.println("Insert !! " +rset);
         } // end catch     
         catch (SQLException ex) {
                out.println("Ikke LAGRET i DB " +ex);
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
             String ins ="insert into hallgeir.student (firstName,lastName)  values (?, ?)";
          
            newStudent = conn.prepareStatement(ins);
     
             newStudent.setString(1,firstName);             
             newStudent.setString(2,lastName);  
                
             out.println(newStudent);
             newStudent.executeUpdate();
             
      } // end try     
         catch (SQLException ex) {
                out.println("Ikke fått opprettet NY Student " +ex);
         }
        
  }
    
    
   // @Resource DataSource LocalhostDS;
    public void loggInn2(PrintWriter out) {
        try {
         // Step 1: Allocate a database 'Connection' object
         Context cont = new InitialContext();
         DataSource ds = (DataSource)cont.lookup("java:comp/env/jdbc/localhostDS");  
         //DataSource ds = (DataSource)cont.lookup("jdbc/LocalhostDS");
         conn =  ds.getConnection();
 
         // Step 2: Allocate a 'Statement' object in the Connection
         stmt = conn.createStatement();
        }
        catch (SQLException ex ) {
            out.println("Not connected to database " +ex);
        }
        catch (NamingException nex) {
            out.println("Not correct naming" + nex);
        }
            
            
    }
    
}// slutt 
    
