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
 
    /*
        Skal inserte en ny url i tabellen webadresse i databasen. Url som
        settes inn er hentet fra formen.
    */
    public void newUrl(String newAddress, PrintWriter out, Connection conn) {
      PreparedStatement insertUrl;
       
      try {    
       String ins ="insert into hallgeir.webadresse (url)  values (?)";
          
       insertUrl = conn.prepareStatement(ins);
       insertUrl.setString(1,newAddress);           // Gir verdi til url
       insertUrl.executeUpdate();   
       out.println("Ny url lagret i DB: " +newAddress);
      } // end try     
         catch (SQLException ex) {
                out.println("Ikke fått opprettet NY url " +ex);
         }
  }
    
    /*
        Will print links as links. To do this we will use 
        out.format. See https://dzone.com/articles/java-string-format-examples 
        New here: use of %s in Strings
    */
    
    public void printUrls(PrintWriter out, Connection conn)
    { 
        String URL  = "<a href='%s'>%s %s</a><br>";          
        PreparedStatement getUrls; 
         
         try {
                getUrls = conn.prepareStatement("select * from webadresse order by ?");
                getUrls.setString(1,"url");
                
                ResultSet rset = getUrls.executeQuery();
 
                // Process the ResultSet by scrolling the cursor forward via next().
                //  For each row, retrieve the contents of the cells with getXxx(columnName).
                out.println("The records selected are:" +"<br>");
                int rowCount = 0;
                while(rset.next()) {   // Move the cursor to the next row, return false if no more row
                    String nr = rset.getString("nr");
                    String url = rset.getString("url");
 
                    // out.println(rowCount +": " +snr + " , " + firstName + ", " + lastName +"<br>");
                    out.println("No: " +nr + " url: " +url +"<br>");
                    out.format(URL,url, nr, url);
                                      
                    out.println("<br>");
                    ++rowCount;
                 }  // end while
                 out.println("Total number of records = " + rowCount +"<br>");
                 
                 out.format(URL,"index.html","Home","");
                 
         } // end try     
         catch (SQLException ex) {
                out.println("Ikke hentet fra DB " +ex);
         }
   }
    
}

