
/*
 * In this version we do all that has to do with the database connection here.
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
public class ModuleTool implements StringConstants {
    Connection conn;        // Must be defined here as class variables, get their value in the login method
    Statement stmt;
    
    PreparedStatement insertStudent; 
    
    
/*
 *  This method will 
    1: create a PreparedStatement variable
    2: The Connection object has the method prepareStament, that takes an SQL with
       one or mor parameters. Our PreparedStatement variable is now refering to this object. 
    2b: Set the parameter values in the PreparedStatement by the method setString, setInt.....
    3: Treat the ResultSet object, that contains the result of the sql query. 
    4: This includes, loop and print all "rows" in the ResultSet
    PS: This is done in a try catch block; What if we cant retrieve data? 
 */
     
  public void skrivModules(PrintWriter out, Connection conn)
    { 
        String MODULE  = "<td><a href='moduleDetail?mnr=%s&moduleName=%s&moduleDescription=%s'>%s %s</a></td>"; 
         
        PreparedStatement getModules; 
         
         try {
                getModules = conn.prepareStatement("select * from module order by ?");
                getModules.setString(1,"mnr");
                
                ResultSet rset = getModules.executeQuery();
 
                
                // Step 4: Process the ResultSet by scrolling the cursor forward via next().
                //  For each row, retrieve the contents of the cells with getXxx(columnName).
                out.println("The records modules selected are:" +"<br>");
                int rowCount = 0;
                out.println("<table>");
                out.println("<tr>");
                out.println("<td> Modulnummer </td>");
                out.println("<td> ModuleName </td>");
                out.println("<td> Description</td>");
                out.println("</tr>");

                while(rset.next()) {   // Move the cursor to the next row, return false if no more row
                    String mnr = rset.getString("mnr");
                    String moduleName = rset.getString("moduleName");
                    String moduleDescription   = rset.getString("moduleDescription");
                    // out.println(rowCount +": " +mnr + " , " + moduleName + ", " + moduleDescription +"<br>");
                    out.println("<tr>");
                    
                    out.format(MODULE,mnr,moduleName,moduleDescription, mnr,moduleName, moduleDescription);
                    out.println("<td>" +moduleName +"</td>");
                    out.println("<td>" +moduleDescription +"</td>");
                    out.println("</tr>");
                    ++rowCount;
                 }  // end while
                out.println("</table>");
                
                 out.println("Total number of modules = " + rowCount);
                 
         } // end try     
         catch (SQLException ex) {
                out.println("Ikke hentet fra DB " +ex);
         }
       // After all modules are listed; we have a new form, for new Module      
       out.println("<br><br>");   
       out.format(FORM, "moduleDetail");           
       out.format(INPSUB, "Submit","valg", "Ny Modul");
       out.println("</form>");
   }
       
  public void skrivEnModule(int mnr, PrintWriter out, Connection conn)
    { 
        PreparedStatement getModule; 
         
         try {
                getModule = conn.prepareStatement("select * from hallgeir.module where mnr = ?");
                getModule.setInt(1,mnr);
                
                ResultSet rset = getModule.executeQuery();
                out.println("Inni skrivEnModul");    
                // Step 4: Process the ResultSet by scrolling the cursor forward via next().
                //  For each row, retrieve the contents of the cells with getXxx(columnName).
                out.println("The records selected are:" +"<br>");
                int rowCount = 0;
                if (rset.next()) {   // Move the cursor to the next row, return false if no more row
                    String mnrString = rset.getString("mnr");
                    String moduleName = rset.getString("moduleName");
                    String moduleDescription   = rset.getString("moduleDescription");
                    out.println(rowCount +": " +mnrString + " , " + moduleName + ", " + moduleDescription +"<br>");
                    out.format("Test" +"<br>");
                    
                    ++rowCount;
                 }  // end if
                 out.println("Total number of records = " + rowCount);
         } // end try     
         catch (SQLException ex) {
                out.println("Ikke hentet fra DB " +ex);
         }
   }
         
    
    public void newModule(int mnr, String moduleName, String moduleDescription, PrintWriter out, Connection conn) {
            
         PreparedStatement newModule; 
                             
            try {
                newModule = conn.prepareStatement("Insert into module (mnr, moduleName, moduleDescription) values (?,?,?)");
                newModule.setInt(1,mnr);
                newModule.setString(2,moduleName);
                newModule.setString(3,moduleDescription);
              
                newModule.executeUpdate();
                
         } // end try     
          catch (SQLException ex) {
                out.println("Module Ikke lagre i DB " +ex);
        }
        }       
            
    public void updateModule(int mnr, String moduleName, String moduleDescription, PrintWriter out, Connection conn) {
        PreparedStatement updateModule; 
        // out.println("Inni updateModule");
         try {
             String upd ="update hallgeir.module set moduleName =?, moduleDescription =? WHERE mnr =?";
          
            // out.println("Nå er upd  " +upd);
            // out.println("Mnr er  "+mnr);
             
            updateModule = conn.prepareStatement(upd);
     
            //  out.println("moduleName er " +moduleName);
            // out.println("moduleDescription  er " +moduleDescription); 
  
             updateModule.setString(1,moduleName);
             updateModule.setString(2,moduleDescription);             
             updateModule.setInt(3,mnr);  
                
             // out.println(updateModule);
             int ant = updateModule.executeUpdate();
                
      } // end try     
         catch (SQLException ex) {
                out.println("Ikke fått oppdatert Module " +ex);
         }
  }
  
    
    // In moduleLagre, we use the same choice, Lagre, for update and new. 
    // So when we select Lagre, we use this method to check if this mnr exist
    public boolean sjekkomModulefinnes (int mnr, PrintWriter out, Connection conn)
    {           boolean ny = false;
                PreparedStatement getModule; 
         
         try {
                getModule = conn.prepareStatement("select * from module where mnr =?");
                getModule.setInt(1,mnr);
                
                ResultSet rset;
                rset = getModule.executeQuery();
           
                ny = rset.next();
                 return ny;
                 
         } // end try     
         catch (SQLException ex) {
                out.println("Ikke hentet EN fra DB " +ex);
         }
         return ny;
    }

   
}// slutt 
    
