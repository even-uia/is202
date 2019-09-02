/*
 * Hensikten med klassen er å samle all funksjonalitet mot
 * databasen. Hente fra tabell, lagre......
 */
package klasser;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hallgeir
 */
public class DbFunctionality {
    
 Statement stmt;    
 /*
    Skal liste alle tabeller i databasen vi er logget inn på
 */

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
    
    /*
        Skal inserte et nytt ord i tabellen words i databsen. Ordet som
        settes inn er hentet fra formen.
    */
    public void newWord(String word, PrintWriter out, Connection conn) {
      PreparedStatement insertWord;
       
      try {    
       String ins ="insert into hallgeir.words (word)  values (?)";
          
       insertWord = conn.prepareStatement(ins);
       insertWord.setString(1,word);
       insertWord.executeUpdate();   
       out.println("Nytt ord lagret i DB");
      } // end try     
         catch (SQLException ex) {
                out.println("Ikke fått opprettet NYTT ord " +ex);
         }
  }
    
}

