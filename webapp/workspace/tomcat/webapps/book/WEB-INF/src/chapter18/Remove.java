package chapter18;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/chapter18/remove"})
public class Remove extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var cookies = req.getCookies();

        if (cookies != null) {
            for (var cookie : cookies) {
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }
    }
}
