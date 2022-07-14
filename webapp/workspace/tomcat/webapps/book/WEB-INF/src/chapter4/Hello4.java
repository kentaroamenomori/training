package chapter4;

import java.io.IOException;
import java.rmi.ServerException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Page;

@WebServlet(urlPatterns = {"/chapter4/hello4"})
public class Hello4 extends HttpServlet {
    public void doGet(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServerException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        var out = response.getWriter();

        Page.header(out);

        out.println("<p>Hello!</p>");
        out.println("<p>こんにちは！</p>");
        out.println("<p>" + new java.util.Date() + "</p>");
        
        Page.footer(out);
    }
}
