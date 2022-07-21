package dao;

import java.util.ArrayList;
import java.util.List;

import bean.Product;

public class ProductDAO extends DAO {
    public List<Product> search(String keyword) throws Exception {
        var list = new ArrayList<Product>();
        var con = getConnection();

        var st = con.prepareStatement(
            "select * from product where name like ?");
        st.setString(1, "%" + keyword + "%");
        var rs = st.executeQuery();

        while (rs.next()) {
            var p = new Product();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setPrice(rs.getInt("price"));
            list.add(p);
        }

        st.close();
        con.close();

        return list;
    }

    public int insert(Product product) throws Exception {
        var con = getConnection();
        var st = con.prepareStatement(
            "insert into product(name, price) values(?, ?)"
        );
        st.setString(1, product.getName());
        st.setInt(2, product.getPrice());
        int line = st.executeUpdate();

        st.close();
        con.close();
        return line;
    }
}
