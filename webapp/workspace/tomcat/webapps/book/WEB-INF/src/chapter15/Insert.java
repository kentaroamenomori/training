package chapter15;

import java.io.IOException;

import bean.Product;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Page;

@WebServlet(urlPatterns = {"/chapter15/insert"})
public class Insert extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var out = resp.getWriter();
        Page.header(out);

        try {
            var name = req.getParameter("name");
            var price = Integer.parseInt(req.getParameter("price"));
            
            var product = new Product();
            product.setName(name);
            product.setPrice(price);

            var dao = new ProductDAO();
            dao.insert(product);

            var list = dao.search("");

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