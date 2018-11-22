package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author evenal
 *
 */
@WebServlet(name = "TestServlet", urlPatterns = {"*.s", "*.t", "*.a"})
public class TestServlet extends HttpServlet {

    public static final String PAGE = ""
            + "<html>\n"
            + "  <head>\n"
            + "    <link rel=\"stylesheet\" type='text/css' href='rolodex.css'/>\n"
            + "    <title>Testapp</title>\n"
            + "    <meta charset=\"UTF-8\">\n"
            + "  </head>\n"
            + "  <body>\n"
            + "  <h1>Secure Webapp</h1>\n"
            + "  <p>Here is most of the info that can be pulled out out the request</p>\n"
            + "  <pre>\n"
            + "URL = '%s'\n"
            + "ContextPafh = '%s'\n"
            + "ServletPath = '%s'\n"
            + "Parameters =%s\n"
            + "AuthType = '%s'\n"
            + "Principal = '%s'\n"
            + "Is user admin = %b\n"
            + "  </pre>\n</body>\n</html>\n";

    protected void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String authType = req.getAuthType();
        String contextPath = req.getContextPath();
        String uri = req.getRequestURI();
        String servletPath = req.getServletPath();
        Map<String, String[]> parMap = req.getParameterMap();
        String url = req.getRequestURL().toString();
        Principal principal = req.getUserPrincipal();
        boolean inRole = req.isUserInRole("sysadm");

        StringBuffer sbuf = new StringBuffer();
        sbuf.append("\n");
        for (Map.Entry<String, String[]> entry : parMap.entrySet()) {
            sbuf.append("   ").append(entry.getKey()).append(" = { ");
            for (String s : entry.getValue())
                sbuf.append(s).append(" ");
            sbuf.append("}\n");
        }

        PrintWriter out = resp.getWriter();
        out.format(PAGE,
                   url, contextPath, servletPath,
                   sbuf.toString(),
                   authType,
                   (principal == null ? null : principal.getName()),
                   inRole);
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
