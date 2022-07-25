package chapter19;

import java.io.IOException;
import java.util.Collections;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Page;

@WebServlet(urlPatterns = {"/chapter19/attribute2"})
public class Attribute2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var out = resp.getWriter();
        Page.header(out);

        var context = getServletContext();
        var list = Collections.list(context.getAttributeNames());
        for (var name : list) {
            out.println("<p>" + name + " : ");
            out.println(context.getAttribute(name));
            out.println("<p>");
        }
        Page.footer(out);
    }
}
