package chapter12;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/chapter12/response"})
public class Response extends HttpServlet {
    
    @Override
    protected void doGet(
        HttpServletRequest req, HttpServletResponse resp
    ) throws ServletException, IOException {
        
        resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        resp.setHeader("Location", "https://www.sbcr.jp");
    }
}
