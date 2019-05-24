
/*
 * 
 * 
 */
package Classes;

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
public class DBTools {
    Connection conn;        // Must be defined here as class variables, get their value in the login method
    Statement stmt;
    
/*
 *  This method will 
    1: create a String with the query
    1b: The Statemet object is set to the Statement object created by the Connection object. 
    2: Use the Statement object, and call its executeQuery methos, with the String 
        that contains the SQL statement as parameter. 
        See also https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html 
    3: Treat the ResultSet object, that contains the result of the sql query. 
    4: This includes, loop and print all "rows" in the ResultSet
    PS: This is done in a try catch block; What if we cant retrieve data? 
 */
    
       
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
        
  public Connection getConnection(PrintWriter out) {
         
         
      try {
         // Step 1: Allocate a database 'Connection' object
         Context cont = new InitialContext();
         DataSource ds = (DataSource)cont.lookup("java:comp/env/jdbc/localhostDS");  
         //DataSource ds = (DataSource)cont.lookup("jdbc/LocalhostDS");
         conn =  ds.getConnection();
         return conn; 
 
        }
        catch (SQLException ex ) {
            out.println("Not connected to database " +ex);
        }
        catch (NamingException nex) {
            out.println("Not correct naming" + nex);
        }
        return null; 
    }  // end getConnection       
      
   // @Resource DataSource LocalhostDS;
    /*
    *   This method is when we use the DataSource given in \conf\context.xml
        What we do is: 
        1: Create a Context object
           See https://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html
        2: Create a DataSource object, and we "connect" this DataSource object to 
           the datasource LocalhostDS, given in the context.xml file. 
        3: The connection object is set to the DataSource getConnection.    
        4: The Statemet object is set to the Statement object created by the Connection objects crateStatement() method 
    */
  
  
 
    
}// slutt 
            
   

    
