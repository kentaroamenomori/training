package chapter12;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Page;

@WebServlet(urlPatterns = {"/chapter12/request"})
public class Request extends HttpServlet {
    
    @Override
    protected void doGet(
        HttpServletRequest req, HttpServletResponse resp
    ) throws ServletException, IOException {
        
        var out = resp.getWriter();

        Page.header(out);
        out.println("<p>Request URL<br>" + req.getRequestURL() + "</p>");
        out.println("<p>Host<br>" + req.getHeader("Host") + "</p>");
        Page.footer(out);
    }
}
