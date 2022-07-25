package chapter24;

import dao.CustomerDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class LoginAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        var session = request.getSession();
        var login = request.getParameter("login");
        var password = request.getParameter("password");
        var dao = new CustomerDAO();
        var customer = dao.search(login, password);

        if (customer != null) {
            session.setAttribute("customer", customer);
            return "login-out.jsp";
        }
        return "login-error.jsp";
    }
}
