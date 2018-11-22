package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * This servlet can be bound to any url, and will respond with all available
 * detail about the request.
 *
 * Here it is configured to handle urls that end in .s, .t. or .a. These are the
 * same url-patterns that are configured to require login in web.xml. So this
 * servlet is pretending to be all login-protected pages and servlets in the
 * system
 *
 * @author evenal
 *
 */
@WebServlet(name = "DebugServlet", urlPatterns = {"*.s", "*.t", "*.a"})
public class DebugServlet extends HttpServlet
        implements StringConstants {

    protected void doRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (req.getServletPath().startsWith("/bang"))
            throw new NullPointerException();
        PrintWriter out = resp.getWriter();
        printHead(out, "Security Example");
        printStartBody(out, req.getRequestURL().toString());
        printUrlInfo(out, req);
        printUserInfo(out, req);
        printParameters(out, req);
        printEndBody(out);
    }

    protected void printHead(PrintWriter out, String title) {
        out.format(HEAD_TMPL, title);
    }

    protected void printStartBody(PrintWriter out, String title) {
        out.format(BODY_BEGIN_TMPL, title);
    }

    protected void printEndBody(PrintWriter out) {
        out.format(BODY_END_TMPL);
    }

    protected void printUrlInfo(PrintWriter out, HttpServletRequest req) {
        out.format(URLINFO_TMPL,
                   req.getRequestURL().toString(),
                   req.getContextPath(),
                   req.getServletPath());
    }

    protected void printUserInfo(PrintWriter out, HttpServletRequest req) {
        out.format(USERINFO_TMPL,
                   req.getAuthType(),
                   req.getUserPrincipal(),
                   req.getRemoteUser());

    }

    protected void printParameters(PrintWriter out, HttpServletRequest req) {
        Map<String, String[]> parMap = req.getParameterMap();

        out.print(PARAM_H_TMPL);
        if (parMap == null || parMap.isEmpty()) {
            out.println("    None");
            out.print(END_TMPL);
            return;
        }
        for (Map.Entry<String, String[]> entry
                : parMap.entrySet()) {
            StringBuilder sb = new StringBuilder();
            for (String value : entry.getValue()) {
                sb.append(" ").append(value);
            }
            out.format(PARAM_TMPL,
                       entry.getKey(),
                       sb.toString());
        }
        out.format(END_TMPL);
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
