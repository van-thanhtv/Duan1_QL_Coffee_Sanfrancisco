/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_Services;

import DAL_IServices.IPromotions;
import DAL_Models.ENTITY_Promotion;
import Utils.JDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class Promotion_Service implements IPromotions {

    String INSERT_SQL = "InSert into Promotions(NamePromo,DiscountPromo,StartPromo,EndPromo,Description,Status) Values(?,?,?,?,?,1)";
    String UPDATE_SQL = "UPDATE Promotions SET NamePromo=?,DiscountPromo=?,StartPromo=?,EndPromo=?,Description=? WHERE [IDPromo] = ?";
    String DELETE_SQL = "UPDATE Promotions SET Status =2 WHERE [IDPromo] = ?";
    String SELECT_ALL_SQL = "Select * from Promotions where [Status]=1 OR [Status]=0";
    String SELECT_BY_ID_SQL = "Select * from Promotions where IDPromo=?";
    String updateOrder = "Update [Order] Set NamePromo=? where NamePromo=?";
    String DELETE_SQL2 = "UPDATE Promotions SET Status =0 WHERE [IDPromo] = ?";

    @Override
    public void insert(ENTITY_Promotion entity) {
        try {
            JDBC.update(INSERT_SQL,
                    entity.getName(),
                    entity.getDiscountPromo(),
                    entity.getStartPromo(),
                    entity.getEndPromo(),
                    entity.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ENTITY_Promotion entity) {
        try {
            JDBC.update(UPDATE_SQL,
                    entity.getName(),
                    entity.getDiscountPromo(),
                    entity.getStartPromo(),
                    entity.getEndPromo(),
                    entity.getDescription(),
                    entity.getIDPro());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String ID) {
        try {
            JDBC.update(DELETE_SQL, ID);
        } catch (SQLException ex) {
            Logger.getLogger(ProductType_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public void delete2(String ID) {
        try {
            JDBC.update(DELETE_SQL2, ID);
        } catch (SQLException ex) {
            Logger.getLogger(ProductType_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<ENTITY_Promotion> select() {
        return this.SelectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public ENTITY_Promotion findById(String ID) {
        List<ENTITY_Promotion> list = this.SelectBySQL(SELECT_BY_ID_SQL, ID);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ENTITY_Promotion> SelectBySQL(String sql, Object... args) {
        List<ENTITY_Promotion> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                ENTITY_Promotion pro = new ENTITY_Promotion();
                pro.setIDPro(rs.getInt("IDPromo"));
                pro.setName(rs.getString("NamePromo"));
                pro.setDiscountPromo(rs.getInt("DiscountPromo"));
                pro.setStartPromo(rs.getDate("StartPromo"));
                pro.setEndPromo(rs.getDate("EndPromo"));
                pro.setDescription(rs.getString("Description"));
                pro.setStatus((rs.getInt("Status")));
                list.add(pro);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void khoiphuc(String ID) {
        String sql = "UPDATE Promotions SET Status=1 WHERE IDPromo = ?";
        try {
            JDBC.update(sql, ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void khuyemmai(String name, String name2) {
        try {
            JDBC.update(updateOrder, name, name2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void up(String DateEnd) throws SQLException {
        String up = "Update [Promotions] Set Status=0 where EndPromo=?";
        JDBC.update(up, DateEnd);
    }

    public void up1(String DateEnd) throws SQLException {
        String sql = "Update [Promotions] Set Status=1 where EndPromo=?";
        JDBC.update(sql, DateEnd);
    }

}
