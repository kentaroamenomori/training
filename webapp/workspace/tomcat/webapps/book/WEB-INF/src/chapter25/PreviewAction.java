package chapter25;

import java.util.List;

import bean.Item;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class PreviewAction extends Action {
    
    @Override
    @SuppressWarnings("unchecked")
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        var session = request.getSession();
        if (session.getAttribute("customer") == null) {
            return "preview-error-login.jsp";
        }

        var cart = (List<Item>) session.getAttribute("cart");
        if (cart == null || cart.size() == 0) {
            return "preview-error-cart.jsp";
        }

        return "purchase-in.jsp";
    }
}
