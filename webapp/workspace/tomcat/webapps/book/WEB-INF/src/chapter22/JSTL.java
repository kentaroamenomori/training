package chapter22;

import java.io.IOException;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/chapter22/jstl"})
public class JSTL extends HttpServlet {
    
    @Override
    protected void doGet(
        HttpServletRequest req, HttpServletResponse resp
    ) throws ServletException, IOException {
        try {
            var dao = new ProductDAO();
            var list = dao.search("");

            req.setAttribute("list", list);
            req.getRequestDispatcher("jstl.jsp")
                .forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
