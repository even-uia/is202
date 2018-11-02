/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import klasser.ToolBox;

/**
 *
 * @author hallgeir
 */
public class StudentTools {
    
    ToolBox toolBox;
    // Statement stmt;
        
    /*
    Notice how the Connection object is crated and given value,
    in a try with resources structure. 
    The purpose is that the connection should close and "die"
    when the job is done. To check this we send an parameter
    in, and we will do a full run of the method 
    studentRetrieve(out), the given number of times. 
    */
    
    public void studentRetrieveOne(PrintWriter out)
    {
        toolBox = new ToolBox();   
        String strSelect = "Select * FROM student";
        out. println("The SQL query is: " + strSelect);
        out.println();
        // try with resources  see https://stackoverflow.com/questions/8066501/how-should-i-use-try-with-resources-with-jdbc
        try (Connection conn = toolBox.loggInn(out);
             Statement stmt = conn.createStatement();   
            ) 
        {
            //stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(strSelect);
 
            // Step 4: Process the ResultSet by scrolling the cursor forward via next().
            //  For each row, retrieve the contents of the cells with getXxx(columnName).
            out.println("The records selected are:" +"<br>");
            int rowCount = 0;
            int snr;
            String firstName;
            String lastName;
                
            while(rset.next()) {   // Move the cursor to the next row, return false if no more row
                snr = rset.getInt("snr");
                firstName = rset.getString("firstName");
                lastName = rset.getString("lastName");
                out.println(rowCount +": Snr er: " +snr +"   "  + firstName +" " +lastName +"<br>");
                ++rowCount;
            }  // end while
            out.println("Total number of records = " + rowCount);
            toolBox.close(conn);
         } // end catch     
         catch (SQLException ex) {
          out.println("Ikke hentet fra DB " +ex);
         }   
    }
   
    /*
    The purpose here is to be able to test that we have 
    enough connections. Notice that studetRetrieve uses 
    try with resources. 
    */
    public void studentRetrieve(PrintWriter out, int number)
    {
        int runner = 1;
        while (runner <= number)
        {
            out.println("<h1> Loop index is " +runner +"<br> </h1>");
            studentRetrieveOne(out);               
            runner++; 
        }
        
    }
}
