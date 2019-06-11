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
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 *
 * @author hallgeir
 */
public class DBTools {

    public Connection loggInn(PrintWriter out) {
        // delegate the actual connection to a
        // separate method; to simplify exception handling
        // the try-with-resources variant of a try-catch statement
        // automatically closes all resources opened in the parenthesis
        // right after 'try', are automatically closed when the
        // try-statement has ended either normally, or through one of
        // the catch blocks
        try (Connection conn = connect()) {
            // Step 1: Allocate a database 'Connection' object
            out.println("Vil returnere Connection");
            return conn;
        }
        catch (SQLException ex) {
            out.println("Not connected to database " + ex);
        }
        catch (NamingException nex) {
            out.println("Not correct naming" + nex);
        }
        return null;
    }

    private Connection connect() throws NamingException, SQLException {
        // Get an initial jdni context
        Context cont = new InitialContext();
        // look up the datasource you want to use in the context
        // created above
        //DataSource ds = (DataSource) cont.lookup("java:comp/env/jdbc/localhostDS");
         DataSource ds = (DataSource)cont.lookup("java:comp/env/jdbc/localhostDS");
        
        return ds.getConnection();
    }

}
