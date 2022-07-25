package chapter25;

import java.util.List;

import bean.Item;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class CartRemoveAction extends Action {
    @SuppressWarnings("unchecked")
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        var session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        var cart = (List<Item>) session.getAttribute("cart");

        for (var i : cart) {
            if (i.getProduct().getId() == id) {
                cart.remove(i);
                break;
            }
        }

        return "cart.jsp";
    }
}
