package chapter25;

import java.util.ArrayList;
import java.util.List;

import bean.Item;
import bean.Product;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class CartAddAction extends Action {
    
    @Override
    @SuppressWarnings("unchecked")
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        var session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        
        var cart = (List<Item>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<Item>();
            session.setAttribute("cart", cart);
        }

        for (var i : cart) {
            if (i.getProduct().getId() == id) {
                i.setCount(i.getCount() + 1);
                return "cart.jsp";
            }
        }

        var list = (List<Product>) session.getAttribute("list");
        for (var p : list) {
            if (p.getId() == id) {
                var i = new Item();
                i.setProduct(p);
                i.setCount(1);
                cart.add(i);
            }
        }

        return "cart.jsp";
    }
}
