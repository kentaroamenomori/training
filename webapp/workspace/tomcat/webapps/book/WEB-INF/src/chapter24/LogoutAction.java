package chapter24;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class LogoutAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        var session = request.getSession();

        if (session.getAttribute("customer") != null) {
            session.removeAttribute("customer");
            return "logout-out.jsp";
        }
        return "logout-error.jsp";
    }
}
