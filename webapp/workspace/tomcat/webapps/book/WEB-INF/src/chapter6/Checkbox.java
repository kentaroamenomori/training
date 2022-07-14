package chapter6;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Page;

@WebServlet(urlPatterns = {"/chapter6/checkbox"})
public class Checkbox extends HttpServlet {
    
    public void doPost(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        var out = response.getWriter();

        request.setCharacterEncoding("UTF-8");
        var genre = request.getParameterValues("genre");

        Page.header(out);
        if (genre != null) {
            for (var item : genre) {
                out.println("「" + item + "」");
            }
            out.println("に関するお買い得情報をお送りします。");
        } else {
            out.println("お買い得情報はお送りいたしません。");
        }
        Page.footer(out);
    }
}
