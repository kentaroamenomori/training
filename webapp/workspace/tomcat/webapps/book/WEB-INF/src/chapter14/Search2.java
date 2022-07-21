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

@WebServlet(urlPatterns = {"/chapter14/search2"})
public class Search2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var out = resp.getWriter();
        Page.header(out);

        try {
            var ic = new InitialContext();
            var ds = (DataSource) ic.lookup("java:/comp/env/jdbc/book");
            var con = ds.getConnection();

            var price = Integer.parseInt(req.getParameter("price"));
            var st = con.prepareStatement(
                "select * from product where price <= ?"
            );
            st.setInt(1, price);
            var rs = st.executeQuery();

            while (rs.next()) {
                out.println(rs.getInt("id"));
                out.println(" : ");
                out.println(rs.getString("name"));
                out.println(" : ");
                out.println(rs.getInt("price"));
                out.println("<br>");
            }

            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Page.footer(out);
    }
}
