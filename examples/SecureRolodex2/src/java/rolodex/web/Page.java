package rolodex.web;

import java.io.PrintWriter;
import rolodex.data.Rolodex;


/**
 * This is the abstract superclass of the page writing classes. The servlets
 * delegates writing the html code for the pages to subclasses of this class.
 *
 * @author evenal
 */
public class Page {

    public static final String PAGE_BEGIN = "<!DOCTYPE html>\n"
            + "<html>\n"
            + "<head>\n"
            + "<title>JRolodex</title>\n"
            + "</head>\n"
            + "<body>\n"
            + "<h1>JRolodex</h1>\n";
    public static final String SUBMIT_BUTTON
            = "<form action='%s'><input type='submit' name='%s' value='%s'/></form>\n";
    public static final String PAGE_END = "</body>\n</html>\n";

    protected Rolodex rolodex;

    public void writePage(PrintWriter out, String pageContent) {
        out.append(PAGE_BEGIN);
        out.append(pageContent);
        out.append(PAGE_END);
    }

}
