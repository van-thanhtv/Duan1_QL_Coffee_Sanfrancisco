/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_Services;

import DAL_IServices.IOrder_Service;
import DAL_Models.*;
import Utils.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * @author : ThongPro
 * @since : 11/6/2021 3:34 PM
 * @description :
 * @update :
 *
 * */
public class Order_Service implements IOrder_Service {
    String INSERT_SQL = "INSERT INTO [Order]( [IDOrder], [DateOrder],[TimeOder],[UsernameEMP],[IDCust],[Status],DiscountOrder) VALUES (?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE [Order] SET [DateOrder] = ?, [TimeOder] = ?,[UsernameEMP]=?,[IDCust]=?,[Status]=? WHERE [IDOrder] = ?";
    String DELETE_SQL = "UPDATE [Order] SET [Status]=0 WHERE [IDOrder] = ?";
    String SELECT_ALL_SQL = "SELECT * FROM [Order]";
    String SELECT_BY_ID_SQL = "SELECT * FROM [Order] WHERE [IDOrder] = ?";

    @Override
    public void insert(ENTITY_Order entity) {
        try {
            JDBC.update(INSERT_SQL,
                    entity.getIDOrder(),
                    entity.getDateOrder(),
                    entity.getTimeOrder(),
                    entity.getUsernameEMP(),
                    entity.getIDCust(),
                    entity.getStatus(),0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ENTITY_Order entity) {
        try {
            JDBC.update(UPDATE_SQL,
                    entity.getDateOrder(),
                    entity.getTimeOrder(),
                    entity.getUsernameEMP(),
                    entity.getIDCust(),
                    entity.getStatus(),
                    entity.getIDOrder());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String IDOrder) throws SQLException {
        JDBC.update(DELETE_SQL, IDOrder);
    }

    @Override
    public List<ENTITY_Order> select() {
        return this.SelectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public ENTITY_Order findById(String IDOrder) {
        List<ENTITY_Order> list = this.SelectBySQL(SELECT_BY_ID_SQL, IDOrder);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ENTITY_Order> SelectBySQL(String sql, Object... args) {
        List<ENTITY_Order> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                ENTITY_Order order=new ENTITY_Order();
                order.setIDOrder(rs.getString("IDOrder"));
                order.setDateOrder(rs.getDate("DateOrder"));
                order.setTimeOrder(rs.getDate("TimeOrder"));
                order.setUsernameEMP(rs.getString("UsernameEMP"));
                order.setIDCust(rs.getString("IDCust"));
                order.setStatus(rs.getInt("Status"));
                list.add(order);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
