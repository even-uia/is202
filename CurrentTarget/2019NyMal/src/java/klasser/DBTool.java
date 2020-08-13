
/*
 *  Uses DBTool to connect to the Database, commit, close
 *  DataSource Localhost is defined in the project;
 *  Configuration Files; 
 *      - context.xml
 *  All databse access is done through this DBTools. If we change 
 *  database, it is done in context.xml, one place. 
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
public class DBTool {
    Connection conn;        // Must be defined here as class variables, get their value in the login method
    Statement stmt;
    
         
  
    public void close() {
         
        try {
        conn.close();
        
        }
        catch (SQLException ex) {
                System.out.println("Ikke lukke DB " +ex);
        }
    } // end Close
    
    
    
    public void commit() {
         
             try {           
             conn.commit();
         } // end try     
          
             catch (SQLException ex) {
                System.out.println("Ikke close DB " +ex);
                    
        }
                
    }  // end commit
                
      
   // @Resource DataSource LocalhostDS;
    /*
    *   This method is when we use the DataSource,  given in tomcat.xml
        What we do is: 
        1: Create a Context object
           See https://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html
        2: Create a DataSource object, and we "connect" this DataSource object to 
           the datasource LocalhostDS, given in the tomee.xml file. 
        3: The connection object is set to the DataSource getConnection.    
        4: The Statemet object is set to the Statement object created by the Connection objects crateStatement() method 
    */
    
        public Connection loggInn(PrintWriter out) {
        try {
         // Step 1: Allocate a database 'Connection' object
         Context cont = new InitialContext();
         DataSource ds = (DataSource)cont.lookup("java:comp/env/jdbc/localhostDS");  
         //DataSource ds = (DataSource)cont.lookup("jdbc/LocalhostDS");
         conn =  ds.getConnection();
         out.println(" datasource" +ds);
         
         return conn; 
 
        }
        catch (SQLException ex ) {
            out.println("Not connected to database " +ex);
        }
        catch (NamingException nex) {
            out.println("Not correct naming" + nex);
        }
        return null; 
    }  // end loggInn
}    
    
