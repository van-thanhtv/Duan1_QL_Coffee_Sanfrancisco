/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_IServices;

import DAL_Models.ENTITY_Employee;
import javax.swing.JLabel;
import javax.swing.JTable;

/**
 *
 * @author phamd
 */
public interface IQLEmployee_Service {
    void insertMAEMPLOYEE(ENTITY_Employee entity);
    void updateMAEMPLOYEE(ENTITY_Employee entity);
    void deleteEmployee(String UN);
    void fillTable(JTable tbl);
    public void chonAnh(JLabel lbl);
    
}
