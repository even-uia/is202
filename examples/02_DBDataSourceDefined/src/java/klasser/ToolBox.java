
/*
 * In this version we do all that has to do with the database connection here.
 * No changes in tomee.xml or context.xml
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
public class ToolBox {
    Connection conn;        // Must be defined here as class variables, get their value in the login method
    Statement stmt;
    
    
    public void close() {
         
        try {
        conn.close();
        
        }
        catch (SQLException ex) {
                System.out.println("Ikke lukke DB " +ex);
        }
    }
    
      /*
        See https://docs.oracle.com/javase/tutorial/jdbc/basics/transactions.html 
        It seems like "Auto commit mode" is disabled. Then we have to explicit do a commit 
        in order to actually update the Database. 
        */
        
        public void commit() {
         
             try {           
             conn.commit();
         } // end try     
          
             catch (SQLException ex) {
                System.out.println("Ikke close DB " +ex);
        }
        }
      
 
    /*
    *   1: In Context.xml:  Define Data Source
        2: In Web.xml       Decide which DataSource to use (May be omitted)
        3: In java code:    Use the DataSource to connect to the database. 
    */
           
   
  public void loggInn(PrintWriter out) {
        try {
         // Step 1: Allocate a database 'Connection' object
         Context cont = new InitialContext();
         DataSource ds = (DataSource)cont.lookup("java:comp/env/jdbc/localhostDS");  
         //DataSource ds = (DataSource)cont.lookup("jdbc/localhostDS");
         conn =  ds.getConnection();
 
         // Step 2: Allocate a 'Statement' object in the Connection
         //stmt = conn.createStatement();
         //return conn; 
        }
        catch (SQLException ex ) {
            out.println("Not connected to database " +ex);
        }
        catch (NamingException nex) {
            out.println("Not correct naming" + nex);
        }
        //return null;     
            
    }

     
public void showTables(PrintWriter out) { 
               
            String strSelect = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE='BASE TABLE'";
         
            System.out.println("The SQL query is: " + strSelect); // Echo For debugging
            out. println("The SQL query is: " + strSelect);
         
            System.out.println();
            out.println();
 
            try {
                stmt = conn.createStatement();
                ResultSet rset = stmt.executeQuery(strSelect);
 
                // Step 4: Process the ResultSet by scrolling the cursor forward via next().
                //  For each row, retrieve the contents of the cells with getXxx(columnName).
                out.println("The records selected are:" +"<br>");
                int rowCount = 0;
                while(rset.next()) {   // Move the cursor to the next row, return false if no more row
                    String table_Name = rset.getString("TABLE_NAME");
                    out.println(rowCount +": " + table_Name +"<br>");
                    ++rowCount;
                 }  // end while
                 out.println("Total number of records = " + rowCount);
         } // end catch     
         catch (SQLException ex) {
                out.println("Ikke hentet fra DB " +ex);
         }
     }
}// slutt 
    
