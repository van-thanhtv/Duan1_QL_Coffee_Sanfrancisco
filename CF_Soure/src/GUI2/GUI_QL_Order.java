/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI2;

import BUS_IServices.IQLOrder_Service;
import BUS_Services.QLOrder_Service;
import DAL_Models.ENTITY_Area;
import DAL_Models.ENTITY_BILL;
import Utils.Auth;
import Utils.JDBC;
import Utils.XImage;
import Utils.dateHelper;
import Utils.dialogHelper;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.EventObject;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Tran Van Thanh
 */
public class GUI_QL_Order extends javax.swing.JPanel {

    private IQLOrder_Service qlod;
    private NumberFormat n = new DecimalFormat("#,###");
    private DefaultTableModel modell;
    private CardLayout card;
    private int row;
    private String grop;
    private int huy = 0;

    /**
     * Creates new form GUI_QL_Order
     */
    public GUI_QL_Order() {
        initComponents();
        init();
        tblSanPham.getColumnModel().getColumn(0).setMinWidth(0);
        tblSanPham.getColumnModel().getColumn(0).setMaxWidth(0);
        tblOrder.getColumnModel().getColumn(1).setMinWidth(0);
        tblOrder.getColumnModel().getColumn(1).setMaxWidth(0);
    }

    private void init() {
        this.setSize(1200, 700);
        this.qlod = (IQLOrder_Service) new QLOrder_Service(this, this.btnVaoBan, lblBan, tblOrder, tblLichSu, PanLichSu, PanOrder, txtmaHD, txtMaKH, txtNameEMP, lblTime, txtTong, PanCac, txtPay, txtDis1, txtDis2);
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbKhu.getModel(); //kết nối cbo với model
        this.modell = (DefaultTableModel) this.tblOrder.getModel();
        model.removeAllElements(); //xóa toàn bộ item
        this.qlod.hienTableOder(tblOrder);
        try {
            //lấy tất cả đối tượng nguoiHoc không thuộc khoaHoc từ CSDL
            //rồi thêm vào combobox
            ArrayList<ENTITY_Area> list = this.qlod.getkhu();
            for (ENTITY_Area nh : list) {
                model.addElement(nh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.tblSanPham.setName("SP");
        this.tblOrder.setName("Oder");
        this.qlod.hienTableSP(this.tblSanPham);
        this.txtmaHD.setEditable(false);
        this.txtNameEMP.setEditable(false);
        this.txtTong.setEditable(false);
        this.txtTienThua.setEditable(false);
        this.txtDis1.setEditable(false);
        this.txtDis2.setEditable(false);
        this.txtPay.setEditable(false);
        tblSanPham.getColumnModel().getColumn(6).setCellRenderer(new GUI2.GUI_QL_Order.ClientsTableButtonRenderer());
        tblSanPham.getColumnModel().getColumn(6).setCellEditor(new GUI2.GUI_QL_Order.ClientsTableRenderer(new JCheckBox()));
        tblSanPham.setPreferredScrollableViewportSize(tblSanPham.getPreferredSize());
        tblSanPham.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());
        tblOrder.getColumnModel().getColumn(8).setCellRenderer(new GUI2.GUI_QL_Order.ClientsTableButtonRenderer());
        tblOrder.getColumnModel().getColumn(8).setCellEditor(new GUI2.GUI_QL_Order.ClientsTableRenderer(new JCheckBox()));
        tblOrder.setPreferredScrollableViewportSize(tblOrder.getPreferredSize());
        TableColumnModel tcm = tblOrder.getColumnModel();
        TableColumn tc = tcm.getColumn(5);
        tc.setCellEditor(new GUI2.GUI_QL_Order.SpinnerEditor());
        this.card = (CardLayout) PanCac.getLayout();
        card.show(PanCac, "card4");
        this.lblNgay.setText(this.lblNgay.getText() + dateHelper.DATE_FORMATER.format(dateHelper.now()));
        tblOrder.setRowHeight(30);
        tblSanPham.setRowHeight(80);
        tblSanPham.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
        qlod.ReloadCombobox(cbbkm);
        start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pmnOder = new javax.swing.JPopupMenu();
        mnTachMon = new javax.swing.JMenuItem();
        mnHuymon = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        PanCac = new javax.swing.JPanel();
        panChao = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        PanLichSu = new javax.swing.JPanel();
        lblNgay = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblLichSu = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        PanOrder = new javax.swing.JPanel();
        txtmaHD = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lblBan = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTong = new javax.swing.JTextField();
        btnThanhToan = new javax.swing.JButton();
        btnLuuVSIn = new javax.swing.JButton();
        btnTachHD = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtDis1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        btnhuy = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNameEMP = new javax.swing.JTextField();
        btnguowi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrder = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtPay = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTienTra = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtDis2 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("logos//hqR7r.png");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jLabel6 = new javax.swing.JLabel();
        cbbkm = new javax.swing.JComboBox<>();
        lbNhap1 = new javax.swing.JLabel();
        lbNgayKM = new javax.swing.JLabel();
        lbIDError = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("logos//hqR7r.png");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                //        g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                //        setBackground(new Color(0,0,0,125));
                super.paintComponent(g);
            }
        };
        pnInformation2 = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("logos//hqR7r.png");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                //        g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                //        setBackground(new Color(0,0,0,125));
                super.paintComponent(g);
            }
        };
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        lbIDCus = new javax.swing.JLabel();
        lbNameCus = new javax.swing.JLabel();
        lbDisCus = new javax.swing.JLabel();
        lbDateaddCus = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        lbNhap = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        lbDateEndCus = new javax.swing.JLabel();
        lbLoiGia = new javax.swing.JLabel();
        cbbKhu = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnVaoBan = new javax.swing.JButton();
        btnxemlichsu = new javax.swing.JButton();
        PanSanPham = new javax.swing.JPanel();
        txtTim = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();

        mnTachMon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/tachHD.png"))); // NOI18N
        mnTachMon.setText("Tách món");
        mnTachMon.setToolTipText("");
        mnTachMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnTachMonActionPerformed(evt);
            }
        });
        pmnOder.add(mnTachMon);

        mnHuymon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Button-Close-icon.png"))); // NOI18N
        mnHuymon.setText("Hủy món");
        mnHuymon.setToolTipText("");
        mnHuymon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnHuymonActionPerformed(evt);
            }
        });
        pmnOder.add(mnHuymon);

        setBackground(new java.awt.Color(255, 153, 153));

        jPanel2.setBackground(new java.awt.Color(255, 153, 153));

        PanCac.setForeground(new java.awt.Color(153, 255, 153));
        PanCac.setLayout(new java.awt.CardLayout());

        panChao.setBackground(new java.awt.Color(255, 153, 153));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/chaoOrder.gif"))); // NOI18N

        javax.swing.GroupLayout panChaoLayout = new javax.swing.GroupLayout(panChao);
        panChao.setLayout(panChaoLayout);
        panChaoLayout.setHorizontalGroup(
            panChaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panChaoLayout.createSequentialGroup()
                .addContainerGap(271, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(268, 268, 268))
        );
        panChaoLayout.setVerticalGroup(
            panChaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panChaoLayout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(jLabel13)
                .addContainerGap(555, Short.MAX_VALUE))
        );

        PanCac.add(panChao, "card4");

        PanLichSu.setBackground(new java.awt.Color(255, 153, 153));
        PanLichSu.setBorder(javax.swing.BorderFactory.createTitledBorder("Lịch sử Order"));
        PanLichSu.setForeground(new java.awt.Color(102, 255, 102));

        lblNgay.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblNgay.setText("Lịch sử Oder ngày : ");

        tblLichSu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDOrder", "TimeOrder", "Name NV", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLichSu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLichSuMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblLichSu);

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/sanfrancisco.png"))); // NOI18N

        javax.swing.GroupLayout PanLichSuLayout = new javax.swing.GroupLayout(PanLichSu);
        PanLichSu.setLayout(PanLichSuLayout);
        PanLichSuLayout.setHorizontalGroup(
            PanLichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanLichSuLayout.createSequentialGroup()
                .addGap(346, 346, 346)
                .addComponent(lblNgay)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanLichSuLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(PanLichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanLichSuLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 869, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanLichSuLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(63, 63, 63))))
        );
        PanLichSuLayout.setVerticalGroup(
            PanLichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanLichSuLayout.createSequentialGroup()
                .addComponent(lblNgay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PanCac.add(PanLichSu, "card2");

        PanOrder.setBackground(new java.awt.Color(255, 153, 153));
        PanOrder.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách Order"));
        PanOrder.setForeground(new java.awt.Color(153, 255, 51));

        txtmaHD.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtmaHDCaretUpdate(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Mã HĐ");

        lblBan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblBan.setForeground(new java.awt.Color(51, 51, 255));
        lblBan.setText("00");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Bàn");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Tổng tiền");

        txtTong.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtTong.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTong.setText("0.VNĐ");

        btnThanhToan.setBackground(new java.awt.Color(51, 153, 0));
        btnThanhToan.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(0, 0, 0));
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Cash-register-icon.png"))); // NOI18N
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnLuuVSIn.setBackground(new java.awt.Color(255, 204, 51));
        btnLuuVSIn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnLuuVSIn.setForeground(new java.awt.Color(0, 0, 0));
        btnLuuVSIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/luuhoadon.png"))); // NOI18N
        btnLuuVSIn.setText("Lưu và In HĐ");
        btnLuuVSIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuVSInActionPerformed(evt);
            }
        });

        btnTachHD.setBackground(new java.awt.Color(255, 102, 51));
        btnTachHD.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnTachHD.setForeground(new java.awt.Color(0, 0, 0));
        btnTachHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/tachHD.png"))); // NOI18N
        btnTachHD.setText("Tách HĐ");
        btnTachHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTachHDActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tiền khách trả");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tiền thừa");

        txtTienThua.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTienThua.setText("0.VNĐ");
        txtTienThua.setToolTipText("");

        btnhuy.setBackground(new java.awt.Color(204, 0, 0));
        btnhuy.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnhuy.setForeground(new java.awt.Color(0, 0, 0));
        btnhuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Button-Close-icon.png"))); // NOI18N
        btnhuy.setText("Hủy HĐ");
        btnhuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhuyActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Time Order");

        lblTime.setForeground(new java.awt.Color(51, 51, 255));
        lblTime.setText("12:12 PM");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Nhân viên Order :");

        btnguowi.setBackground(new java.awt.Color(255, 102, 51));
        btnguowi.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnguowi.setForeground(new java.awt.Color(0, 0, 0));
        btnguowi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/order-icon.png"))); // NOI18N
        btnguowi.setText("Gửi Order");
        btnguowi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnguowi.setDefaultCapable(false);
        btnguowi.setFocusable(false);
        btnguowi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguowiActionPerformed(evt);
            }
        });

        tblOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, "", null, null, null, null,  new Boolean(false)},
                {null, null, null, null, "", null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "IDOrder", "IDSP", "Tên SP", "Size", "Giá", "SL", "Ghi Chú", "Hủy", "Bỏ", "Khác"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOrder.setRowHeight(25);
        tblOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrderMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblOrderMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblOrder);

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Chiết Khấu");

        txtPay.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtPay.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPay.setText("0.VNĐ");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Thành tiền");

        txtTienTra.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTienTra.setText("0.VNĐ");
        txtTienTra.setToolTipText("");
        txtTienTra.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienTraCaretUpdate(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("%");

        txtDis2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDis2.setText("0.VNĐ");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Chương trình khuyến mãi");

        cbbkm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbkm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbkmActionPerformed(evt);
            }
        });

        lbNhap1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbNhap1.setForeground(new java.awt.Color(0, 0, 0));
        lbNhap1.setText("Chương trình");

        lbNgayKM.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbNgayKM.setForeground(new java.awt.Color(51, 51, 255));
        lbNgayKM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbIDError.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbIDError.setForeground(new java.awt.Color(0, 0, 0));
        lbIDError.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbIDError.setToolTipText("");

        jLabel32.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Thông tin khách hàng:");

        jLabel33.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Mã thẻ:");

        jLabel34.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Họ và tên:");

        jLabel36.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Được giảm:");

        lbIDCus.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbIDCus.setForeground(new java.awt.Color(255, 0, 51));
        lbIDCus.setText("...");

        lbNameCus.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbNameCus.setForeground(new java.awt.Color(255, 0, 51));
        lbNameCus.setText("...");

        lbDisCus.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbDisCus.setForeground(new java.awt.Color(255, 0, 51));
        lbDisCus.setText("...");

        lbDateaddCus.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbDateaddCus.setForeground(new java.awt.Color(255, 0, 51));
        lbDateaddCus.setText("...");

        jLabel37.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Ngày đăng ký:");

        txtMaKH.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMaKHCaretUpdate(evt);
            }
        });

        lbNhap.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbNhap.setForeground(new java.awt.Color(0, 0, 0));
        lbNhap.setText("Nhập mã thẻ:");

        jLabel38.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Ngày hết hạn :");

        lbDateEndCus.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbDateEndCus.setForeground(new java.awt.Color(255, 0, 51));
        lbDateEndCus.setText("...");

        javax.swing.GroupLayout pnInformation2Layout = new javax.swing.GroupLayout(pnInformation2);
        pnInformation2.setLayout(pnInformation2Layout);
        pnInformation2Layout.setHorizontalGroup(
            pnInformation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnInformation2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pnInformation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addGroup(pnInformation2Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbIDCus))
                    .addGroup(pnInformation2Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNameCus))
                    .addGroup(pnInformation2Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbDisCus))
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNhap)
                    .addGroup(pnInformation2Layout.createSequentialGroup()
                        .addGroup(pnInformation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnInformation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDateEndCus)
                            .addComponent(lbDateaddCus))))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        pnInformation2Layout.setVerticalGroup(
            pnInformation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnInformation2Layout.createSequentialGroup()
                .addComponent(lbNhap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnInformation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(lbIDCus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnInformation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(lbNameCus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnInformation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDateaddCus)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnInformation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDateEndCus)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnInformation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(lbDisCus))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnInformation2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnInformation2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbIDError, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbNhap1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbkm, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbNgayKM, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(132, 132, 132))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbkm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNhap1))
                .addGap(3, 3, 3)
                .addComponent(lbNgayKM, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbIDError, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        lbLoiGia.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        lbLoiGia.setForeground(new java.awt.Color(0, 0, 0));
        lbLoiGia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout PanOrderLayout = new javax.swing.GroupLayout(PanOrder);
        PanOrder.setLayout(PanOrderLayout);
        PanOrderLayout.setHorizontalGroup(
            PanOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanOrderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 882, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanOrderLayout.createSequentialGroup()
                        .addGroup(PanOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanOrderLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(lblBan)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtmaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanOrderLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(PanOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanOrderLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNameEMP, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(lblTime)
                                .addGap(53, 53, 53))
                            .addGroup(PanOrderLayout.createSequentialGroup()
                                .addGroup(PanOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanOrderLayout.createSequentialGroup()
                                        .addGap(89, 89, 89)
                                        .addGroup(PanOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel11)))
                                    .addGroup(PanOrderLayout.createSequentialGroup()
                                        .addGap(88, 88, 88)
                                        .addGroup(PanOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(PanOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(PanOrderLayout.createSequentialGroup()
                                                    .addGap(141, 141, 141)
                                                    .addComponent(lbLoiGia, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel4)
                                                    .addComponent(jLabel8)
                                                    .addGroup(PanOrderLayout.createSequentialGroup()
                                                        .addGap(141, 141, 141)
                                                        .addGroup(PanOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(txtTienTra)
                                                            .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                            .addGroup(PanOrderLayout.createSequentialGroup()
                                                .addComponent(jLabel14)
                                                .addGap(40, 40, 40)
                                                .addGroup(PanOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtPay)
                                                    .addGroup(PanOrderLayout.createSequentialGroup()
                                                        .addComponent(txtDis1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel17)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtDis2))
                                                    .addComponent(txtTong, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(PanOrderLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(PanOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnguowi, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(PanOrderLayout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addComponent(btnhuy, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(PanOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnLuuVSIn, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnTachHD, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        PanOrderLayout.setVerticalGroup(
            PanOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanOrderLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblBan)
                    .addComponent(jLabel5)
                    .addComponent(txtmaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(lblTime)
                    .addComponent(txtNameEMP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanOrderLayout.createSequentialGroup()
                        .addGroup(PanOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtTong, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(PanOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanOrderLayout.createSequentialGroup()
                                .addGroup(PanOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDis1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17)
                                    .addComponent(txtDis2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(txtPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbLoiGia, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(PanOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanOrderLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8))
                            .addGroup(PanOrderLayout.createSequentialGroup()
                                .addComponent(txtTienTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanOrderLayout.createSequentialGroup()
                                .addGroup(PanOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnhuy, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnLuuVSIn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnguowi, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnTachHD, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(406, Short.MAX_VALUE))
        );

        PanCac.add(PanOrder, "card3");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanCac, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanCac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cbbKhu.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cbbKhu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbKhu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbKhuActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Khu vực");

        jLabel1.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/chaomungfran.png"))); // NOI18N

        btnVaoBan.setBackground(new java.awt.Color(0, 255, 0));
        btnVaoBan.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnVaoBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/vaoban.png"))); // NOI18N
        btnVaoBan.setText("Vào bàn");
        btnVaoBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVaoBanActionPerformed(evt);
            }
        });

        btnxemlichsu.setBackground(new java.awt.Color(255, 102, 51));
        btnxemlichsu.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnxemlichsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/lichsu.png"))); // NOI18N
        btnxemlichsu.setText("lịch sử Order");
        btnxemlichsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxemlichsuActionPerformed(evt);
            }
        });

        PanSanPham.setBackground(new java.awt.Color(255, 153, 153));
        PanSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách sản phẩm"));

        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTimKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Zoom-icon.png"))); // NOI18N
        jLabel9.setText("Tìm Kiếm");

        tblSanPham.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblSanPham);

        javax.swing.GroupLayout PanSanPhamLayout = new javax.swing.GroupLayout(PanSanPham);
        PanSanPham.setLayout(PanSanPhamLayout);
        PanSanPhamLayout.setHorizontalGroup(
            PanSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanSanPhamLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(PanSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE)
                    .addGroup(PanSanPhamLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTim)))
                .addContainerGap())
        );
        PanSanPhamLayout.setVerticalGroup(
            PanSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanSanPhamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbbKhu, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnxemlichsu, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnVaoBan, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 236, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(PanSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbKhu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnxemlichsu, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVaoBan, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(PanSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyTyped
        // TODO add your handling code here:
        qlod.timSP(txtTim, tblSanPham);
    }//GEN-LAST:event_txtTimKeyTyped
    private void goiTaoBan(ENTITY_Area khu) {
        this.qlod.taoTable(this, khu.getIDArea(), this.btnVaoBan, lblBan, tblOrder, tblLichSu, PanLichSu, PanOrder, txtmaHD, txtMaKH, txtNameEMP, lblTime, txtTong, PanCac, txtPay, txtDis1, txtDis2);
    }
    private void tblLichSuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLichSuMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            txtmaHD.setText(tblLichSu.getValueAt(tblLichSu.getSelectedRow(), 0).toString());
            qlod.bill(txtmaHD, txtNameEMP, txtMaKH, lblTime, tblOrder);
            CardLayout card = (CardLayout) PanCac.getLayout();
            card.show(PanCac, "card3");
            Total();
            UpdatetxtDis1();            
            qlod.dongC(tblOrder.getRowCount());            
            if (tblLichSu.getValueAt(tblLichSu.getSelectedRow(), 4).toString().equals("Đã hủy")) {
                btnThanhToan.setEnabled(false);
                btnhuy.setEnabled(false);
                txtmaHD.setToolTipText("1");
                this.huy = 1;
            } else if (tblLichSu.getValueAt(tblLichSu.getSelectedRow(), 4).toString().equals("Đã thanh toán")) {
                btnThanhToan.setEnabled(false);
                btnhuy.setEnabled(false);
                txtmaHD.setToolTipText("2");
                this.huy = 2;
            } else {
                btnhuy.setEnabled(true);
                this.huy = 0;
                txtmaHD.setToolTipText("0");
            }
        }
    }//GEN-LAST:event_tblLichSuMouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:        
        if (dialogHelper.confirm(this, "Xác nhận thanh toán HĐ :" + txtmaHD.getText())) {
            btnLuuVSInActionPerformed(null);
            if (cbbkm.getSelectedItem().equals("Khách hàng VIP")) {
                while (true) {
                    if (txtMaKH.getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(this, "Mã thẻ VIP không được để trống!");
                        txtMaKH.grabFocus();
                        return;
                    } else if (!txtMaKH.getText().trim().equals("") && !lbIDError.getText().equals("Thành công.")) {
                        JOptionPane.showMessageDialog(this, "Mã thẻ VIP chưa đúng, vui lòng nhập lại!");
                        txtMaKH.grabFocus();
                        return;
                    } else {
                        qlod.UpdateKM(lbIDCus.getText(), null, txtmaHD.getText(), txtDis1.getText());
                        qlod.xoaNhom(lblBan.getToolTipText());
                        qlod.thanhToan(txtmaHD);
                        qlod.updatebnThanhToan(txtmaHD);
                        ENTITY_Area khu = (ENTITY_Area) cbbKhu.getSelectedItem();
                        goiTaoBan(khu);
                        card.show(PanCac, "card4");
                        break;
                    }
                }
            } else if (cbbkm.getSelectedItem().equals("Không có")) {
                qlod.xoaNhom(lblBan.getToolTipText());
                qlod.thanhToan(txtmaHD);
                qlod.updatebnThanhToan(txtmaHD);
                ENTITY_Area khu = (ENTITY_Area) cbbKhu.getSelectedItem();
                goiTaoBan(khu);
                card.show(PanCac, "card4");
            } else {
                qlod.UpdateKM(null, cbbkm.getSelectedItem().toString(), txtmaHD.getText(), txtDis1.getText());
                qlod.xoaNhom(lblBan.getToolTipText());
                qlod.thanhToan(txtmaHD);
                qlod.updatebnThanhToan(txtmaHD);
                ENTITY_Area khu = (ENTITY_Area) cbbKhu.getSelectedItem();
                goiTaoBan(khu);
                card.show(PanCac, "card4");
            }
            this.txtTienTra.setText("0.VNĐ");
            this.txtTienThua.setText("0.VNĐ");
        }

    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnLuuVSInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuVSInActionPerformed
        if (dialogHelper.confirm(this, "Bạn có muốn in hóa đơn :" + txtmaHD.getText())) {
            if (cbbkm.getSelectedItem().equals("Khách hàng VIP")) {
                while (true) {
                    if (txtMaKH.getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(null, "Mã thẻ VIP không được để trống!");
                        txtMaKH.grabFocus();
                        return;
                    } else if (!txtMaKH.getText().trim().equals("") && !lbIDError.getText().equals("Thành công.")) {
                        JOptionPane.showMessageDialog(this, "Mã thẻ VIP chưa đúng, vui lòng nhập lại!");
                        txtMaKH.grabFocus();
                        return;
                    } else {
                        XuatTxt();
                        break;
                    }
                }
            } else {
                XuatTxt();
            }
        }
    }//GEN-LAST:event_btnLuuVSInActionPerformed

    public void XuatTxt() throws NumberFormatException {
        // TODO add your handling code here:        
        try {
            try (Writer b = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("History//" + txtmaHD.getText().trim() + ".txt"), "UTF8"))) {
                b.write("\t\t\t\t\t\tSanfrancisco Coffee\r\n\r\n");
                b.write("\t\t\t\tĐịa chỉ: Tổ 4, P.Cự Khối, Q.Long Biên, Hà Nội\r\n");
                b.write("\t\t\t\tSĐT: 091 323 40 38\r\n");
                b.write("\t\t\t\tMã hóa đơn :" + txtmaHD.getText() + "\r\n");
                b.write("\t\t\t\tThời gian: " + lblTime.getText().trim() + " " + dateHelper.DATE_FORMATER.format(dateHelper.now()) + "\r\n");
                b.write("\t\t\t\tNhân viên :" + txtNameEMP.getText() + "\r\n");
                b.write("\t-------------------------------------------------------------------------------\r\n");
                b.write("\tMã SP\tTên sản phẩm\t\t\tSize\tGiá tiền (VNĐ)\t\t\t SL\t \r\n");
                b.write("\t-------------------------------------------------------------------------------\r\n");
                int line = tblOrder.getRowCount();
                int tongSl = 0;
                for (int i = 0; i < line; i++) {
                    if (tblOrder.getValueAt(i, 0).toString().equals(txtmaHD.getText())) {
                        if (tblOrder.getValueAt(i, 7).toString().equals("")) {
                            String n1 = tblOrder.getValueAt(i, 1).toString();//IDSP
                            String n2 = tblOrder.getValueAt(i, 2).toString();//Tên SP
                            String n3 = tblOrder.getValueAt(i, 3) == null ? "Ly" : tblOrder.getValueAt(i, 3).toString();//Size
                            double n4 = Double.parseDouble(tblOrder.getValueAt(i, 4).toString());//Giá
                            int n5 = Integer.valueOf(tblOrder.getValueAt(i, 5).toString());//SL
                            tongSl += n5;
                            b.write("\t" + n1 + "\t" + n2 + "\t\t\t\t" + n3 + "\t\t" + n.format(n4) + "\t\t\t\t\t" + n5 + "\t" + "\r\n");
                        }
                    }
                }
                b.write("\t-------------------------------------------------------------------------------\r\n");
                String tt = txtTong.getText().substring(0, txtTong.getText().lastIndexOf(".VNĐ")).replaceAll(",", "");
                double x = Double.parseDouble(tt);
                b.write("\t------------------------------------------------------------\r\n");
                b.write("\tTổng cộng:\t\t" + tongSl + "\t\t\t" + n.format(Total()) + ".VNĐ\r\n");
                b.write("\t\tChiết khấu:\t" + txtDis1.getText() + "%\t\t-" + txtDis2.getText() + "\r\n");
                b.write("\t\t--------------------------------------------\r\n");
                b.write("\t\tThành tiền:\t\t\t" + txtPay.getText() + "\r\n");
                b.write("\t\t--------------------------------------------\r\n");
                b.write("\t\tTiền khách đưa:\t\t\t" + txtTienTra.getText() + "\r\n");
                b.write("\t\tTiền trả lại:\t\t\t" + txtTienThua.getText() + "\r\n");
                b.write("\t------------------------------------------------------------\r\n");
                b.write("\tChương trình khuyến mãi: ");
                if (cbbkm.getSelectedItem().equals("Không có")) {
                    b.write("\tKhông có.\r\n");
                } else if (cbbkm.getSelectedItem().equals("Khách hàng VIP")) {
                    b.write("\tThành viên quán.\r\n");
                    b.write("\t-----Thông tin thành viên-----\r\n");
                    b.write("\tMã thẻ: " + lbIDCus.getText() + "\r\n");
                    b.write("\tTên thành viên: " + lbNameCus.getText() + "\r\n");
                    b.write("\tNgày đăng ký: " + lbDateaddCus.getText() + "\r\n");
                    b.write("\tNgày hết hạn: " + lbDateEndCus.getText() + "\r\n");
                    b.write("\tChiết khấu : " + lbDisCus.getText() + "\r\n");
                } else {
                    b.write(cbbkm.getSelectedItem().toString() + "\r\n");
                }
                b.write("\t-----------------------------------------------------------------\r\n");
                b.write("\tMật khẩu Wifi: Mua 2 ly tà tư rồi cho\r\n");
                b.write("\t-----------------------CẢM ƠN QUÝ KHÁCH--------------------------\r\n");
            }
        } catch (HeadlessException | IOException e) {
            e.printStackTrace();
        }
        //Mở file txt
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("notepad History//" + txtmaHD.getText().trim() + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void btnguowiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguowiActionPerformed
        // TODO add your handling code here:
        if (dialogHelper.confirm(this, "Xác nhận gưởi hóa đơn " + txtmaHD.getText())) {
            int e = 0;
            for (int i = 0; i < qlod.dongC(-1); i++) {
                ENTITY_BILL bill = new ENTITY_BILL();
                bill.setIDOrder(this.txtmaHD.getText());
                bill.setIDProduct(this.tblOrder.getValueAt(i, 1).toString());
                bill.setNote(this.tblOrder.getValueAt(i, 6).toString());
                bill.setQuantity(Integer.valueOf(this.tblOrder.getValueAt(i, 5).toString()));
                qlod.updateOderDe(bill);
            }
            for (int i = qlod.dongC(-1); i < this.modell.getRowCount(); i++) {
                ENTITY_BILL bill = new ENTITY_BILL();
                bill.setIDOrder(this.txtmaHD.getText());
                bill.setIDProduct(this.tblOrder.getValueAt(i, 1).toString());
                bill.setIDTable(this.lblBan.getText());
                bill.setNote(this.tblOrder.getValueAt(i, 6).toString());
                bill.setQuantity(Integer.valueOf(this.tblOrder.getValueAt(i, 5).toString()));
                bill.setReason("");
                bill.setStatus(false);
                qlod.insertOderDe(bill);
                e++;
            }
            if (e > 0) {
                qlod.updatebnGuoi(lblBan.getText());
                dialogHelper.alert(this, "Gửi thành công ");
                qlod.dongC(tblOrder.getRowCount());
            }
            card.show(PanCac, "card4");
            ENTITY_Area khu = (ENTITY_Area) cbbKhu.getSelectedItem();
            goiTaoBan(khu);
        }
    }//GEN-LAST:event_btnguowiActionPerformed

    private void cbbKhuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbKhuActionPerformed
        // TODO add your handling code here:
        this.btnVaoBan.setEnabled(false);
        ENTITY_Area ar = (ENTITY_Area) this.cbbKhu.getSelectedItem();
        if (ar != null) {
            goiTaoBan(ar);
        }
    }//GEN-LAST:event_cbbKhuActionPerformed

    private void btnVaoBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVaoBanActionPerformed
        // TODO add your handling code here:
        qlod.taoHD(txtmaHD);
        if (Auth.isAdmin()) {
            txtNameEMP.setText("Admin");
        } else {
            txtNameEMP.setText(Auth.user.getNameEMP());
        }
        this.btnVaoBan.setEnabled(false);
        this.PanOrder.setVisible(true);
        this.PanLichSu.setVisible(false);
        qlod.insertOr(txtmaHD);
        this.row = -1;
        this.qlod.dongC(tblOrder.getRowCount());
    }//GEN-LAST:event_btnVaoBanActionPerformed

    private void btnxemlichsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxemlichsuActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) PanCac.getLayout();
        card.show(PanCac, "card2");
        qlod.lichsuOrder(PanLichSu, tblLichSu);
    }//GEN-LAST:event_btnxemlichsuActionPerformed

    private void mnHuymonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnHuymonActionPerformed
        // TODO add your handling code here:
        String sl = dialogHelper.prompt(null, "Mày lại Order nhầm đúng không ?\nGhi lý do vào đây :)");
        qlod.huyMon(txtmaHD, sl, tblOrder.getValueAt(this.row, 1).toString(), tblOrder.getValueAt(this.row, 6).toString());
        qlod.billTable(txtmaHD, txtNameEMP, txtMaKH, lblTime, tblOrder, lblBan.getText());
    }//GEN-LAST:event_mnHuymonActionPerformed

    private void btnhuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhuyActionPerformed
        // TODO add your handling code here:
        ENTITY_Area khu = (ENTITY_Area) cbbKhu.getSelectedItem();
        JDialogHuyDon dl = new JDialogHuyDon(null, true, this, btnVaoBan, lblBan, tblOrder, tblLichSu, PanSanPham, PanOrder, txtmaHD, txtMaKH, txtNameEMP, lblTime, txtTong, PanCac, khu.getIDArea(), txtPay, txtDis1, txtDis2, tblOrder.getRowCount());
        dl.setVisible(true);
    }//GEN-LAST:event_btnhuyActionPerformed

    private void mnTachMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnTachMonActionPerformed
        // TODO add your handling code here:
        if (tblOrder.getValueAt(row, 7).toString().equals("Hủy")) {
            dialogHelper.alert(this, "Món này đã bị hủy không thể tách");
        } else {
            String slTach = dialogHelper.prompt(this, "Nhập số lượng muốn tách ra đi cu :)");
            int slTc = Integer.valueOf(slTach);
            int slcu = Integer.valueOf(this.tblOrder.getValueAt(row, 5).toString());
            if (slcu - slTc <= 0) {
                dialogHelper.alert(this, "Có mỗi " + slcu + " Mà đòi tách ra " + slTach + " à :)");
                return;
            }
            ENTITY_BILL bill = new ENTITY_BILL();//Cập nhập món cũ
            bill.setIDOrder(this.txtmaHD.getText());
            bill.setIDProduct(this.tblOrder.getValueAt(row, 1).toString());
            bill.setNote(this.tblOrder.getValueAt(row, 6).toString());
            bill.setQuantity(slTc);
            bill.setReason("");
            bill.setStatus(false);
            qlod.updateOderDe(bill);
            ENTITY_BILL bill1 = new ENTITY_BILL();
            bill1.setIDOrder(this.txtmaHD.getText());
            bill1.setIDProduct(this.tblOrder.getValueAt(row, 1).toString());
            bill1.setNote(this.tblOrder.getValueAt(row, 6).toString() + "Tách : " + (slcu - slTc));
            bill1.setQuantity(Integer.valueOf(slcu - slTc));
            bill1.setReason("");
            bill1.setIDTable(lblBan.getText());
            bill1.setStatus(false);
            bill.setIDTable(lblBan.getText());
            qlod.insertOderDe(bill1);
            qlod.billTable(txtmaHD, txtNameEMP, txtMaKH, lblTime, tblOrder, lblBan.getText());
        }
    }//GEN-LAST:event_mnTachMonActionPerformed

    private void btnTachHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTachHDActionPerformed
        // TODO add your handling code here:
        if (dialogHelper.confirm(this, "Xác nhận tách hóa đơn ")) {
            int a = 0;
            JTextField txt = new JTextField();
            this.qlod.taoHD(txt);
            this.qlod.insertOr(txt);
            for (int i = 0; i < this.tblOrder.getRowCount(); i++) {
                Boolean isDelete = (Boolean) tblOrder.getValueAt(i, 9);
                if (isDelete) {
                    a++;
                    ENTITY_BILL bill1 = new ENTITY_BILL();
                    bill1.setIDOrder(txt.getText());
                    bill1.setIDProduct(this.tblOrder.getValueAt(i, 1).toString());
                    bill1.setNote(this.tblOrder.getValueAt(i, 6).toString());
                    bill1.setQuantity(Integer.valueOf(this.tblOrder.getValueAt(i, 5).toString()));
                    bill1.setReason("");
                    this.qlod.tachHDon(this.txtmaHD.getText(), txt.getText(), bill1.getIDProduct(), bill1.getNote(), lblBan.getText());
                }
            }
            this.qlod.billTable(txtmaHD, txtNameEMP, txtMaKH, lblTime, tblOrder, lblBan.getText());
            dialogHelper.alert(this, "Tách hóa đơn thành công ");
            qlod.dongC(-1);
        }
    }//GEN-LAST:event_btnTachHDActionPerformed

    private void tblOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrderMouseClicked
        // TODO add your handling code here:
        int vitri = tblOrder.getSelectedRow();
        this.txtmaHD.setText(tblOrder.getValueAt(vitri, 0).toString());
        this.Total();
        UpdatetxtDis1();
    }//GEN-LAST:event_tblOrderMouseClicked

    private void tblOrderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrderMouseReleased
        // TODO add your handling code here:
        int r = tblOrder.rowAtPoint(evt.getPoint());
        this.row = r;
        if (r >= 0 && r < tblOrder.getRowCount()) {
            tblOrder.setRowSelectionInterval(r, r);
        } else {
            tblOrder.clearSelection();
        }
        int rowindex = tblOrder.getSelectedRow();
        if (rowindex < 0 || rowindex >= qlod.dongC(-1)) {
            return;
        }
        if (evt.isPopupTrigger() && evt.getComponent() instanceof JTable) {
            pmnOder.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblOrderMouseReleased
    public void PanelOnOff(boolean b) {
        lbNhap.setVisible(b);
        txtMaKH.setVisible(b);
        lbIDError.setVisible(b);
        pnInformation2.setVisible(b);
    }

    public void UpdatetxtDis1() {
        double Dis;
        NumberFormat formatter = new DecimalFormat("#,###");
        //tính Discount
        String Order = txtTong.getText().substring(0, txtTong.getText().lastIndexOf(".VNĐ")).replaceAll(",", "");
        Dis = (Double.valueOf(txtDis1.getText()) * Double.valueOf(Order)) / 100;
        txtDis2.setText(formatter.format(Dis) + ".VNĐ");
        //tính total
        double total = Double.parseDouble(Order) - Dis;
        txtPay.setText(formatter.format(total) + ".VNĐ");
        txtTienTraCaretUpdate(null);
    }

    private void ResetPnInfor() {
        lbIDCus.setText(".......");
        lbNameCus.setText(".......");
        lbDateaddCus.setText(".......");
        lbDateEndCus.setText(".......");
        lbDisCus.setText("........");
    }
    private void cbbkmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbkmActionPerformed
        // TODO add your handling code here:
        if (cbbkm.getSelectedItem() == null) {
            return;
        }
        if (cbbkm.getSelectedItem().equals("Không có")) {
            txtDis1.setText("0");
            lbNgayKM.setText("");
            PanelOnOff(false);
            UpdatetxtDis1();
            ResetPnInfor();
            txtMaKH.setText("");
            lbIDError.setText("");
        } else if (cbbkm.getSelectedItem().equals("Khách hàng VIP")) {
            txtDis1.setText("0");
            lbNgayKM.setText("");
            UpdatetxtDis1();
            PanelOnOff(true);
        } else {
            PanelOnOff(false);
            ResetPnInfor();
            txtMaKH.setText("");
            lbIDError.setText("");
            try {
                String sql = "Select * from Promotions where NamePromo=?";
                ResultSet rs = JDBC.query(sql, cbbkm.getSelectedItem().toString());
                if (rs.next()) {
                    txtDis1.setText(rs.getString(3));
                    lbNgayKM.setText(dateHelper.DATE_FORMATER.format(rs.getDate(4)) + " - " + dateHelper.DATE_FORMATER.format(rs.getDate(5)));
                    UpdatetxtDis1();
                }
            } catch (SQLException ex) {
                dialogHelper.alert(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
            }
        }
    }//GEN-LAST:event_cbbkmActionPerformed

    private void txtMaKHCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMaKHCaretUpdate
        // TODO add your handling code here:                
        if (txtMaKH.getText().trim().equals("")) {
            txtDis1.setText("0");
            lbIDError.setText("Vui lòng nhập mã thẻ.");
            lbIDError.setForeground(Color.red);
            ResetPnInfor();
        } else {
            qlod.txtMaKHCaretUpdate(txtMaKH, txtDis1, lbIDError, lbIDCus, lbNameCus, lbDateaddCus, lbDateEndCus, lbDisCus);
        }
        UpdatetxtDis1();
    }//GEN-LAST:event_txtMaKHCaretUpdate

    private void txtTienTraCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienTraCaretUpdate
        // TODO add your handling code here:
        double Repay;
        //tính Discount
        if (huy == 1) {
            lbLoiGia.setText("Đơn này đã hủy.");
        } else if (huy == 2) {
            lbLoiGia.setText("Khách hàng đã thanh toán.");
        } else {
            try {
                String tientra = "";
                if (!txtTienTra.getText().matches("\\d+.VNĐ")) {
                    txtTienTra.setText("0.VNĐ");
                    dialogHelper.alert(this, "Sai định dạng tiền Việt.");
                } else {
                    tientra = txtTienTra.getText().substring(0, txtTienTra.getText().lastIndexOf(".VNĐ")).replaceAll(",", "").trim();
                }
                if (txtTienTra.getText().trim().equals("0.VNĐ")) {
                    lbLoiGia.setText("Khách hàng chưa đưa tiền.");
                    txtTienThua.setText("0.VNĐ");
                    btnThanhToan.setEnabled(false);
                    return;
                } else if (!tientra.matches("\\d+")) {
                    lbLoiGia.setText("Tiền có dạng số.");
                    txtTienThua.setText("0.VNĐ");
                    btnThanhToan.setEnabled(false);
                    return;
                } else {
                    lbLoiGia.setText("");
                    btnThanhToan.setEnabled(false);
                }
                String total = txtPay.getText().substring(0, txtPay.getText().lastIndexOf(".VNĐ")).replaceAll(",", "");
                Repay = Double.parseDouble(tientra) - Double.parseDouble(total);
                txtTienThua.setText(n.format(Repay) + ".VNĐ");
                if (Repay < 0) {
                    lbLoiGia.setText("Khách hàng chưa đưa đủ tiền.");
                    btnThanhToan.setEnabled(false);
                    txtTienThua.setText("0.VNĐ");
                } else if (Double.parseDouble(tientra) == 0) {
                    btnThanhToan.setEnabled(false);
                    txtTienThua.setText("0.VNĐ");
                } else {
                    lbLoiGia.setText("");
                    btnThanhToan.setEnabled(true);
                }
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtTienTraCaretUpdate

    private void txtmaHDCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtmaHDCaretUpdate
        // TODO add your handling code here:     
        if (qlod.ChuongTrinhKM(txtmaHD.getText(), txtMaKH).equals("VIP")) {
            this.cbbkm.setSelectedIndex(1);
        } else if (qlod.ChuongTrinhKM(txtmaHD.getText(), txtMaKH).equals("")) {
            this.cbbkm.setSelectedIndex(0);
        } else {
            this.cbbkm.setSelectedItem(qlod.ChuongTrinhKM(txtmaHD.getText(), txtMaKH));
        }
    }//GEN-LAST:event_txtmaHDCaretUpdate
    public double Total() {
        double total = 0;
        NumberFormat formatter = new DecimalFormat("#,###");
        int line = this.tblOrder.getRowCount();
        for (int i = 0; i < line; i++) {
            if (this.tblOrder.getValueAt(i, 0).toString().equals(txtmaHD.getText())) {//Kiểm tra mã HĐ cùng với mã hóa đơn                 
                if (this.tblOrder.getValueAt(i, 7).toString().equals("")) {//Kiểm tra có hủy món ko
                    double gia = Double.valueOf(this.tblOrder.getValueAt(i, 4).toString());
                    int SL = Integer.valueOf(this.tblOrder.getValueAt(i, 5).toString());
                    double thanhtien = gia * SL;
                    total += thanhtien;
                }
            }
        }
        this.txtTong.setText(formatter.format(total) + ".VNĐ");
        return total;
    }

    class ClientsTableButtonRenderer extends JButton implements TableCellRenderer {

        public ClientsTableButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setForeground(Color.black);
            setBackground(UIManager.getColor("Button.background"));
//            setText((value == null) ? "" : value.toString());           
            setPreferredSize(new Dimension(40, 40));
            String s = value.toString();
            ClassLoader classLoader = this.getClass().getClassLoader();
            if (s.equals("Thêm")) {
                URL imagePath = classLoader.getResource("ICON/" + "add-cart" + ".png");
                Image imgBan = new ImageIcon(imagePath).getImage();
                Icon iconBan = new ImageIcon(imgBan.getScaledInstance(20, 20, imgBan.SCALE_SMOOTH));
                setIcon(iconBan);
            }
            if (s.equals("Xóa")) {
                URL imagePath = classLoader.getResource("ICON/" + "Button-Close-icon" + ".png");
                Image imgBan = new ImageIcon(imagePath).getImage();
                Icon iconBan = new ImageIcon(imgBan.getScaledInstance(20, 20, imgBan.SCALE_SMOOTH));
                setIcon(iconBan);
            }
            return this;
        }
    }

    public class ClientsTableRenderer extends DefaultCellEditor {

        private JButton button;
        private String label;
        private boolean clicked;
        private int row, col;
        private JTable table;

        public ClientsTableRenderer(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.table = table;
            this.row = row;
            this.col = column;
            button.setForeground(Color.black);
            button.setBackground(UIManager.getColor("Button.background"));
            label = (value == null) ? "" : value.toString();
//            button.setText(label);
            clicked = true;
            String s = value.toString();
            ClassLoader classLoader = this.getClass().getClassLoader();
            if (s.equals("Thêm")) {
                URL imagePath = classLoader.getResource("ICON/" + "add-cart" + ".png");
                Image imgBan = new ImageIcon(imagePath).getImage();
                Icon iconBan = new ImageIcon(imgBan.getScaledInstance(20, 20, imgBan.SCALE_SMOOTH));
                button.setIcon(iconBan);
            }
            if (s.equals("Xóa")) {
                URL imagePath = classLoader.getResource("ICON/" + "Button-Close-icon" + ".png");
                Image imgBan = new ImageIcon(imagePath).getImage();
                Icon iconBan = new ImageIcon(imgBan.getScaledInstance(20, 20, imgBan.SCALE_SMOOTH));
                button.setIcon(iconBan);
            }
            return button;
        }

        public Object getCellEditorValue() {
            if (clicked) {
                if (table.getName().equals("SP")) {
                    if (txtmaHD.getText().equals("")) {
                        dialogHelper.alert(PanSanPham, "Bố chưa vào bàn mà đòi thêm cái gì trời ?");
                    } else {
                        if (dialogHelper.confirm(null, "Bạn có chắc muốn thêm " + tblSanPham.getValueAt(this.row, 3) + " vào hóa đơn " + txtmaHD.getText() + " không ?")) {
                            Object[] row = {
                                txtmaHD.getText(),
                                tblSanPham.getValueAt(this.row, 0),
                                tblSanPham.getValueAt(this.row, 3),
                                tblSanPham.getValueAt(this.row, 4),
                                tblSanPham.getValueAt(this.row, 5),
                                1, "", "", "Xóa", false
                            };
                            modell.addRow(row);
                            Total();
                            UpdatetxtDis1();
                        }
                    }
                } else {
                    if (this.row >= qlod.dongC(-1)) {
                        modell.removeRow(this.row);
                        Total();
                        UpdatetxtDis1();
                    }
                }
            }
            clicked = false;
            return new String(label);
        }

        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            try {
                super.fireEditingStopped();
            } catch (ArrayIndexOutOfBoundsException e) {

            }
        }
    }

    public static class SpinnerEditor extends DefaultCellEditor {

        JSpinner spinner;
        JSpinner.DefaultEditor editor;
        JTextField textField;
        boolean valueSet;

        // Initializes the spinner.
        public SpinnerEditor() {
            super(new JTextField());
            spinner = new javax.swing.JSpinner();
            spinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 200, 1));
            editor = ((JSpinner.DefaultEditor) spinner.getEditor());
            textField = editor.getTextField();
            textField.addFocusListener(new FocusListener() {
                public void focusGained(FocusEvent fe) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            if (valueSet) {
                                textField.setCaretPosition(1);
                            }
                        }
                    });
                }

                public void focusLost(FocusEvent fe) {
                }
            });
            textField.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    stopCellEditing();
                }
            });
        }

        // Prepares the spinner component and returns it.
        public Component getTableCellEditorComponent(
                JTable table, Object value, boolean isSelected, int row, int column
        ) {
            if (!valueSet) {
                spinner.setValue(value);

            }
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    textField.requestFocus();
                }
            });
            return spinner;
        }

        public boolean isCellEditable(EventObject eo) {
            if (eo instanceof KeyEvent) {
                KeyEvent ke = (KeyEvent) eo;
                textField.setText(String.valueOf(ke.getKeyChar()));
                valueSet = true;
            } else {
                valueSet = false;
            }
            return true;
        }

        // Returns the spinners current value.
        public Object getCellEditorValue() {
            return spinner.getValue();
        }

        public boolean stopCellEditing() {
//            System.err.println("Stopping edit");
            try {
                editor.commitEdit();
                spinner.commitEdit();
            } catch (java.text.ParseException e) {
                JOptionPane.showMessageDialog(null,
                        "Invalid value, discarding.");
            }
            return super.stopCellEditing();
        }
    }

    void start() {
        JButton p = this.btnhuy;
        JTextField t = this.txtmaHD;
        new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtmaHD.getToolTipText() != null) {
                    if (Integer.parseInt(txtmaHD.getToolTipText()) == 0) {
                        huy = 0;
                        btnhuy.setEnabled(true);
                    }
                }
            }
        }).start();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanCac;
    private javax.swing.JPanel PanLichSu;
    private javax.swing.JPanel PanOrder;
    private javax.swing.JPanel PanSanPham;
    private javax.swing.JButton btnLuuVSIn;
    private javax.swing.JButton btnTachHD;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnVaoBan;
    private javax.swing.JButton btnguowi;
    private javax.swing.JButton btnhuy;
    private javax.swing.JButton btnxemlichsu;
    private javax.swing.JComboBox<String> cbbKhu;
    private javax.swing.JComboBox<String> cbbkm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbDateEndCus;
    private javax.swing.JLabel lbDateaddCus;
    private javax.swing.JLabel lbDisCus;
    private javax.swing.JLabel lbIDCus;
    private javax.swing.JLabel lbIDError;
    private javax.swing.JLabel lbLoiGia;
    private javax.swing.JLabel lbNameCus;
    private javax.swing.JLabel lbNgayKM;
    private javax.swing.JLabel lbNhap;
    private javax.swing.JLabel lbNhap1;
    private javax.swing.JLabel lblBan;
    private javax.swing.JLabel lblNgay;
    private javax.swing.JLabel lblTime;
    private javax.swing.JMenuItem mnHuymon;
    private javax.swing.JMenuItem mnTachMon;
    private javax.swing.JPanel panChao;
    private javax.swing.JPopupMenu pmnOder;
    private javax.swing.JPanel pnInformation2;
    private javax.swing.JTable tblLichSu;
    private javax.swing.JTable tblOrder;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtDis1;
    private javax.swing.JTextField txtDis2;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtNameEMP;
    private javax.swing.JTextField txtPay;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTienTra;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtTong;
    private javax.swing.JTextField txtmaHD;
    // End of variables declaration//GEN-END:variables
}

class SpinnerEditor extends DefaultCellEditor {

    private JSpinner spinner;

    public SpinnerEditor() {
        super(new JTextField());
        spinner = new javax.swing.JSpinner();
        spinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 200, 1));
//        spinner.setBorder( null );
    }

    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column) {

        if (Integer.valueOf(value.toString()) < 0) {
            value = 0;
        }
        spinner.setValue(value);
        return spinner;
    }

    public Object getCellEditorValue() {
        return spinner.getValue();
    }
}

class ImageRenderer extends DefaultTableCellRenderer {

    JLabel lbl = new JLabel();
    ClassLoader classLoader = this.getClass().getClassLoader();

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        if (value != null) {
            lbl.setIcon(XImage.read1(String.valueOf(value)));
        } else {
            lbl.setIcon(XImage.read1("no_image.jpg"));
        }
        return lbl;
    }
}
