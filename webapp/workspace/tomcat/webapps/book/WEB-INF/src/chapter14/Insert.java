package chapter14;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Page;

@WebServlet(urlPatterns = {"/chapter14/insert"})
public class Insert extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var out = resp.getWriter();
        Page.header(out);

        try {
            var ic = new InitialContext();
            var ds = (DataSource) ic.lookup("java:/comp/env/jdbc/book");
            var con = ds.getConnection();

            var name = req.getParameter("name");
            var price = Integer.parseInt(req.getParameter("price"));
            var st = con.prepareStatement(
                "insert into product(name, price) values(?, ?)"
            );
            st.setString(1, name);
            st.setInt(2, price);
            var line = st.executeUpdate();

            if (line > 0) {
                out.println("追加しました。");
            }

            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Page.footer(out);
    }
}
