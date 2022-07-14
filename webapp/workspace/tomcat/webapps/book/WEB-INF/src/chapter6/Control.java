package chapter6;

import java.io.IOException;
import java.util.Collections;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Page;

@WebServlet(urlPatterns = {"/chapter6/control"})
public class Control extends HttpServlet {
    
    public void doPost(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        var out = response.getWriter();

        Page.header(out);

        request.setCharacterEncoding("UTF-8");
        var names = Collections.list(request.getParameterNames());
        for (var name : names) {
            var values = request.getParameterValues(name);
            for (var value : values) {
                out.println("<p>" + name + "=" + value + "</p>");
            }
        }

        Page.footer(out);
    }
}
