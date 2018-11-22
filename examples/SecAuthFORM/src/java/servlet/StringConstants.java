package servlet;


/**
 *
 * @author evenal
 */
public interface StringConstants {

    String HEAD_TMPL = "<html>\n  <head>\n"
            + "    <link rel='stylesheet'"
            + " type='text/css' href='example.css'/>\n"
            + "    <title>%s</title>\n"
            + "    <meta charset=\"UTF-8\">\n"
            + "  </head>\n";

    String BODY_BEGIN_TMPL = "  <body>\n"
            + "    <h1>%s</h1>\n";
    String BODY_END_TMPL = "  </body>\n</html>";

    String URLINFO_TMPL = "    <h3>Request Info</h3>\n"
            + "    <pre>\n"
            + "    URL = '%s'\n"
            + "    ContextPath = '%s'\n"
            + "    ServletPath = '%s'\n"
            + "    </pre>\n";

    String USERINFO_TMPL
            = "    \n<h4>User Data</h4>\n"
            + "    <pre>\n"
            + "    AuthType = '%s'\n"
            + "    Principal = '%s'\n"
            + "    Remote user = '%s'\n"
            + "    </pre>\n";

    String PARAM_H_TMPL = "    \n<h4>Request Parameters</h4>\n    <pre>\n";
    String PARAM_TMPL = "    %s = {'%s'}";
    String END_TMPL = "    </pre>\n";
}
