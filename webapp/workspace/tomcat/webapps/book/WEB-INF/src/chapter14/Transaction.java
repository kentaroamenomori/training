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

@WebServlet(urlPatterns = {"/chapter14/transaction"})
public class Transaction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var out = resp.getWriter();
        Page.header(out);

        try {
            var ic = new InitialContext();
            var ds = (DataSource) ic.lookup("java:/comp/env/jdbc/book");
            var con = ds.getConnection();

            con.setAutoCommit(false);

            var name = req.getParameter("name");
            var price = Integer.parseInt(req.getParameter("price"));
            var st = con.prepareStatement(
                "insert into product(name, price) values(?, ?)"
            );
            st.setString(1, name);
            st.setInt(2, price);
            st.executeUpdate();

            st = con.prepareStatement(
                "select * from product where name=?"
            );
            st.setString(1, name);
            var rs = st.executeQuery();

            int line = 0;
            while (rs.next()) {
                line++;
            }
            
            if (line == 1) {
                con.commit();
                out.println("追加しました。");
            } else {
                con.rollback();
                out.println("すでに登録されています。");
            }

            con.setAutoCommit(true);

            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Page.footer(out);
    }
}
