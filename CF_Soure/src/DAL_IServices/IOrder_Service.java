/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_IServices;

import DAL_Models.ENTITY_Order;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/*
* @author : ThongPro
* @since : 11/6/2021 11:35 AM
* @description :
* @update :
*
* */
public interface IOrder_Service {
    public void insert(ENTITY_Order entity);

    public void update(ENTITY_Order entity);

    public void delete(String IDOrder) throws SQLException;

    public List<ENTITY_Order> select();

    public ENTITY_Order findById(String IDOrder);

    public List<ENTITY_Order> SelectBySQL(String sql, Object... args);
}
