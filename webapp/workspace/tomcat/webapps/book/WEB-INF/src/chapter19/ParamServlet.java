package chapter19;

import java.io.IOException;
import java.rmi.ServerException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ParamServlet extends HttpServlet {
    
    private String message;

    @Override
    public void init() throws ServletException {
        var config = getServletConfig();
        message = config.getInitParameter("message");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var out = resp.getWriter();
        out.println(message);
    }
}
