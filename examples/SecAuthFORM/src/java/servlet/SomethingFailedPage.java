package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * This subclass of the debugservlet is acting as a failed login error page.
 * Subclassing DebugServlet give us extra information in case something goes
 * wrong. Normally, custom error pages are used to get more information about
 * what went wrong.
 *
 * @author evenal
 */
@WebServlet(name = "LoginFailedPage",
            urlPatterns = {"/loginfailed", "/failed", "/logout", "/errorcode"})
public class SomethingFailedPage extends DebugServlet implements StringConstants {

    protected void doRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        printHead(out, "Login failed");
        printStartBody(out, "Login failed");
        printUrlInfo(out, req);
        printUserInfo(out, req);
        printParameters(out, req);
        printEndBody(out);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }
}
