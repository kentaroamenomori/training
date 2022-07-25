package chapter23;

import bean.Product;
import dao.ProductDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class Insert2Action extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        var name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));

        var p = new Product();
        p.setName(name);
        p.setPrice(price);
        var dao = new ProductDAO();
        dao.insert(p);

        var newP = new Product();
        newP.setName(p.getName() + "づくし");
        newP.setPrice(p.getPrice() * 5);
        dao.insert(newP);

        var list = dao.search("");
        request.setAttribute("list", list);

        return "list.jsp";
    }
}
