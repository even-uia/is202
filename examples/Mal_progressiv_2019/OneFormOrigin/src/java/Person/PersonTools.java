
/*
 * In this version we do all that has to do with the database connection here.
 * Client represents one insurance client
 * New here is use of Prepared Statement 
 */
package Person;

import Printers.PersonPrinter;
import java.io.PrintWriter;
import java.sql.*; 
import Printers.StringConstants;
/**
 *
 * @author hallgeir
 */
public class PersonTools implements StringConstants {
    Connection conn;        // Must be defined here as class variables, get their value in the login method
    Statement stmt;
    
    PreparedStatement insertPerson; 
       
/*
 *  This method will
    Use a String and the out.format.() to manage the print, where we for every student we 
    list will generate a link ti this specific student.
    Will link to the Servlet studentDetail, 
    
    1: create a PreparedStatement variable
    2: The Connection object has the method prepareStament, that takes an SQL with
       one or mor parameters. Our PreparedStatement variable is now refering to this object. 
    2b: Set the parameter values in the PreparedStatement by the method setString, setInt.....
    3: Treat the ResultSet object, that contains the result of the sql query. 
    4: This includes, loop and print all "rows" in the ResultSet
    PS: This is done in a try catch block; What if we cant retrieve data? 
 */
     
  public void printClients(PrintWriter out, Connection conn)
    {   // Be aware how we can set things in the url, and what is acually written, (what we see)
          
        // Oppretter en Tekst, link, med 
        String PERSON  = "<li><a href='personDetail?pnr=%s&firstName=%s&lastName=%s&email=%s'>%s %s %s %s</a> </li>"; 
       
        PreparedStatement getPerson; 
        
         try {
                getPerson = conn.prepareStatement("select * from person order by ?");
                getPerson.setString(1,"pnr");
                                
                ResultSet rset = getPerson.executeQuery();
 
                // Step 4: Process the ResultSet by scrolling the cursor forward via next().
                //  For each row, retrieve the contents of the cells with getXxx(columnName).
                // out.println("The records selected are:" +"<br>");
                
                out.println("<h1> All Persons:  </h1>");
                int rowCount = 0;
                while(rset.next()) {   // Move the cursor to the next row, return false if no more row
                    String pnr = rset.getString("pnr");
                    String firstName = rset.getString("firstName");
                    String lastName   = rset.getString("lastName");
                    String email   = rset.getString("email");
                    // out.println(rowCount +" email " +email +"<br>");
                    int pnrInt = Integer.parseInt(pnr);
                   
                    /*
                    *   PERSON er en string, med (6) 7 parametre.                    
                    *   Bruker denne stringen inni out.format, hvor vi sender med 
                    *   de (6) 7 parametrene. 
                    */
                    out.format(PERSON,pnrInt, firstName,lastName, email, pnrInt, firstName,lastName,email);

                    ++rowCount;
                 }  // end while
                 out.println("Total number of persons = " + rowCount);
                                       
         } // end try      // end try     
         catch (SQLException ex) {
                out.println("Ikke hentet fra DB " +ex);
         }
         
         //  HN FORM TO CALL FOR A NEW CLIENT
            
         out.format(FORM, "personNew");
         out.format(INPSUB,"Submit", "valg", "Ny");
         out.println("</form>");  
   }
  
    /*
        Update given client. All data is given in parameter. 
    */
  
  public void updatePerson(int pnr, String firstName, String lastName, String email,  PrintWriter out, Connection conn) {
        PreparedStatement updatePerson; 
        // out.println("Inni updateAvtale");
         try {
             String upd ="update hallgeir.person set firstName =?, lastName =?, email =? WHERE pnr =?";
          
            //out.println("Nå er upd  " +upd);
            //out.println("avtalenr er  "+pnr);
            updatePerson = conn.prepareStatement(upd);
     
            // out.println("fornavn er " +lastName);

             //firstName = fn+firstName+fn;
             //out.println("firstName er " +lastName); 
  
             updatePerson.setString(1,firstName);
             updatePerson.setString(2,lastName);
             updatePerson.setString(3,email);             
             updatePerson.setInt(4,pnr);  
                
             // out.println(updatePerson);
             int ant = updatePerson.executeUpdate();
                
      } // end try      // end try      // end try      // end try      // end try      // end try      // end try      // end try      // end try      // end try      // end try      // end try      // end try      // end try      // end try      // end try     
         catch (SQLException ex) {
                out.println("Ikke fått oppdatert Person " +ex);
         }
  }
 
  
  /*
        Create new person.    
  */
  public void newPerson(int pnr, String firstName, String lastName,String email, PrintWriter out, Connection conn) {
        PreparedStatement newPerson; 
        // out.println("Inni newPerson: Pnr  " +pnr );

             try {                       
             
             out.println("Fornavn " +firstName);
             String ins ="insert into hallgeir.person (firstName,lastName, email)  values (?, ?, ?)";        
             newPerson = conn.prepareStatement(ins);
     
             newPerson.setString(1,firstName);
             newPerson.setString(2,lastName);             
             newPerson.setString(3,email);  
                
             // out.println(newPerson);
             newPerson.executeUpdate();

      } // end try  
                       
         catch (SQLException ex) {
                out.println("New Person not created " +ex);
         }
  // } //end if
  }
  
            
    /*
            Before a new client is inserted in the database
            we check if it aleready exist. 
     */
 
     public boolean checkPersonExist (int pnr, PrintWriter out, Connection conn)
    {           boolean ny = false;
                PreparedStatement getPerson; 
         
         try {
                getPerson = conn.prepareStatement("select * from person where pnr =?");
                getPerson.setInt(1,pnr);
                
                ResultSet rset;
                rset = getPerson.executeQuery();
           
                ny = rset.next();
                return ny;
                 
         } // end try     
         catch (SQLException ex) {
                out.println("Ikke hentet EN fra DB " +ex);
         }
         return ny;
    }
     
/*
     Will print all for each person we add to the database. 
     Just to check connections, and see if we can stress the system 
     */     
     
public void createTestData (int number, PrintWriter out, Connection conn) {
    String firstName;
    String lastName;
    String email; 
    int pnr; 
    
    int ant = number;
    
   int counter = 0; 
   while (counter < ant)
   {
    pnr =0;
    firstName = "Arne no " +counter;
    lastName = "Olsen no" +counter;
    email = "mailadress no: " +counter;
    
    newPerson(pnr, firstName,lastName, email, out, conn);
    
    counter++; 
    printClients(out, conn); 
   }   
   printClients(out, conn);
    
}

public void deleteTestData (PrintWriter out, Connection conn)
{
    PreparedStatement deletePersons; 
   // out.println("Inni newPerson: Pnr  " +pnr );

    try {                       
            
   String del ="delete from  hallgeir.person where pnr > ?";        
   deletePersons = conn.prepareStatement(del);
     
   deletePersons.setInt(1,0);
                
   out.println(deletePersons);
   deletePersons.executeUpdate();

      } // end try  
                       
         catch (SQLException ex) {
                out.println("persons not deleted " +ex);
         } 
}
        
}// slutt 
    
