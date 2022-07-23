/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_Services;

import DAL_IServices.IProductType_Service;
import DAL_Models.ENTITY_Product;
import DAL_Models.ENTITY_ProductType;
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
public class ProductType_Service implements IProductType_Service {

    String INSERT_SQL = "Insert into ProductType(IDType,TypeName,Size,Status) Values(?,?,?,1)";
    String UPDATE_SQL = "Update ProductType Set TypeName=?,Size=? Where IDType=?";
    String DELETE_SQL = "Update ProductType set Status=0 where IDType=?";
    String SELECT_ALL_SQL = "Select * from [ProductType] Where Status=1";
    String SELECT_BY_ID_SQL = "Select * from ProductType Where IDType=?";

    @Override
    public void insert(ENTITY_ProductType entity) {
        try {
            JDBC.update(INSERT_SQL,
                    entity.getIDType(),
                    entity.getTypeName(),
                    entity.getSize());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void update(ENTITY_ProductType entity) {
        try {
            JDBC.update(UPDATE_SQL,
                    entity.getTypeName(),
                    entity.getSize(),
                    entity.getIDType());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void delete(String IDType) {
        try {
            JDBC.update(DELETE_SQL, IDType);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ProductType_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<ENTITY_ProductType> select() {
        return this.SelectBySQL(SELECT_ALL_SQL);
    }
    
    @Override
    public ENTITY_ProductType findById(String IDType) {
        List<ENTITY_ProductType> list = this.SelectBySQL(SELECT_BY_ID_SQL, IDType);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    @Override
    public List<ENTITY_ProductType> SelectBySQL(String sql, Object... args) {
        List<ENTITY_ProductType> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                ENTITY_ProductType product = new ENTITY_ProductType();
                product.setIDType(rs.getInt("IDType"));
                product.setTypeName(rs.getString("TypeName"));
                product.setSize(rs.getString("Size"));
                product.setStatus(rs.getBoolean("Status"));
                list.add(product);
            }
            
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
