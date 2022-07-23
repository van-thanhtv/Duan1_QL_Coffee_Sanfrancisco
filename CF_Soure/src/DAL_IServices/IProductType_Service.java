/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_IServices;

import DAL_Models.ENTITY_ProductType;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IProductType_Service {
    public void insert(ENTITY_ProductType entity);

    public void update(ENTITY_ProductType entity);

    public void delete(String IDType);

    public List<ENTITY_ProductType> select();

    public ENTITY_ProductType findById(String IDType);

    public List<ENTITY_ProductType> SelectBySQL(String sql, Object... args);
}
