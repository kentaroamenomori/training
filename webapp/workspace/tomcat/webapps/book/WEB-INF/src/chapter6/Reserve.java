package chapter6;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Page;

@WebServlet(urlPatterns = {"/chapter6/reserve"})
public class Reserve extends HttpServlet {
    
    public void doPost(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        var out = response.getWriter();

        request.setCharacterEncoding("UTF-8");
        var count = request.getParameter("count");
        var seat = request.getParameter("seat");
        var options = request.getParameterValues("option");

        Page.header(out);

        out.println(count + "名様で" + seat + "席のご予約を承りました。");
        if (options != null) {
            for (var option : options) {
                out.println("「" + option + "」");
            }
            out.println("をご用意いたします。");
        }
        
        Page.footer(out);
    }
}
