package chapter25;

import java.util.List;

import bean.Item;
import dao.PurchaseDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class PurchaseAction extends Action {
    @SuppressWarnings("unchecked")
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        var session = request.getSession();
        var name = request.getParameter("name");
        var address = request.getParameter("address");
        if (name.isEmpty() || address.isEmpty()) {
            return "purchase-error-empty.jsp";
        }

        var dao = new PurchaseDAO();
        var cart = (List<Item>) session.getAttribute("cart");
        if (cart == null || !dao.insert(cart, name, address)) {
            return "purchase-error-insert.jsp";
        }

        session.removeAttribute("cart");
        return "purchase-out.jsp";
    }
}
