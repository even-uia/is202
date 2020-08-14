package rolodex.web;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import rolodex.data.Contact;
import rolodex.data.Rolodex;


/**
 * Write the contact form page.
 *
 * @author evenal
 */
public class ContactPage extends Page {

    public static final String FORM_BEGIN = "<h2>Contact details</h2>\n"
            + "<form action='%s' method='GET'>\n"
            + "<table>\n";
    public static final String FIELD = "<tr>\n"
            + "<th>%s:</th>\n"
            + "<td><input type='text' name='%s' value='%s'/></td>\n"
            + "</tr>\n";
//    public static final String SUBMIT = "<input type='submit' value='%s'/>";
    public static final String SUBMIT_ACTION
            = "<input type='submit' formaction='%s' value='%s'/>\n";
    public static final String FORM_END = "</form>\n";

    private String name;

    public ContactPage(Rolodex rolodex, String name) {
        this.rolodex = rolodex;
        this.name = name;
    }

    /**
     * This method does the actual writing
     *
     * @param out
     */
    public void writePage(HttpServletRequest req, PrintWriter out) {
        Contact contact = null;
        try {
            contact = rolodex.getContact(name);
        } catch (ServletException e) {

        }

        out.append(PAGE_BEGIN);
        out.format(FORM_BEGIN, "ShowList");
        // the same form is used both to edit existing contacts
        // and enter new ones. If there is no
        // contact data, display empty fields, else populate the field
        // with data from the selected contact
        if (contact == null) {
            out.format(FIELD, "Name", "name", "");
            out.format(FIELD, "Email", "email", "");
        }
        else {
            out.format(FIELD, "Name", "name", contact.getName());
            out.format(FIELD, "Email", "email", contact.getEmail());
        }
        writeButtons(req, out, contact != null);
        out.append("</table>\n");
        out.append(FORM_END);
        out.append(PAGE_END);
    }

    // write the correct set of buttons for each of the cases
    // in writePage() above.
    // this demonstrates how pages can be tailored to the user's role
    private void writeButtons(HttpServletRequest req, PrintWriter out, boolean showDelete) {
        out.append("<tr><td colspan='2'>");
        if (req.isUserInRole("RolodexAdmin")) { // admin will see all buttons
            out.format(SUBMIT_ACTION, "AdminSaveContact", "Save");
            out.format(SUBMIT_ACTION, "ShowList", "Cancel");
            if (showDelete)
                out.format(SUBMIT_ACTION, "AdminDeleteContact", "Delete");
        }
        else { // ordinary user will only see the cancel button (relabeled as "Back"
            out.format(SUBMIT_ACTION, "ShowList", "Back");
        }
        out.append("</td></tr>\n");
    }
}
