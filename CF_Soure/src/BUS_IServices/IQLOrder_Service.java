/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_IServices;

import DAL_Models.ENTITY_Area;
import DAL_Models.ENTITY_BILL;
import DAL_Models.ENTITY_Product;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author phamd
 */
public interface IQLOrder_Service {

    public void taoTable(JPanel that, int cbbkhu, JButton btnVaoBan, JLabel lblBan, JTable tblOder, JTable tblLichSu, JPanel PanlPanelLS, JPanel Oder, JTextField txtMaHD, JTextField txtMaKH, JTextField txtNameEMP, JLabel TimeOrder, JTextField txtTong, JPanel PanCac,JTextField txtThanhTien,JTextField txtDis1,JTextField txtDis2);

    public ArrayList<ENTITY_Area> getkhu();

    public ArrayList<ENTITY_Product> getsp();

    public void hienTableSP(JTable tblSP);

    public void hienTableOder(JTable tblOD);

    public void bill(JTextField txtMaHD, JTextField txtNameEMP, JTextField txtMaKH, JLabel TimeOrder, JTable tblOrder);
    
    public void billTable(JTextField txtMaHD, JTextField txtNameEMP, JTextField txtMaKH, JLabel TimeOrder, JTable tblOrder,String IDTable);

    public void timSP(JTextField txt, JTable tbl);

    public void taoHD(JTextField txt);

    public void updatebnGuoi(String idBan);

    public void updatebnThanhToan(JTextField txtMaHD);

    public void thanhToan(JTextField txtMaHD);

    public void insertOr(JTextField txt);

    public void insertOderDe(ENTITY_BILL bill);

    public void updateOderDe(ENTITY_BILL bill);

    public void lichsuOrder(JPanel PanlPanelLS, JTable tblLichSu);

    public int dongC(int dong);

    public double tongTien(JTextField txttong, JTable tblOder,JTextField txtThanhTien);
    
    public void huyMon(JTextField txtMaHD,String Reason,String IDProduct,String Note);
    
    public String OrderCTT(JTextField txt, String IDTable);
    
    public void huyDon(String txtMaHD,String Reason);
        
    public void tachHDon(String txtMaHDCu,String txtMaHDMoi,String IDProduct,String Note,String IDTable);
    
    public void taoNhom(String tenNhom,String IDTable);
    
    public void xoaNhom(String nhom);
    
    public void ReloadCombobox(JComboBox cbb);
    
    public void txtMaKHCaretUpdate(JTextField txtMaKH,JTextField txtdis1,JLabel lbl,JLabel lbIDCus,JLabel lbNameCus,JLabel lbDateCus,JLabel lbDateEndCus,JLabel lbDisCus);
    
    public void UpdatetxtDis1(JTextField txtTong,JTextField txtDis1,JTextField txtDis2,JTextField txtPay);
    
    public void UpdateKM(String IDCust,String NamePromo,String IDOrder,String DiscountOrder);
    
    public String ChuongTrinhKM (String IDOrder,JTextField txtMaKH);
}
