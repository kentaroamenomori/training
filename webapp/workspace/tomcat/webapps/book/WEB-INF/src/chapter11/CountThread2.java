package chapter11;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Page;

@WebServlet(urlPatterns = {"/chapter11/count-thread2"})
public class CountThread2 extends HttpServlet {
    int count = 0;

    @Override
    protected void doGet(
        HttpServletRequest req, HttpServletResponse resp
    ) throws ServletException, IOException {
        var out = resp.getWriter();
        Page.header(out);

        synchronized (this) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            count++;
            out.println(count);
        }

        Page.footer(out);
    }
}
