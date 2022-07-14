package chapter9;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/chapter9/redirect"})
public class Redirect extends HttpServlet {
    
    public void doGet(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        response.sendRedirect("https://tomcat.apache.org/");
    }
}
