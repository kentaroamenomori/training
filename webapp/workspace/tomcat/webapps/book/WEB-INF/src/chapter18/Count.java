package chapter18;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Page;

@WebServlet(urlPatterns = {"/chapter18/count"})
public class Count extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var out = resp.getWriter();
        Page.header(out);

        var cookies = req.getCookies();

        Integer count = null;
        if (cookies != null) {
            for (var cookie : cookies) {
                try {
                    count = Integer.valueOf(cookie.getValue());
                    break;
                } catch (Exception e) {
                    continue;
                }
            }
        }

        if (count == null) count = 0;
        count++;

        var cookie = new Cookie("count", count.toString());
        cookie.setMaxAge(60 * 60 * 24);
        resp.addCookie(cookie);

        out.println(count);
        Page.footer(out);
    }
}
