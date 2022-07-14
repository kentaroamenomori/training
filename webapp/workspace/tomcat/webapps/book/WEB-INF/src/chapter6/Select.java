package chapter6;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Page;

@WebServlet(urlPatterns = {"/chapter6/select"})
public class Select extends HttpServlet {
    
    public void doPost(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        
        response.setContentType("text/html; charset=UTF-8");
        var out = response.getWriter();

        request.setCharacterEncoding("UTF-8");
        var count = request.getParameter("count");
        var payment = request.getParameter("payment");
        var review = request.getParameter("review");
        var mail = request.getParameter("mail");

        Page.header(out);
        out.println("<p>" + count + "個の商品をカートに入れました。</p>");
        out.println("<p>お支払い方法を" + payment + "に設定しました。</p>");
        out.println("<p>ご感想ありがとうございます。</p>");
        out.println("<p>「" + review + "」</p>");
        if (mail != null) {
            out.println("<p>メールをお送りします。</p>");
        } else {
            out.println("<p>メールはお送りしません。</p>");
        }
        Page.footer(out);
    }
}
