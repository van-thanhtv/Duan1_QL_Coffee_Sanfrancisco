/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_Services;

import BUS_IServices.*;
import DAL_Models.ENTITY_Employee;
import DAL_Services.Employee_Service;
import Utils.XImage;
import java.io.File;
import java.util.List;
import javafx.stage.FileChooser;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author phamd
 */
public class QLEmployee_Service implements IQLEmployee_Service{
    Employee_Service dao = new Employee_Service();
     FileNameExtensionFilter filter = new FileNameExtensionFilter(".jpg","jpg");
            FileNameExtensionFilter filter1 = new FileNameExtensionFilter(".png","png");
    @Override
    public void insertMAEMPLOYEE(ENTITY_Employee entity) {
        dao.insert(entity);
    }

    @Override
    public void fillTable(JTable tbl) {
        try {
            DefaultTableModel d = (DefaultTableModel) tbl.getModel();
            d.setRowCount(0);
            List<ENTITY_Employee> list = (List<ENTITY_Employee>) dao.select();
            for (ENTITY_Employee e : list) {
                Object[] row = new Object[]{
                e.getNameEMP(),
                e.getPhone(),
                e.getBirthday(),
                e.getAddress(),
                e.getSex()?"Nam":"Nữ",
                e.getEmail(),
                e.getUsernameEMP(),
                "******"
                };
                d.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    JFileChooser fileChooser = new JFileChooser();
    @Override
    public void chonAnh(JLabel lbl) {
        fileChooser.setDialogTitle("chọn ảnh đi bro");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(filter);
            fileChooser.addChoosableFileFilter(filter1);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();//lay ra file dc chon
            XImage.save(file); // luu vao trong thu muc
            ImageIcon icon = XImage.read(file.getName()); //doc file tu thu muc
            lbl.setIcon(icon); //hien thi len lblhinh
            lbl.setToolTipText(file.getName()); //giu ten hinh trong tooltip
        }
    }

    @Override
    public void deleteEmployee(String UN) {
        try {
            dao.delete(UN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMAEMPLOYEE(ENTITY_Employee entity) {
        dao.update(entity);
    }
}
