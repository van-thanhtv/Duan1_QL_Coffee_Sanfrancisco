package DAL_Services;

import DAL_IServices.IArea_Service;
import DAL_Models.ENTITY_Area;
import Utils.JDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Area_Service implements IArea_Service {
    String INSERT_SQL = "INSERT INTO [Area]([AreaName],Status) VALUES(?,1);";
    String UPDATE_SQL = "UPDATE [Area] Set [AreaName]=?,[MaxTable]=?, WHERE [IDArea]=?";
    String DELETE_SQL = "Update [Area] set Status=0 WHERE [IDArea]=?";
    String SELECT_ALL_SQL = "SELECT * FROM [Area] where Status=1";
    String SELECT_BY_ID_SQL = "SELECT * FROM [Area] WHERE [AreaName]=?";
    String select = "SELECT * FROM [Area] WHERE [IDArea]=? and Status =1";

    @Override
    public void insert(String khu) {
        try {
            JDBC.update(INSERT_SQL,khu);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ENTITY_Area entity) {
        try {
            JDBC.update(UPDATE_SQL,
                    entity.getAreaName(),
                    entity.getMaxTable(),
                    entity.getIDArea());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String IDArea) throws SQLException {
        JDBC.update(DELETE_SQL, IDArea);
    }

    @Override
    public List<ENTITY_Area> select() {
        return this.SelectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public List<ENTITY_Area> findById(String IDTable) {
        return this.SelectBySQL(SELECT_BY_ID_SQL, IDTable);
    }
    @Override
    public List<ENTITY_Area> findByIdArea(String IDTable) {
        return this.SelectBySQL(select, IDTable);
    }

    @Override
    public List<ENTITY_Area> SelectBySQL(String sql, Object... args) {
        List<ENTITY_Area> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                ENTITY_Area area = new ENTITY_Area();
                area.setIDArea(rs.getInt("IDArea"));
                area.setAreaName(rs.getString("AreaName"));
                area.setMaxTable(rs.getInt("MaxTable"));
                list.add(area);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
