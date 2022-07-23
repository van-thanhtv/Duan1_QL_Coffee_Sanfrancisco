/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_Services;

import DAL_Models.ENTITY_ADMIN;
import Utils.JDBC;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phamd
 */
public class Admin {

    String SELECT = "SELECT * From [Admin]";
    String SELECT1 = "SELECT * From [Admin] WHERE [Username] = ?";
    String UPDATE = "UPDATE [ADMIN] SET [Password]=?";

    public void update(ENTITY_ADMIN ad) {
        try {
            JDBC.update(UPDATE, ad.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ENTITY_ADMIN> selectBySQL(String sql, Object... args) {
        List<ENTITY_ADMIN> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                ENTITY_ADMIN ad = new ENTITY_ADMIN();
                ad.setUsername(rs.getString(1));
                ad.setPassword(rs.getString(2));
                ad.setEmail(rs.getString(3));
                list.add(ad);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public ENTITY_ADMIN select() {
        List<ENTITY_ADMIN> list = this.selectBySQL(SELECT);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public ENTITY_ADMIN findById(String user) {
        List<ENTITY_ADMIN> list = this.selectBySQL(SELECT1, user);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
