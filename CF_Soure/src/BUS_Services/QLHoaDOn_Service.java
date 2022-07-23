/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_Services;

import BUS_IServices.IQLEmployee_Service;
import DAL_IServices.IEmployee_Service;
import DAL_Models.ENTITY_Employee;
import DAL_Models.ENTITY_Order;
import DAL_Models.ENTITY_Product;
import DAL_Services.Employee_Service;
import Utils.JDBC;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author phamd
 */
public class QLHoaDOn_Service {

    private NumberFormat n = new DecimalFormat("#,###");

    public List<ENTITY_Product> SelectBySQL(String sql, Object... args) {
        List<ENTITY_Product> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                ENTITY_Product customer = new ENTITY_Product();
                customer.setProductName(rs.getString(1));

                list.add(customer);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Object[]> getListHoaDon() {
        String sql = "{CALL getListHoaDon}";
        String[] cols = {"IDHD", "NameEMP", "CusName", "NamePromo", "DateOrder", "TimeOder", "Reason", "TongTien", "Status", "DiscountOrder"};
        return this.getListOfArray(sql, cols);
    }

    public List<Object[]> getListHoaDonNgay(Date ngay) {
        String sql = "{CALL getListHoaDonNgay(?)}";
        String[] cols = {"IDHD", "NameEMP", "CusName", "NamePromo", "DateOrder", "TimeOder", "Reason", "TongTien", "Status", "DiscountOrder"};
        return this.getListOfArray(sql, cols, ngay);
    }

    public List<Object[]> getListHoaDonTHANG(int thang, int nam) {
        String sql = "{CALL getListHoaDonThang(?,?)}";
        String[] cols = {"IDHD", "NameEMP", "CusName", "NamePromo", "DateOrder", "TimeOder", "Reason", "TongTien", "Status", "DiscountOrder"};
        return this.getListOfArray(sql, cols, thang, nam);
    }

    public List<Object[]> getListHoaDonNAM(int nam) {
        String sql = "{CALL getListHoaDonNAM(?)}";
        String[] cols = {"IDHD", "NameEMP", "CusName", "NamePromo", "DateOrder", "TimeOder", "Reason", "TongTien", "Status", "DiscountOrder"};
        return this.getListOfArray(sql, cols, nam);
    }

    public List<ENTITY_Product> getListDoUong(String idHD) {
        String sql = "select ProductName from Product p join OrderDetail od on p.IDProduct=od.IDProduct where od.IDOrder=?";
        return SelectBySQL(sql, idHD);
    }

    public void fillTable(JTable tbl, JLabel lblTIen, JLabel lblHDHuy, JLabel lblTongBill, JLabel lblDTT, JLabel lblTOngDH,JLabel lblCTT) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        String tt = null;
        String doUong = "";
        String nv = "";
        float tongTien = 0;
        float tien = 0;
        float tienDTT = 0;
        float tienDH = 0;
        int billHUY = 0;
        int billCTT = 0;
        List<Object[]> list = getListHoaDon();
        if (list != null) {
            for (Object[] o : list) {
                List<ENTITY_Product> listdoUong = getListDoUong(String.valueOf(o[0]));
                doUong = "";
                tongTien = Float.parseFloat(String.valueOf(o[7]));
                for (ENTITY_Product odu : listdoUong) {
                    doUong = doUong + odu.getProductName() + ",";
                }
                if (o[2] == "0" && o[3] == "0") {
                    tongTien = Float.parseFloat(String.valueOf(o[7]));
                } else {
                    if (o[2] != "0") {
                        tongTien = Float.parseFloat(String.valueOf(o[7])) - (Float.parseFloat(String.valueOf(o[7])) * Float.parseFloat(String.valueOf(o[9]))) / 100;
                    } else {
                        tongTien = Float.parseFloat(String.valueOf(o[7])) - (Float.parseFloat(String.valueOf(o[7])) * Float.parseFloat(String.valueOf(o[9]))) / 100;
                    }
                }

                int ma = Integer.valueOf(String.valueOf(o[8]));
                if (ma == 1) {
                    tt = "Chưa thanh toán";
                    billCTT++;
                } else if (ma == 2) {
                    tt = "Đã thanh toán";
                    tienDTT += tongTien;
                } else if (ma == 3) {
                    tt = "Đã hủy";
                    tienDH += tongTien;
                    billHUY++;
                }
                tien += tongTien;
                Object[] row = new Object[]{
                    o[0],
                    o[1] == null ? "ADMIN" : o[1],
                    o[2] == null ? "Khách vãng lai" : o[2],
                    o[3] == null ? "Không có" : o[3],
                    o[4],
                    o[5],
                    o[6],
                    doUong,
                    n.format(tongTien) + "VNĐ",
                    tt
                };
                model.addRow(row);
            }

            lblTongBill.setText(String.valueOf(list.size()));
            lblTIen.setText(n.format(tien) + " VNĐ");
            lblHDHuy.setText(String.valueOf(billHUY));
            lblCTT.setText(String.valueOf(billCTT));
            lblDTT.setText(String.valueOf(n.format(tienDTT) + " VNĐ"));
            lblTOngDH.setText(String.valueOf(n.format(tienDH) + " VNĐ"));

        }
    }

    public void fillTableNgay(JTable tbl, Date date, JLabel lblTIen, JLabel lblHDHuy, JLabel lblTongBill, JLabel lblDTT, JLabel lblTOngDH,JLabel lblCTT) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        String tt = null;
        String doUong = "";
        String nv = "";
        float tongTien = 0;
        float tien = 0;
        int billHUY = 0;
        int billCTT = 0;
        float tienDTT = 0;
        float tienDH = 0;
        List<Object[]> list = getListHoaDonNgay(date);
        if (list != null) {
            for (Object[] o : list) {
                List<ENTITY_Product> listdoUong = getListDoUong(String.valueOf(o[0]));
                doUong = "";
                for (ENTITY_Product odu : listdoUong) {
                    doUong = doUong + odu.getProductName() + ",";
                }

                if (o[2] == "0" && o[3] == "0") {
                    tongTien = Float.parseFloat(String.valueOf(o[7]));
                } else {
                    if (o[2] != "0") {
                        tongTien = Float.parseFloat(String.valueOf(o[7])) - (Float.parseFloat(String.valueOf(o[7])) * Float.parseFloat(String.valueOf(o[9]))) / 100;
                    } else {
                        tongTien = Float.parseFloat(String.valueOf(o[7])) - (Float.parseFloat(String.valueOf(o[7])) * Float.parseFloat(String.valueOf(o[9]))) / 100;
                    }
                }
                tien += tongTien;
                int ma = Integer.valueOf(String.valueOf(o[8]));
                if (ma == 1) {
                    tt = "Chưa thanh toán";
                    billCTT++;
                } else if (ma == 2) {
                    tt = "Đã thanh toán";
                    tienDTT += tongTien;
                } else if (ma == 3) {
                    tt = "Đã hủy";
                    tienDH += tongTien;
                    billHUY++;
                }
                Object[] row = new Object[]{
                    o[0],
                    o[1] == null ? "ADMIN" : o[1],
                    o[2] == null ? "Khách vãng lai" : o[2],
                    o[3] == null ? "Không có" : o[3],
                    o[4],
                    o[5],
                    o[6],
                    doUong,
                    n.format(tongTien) + "VNĐ",
                    tt
                };
                model.addRow(row);
                lblTongBill.setText(String.valueOf(list.size()));
                lblTIen.setText(n.format(tien) + " VNĐ");
                lblHDHuy.setText(String.valueOf(billHUY));
                lblCTT.setText(String.valueOf(billCTT));
                lblDTT.setText(String.valueOf(n.format(tienDTT) + " VNĐ"));
                lblTOngDH.setText(String.valueOf(n.format(tienDH) + " VNĐ"));
            }
        }
    }

    public void fillTableTHANG2(JTable tbl, int thang, int nam, JLabel lblTIen, JLabel lblHDHuy, JLabel lblTongBill, JLabel lblDTT, JLabel lblTOngDH,JLabel lblCTT) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        String tt = null;
        String doUong = "";
        String nv = "";
        float tongTien = 0;
        float tien = 0;
        int billHUY = 0;
        int billCTT = 0;
        float tienDTT = 0;
        float tienDH = 0;
        List<Object[]> list = getListHoaDonTHANG(thang, nam);
        if (list != null) {
            for (Object[] o : list) {
                List<ENTITY_Product> listdoUong = getListDoUong(String.valueOf(o[0]));
                doUong = "";
                for (ENTITY_Product odu : listdoUong) {
                    doUong = doUong + odu.getProductName() + ",";
                }


                if (o[2] == "0" && o[3] == "0") {
                    tongTien = Float.parseFloat(String.valueOf(o[7]));
                } else {
                    if (o[2] != "0") {
                        tongTien = Float.parseFloat(String.valueOf(o[7])) - (Float.parseFloat(String.valueOf(o[7])) * Float.parseFloat(String.valueOf(o[9]))) / 100;
                    } else {
                        tongTien = Float.parseFloat(String.valueOf(o[7])) - (Float.parseFloat(String.valueOf(o[7])) * Float.parseFloat(String.valueOf(o[9]))) / 100;
                    }
                }
                tien += tongTien;
                                int ma = Integer.valueOf(String.valueOf(o[8]));
                if (ma == 1) {
                    tt = "Chưa thanh toán";
                    billCTT++;
                } else if (ma == 2) {
                    tt = "Đã thanh toán";
                    tienDTT += tongTien;
                } else if (ma == 3) {
                    tt = "Đã hủy";
                    tienDH += tongTien;
                    billHUY++;
                }
                Object[] row = new Object[]{
                    o[0],
                    o[1] == null ? "ADMIN" : o[1],
                    o[2] == null ? "Khách vãng lai" : o[2],
                    o[3] == null ? "Không có" : o[3],
                    o[4],
                    o[5],
                    o[6],
                    doUong,
                    n.format(tongTien) + "VNĐ",
                    tt
                };
                model.addRow(row);
                lblTongBill.setText(String.valueOf(list.size()));
                lblTIen.setText(n.format(tien) + " VNĐ");
                lblHDHuy.setText(String.valueOf(billHUY));
                lblCTT.setText(String.valueOf(billCTT));
                lblDTT.setText(String.valueOf(n.format(tienDTT) + " VNĐ"));
                lblTOngDH.setText(String.valueOf(n.format(tienDH) + " VNĐ"));
            }
        }
    }

    public void fillTableNAM(JTable tbl, int nam, JLabel lblTIen, JLabel lblHDHuy, JLabel lblTongBill, JLabel lblDTT, JLabel lblTOngDH,JLabel lblCTT) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        String tt = null;
        String doUong = "";
        String nv = "";
        float tongTien = 0;
        float tien = 0;
        int billHUY = 0;
        int billCTT = 0;
        float tienDTT = 0;
        float tienDH = 0;
        List<Object[]> list = getListHoaDonNAM(nam);
        if (list != null) {
            for (Object[] o : list) {
                List<ENTITY_Product> listdoUong = getListDoUong(String.valueOf(o[0]));
                doUong = "";
                for (ENTITY_Product odu : listdoUong) {
                    doUong = doUong + odu.getProductName() + ",";
                }


                if (o[2] == "0" && o[3] == "0") {
                    tongTien = Float.parseFloat(String.valueOf(o[7]));
                } else {
                    if (o[2] != "0") {
                        tongTien = Float.parseFloat(String.valueOf(o[7])) - (Float.parseFloat(String.valueOf(o[7])) * Float.parseFloat(String.valueOf(o[9]))) / 100;
                    } else {
                        tongTien = Float.parseFloat(String.valueOf(o[7])) - (Float.parseFloat(String.valueOf(o[7])) * Float.parseFloat(String.valueOf(o[9]))) / 100;
                    }
                }
                tien += tongTien;
                                int ma = Integer.valueOf(String.valueOf(o[8]));
                if (ma == 1) {
                    tt = "Chưa thanh toán";
                    billCTT++;
                } else if (ma == 2) {
                    tt = "Đã thanh toán";
                    tienDTT += tongTien;
                } else if (ma == 3) {
                    tt = "Đã hủy";
                    tienDH += tongTien;
                    billHUY++;
                }
                Object[] row = new Object[]{
                    o[0],
                    o[1] == null ? "ADMIN" : o[1],
                    o[2] == null ? "Khách vãng lai" : o[2],
                    o[3] == null ? "Không có" : o[3],
                    o[4],
                    o[5],
                    o[6],
                    doUong,
                    n.format(tongTien) + "VNĐ",
                    tt
                };
                model.addRow(row);
                lblTongBill.setText(String.valueOf(list.size()));
                lblTIen.setText(n.format(tien) + " VNĐ");
                lblHDHuy.setText(String.valueOf(billHUY));
                lblDTT.setText(String.valueOf(n.format(tienDTT) + " VNĐ"));
                lblTOngDH.setText(String.valueOf(n.format(tienDH) + " VNĐ"));
                lblCTT.setText(String.valueOf(billCTT));
            }
        }
    }

    public void huyHoaDon(String lyDo, String idOrder) {
        try {
            String sql = "Update [Order] SET Status=3,Reason=? where IDOrder=?";
            JDBC.update(sql, lyDo, idOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void khoiPhucHD(String lyDo, String idOrder) {
        try {
            String sql = "Update [Order] SET Status=2,Reason=? where IDOrder=?";
            JDBC.update(sql, lyDo, idOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
