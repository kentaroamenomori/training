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

@WebServlet(urlPatterns = {"/chapter14/all"})
public class All extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var out = resp.getWriter();
        Page.header(out);
        try {
            var ic = new InitialContext();
            var ds = (DataSource) ic.lookup("java:/comp/env/jdbc/book");
            var con = ds.getConnection();

            var st = con.prepareStatement("select * from product");
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
            e.printStackTrace(out);
        }
        Page.footer(out);
    }
}
