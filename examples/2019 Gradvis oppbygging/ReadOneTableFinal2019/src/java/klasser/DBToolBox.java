
/*
 *  DataSource is defined in Context.xml
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
public class DBToolBox {
    
    
    public void close(Connection conn) {
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
        
        public void commit(Connection conn) {
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
   
  public Connection loggInn(PrintWriter out) {
      Connection conn;  
      try {
         // Step 1: Allocate a database 'Connection' object
         Context cont = new InitialContext();
         DataSource ds = (DataSource)cont.lookup("java:comp/env/jdbc/localhostDS");  
         //DataSource ds = (DataSource)cont.lookup("jdbc/localhostDS");
         conn =  ds.getConnection();
 
         // Step 2: Allocate a 'Statement' object in the Connection
         //stmt = conn.createStatement();
         return conn; 
        }
        catch (SQLException ex ) {
            out.println("Not connected to database " +ex);
        }
        catch (NamingException nex) {
            out.println("Not correct naming" + nex);
        }
        return null;     
            
    }

     

}// slutt 
    
