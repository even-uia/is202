package rolodex.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;


/**
 * This is a worker class. It hides the database from the servlets, and carries
 * out the actions that are needed on the datbase.
 *
 * @author evenal
 */
public class Rolodex {

    private static final String INSERT_STMT = "insert into contacts values(?,?)";
    private static final String UPDATE_STMT = "update contacts set email=? where name=?";
    private static final String DELETE_STMT = "delete from contacts where name=?";
    private static final String SELECTSINGLE_Q = "select * from contacts where name=?";
    private static final String SELECTALL_Q = "select * from contacts";

    public Rolodex() {
    }

    /**
     * Get a list of all registered contacts
     */
    public List<Contact> getAllContacts() throws ServletException {
        ArrayList<Contact> retVal = null;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECTALL_Q);
             ResultSet result = stmt.executeQuery()) {
            retVal = new ArrayList<>();
            while (result.next()) {
                Contact c = new Contact(result.getString(1),
                                        result.getString(2));
                retVal.add(c);
            }
        } catch (SQLException | NamingException sqle) {
            throw new ServletException("DB query failed, because: ", sqle);
        }
        return retVal;
    }

    /**
     * Get one contact, specified by name (which is the id for the contacts)
     *
     * @param name
     * @return
     */
    public Contact getContact(String name) throws ServletException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECTSINGLE_Q)) {
            stmt.setString(1, name);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    Contact contact = new Contact(result.getString(1), result.getString(2));
                    return contact;
                }
            }
        } catch (SQLException | NamingException e) {
            throw new ServletException("DB query failed, because: ", e);
        }
        return null;
    }

    /**
     * Save data for one contact to the database. Overwrites the data if an
     * existing contact has the specified name, otherwise adds a new contact.
     * Returns without doing anything if name is null or the empty string, or if
     * email is null
     *
     * @param name
     * @param email
     */
    public void saveContact(String name, String email) throws ServletException {
        if (name == null || email == null || "".equals(name))
            return;
        Contact c = getContact(name);
        if (c == null) {
            insert(name, email);
            return;
        }
        else {
            update(name, email);
        }
    }

    /**
     * Delete the contact identified by name
     *
     * @param name
     */
    public void deleteContact(String name) throws NamingException, ServletException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_STMT)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new ServletException("DB delete failed, because: ", e);
        }
    }
    // helper methods for saveContact() and deleteContact()

    private void insert(String name, String email) throws ServletException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_STMT)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.executeUpdate();
            con.commit();
        } catch (SQLException | NamingException e) {
            throw new ServletException("DB insert failed, because: ", e);
        }
    }

    private void update(String name, String email) throws ServletException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "update contacts set email = ? where name = ?")) {
            ps.setString(1, email);
            ps.setString(2, email);
            ps.execute();
        } catch (SQLException | NamingException e) {
            throw new ServletException("DB insert failed, because: ", e);
        }
    }

    private Connection getConnection() throws NamingException, SQLException {
        Context inital = new InitialContext();
        Context env = (Context) inital.lookup("java:/comp/env");
        DataSource ds = (DataSource) env.lookup("jdbc/Rolodex");
        Connection con = ds.getConnection();
        con.setAutoCommit(false);
        return con;
    }
}
