package chapter17;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Page;

@WebServlet(urlPatterns = {"/chapter17/count"})
public class Count extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var out = resp.getWriter();
        Page.header(out);

        var session = req.getSession();

        var count = (Integer) session.getAttribute("count");
        if (count == null) count = 0;
        count++;
        session.setAttribute("count", count);

        out.println("<p>" + count + "</p>");
        out.println("<p>" + session.getId() + "<p/>");
        Page.footer(out);
    }
}
