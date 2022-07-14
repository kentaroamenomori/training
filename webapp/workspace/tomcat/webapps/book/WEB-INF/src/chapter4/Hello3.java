package chapter4;

import java.io.IOException;
import java.rmi.ServerException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Hello3 extends HttpServlet {
    
    public void doGet(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServerException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        var out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Servlet/JSP Sample Programs</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<p>Hello!</p>");
        out.println("<p>こんにちは！</p>");
        out.println("<p>" + new java.util.Date() + "</p>");
        
        out.println("</body>");
        out.println("</html>");
    }

}
