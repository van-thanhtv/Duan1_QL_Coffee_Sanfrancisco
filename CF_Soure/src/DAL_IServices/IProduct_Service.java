/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_IServices;

import DAL_Models.ENTITY_Product;

import java.util.ArrayList;
import java.util.List;

/*
* @author : ThongPro
* @since : 11/6/2021 11:33 AM
* @description :
* @update :
*
* */
public interface IProduct_Service {
    public void insert(ENTITY_Product entity);

    public void update(ENTITY_Product entity);

    public void delete(String IDProduct);

    public List<ENTITY_Product> select();

    public ENTITY_Product findById(String IDProduct);

    public List<ENTITY_Product> SelectBySQL(String sql, Object... args);
}
