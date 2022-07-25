package chapter25;

import dao.ProductDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class ProductAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        var session = request.getSession();
        var keyword = request.getParameter("keyword");
        if (keyword == null) keyword = "";

        var dao = new ProductDAO();
        var list = dao.search(keyword);

        session.setAttribute("list", list);
        return "product.jsp";
    }
}
