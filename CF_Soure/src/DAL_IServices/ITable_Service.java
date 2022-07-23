/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_IServices;

import DAL_Models.ENTITY_Area;
import DAL_Models.ENTITY_Table;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/*
* @author : ThongPro
* @since : 11/6/2021 11:30 AM
* @description :
* @update :
*
* */
public interface ITable_Service {
    public void insert(ENTITY_Table entity);

    public void update(ENTITY_Table entity);

    public void delete(String IDTable) throws SQLException;

    public List<ENTITY_Table> select();

    public ENTITY_Table findById(String IDTable);

    public List<ENTITY_Table> SelectBySQL(String sql, Object... args);
    
    public List<ENTITY_Table> SQLKhu(int khu);
    public List<ENTITY_Table> findByIdArea(String IDArea);
     public List<ENTITY_Area> selectIDArea();
    public List<ENTITY_Table> select_viTri(int viTri,int idTable);
}
