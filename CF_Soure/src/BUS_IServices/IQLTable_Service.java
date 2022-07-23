/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_IServices;

import DAL_Models.ENTITY_Area;
import DAL_Models.ENTITY_Table;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author phamd
 */
public interface IQLTable_Service {

    void fillTable(JTable tbl);
    void fillTableByID(JTable tbl,JTextField id);

    void taoIDTable(JTextField maTable);

    void insertMATABLE(ENTITY_Table entity);
    
    void deleteTABLE(String ma);
     public  List<ENTITY_Area> selectIDArea();
     public  void updatetTABLE(ENTITY_Table entity);
     public void fillTableIDArea(JTable tbl,String cbb);
     
}
