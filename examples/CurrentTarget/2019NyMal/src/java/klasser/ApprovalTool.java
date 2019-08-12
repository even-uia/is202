/*
 *  When a new student is crated, all approvals for modules 
 *  are created. So only one method needed her; updateApproval
*/
package klasser;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author hallgeir
 */
public class ApprovalTool {
    
    
    public void updateApproval(int snr, int mnr, int points, String description, PrintWriter out, Connection conn) {
        // out.println("Kommer vi til ApprovalVerktøy , updateApproval==");

        PreparedStatement updateApproval; 
        try {
             String upd ="update hallgeir.approval set points =?, description =? WHERE snr =? and mnr =?";
          
            // out.println("Nå er upd  " +upd);
            // out.println("Snr er  "+snr);
             updateApproval = conn.prepareStatement(upd);
     
     
             updateApproval.setInt(1,points);
             updateApproval.setString(2,description);             
             updateApproval.setInt(3,snr);
             updateApproval.setInt(4,mnr);
                
             // out.println(updateApproval);
             int ant = updateApproval.executeUpdate();
                
      } // end try     
         catch (SQLException ex) {
                out.println("Did not manage to update approval " +ex);
         }
  }
  
    
    
    
}
