package chapter19;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Page;

@WebServlet(urlPatterns = {"/chapter19/attribute3"})
public class Attribute3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var out = resp.getWriter();
        Page.header(out);

        var context = getServletContext();
        var debug = (String) context.getAttribute("debug");
        if (debug.equals("yes")) {
            out.println("<p>デバッグモードで実行します</p>");
        }

        int memory = Integer.parseInt((String) context.getAttribute("memory"));
        if (memory < 1000000) {
            out.println("<p>省メモリモードで実行します</p>");
        }

        Page.footer(out);
    }
}
