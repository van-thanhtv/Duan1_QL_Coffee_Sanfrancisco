/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_Services;

import DAL_IServices.IProduct_Service;
import DAL_Models.*;
import Utils.JDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phamd
 */
public class Product_Service implements IProduct_Service {

    String INSERT_SQL = "INSERT INTO [Product]([IDProduct], [ProductName], [Price], [Image],[Status],[IDType]) VALUES (?, ?, ?, ?,0,?)";
    String UPDATE_SQL = "UPDATE [Product] SET [ProductName] = ?, [Price] = ?, [Image] = ?,[IDType]=? WHERE [IDProduct] = ?";
    String DELETE_SQL = "UPDATE [Product] SET [Status]=2 WHERE [IDProduct] = ?";
    String SELECT_ALL_SQL = "SELECT * FROM [Product] where [Status]=1 OR [Status]=0";
    String SELECT_BY_ID_SQL = "SELECT * FROM [Product] WHERE [IDProduct] = ?";

    @Override
    public void insert(ENTITY_Product entity) {
        try {
            JDBC.update(INSERT_SQL,
                    entity.getIDProduct(),
                    entity.getProductName(),
                    entity.getPrice(),
                    entity.getImage(),
                    entity.getStatus(),
                    entity.getIDType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ENTITY_Product entity) {
        try {
            JDBC.update(UPDATE_SQL,
                    entity.getProductName(),
                    entity.getPrice(),
                    entity.getImage(),
                    entity.getIDType(),
                    entity.getStatus(),
                    entity.getIDProduct());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String IDProduct) {
        try {
            JDBC.update(DELETE_SQL, IDProduct);
        } catch (SQLException ex) {
            Logger.getLogger(Product_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<ENTITY_Product> select() {
        return this.SelectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public ENTITY_Product findById(String IDProduct) {
        List<ENTITY_Product> list = this.SelectBySQL(SELECT_BY_ID_SQL, IDProduct);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ENTITY_Product> SelectBySQL(String sql, Object... args) {
        List<ENTITY_Product> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                ENTITY_Product product = new ENTITY_Product();
                product.setIDProduct(rs.getString("IDProduct"));
                product.setProductName(rs.getString("ProductName"));
                product.setPrice(rs.getFloat("Price"));
                product.setImage(rs.getString("Image"));
                product.setStatus(rs.getInt("Status"));
                product.setIDType(rs.getInt("IDType"));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
