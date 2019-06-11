/*
 * Tools to connect to a database
 * We define a DataSource, either in 
        - Tomcat \conf\context.xml
        - Project, in web\META-INF\context.xml
 *  Purpose of DataSource is to get a secure and easy way to connect 
    to the database https://en.wikipedia.org/wiki/Datasource 
 */
package klasser;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author hallgeir
 */


public class DBTools {
    
   /*   her bruker vi Try with resources, 
        see http://www.mastertheboss.com/jboss-server/jboss-datasource/using-try-with-resources-to-close-database-connections 
        Når vi bruker try with resources vil Connection til databasen
        bli lukket automatisk. 
*/
    
     public Connection loggInn(PrintWriter out) {
        
         Connection tmpConnection;
                Context cont = new InitialContext();
         try (  
                DataSource ds = (DataSource)cont.lookup("java:comp/env/jdbc/localhostDS");  
         
                 
                 tmpConnection =  ds.getConnection();) 
         {
             
         // Step 1: Allocate a database 'Connection' object
         
         //DataSource ds = (DataSource)cont.lookup("jdbc/LocalhostDS");
         
             
         return tmpConnection;
        }
        catch (SQLException ex ) {
            out.println("Not connected to database " +ex);
        }
        catch (NamingException nex) {
            out.println("Not correct naming" + nex);
        }
            
        return null;     
    }
   
    
    public Connection loggInnxx(PrintWriter out) {
        try {
         Connection tmpConnection;    
         // Step 1: Allocate a database 'Connection' object
         Context cont = new InitialContext();
         DataSource ds = (DataSource)cont.lookup("java:comp/env/jdbc/localhostDS");  
         //DataSource ds = (DataSource)cont.lookup("jdbc/LocalhostDS");
         tmpConnection =  ds.getConnection();
             
         return tmpConnection;
        }
        catch (SQLException ex ) {
            out.println("Not connected to database " +ex);
        }
        catch (NamingException nex) {
            out.println("Not correct naming" + nex);
        }
            
        return null;     
    }
   
}
