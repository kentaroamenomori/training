package chapter5;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Page;

@WebServlet(urlPatterns = {"/chapter5/greeting"})
public class Greeting extends HttpServlet {
    
    public void doGet(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        var out = response.getWriter();

        request.setCharacterEncoding("UTF-8");
        var user = request.getParameter("user");

        Page.header(out);
        out.println("<p>こんにちは、" + user + "さん</p>");
        Page.footer(out);
    }
}
