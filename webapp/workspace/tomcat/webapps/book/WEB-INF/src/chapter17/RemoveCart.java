package chapter17;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Page;

@WebServlet(urlPatterns = {"/chapter17/cart-remove"})
public class RemoveCart extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var out = resp.getWriter();
        Page.header(out);

        var session = req.getSession();
        session.removeAttribute("cart");

        out.println("カートを削除しました。");
        Page.footer(out);
    }
}
