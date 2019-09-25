/*
 * In this version we do all that has to do 
   with the database connection here.
 * No changes in tomee.xml or context.xml
 * New here is use of Prepared Statement 
 */
package klasser;

import java.io.PrintWriter;
import java.sql.*; 
import printers.StringConstants;
/**
 *
 * @author hallgeir
 */
public class StudentTool implements StringConstants {
    Connection conn;        // Must be defined here as class variables, get their value in the login method
    Statement stmt;
    
    PreparedStatement insertStudent; 
    
    
/*
 *  This method will 
    1: create a PreparedStatement variable
    2: The Connection object has the method prepareStament, that takes an SQL with
       one or more parameters. Our PreparedStatement variable is now refering to this object. 
    2b: Set the parameter values in the PreparedStatement by the method setString, setInt.....
    3: Treat the ResultSet object, that contains the result of the sql query. 
    4: This includes, loop and print all "rows" in the ResultSet
    PS: This is done in a try catch block; What if we cant retrieve data? 
 */
     
  public void printStudents(PrintWriter out, Connection conn)
    { 
        PreparedStatement getStudents; 
         
         try {
                getStudents = conn.prepareStatement("select * from studenthn order by ?");
                getStudents.setString(1,"firstName");
                
                ResultSet rset = getStudents.executeQuery();
 
                // Step 4: Process the ResultSet by scrolling the cursor forward via next().
                //  For each row, retrieve the contents of the cells with getXxx(columnName).
                out.println("The records selected are:" +"<br>");
                int rowCount = 0;
                while(rset.next()) {   // Move the cursor to the next row, return false if no more row
                    String snr = rset.getString("snr");
                    String firstName = rset.getString("firstName");
                    String lastName   = rset.getString("lastName");
                    // out.println(rowCount +": " +snr + " , " + firstName + ", " + lastName +"<br>");
                    out.format(STUDENT,snr,firstName,lastName, snr,lastName,firstName);
                                      
                    ++rowCount;
                 }  // end while
                 out.println("Total number of records = " + rowCount);
                 
                 // After all students are listed, can create a new student
                 out.format(FORM, "studentDetail");           
                 out.format(INPSUB, "Submit","valg", "Ny");
                 out.println("</form>");
                 
         } // end try     
         catch (SQLException ex) {
                out.println("Ikke hentet fra DB " +ex);
         }
   }

  
  public void updateStudent(int snr, String firstName, String lastName, PrintWriter out, Connection conn) {
        out.println("<h1> Update student " + firstName +" " + lastName +"</h1>");  
        PreparedStatement updateStudent; 
         
         try {
             String upd ="update hallgeir.studenthn set firstName =?, lastName =? WHERE snr =?";
          
            // out.println("Nå er upd  " +upd);
            // out.println("Snr er  "+snr);
             updateStudent = conn.prepareStatement(upd);
     
             // out.println("firstName er " +firstName);

             //firstName = fn+firstName+fn;
             // out.println("firstName er " +firstName); 
  
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
  
  
  public void newStudent(int snr, String firstName, String lastName, PrintWriter out, Connection conn) {
      out.println("<h1> New student " + firstName +" " + lastName +"</h1>");  
      PreparedStatement newStudent; 
        
         try {
             String ins ="insert into hallgeir.studenthn (snr,firstName,lastName)  values (?, ?, ?)";
          
             newStudent = conn.prepareStatement(ins);
     
             newStudent.setInt(1,snr);
             newStudent.setString(2,firstName);             
             newStudent.setString(3,lastName);  
                
             out.println(newStudent);
             newStudent.executeUpdate();
             add5ModulesToStudent(snr, out, conn );

      } // end try     
         catch (SQLException ex) {
                out.println("Ikke fått opprettet NY Student " +ex);
         }
  }
  
  
 /*
  When modules are listed, they are all "links", can 
  go into ApprovalDetail
  */
    public void printOneStudentsModules(int snr,PrintWriter out, Connection conn) {
        String APPROVALS  = "<td> <li><a href='approvalDetail?snr=%s&mnr=%s'> %s</a></li> </td>\n"; 
        
        PreparedStatement getApprovals; 
         
         try {
               // out.println("iNNI  printOneStudentsModules, SNR ER " +snr +"<br>");   
               getApprovals = conn.prepareStatement("select * from approval where snr = ?");
                getApprovals.setInt(1,snr);
                
                ResultSet rset = getApprovals.executeQuery();
 
                // Step 4: Process the ResultSet by scrolling the cursor forward via next().
                //  For each row, retrieve the contents of the cells with getXxx(columnName).
                out.println("The Approvals for this student is: " +"<br>");
                int rowCount = 0;
                out.println("<table>");
                out.println("<tr>");
                out.println("<td> Modulno </td>");
                out.println("<td> Points </td>");
                out.println("<td> Description</td>");
                out.println("</tr>");
//                out.println("</table>");

                String points=" ";
                String description = " ";

                while(rset.next()) {   // Move the cursor to the next row, return false if no more row
                    
                    String mnrS = rset.getString("mnr");
                    int mnr = Integer.parseInt(mnrS);
                    points=rset.getString("points");
                    description=rset.getString("description");
                    out.println("<tr>");
                    out.format(APPROVALS, snr,mnr, mnr);
                    out.println("<td>" +points +"</td>");
                    out.println("<td>" +description +"</td>");
                    out.println("</tr>");
                    //out.format("Test" +"<br>");
                    
                    ++rowCount;
                 }  // end while

                    out.println("</table>");
                 out.println("Total number of records = " + rowCount);
         } // end try     
         catch (SQLException ex) {
                out.println("Ikke hentet fra DB Approval" +ex);
         }

    }
            
    /*
        When a student is created, each student will get 5 approvals
        connected. 
        SUGGESTION: Make this flexible, a given number of approvals will 
        be added. 
    */
    public void add5ModulesToStudent(int snr,PrintWriter out, Connection conn )
    {
        PreparedStatement addModulesOneStudent; 
        int pointsNull =0;
        String tom ="";
                             
            try {
                addModulesOneStudent = conn.prepareStatement("Insert into approval (snr, mnr, points, description ) values (?,?,?,?)");
                addModulesOneStudent.setInt(1,snr);
                addModulesOneStudent.setInt(3,pointsNull);
                addModulesOneStudent.setString(4,tom);
                
                int loper=1;
                while (loper < 6)
                {
                addModulesOneStudent.setInt(2,loper);                
                addModulesOneStudent.executeUpdate();
                loper++;    
                }
              
         } // end try     
          catch (SQLException ex) {
                out.println("Ikke lagre i DB New Approval " +ex);
        }
        
    }
 
     public boolean sjekkomStudentfinnes (int snr, PrintWriter out, Connection conn)
    {           boolean ny = false;
                PreparedStatement getStudent; 
         
         try {
                getStudent = conn.prepareStatement("select * from studenthn where snr =?");
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
     
     public void listStudents(PrintWriter out, Connection conn)
    { 
               
        PreparedStatement getStudents; 
         
         try {
                getStudents = conn.prepareStatement("select * from studenthn order by ?");
                getStudents.setString(1,"firstName");
                
                ResultSet rset = getStudents.executeQuery();
 
                // Step 4: Process the ResultSet by scrolling the cursor forward via next().
                //  For each row, retrieve the contents of the cells with getXxx(columnName).
                out.println("The records selected are:" +"<br>");
                int rowCount = 0;
                out.format(LIST,"alle");
                
                while(rset.next()) {   // Move the cursor to the next row, return false if no more row
                    String firstName = rset.getString("firstName");

                    // out.println(rowCount +": " +snr + " , " + firstName + ", " + lastName +"<br>");
                    out.format(ENSTUD,firstName,firstName);
                                      
                    ++rowCount;
                 }  // end while
                 out.println("Total number of records = " + rowCount);
                 out.format(LISTSLUTT);
                 // After all students are listed, can create a new student
                 out.format(FORM, "studentDetail");           
                 out.format(INPSUB, "Submit","valg", "Ny");
                 out.println("</form>");
                 
         } // end try     
         catch (SQLException ex) {
                out.println("Ikke hentet fra DB " +ex);
         }
   }

 
    public void listStudentsLinks(PrintWriter out, Connection conn)
    { 
               
        PreparedStatement getStudents; 
         
         try {
                getStudents = conn.prepareStatement("select * from studenthn order by ?");
                getStudents.setString(1,"firstName");
                
                ResultSet rset = getStudents.executeQuery();
 
                // Step 4: Process the ResultSet by scrolling the cursor forward via next().
                //  For each row, retrieve the contents of the cells with getXxx(columnName).
                out.println("<h1> The records selected are: PS links</h1>" +"<br>");
                int rowCount = 0;
                 out.format(LIST,"alle");
                //out.format(BUTTON,"Studenter");
                
                while(rset.next()) {   // Move the cursor to the next row, return false if no more row
     
                    String snr = rset.getString("snr");
                    String firstName = rset.getString("firstName");
                    String lastName   = rset.getString("lastName");

                   out.format(STUDENTLINK,snr,firstName,lastName,snr,firstName,lastName);

                   // out.format(LINK2,snr,firstName,lastName,snr,firstName,lastName);
                    ++rowCount;
                 }  // end while
                 out.println("Total number of records = " + rowCount);
                 out.format(LISTSLUTT);
                 // After all students are listed, can create a new student
                 out.format(FORM, "studentDetail");           
                 out.format(INPSUB, "Submit","valg", "Ny");
                 out.println("</form>");
                 
         } // end try     
         catch (SQLException ex) {
                out.println("Ikke hentet fra DB " +ex);
         }
   }

 
}// slutt 
    
