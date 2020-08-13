package rolodex.web;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import rolodex.data.Contact;
import rolodex.data.Rolodex;


/**
 * This servlet will display the contact list page, which is the main/home page
 * of the application. Before displaying the page, it can carry out database
 * operations (add, change, or remove contacts.
 *
 * @author evenal
 */
public class ListPage extends Page {

    // The string constants are templates for various parts of the page
    public static final String TABLE_BEGIN = "<table>\n<tr><th>Name</th><th>Email</th></tr>\n";
    public static final String TABLE_ROW = "<tr>\n"
            + "<td><a href='ShowContact?name=%s'>%s</a></td><td>%s</td></tr>\n";
    public static final String TABLE_END = "</table>\n";

    private Rolodex rolodex;

    public ListPage(Rolodex rolodex) {
        this.rolodex = rolodex;
    }

    /**
     * Write the html code for the Contact list page, using the templates above.
     *
     * @param out
     */
    public void writePage(HttpServletRequest req, PrintWriter out) {
        out.append(PAGE_BEGIN);
        out.append(TABLE_BEGIN);
        List<Contact> contacts = null;
        try {
            contacts = rolodex.getAllContacts();
        } catch (Exception e) {
            contacts = new ArrayList<>();
            out.format("<h3>%s</h3>\n<pre>\n", e.getMessage());
            e.printStackTrace(out);
            out.println("</pre>");
        }
        System.out.println("contacts = " + contacts);
        for (Contact c : contacts) {
            out.format(TABLE_ROW, c.getName(), c.getName(), c.getEmail());
        }
        out.append(TABLE_END);

        // hide add button if user is not admin
        if (req.isUserInRole("RolodexAdmin")) {
            out.append("<p>");
            out.format(SUBMIT_BUTTON, "ShowContact", "new", "New Contact");
            out.append("</p>");
        }
        out.append(PAGE_END);
    }
}
