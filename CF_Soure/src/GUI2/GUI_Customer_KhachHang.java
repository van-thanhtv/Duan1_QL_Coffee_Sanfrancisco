/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI2;

import DAL_Models.ENTITY_Customer;
import DAL_Services.Customer_Service;
import Utils.Check;
import Utils.JDBC;
import Utils.dateHelper;
import Utils.dialogHelper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author notak
 */
public class GUI_Customer_KhachHang extends javax.swing.JPanel {

    SimpleDateFormat fomat = new SimpleDateFormat("dd/MM/yyyy");
    JPopupMenu menu = new JPopupMenu("Popup");
    Customer_Service dao = new Customer_Service();
    int row = -1;

    /**
     * Creates new form GUI_Customer_KhachHang
     */
    public GUI_Customer_KhachHang() {
        initComponents();
//        tblKhachHang.getColumnModel().getColumn(0).setMinWidth(0);
//        tblKhachHang.getColumnModel().getColumn(0).setMaxWidth(0);
        init();
    }

    void init() {
        date();
        filltoTable();
        clearForm();
        this.clearForm();
        taoID();
    }

    void date() {
        try {
            SimpleDateFormat fo = new SimpleDateFormat("yyyy-MM-dd");
            List<ENTITY_Customer> list = dao.select();
            for (ENTITY_Customer kh : list) {
                String date1 = fo.format(dateHelper.now());
                String date2 = fo.format(kh.getDateEnd());
//                System.out.println(date1);
//                System.out.println(date2 + "\n");
                if (date1.compareTo(date2) > 0) { //neu DateEnd < Today
                    dao.up(String.valueOf(kh.getDateEnd()));
                } else {
                    dao.up1(String.valueOf(kh.getDateEnd()));
                }
            }
        } catch (Exception e) {
            dialogHelper.alert(this, " Lỗi Date");
            e.printStackTrace();
        }
    }

    void filltoTable() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        try {
            String t = "";
            List<ENTITY_Customer> list = dao.select();
            for (ENTITY_Customer kh : list) {
                if (kh.getStatus() == 1) {
                    t = "Hoạt Động";
                } else if (kh.getStatus() == 0) {
                    t = "Hết Hạn";
                } else if (kh.getStatus() == 2) {
                    t = "";
                }
                Object[] row = {
                    kh.getIDCust(),
                    kh.getCCCD(),
                    kh.getCusName(),
                    fomat.format(kh.getDateAdd()),
                    fomat.format(kh.getDateEnd()),
                    kh.getPhone(),
                    kh.getEmail(),
                    kh.getDiscount() + "%",
                    t
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            dialogHelper.alert(this, "Loi Truy Van Du Lieu");
        }
    }

    ENTITY_Customer getForm() { //lấy ra nv từ thông tin nhập trên Form
        ENTITY_Customer kh = new ENTITY_Customer();
        kh.setIDCust(lblID.getText());
        kh.setCCCD(txtCCCD.getText());
        kh.setCusName(txtTenKH.getText());
        kh.setDateAdd(dateHelper.toDate(txtNgayMo.getText(), "dd/MM/yyyy"));
        kh.setDateEnd(txtNgayHetHan.getDate());
        kh.setPhone(txtSDT.getText());
        kh.setEmail(txtEmail.getText());
        kh.setDiscount(Integer.valueOf(txtGiamGia.getText()));
        return kh;
    }

    void setForm(ENTITY_Customer nv) { //lấy thông tin 1 nv có sẵn hiện thị len Forrm
        lblID.setText(nv.getIDCust());
        txtCCCD.setText(nv.getCCCD());
        txtTenKH.setText(nv.getCusName());
        txtNgayMo.setText(fomat.format(nv.getDateAdd()));
        txtNgayHetHan.setDate(nv.getDateEnd());
        txtSDT.setText(nv.getPhone());
        txtEmail.setText(nv.getEmail());
        txtGiamGia.setText(String.valueOf(nv.getDiscount()));
    }

    void clearForm() {
        ENTITY_Customer nv = new ENTITY_Customer();
        nv.setDateAdd(dateHelper.now());
        nv.setDateEnd(dateHelper.add(30));
        this.setForm(nv);
        this.row = -1;
        taoID();
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
    }

    public void taoID() {
//        String character="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//        String randomString="";
//        int length=5;
//        Random random=new Random();
//        char[] text =new char[length];
//        for (int i = 0; i < length; i++) {
//            text[i]=character.charAt(random.nextInt(character.length()));
//        }
//        for (int i = 0; i < text.length; i++) {
//            randomString+=text[i];   
//        }
//        System.out.println(randomString);
        try {
            ResultSet rs = JDBC.query("Select MAX(IDCust) From Customer");
            rs.next();
            rs.getString(1);
            if (rs.getString(1) == null) {
                lblID.setText("KH01");
            } else {
                long id = Long.parseLong(rs.getString(1).substring(2, rs.getString(1).length()));
                id++;
                lblID.setText("KH" + String.format("%3d", id).trim());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void insert() {
        ENTITY_Customer kh = this.getForm();
        try {
            int p = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn Thêm Khách Hàng này?", "Hệ thống quản lý Ƹ̵̡Ӝ̵̨̄Ʒ☆", JOptionPane.YES_NO_OPTION);
            if (p == JOptionPane.YES_OPTION) {
                dao.insert(kh);
                date();
                filltoTable();
                dialogHelper.alert(this, "Thêm Khách Hàng Thành Công");
                clearForm();
                taoID();
            }
        } catch (Exception e) {
            dialogHelper.alert(this, "Lỗi Khi Thêm");
            e.printStackTrace();
        }
    }

    private void update() {
        ENTITY_Customer kh = this.getForm();
        try {
            int p = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn Sửa khách hàng này?", "Hệ thống quản lý Ƹ̵̡Ӝ̵̨̄Ʒ☆", JOptionPane.YES_NO_OPTION);
            if (p == JOptionPane.YES_OPTION) {
                dao.update(kh);
                date();
                filltoTable();
                dialogHelper.alert(this, "Sửa Thành Công");
                clearForm();
                taoID();
            }
        } catch (Exception e) {
            dialogHelper.alert(this, "Sửa Thất Bại");
            e.printStackTrace();
        }
    }

    private void delete() {
        String IDCust = lblID.getText();
        int p = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn Xóa khách hàng này?", "Hệ thống quản lý Ƹ̵̡Ӝ̵̨̄Ʒ☆", JOptionPane.YES_NO_OPTION);
        if (p == JOptionPane.YES_OPTION) {
            try {
                dao.delete(IDCust);
                filltoTable();
                dialogHelper.alert(this, "Xoá Thành Công");
                clearForm();
                taoID();
            } catch (Exception e) {
                dialogHelper.alert(this, "Xóa Thất Bại");
                e.printStackTrace();
            }
        }
    }

    void edit() {
        try {
            String ID = (String) tblKhachHang.getValueAt(this.row, 0); //lay giá trị hàng hiện tại & cột 0
            ENTITY_Customer cus = dao.findById(ID); //lay thong tin sp tuong ung trong csdl
            if (cus != null) {
                this.setForm(cus);
            }
        } catch (Exception e) {
            dialogHelper.alert(this, "Lỗi");
        }

    }
    DefaultTableModel model;

    public void timSP() {
        this.model = (DefaultTableModel) tblKhachHang.getModel();
        this.model.fireTableDataChanged();
        TableRowSorter Sorter = new TableRowSorter(this.model);
        tblKhachHang.setRowSorter(Sorter);
        Sorter.setRowFilter(RowFilter.regexFilter(txtTimKiem.getText()));
    }

    boolean checktrungSua() {
        row = tblKhachHang.getSelectedRow();
        String id = (String) tblKhachHang.getValueAt(row, 0);
        List<ENTITY_Customer> customer = dao.select();
        ENTITY_Customer cus = dao.findById(id);
        System.out.println("-----------------------------------" + cus.getCCCD());
        for (ENTITY_Customer ok : customer) {
            if (txtCCCD.getText().trim().equals(ok.getCCCD())) {
                dialogHelper.alert(this, "Chứng Minh Nhân Dân này đã tồn tại");
                return false;
            } else if (txtCCCD.getText().trim().equals(cus.getCCCD())) {
                return true;
            } else {
                return true;
            }
        }
        return false;
    }

    boolean checktrungThem() {
        List<ENTITY_Customer> customer = dao.select();
        for (ENTITY_Customer ok : customer) {
            if (txtCCCD.getText().trim().equals(ok.getCCCD())) {
                dialogHelper.alert(this, "Chứng Minh Nhân Dân này đã tồn tại");
                return false;
            } else {
                insert();
                return true;
            }
        }
        return false;
    }

    public void Giahanthe() {
        menu.removeAll();
        JMenuItem item = new JMenuItem("Gia Hạn Thẻ");
        JMenuItem item1 = new JMenuItem("Gửi Thông Báo");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat fo = new SimpleDateFormat("dd-MM-yyyy");
                row = tblKhachHang.getSelectedRow();
                if (row >= 0) {
                    String ID = (String) tblKhachHang.getValueAt(row, 0);
                    ENTITY_Customer pro = dao.findById(ID);
                    Date date1 = dateHelper.now();
                    Date date2 = pro.getDateEnd();
//                    System.out.println(pro.getDateEnd());
                    String songay = dialogHelper.prompt(null, "Nhập số ngày muốn gia hạn thẻ");
                    if (songay == null) {
                    } else {
                        if (Check.checkso(songay)) {
                            if (date1.after(date2)) { //neu dateEnd<today
                                pro.setDateEnd(dateHelper.add(Integer.valueOf(songay)));
                            } else if (date1.before(date2)) {
                                pro.setDateEnd(dateHelper.addDays(pro.getDateEnd(), Integer.valueOf(songay)));
                            } else {
                                pro.setDateEnd(dateHelper.addDays(pro.getDateEnd(), Integer.valueOf(songay)));
                            }
                            try {
                                dao.giahan(pro);
                                date();
                                filltoTable();
                                dialogHelper.alert(null, "Gia Hạn Thành Công");
                                dialogHelper.alert(null,
                                        "Mã Thẻ : " + pro.getIDCust()
                                        + "\n" + "Tên Khách Hàng : " + pro.getCusName()
                                        + "\n" + "Ngày Mở Thẻ  : " + fo.format(pro.getDateAdd())
                                        + "\n" + "Ngày Hết Hạn : " + fo.format(pro.getDateEnd()));
                            } catch (SQLException ex) {
                                dialogHelper.alert(null, "Gia Hạn Thất Bại");
                                Logger.getLogger(GUI_Customer_KhachHang.class.getName()).log(Level.SEVERE, null, ex);
                            }
//                            System.out.println(fo.format(pro.getDateEnd()));
//                            System.out.println(songay);
                        }
                    }
                }
            }
        });
        menu.add(item);
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat fo = new SimpleDateFormat("dd-MM-yyyy");
                row = tblKhachHang.getSelectedRow();
                if (row >= 0) {
                    String ID = (String) tblKhachHang.getValueAt(row, 0);
                    ENTITY_Customer pro = dao.findById(ID);
                    Date date1 = dateHelper.now();
                    Date date2 = pro.getDateEnd();
                    int p = JOptionPane.showConfirmDialog(null, "Bạn Muốn Gửi Thông Báo Cho Khách Hàng Này?", "Hệ thống quản lý Ƹ̵̡Ӝ̵̨̄Ʒ☆", JOptionPane.YES_NO_OPTION);
                    if (p == JOptionPane.YES_OPTION) {
                        String message = "Thẻ Khách Hàng Của Bạn Đã Hết Hạn Vào Ngày " + fo.format(pro.getDateEnd()) + "\n"
                                + "Vui Lòng Đến Cửa Hàng Để Gia Hạn Thẻ";
                        String message1 = "Thẻ Khách Hàng Của Bạn Sẽ Hết Hạn Vào Ngày Hôm Nay";
                        String message2 = "Thẻ Khách Hàng Của Bạn Sẽ Hết hạn Vào Ngày " + fo.format(pro.getDateEnd());
                        if (date1.after(date2)) { //neu DateEnd < Today
                            dao.sendmail(message, tblKhachHang, row);
                        } else if (date1.equals(date2)) {
                            dao.sendmail(message1, tblKhachHang, row);
                        } else {
                            dao.sendmail(message2, tblKhachHang, row);
                        }
                    } else {
                        return;
                    }
                }
            }
        });
        menu.add(item1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtNgayMo = new javax.swing.JTextField();
        txtGiamGia = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNgayHetHan = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhachHang = new rojeru_san.complementos.RSTableMetro();

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Ngày hết hạn");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Điện Thoại");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("Ngày mở");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("Chiết Khấu");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setText("Email");

        jButton1.setBackground(new java.awt.Color(0, 102, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/add_KH.png"))); // NOI18N
        jButton1.setText("Thêm KH");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 102, 51));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/edit_item.png"))); // NOI18N
        jButton2.setText("Sửa KH");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 51));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/remove-icon.png"))); // NOI18N
        jButton3.setText("Xóa ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 102, 51));
        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/clear.png"))); // NOI18N
        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setText("Tên Khách Hàng");

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        txtNgayMo.setEditable(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Mã Thẻ");

        lblID.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblID.setForeground(new java.awt.Color(255, 0, 0));
        lblID.setText("00");

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Tìm Kiếm");

        txtNgayHetHan.setDateFormatString("dd/MM/yyyy");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/VIP.gif"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/KHVIP.png"))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setText("CMND");

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã thẻ", "Số CMND", "Tên khách hàng", "Ngày đăng ký", "Ngày hết hạn", "Số điện thoại", "Email", "Chiết khấu", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tblKhachHang.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblKhachHang.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblKhachHang.setFillsViewportHeight(true);
        tblKhachHang.setFuenteFilas(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblKhachHang.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblKhachHang.setFuenteHead(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tblKhachHang.setRowHeight(30);
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblKhachHangMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhachHang);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4))
                                .addGap(57, 57, 57))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCCCD, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                            .addComponent(txtTenKH)
                            .addComponent(txtSDT)
                            .addComponent(lblID))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(64, 64, 64)
                                    .addComponent(jLabel5)
                                    .addGap(35, 35, 35))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel2))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNgayMo)
                            .addComponent(txtEmail)
                            .addComponent(txtGiamGia)
                            .addComponent(txtNgayHetHan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(240, 240, 240)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(73, 73, 73)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(385, 385, 385))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1650, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(214, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel1)
                                    .addComponent(lblID)
                                    .addComponent(txtNgayMo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(314, 314, 314)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(27, 27, 27)
                                        .addComponent(jLabel8))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtNgayHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                                .addGap(89, 89, 89)))))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (Check.checkNullText(txtSDT)
                && Check.checkNullText(txtEmail)
                && Check.checkNullText(txtGiamGia)
                && Check.checkNullText(txtNgayMo)
                && Check.checkNullText(txtTenKH)) {
            if (Check.checkEmail(txtEmail)
                    && Check.checkSDT(txtSDT)
                    && Check.checkso2(txtGiamGia)
                    && Check.checkName(txtTenKH)
                    && Check.Checkso4(txtGiamGia)) {
                checktrungThem();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (Check.checkNullText(txtSDT)
                && Check.checkNullText(txtEmail)
                && Check.checkNullText(txtGiamGia)
                && Check.checkNullText(txtNgayMo)
                && Check.checkNullText(txtTenKH)) {
            if (Check.checkEmail(txtEmail)
                    && Check.checkSDT(txtSDT)
                    && Check.checkso2(txtGiamGia)
                    && Check.checkName(txtTenKH)
                    && Check.Checkso4(txtGiamGia)) {
                row = tblKhachHang.getSelectedRow();
                String id = (String) tblKhachHang.getValueAt(row, 0);
                List<ENTITY_Customer> customer = dao.select();
                ENTITY_Customer cus = dao.findById(id);
                int n = 0;                
                if (txtCCCD.getText().trim().equals(cus.getCCCD())) {                    
                    update();
                    return;
                }
                for (ENTITY_Customer ok : customer) {                    
                    if (txtCCCD.getText().trim().equals(ok.getCCCD())) {                        
                        n=0;
                        break;
                    }else{                        
                        n++;
                    }
                }
                if (n > 0) {
                    update();
                } else {
                    dialogHelper.alert(this, "Chứng Minh Nhân Dân này đã tồn tại");
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        delete();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        clearForm();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        timSP();
    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            this.row = tblKhachHang.getSelectedRow();
            if (this.row >= 0) {
                this.edit();
                jButton2.setEnabled(true);
                jButton3.setEnabled(true);
                jButton1.setEnabled(false);
            }
        }
        Giahanthe();
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void tblKhachHangMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseReleased
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblKhachHangMouseReleased

    private void tblKhachHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMousePressed
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblKhachHangMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblID;
    private rojeru_san.complementos.RSTableMetro tblKhachHang;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGiamGia;
    private com.toedter.calendar.JDateChooser txtNgayHetHan;
    private javax.swing.JTextField txtNgayMo;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
