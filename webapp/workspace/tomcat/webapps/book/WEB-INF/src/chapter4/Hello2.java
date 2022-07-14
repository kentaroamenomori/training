package chapter4;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/chapter4/hello2"})
public class Hello2 extends HttpServlet {
    
    public void doGet(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");

        var out = response.getWriter();
        out.println("Hello!");
        out.println("ヤッホー");
        out.println(new java.util.Date());
    }

}
