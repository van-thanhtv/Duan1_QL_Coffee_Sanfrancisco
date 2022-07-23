/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_IServices;

import DAL_Models.ENTITY_Employee;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/*
 * @author : ThongPro
 * @since : 11/5/2021 11:50 PM
 * @description :
 * @update :
 *
 * */
public interface IEmployee_Service {
    public void insert(ENTITY_Employee entity);

    public void update(ENTITY_Employee entity);

    public void delete(String UsernameEMP) throws SQLException;

    public List<ENTITY_Employee> select();

    public ENTITY_Employee findById(String UsernameEMP);

    public List<ENTITY_Employee> SelectBySQL(String sql, Object... args);
    
    public void updateMK(String pass,String maNV);
}
