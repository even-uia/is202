/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class DbTools {
    
    
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
    
    
    public void showTables(Connection conn, PrintWriter out) { 
               
            String strSelect = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE='BASE TABLE'";
         
            out. println("The SQL query is: " + strSelect+ "<br>");
            
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
    
}
