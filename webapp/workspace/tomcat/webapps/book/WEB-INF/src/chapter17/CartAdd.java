package chapter17;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bean.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Page;

@WebServlet(urlPatterns = {"/chapter17/cart-add"})
public class CartAdd extends HttpServlet {
    
    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var out = resp.getWriter();
        Page.header(out);

        var name = req.getParameter("name");
        int price = Integer.parseInt(req.getParameter("price"));

        var session = req.getSession();

        var cart = (List<Product>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<Product>();
        }

        var p = new Product();
        p.setName(name);
        p.setPrice(price);
        cart.add(p);

        session.setAttribute("cart", cart);

        out.println("カートに商品を追加しました。");
        Page.footer(out);
    }
}
