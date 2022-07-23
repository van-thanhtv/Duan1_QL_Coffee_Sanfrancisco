/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_IServices;

import DAL_Models.ENTITY_Area;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IArea_Service {
        public void insert(String entity);

    public void update(ENTITY_Area entity);

    public void delete(String IDTable) throws SQLException;

    public List<ENTITY_Area> select();

    public List<ENTITY_Area> findById(String IDTable);

    public List<ENTITY_Area> SelectBySQL(String sql, Object... args);
    public List<ENTITY_Area> findByIdArea(String IDTable);
}
