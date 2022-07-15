package chapter18;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Page;

@WebServlet(urlPatterns = {"/chapter18/get"})
public class Get extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var out = resp.getWriter();
        Page.header(out);

        var cookies = req.getCookies();
        if (cookies != null) {
            for (var cookie : cookies) {
                var name = cookie.getName();
                var value = cookie.getValue();
                out.println("<p>" + name + " : " + value + "</p>");
            }
        } else {
            out.println("クッキーが存在しません");
        }

        Page.footer(out);
    }
}
