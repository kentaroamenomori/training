package chapter17;

import java.io.IOException;
import java.util.List;

import bean.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Page;

@WebServlet(urlPatterns = {"/chapter17/cart-get"})
public class GetCart extends HttpServlet {

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var out = resp.getWriter();
        Page.header(out);

        var session = req.getSession();

        var cart = (List<Product>) session.getAttribute("cart");
        if (cart != null) {
            for (var p : cart) {
                out.println("<p>");
                out.println(p.getName());
                out.println(" : ");
                out.println(p.getPrice());
                out.println("</p>");
            }
        }
        Page.footer(out);
    }
}
