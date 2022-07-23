/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_Services;

import DAL_IServices.ITable_Service;
import DAL_Models.*;
import Utils.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
* @author : ThongPro
* @since : 11/6/2021 12:07 PM
* @description :
* @update :
*
* */
public class Table_Service implements ITable_Service {
    String INSERT_SQL = "INSERT INTO [Table]([IDTable], [Location],[Status],[IDArea]) VALUES (?, ?,?, ?)";
    String UPDATE_SQL = "UPDATE [Table] SET [Location] = ?,[IDArea]=? WHERE [IDTable]=?";
    String DELETE_SQL = "UPDATE [Table] SET [Status]=2 WHERE [IDTable] = ?";
    String UPDATE_Trung = "UPDATE [Table] SET [IDArea]=?, [Status]=0 WHERE [Location] = ? and [Status]=2";
        String SELECT_ALL_SQL = "SELECT DISTINCT * FROM [Table] where [Status]=0";
    String Select_IDArea ="SELECT [AreaName],IDArea FROM [Area] where Status=1";
    String SELECT_BY_ID_SQL = "SELECT * FROM [Table] WHERE [IDTable] = ? and [Status]=0";
    String SQL = "SELECT * FROM [Table] WHERE [IDArea] = ? ORDER BY Location";
    String SELECT_BY_ID_Area = "SELECT * FROM [Table] WHERE [IDArea] = ? and [Status]=0";
    String select_ViTri = "select * from [Table] where  [Location] = ? and IDArea=?";
    String select_CheckTrung = "select * from [Table] where  [Location] = ? and [Status]=0";
   
    public void update_trung(String idKhu,int id){
        try {
            JDBC.update(UPDATE_Trung,idKhu, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void insert(ENTITY_Table entity) {
        try {
            JDBC.update(INSERT_SQL,
                    entity.getIDTable(),
                    entity.getLocation(),
                    0,
                    entity.getIDArea());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ENTITY_Table entity) {
        try {
            JDBC.update(UPDATE_SQL,
                    entity.getLocation(),
                  //  entity.getStatus(),
                   entity.getIDArea(),
                    entity.getIDTable());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String IDTable) throws SQLException {
        JDBC.update(DELETE_SQL, IDTable);
    }

    @Override
    public List<ENTITY_Table> select() {
        return this.SelectBySQL(SELECT_ALL_SQL);
    }
    @Override
    public List<ENTITY_Table> select_viTri(int viTri,int idTable) {
        return this.SelectBySQL(select_ViTri,viTri,idTable);
    }
    public List<ENTITY_Table> select_CheckTrung(int viTri) {
        return this.SelectBySQL(select_CheckTrung,viTri);
    }
    @Override
    public List<ENTITY_Area> selectIDArea() {
        return this.SelectBySQLIDArea(Select_IDArea);
    }
     public List<ENTITY_Area> SelectBySQLIDArea(String sql, Object... args) {
        List<ENTITY_Area> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                ENTITY_Area table = new ENTITY_Area();
                table.setAreaName(rs.getString(1));
                table.setIDArea(rs.getInt(2));
                list.add(table);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public ENTITY_Table findById(String IDTable) {
        List<ENTITY_Table> list = this.SelectBySQL(SELECT_BY_ID_SQL, IDTable);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ENTITY_Table> SelectBySQL(String sql, Object... args) {
        List<ENTITY_Table> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                ENTITY_Table table = new ENTITY_Table();
                table.setIDTable(rs.getString("IDTable"));
                table.setLocation(rs.getInt("Location"));
                table.setStatus(rs.getInt("Status"));
                table.setIDArea(rs.getInt("IDArea"));
                table.setTableGroup(rs.getString("TableGroup"));
                list.add(table);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

        @Override
    public List<ENTITY_Table> findByIdArea(String IDArea) {
        return this.SelectBySQL(SELECT_BY_ID_Area, IDArea);

    }

    @Override
    public List<ENTITY_Table> SQLKhu(int khu) {
    return this.SelectBySQL(SQL, khu); 
    }

}
