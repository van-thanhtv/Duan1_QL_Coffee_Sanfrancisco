/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_IServices;

import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author phamd
 */
public interface IQLStatistical_Service {

    List<Object[]> getListOfArray(String sql, String[] cols, Object... args);

    public List<Object[]> getListByTKNgay(Date ngayDate);

    public void setDataTKTONG(JPanel pnlNgay, JLabel lbltien);

    public void setTongMonvaHDTC(JLabel lblTM, JLabel lblHD);

    public void setDataNgay(JPanel pnlNgay, Date jdateNgay, JLabel lbltien);

    public List<Object[]> getListByTKThang(int thang, int nam);

    public void setDataThang(JPanel pnlNgay, int thang, int nam, JLabel lbltien);

    public void setDataNam(JPanel pnlNgay, int nam, JLabel lbltien);

    public List<Object[]> getListByTKNam(int nam);

    public void setDataKhoang(JPanel pnlNgay, Date ngayBD, Date ngayKT, JLabel lbltien);

    public List<Object[]> getListByTKKhoangList(Date ngayBD, Date ngayKT);

    public List<Object[]> getListTongMonvaHDNgay(Date date);

    public void setTongMonNgay(JLabel lblTM, JLabel lblHD, Date ngay);

    public List<Object[]> getListTongMonvaHDThang(int date);

    public void setTongMonThang(JLabel lblTM, JLabel lblHD, int nam);

    public List<Object[]> getListTongMonvaHDNam(int date);

    public void setTongMonNam(JLabel lblTM, JLabel lblHD, int nam);

    public void setTongMonKhoang(JLabel lblTM, JLabel lblHD, Date ngayBD, Date ngayKT);

    public List<Object[]> getListTongMonvaHDKhoang(Date ngayBD, Date ngayKT);

    void sendmail(String message);

    void guiBCN(int nam);

    void guiBCNgay(Date ngay);

    void guiBCThang(int thang, int nam);

    public void fillTableSanPham(JTable tbl, JTable tbl2, JLabel lbl, JLabel lblTM, JLabel lbl2, JLabel lblTM2);

    public void fillTableSanPhamNGAY(String ngay, JTable tbl, JTable tbl2, JLabel lbl, JLabel lblTM, JLabel lbl2, JLabel lblTM2);

    public void fillTableSanPhamTHANG(int Thang, JTable tbl, JTable tbl2, JLabel lbl, JLabel lblTM, JLabel lbl2, JLabel lblTM2);

    public void fillTableSanPhamNAM(int Nam, JTable tbl, JTable tbl2, JLabel lbl, JLabel lblTM, JLabel lbl2, JLabel lblTM2);
}
