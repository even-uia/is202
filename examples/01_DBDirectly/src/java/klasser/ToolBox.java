
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
    *   This method is when we DO not use any DataSource given in tomee.xml
        Therefor we have to "manually" define this datasource. 
        The disadvantage with this is, amongst many issues, that we have to hardcode username and password to the database. 
        What we here in this version is: 
        1: the connection object is set to the DriverManager.getConnection(...)
           where the parameter is the hardcoded reference to the database, including database name, username, password
               Create a Context object           
    */
           
     public void loggInn(PrintWriter out) {        
        try {
            out.println("Log in to DB, directly ");
            // Step 1: Allocate a database 'Connection' object
            //Class.forName("com.mysql.jdbc.Driver").newInstance();   //MySql
            Class.forName("org.mariadb.jdbc.Driver").newInstance();   //MariaDB
            out.println("Class.forname............ er utført");   
        
            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hallgeir", "hallgeir", "hallgeir");  //MySql
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/hallgeir", "hallgeir", "hallgeir");  //MariaDB
            out.println("conn har fått verdi");     
            }
                   
            catch (SQLException ex ) 
            {
                out.println("Not connected to database " +ex);
            }
            catch(ClassNotFoundException | IllegalAccessException | InstantiationException e)
            {
                System.out.println("There was some error while establishing connection to the database"+e);
            }
        out.println("Logginn3 ferdig");
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
    
