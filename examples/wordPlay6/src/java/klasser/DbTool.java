/*
 * Hensikten med klassen er etablere kontakt med databasen
 */
package klasser;


import java.io.PrintWriter;
import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;


/**
 *
 * @author hallgeir
 */
public class DbTool {
    Connection conn;        // Must be defined here as class variables, get their value in the login method
    Statement stmt;

    /*
        Vil etablere kontakt med databsen og returner et Connection objekt. 
    */
    public Connection loggInn(PrintWriter out) {
        try {
         // Step 1: Allocate a database 'Connection' object
         Context cont = new InitialContext();
         DataSource ds = (DataSource)cont.lookup("java:comp/env/jdbc/localhostDS");  

         conn = ds.getConnection();        
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
