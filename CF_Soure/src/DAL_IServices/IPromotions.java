/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_IServices;

import DAL_Models.ENTITY_Promotion;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IPromotions {

    public void insert(ENTITY_Promotion entity);

    public void update(ENTITY_Promotion entity);

    public void delete(String ID);

    public List<ENTITY_Promotion> select();

    public ENTITY_Promotion findById(String IDProduct);

    public List<ENTITY_Promotion> SelectBySQL(String sql, Object... args);
}
