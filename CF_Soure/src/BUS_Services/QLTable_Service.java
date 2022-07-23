/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_Services;

import BUS_IServices.*;
import DAL_Models.ENTITY_Area;
import DAL_Models.ENTITY_Table;
import DAL_Services.Table_Service;
import Utils.JDBC;
import Utils.ThongBao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author phamd
 */
public class QLTable_Service implements IQLTable_Service {

    JPopupMenu menu = new JPopupMenu("Popup");
    Table_Service dao = new Table_Service();

    @Override
    public void fillTable(JTable tbl) {
        try {
            DefaultTableModel d = (DefaultTableModel) tbl.getModel();
            d.setRowCount(0);
            List<ENTITY_Table> list = (List<ENTITY_Table>) dao.select();
            for (ENTITY_Table t : list) {
               List<ENTITY_Area> l= layAreaname(String.valueOf(t.getIDArea()));
                for (ENTITY_Area e : l) {
                    
                Object[] row = new Object[]{
                    t.getIDTable(),
                    e.getAreaName(),
                    t.getLocation(),
                    "Hoạt động"
                };
                d.addRow(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<ENTITY_Area> layAreaname(String ma){
        try {
                    List<ENTITY_Area> list = new ArrayList<>();
            String sql = "SELECT Areaname from Area where IDArea=?";
            ResultSet rs = JDBC.query(sql, ma);
            while(rs.next()){
                ENTITY_Area a = new ENTITY_Area();
                a.setAreaName(rs.getString(1));
                list.add(a);
            }
             rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
    @Override
    public void taoIDTable(JTextField maTable) {
        try {
            String sql = "Select MAX([IDTable]) from [Table]";
            ResultSet rs = JDBC.query(sql);
            rs.next();
            rs.getString(1);
            if (rs.getString(1) == null) {
                maTable.setText("TB001");
            } else {
                long id = Long.parseLong(rs.getString(1).substring(2, rs.getString(1).length()));
                id++;
                maTable.setText("TB" + String.format("%03d", id));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertMATABLE(ENTITY_Table entity) {
        dao.insert(entity);
    }

    @Override
    public void updatetTABLE(ENTITY_Table entity) {
        dao.update(entity);
    }

    @Override
    public void deleteTABLE(String ma) {
        try {
            dao.delete(ma);
        } catch (SQLException ex) {
            Logger.getLogger(QLTable_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<ENTITY_Area> selectIDArea() {
        return dao.selectIDArea();
    }

    @Override
    public void fillTableByID(JTable tbl, JTextField id) {
        DefaultTableModel m = (DefaultTableModel) tbl.getModel();
        m.fireTableDataChanged();
        TableRowSorter sorter = new TableRowSorter(m);
        tbl.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(id.getText()));

    }

    @Override
    public void fillTableIDArea(JTable tbl, String cbb) {
        try {
            DefaultTableModel d = (DefaultTableModel) tbl.getModel();
            d.setRowCount(0);
            List<ENTITY_Table> list = (List<ENTITY_Table>) dao.findByIdArea(cbb);

            for (ENTITY_Table t : list) {

                Object[] row = new Object[]{
                    t.getIDTable(),
                    "Khu " + t.getIDArea(),
                    t.getLocation(),
                    "Hoạt động"
                };
                d.addRow(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
