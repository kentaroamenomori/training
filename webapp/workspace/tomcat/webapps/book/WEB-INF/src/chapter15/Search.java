package chapter15;

import java.io.IOException;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Page;

@WebServlet(urlPatterns = {"/chapter15/search"})
public class Search extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var out = resp.getWriter();
        Page.header(out);

        try {
            var keyword = req.getParameter("keyword");
            var dao = new ProductDAO();
            var list = dao.search(keyword);

            for (var p : list) {
                out.println(p.getId());
                out.println(" : ");
                out.println(p.getName());
                out.println(" : ");
                out.println(p.getPrice());
                out.println("<br>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Page.footer(out);
    }
}
