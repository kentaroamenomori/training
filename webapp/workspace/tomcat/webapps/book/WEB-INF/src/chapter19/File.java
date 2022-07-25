package chapter19;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Page;

@WebServlet(urlPatterns = {"/chapter19/file"})
public class File extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var out = resp.getWriter();
        Page.header(out);

        var context = getServletContext();
        var path = context.getRealPath("/WEB-INF/setting.txt");

        var in = new FileInputStream(path);
        var p = new Properties();
        p.load(in);
        in.close();

        for (var key : p.stringPropertyNames()) {
            out.println("<p>" + key + " : " + p.getProperty(key) + "</p>");
        }

        Page.footer(out);
    }
}
