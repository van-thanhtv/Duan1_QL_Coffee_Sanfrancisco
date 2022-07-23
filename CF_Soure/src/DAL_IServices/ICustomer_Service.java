/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_IServices;

import DAL_Models.ENTITY_Customer;
import java.sql.SQLException;
import java.util.List;

/*
* @author : ThongPro
* @since : 11/6/2021 11:37 AM
* @description :
* @update :
*
* */
public interface ICustomer_Service {
    public void insert(ENTITY_Customer entity);

    public void update(ENTITY_Customer entity);

    public void delete(String IDCust) throws SQLException;

    public List<ENTITY_Customer> select();

    public ENTITY_Customer findById(String IDCust);

    public List<ENTITY_Customer> SelectBySQL(String sql, Object... args);
}
