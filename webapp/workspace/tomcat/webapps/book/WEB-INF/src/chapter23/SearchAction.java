package chapter23;

import dao.ProductDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SearchAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        var keyword = request.getParameter("keyword");
        var dao = new ProductDAO();
        var list = dao.search(keyword);

        request.setAttribute("list", list);
        return "list.jsp";
    }
}
