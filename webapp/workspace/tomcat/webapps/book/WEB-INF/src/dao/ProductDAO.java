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
}
