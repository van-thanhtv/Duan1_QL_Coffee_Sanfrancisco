/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI2;

import BUS_IServices.IQLTable_Service;
import BUS_Services.QLTable_Service;
import DAL_Models.ENTITY_Area;
import DAL_Models.ENTITY_Promotion;
import DAL_Models.ENTITY_Table;
import DAL_Services.Area_Service;
import DAL_Services.Promotion_Service;
import DAL_Services.Table_Service;
import Utils.Check;
import Utils.ThongBao;
import Utils.dateHelper;
import static Utils.dateHelper.now;
import Utils.dialogHelper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class GUI_Promotions extends javax.swing.JPanel {

    SimpleDateFormat fomat = new SimpleDateFormat("dd/MM/yyyy");
    Area_Service khu;
    Table_Service tbdao;
    IQLTable_Service daotb;

    Table_Service ban = new Table_Service();
    JPopupMenu menu = new JPopupMenu("Popup");
    JPopupMenu menu1 = new JPopupMenu("Popup2");
    Promotion_Service dao = new Promotion_Service();
    int row = -1;

    /**
     * Creates new form GUI_Promotions
     */
    public GUI_Promotions() {
        initComponents();
        tblGiamgia.getColumnModel().getColumn(0).setMinWidth(0);
        tblGiamgia.getColumnModel().getColumn(0).setMaxWidth(0);
        init();
        lblID.setVisible(false);
    }

    void init() {
        date();
        filltoTable();
        clearForm();
        tbdao = new Table_Service();
        daotb = new QLTable_Service();
        khu = new Area_Service();
        fillComboBoxKhu();
        txtMaBan.setEditable(false);
        daotb.taoIDTable(txtMaBan);
        daotb.fillTable(tblTable);
        lblID.setVisible(false);
        this.xoaform();
    }

    void date() {
        try {
            SimpleDateFormat fo = new SimpleDateFormat("yyyy-MM-dd");
            List<ENTITY_Promotion> list = dao.select();
            for (ENTITY_Promotion kh : list) {
                String date1 = fo.format(dateHelper.now());
                String date2 = fo.format(kh.getEndPromo());
//                System.out.println(date1);
//                System.out.println(date2 + "\n");
                if (date1.compareTo(date2) > 0) { //neu DateEnd < Today
                    dao.up(String.valueOf(kh.getEndPromo()));
                } else {
                    dao.up1(String.valueOf(kh.getEndPromo()));
                }
            }
        } catch (Exception e) {
            dialogHelper.alert(this, " Lỗi Date");
            e.printStackTrace();
        }
    }
//--------------------------------------------------------------------------------------------

    private void fillComboBoxKhu() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) this.cbbKhu.getModel();
        model.removeAllElements();
        List<ENTITY_Area> list = daotb.selectIDArea();

        for (ENTITY_Area cd : list) {
            model.addElement(cd);
        }
    }

    void xoaform() {
        this.txtViTri.setText("");
        this.txtTimKiem.setText("");
        this.cbbKhu.setSelectedIndex(0);
        daotb.taoIDTable(txtMaBan);
        btnThem.setEnabled(true);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
    }

    void deletetb() {
        try {
            this.row = tblTable.getSelectedRow();
            if (this.row >= 0) {
                daotb.deleteTABLE(txtMaBan.getText());
                daotb.fillTable(tblTable);
                xoaform();
                ThongBao.alert(this, "Xóa thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void inserttb() {
        try {
            ENTITY_Table tbl = getModeltb();
            daotb.insertMATABLE(tbl);
            load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ENTITY_Table getModeltb() {
        ENTITY_Table tbl = new ENTITY_Table();
        ENTITY_Area tt = (ENTITY_Area) cbbKhu.getSelectedItem();
        tbl.setLocation(Integer.valueOf(txtViTri.getText()));
        tbl.setIDArea(tt.getIDArea());
//        System.out.println("" + tt.getIDArea());
        tbl.setIDTable(txtMaBan.getText());
        return tbl;
    }

    void edittb() {
        try {
            String maTB = (String) tblTable.getValueAt(this.row, 0);
            ENTITY_Table ban = this.ban.findById(maTB);
            if (ban != null) {
                this.setformtb(ban);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setformtb(ENTITY_Table tb) {
        String ma = (String) tblTable.getValueAt(row, 1);
        List<ENTITY_Area> c = khu.findById(ma);
        for (ENTITY_Area e : c) {
            cbbKhu.getModel().setSelectedItem(e);
            String maTB = (String) tblTable.getValueAt(this.row, 0);
            txtMaBan.setText(maTB);
            txtViTri.setText(String.valueOf(tb.getLocation()));
        }

    }

    void updatetb() {
        try {
            ENTITY_Table tbl = getModeltb();
            daotb.updatetTABLE(tbl);
            daotb.fillTable(tblTable);
            xoaform();
            ThongBao.alert(this, "Cập nhập thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void filltoTable() {
        DefaultTableModel model = (DefaultTableModel) tblGiamgia.getModel();
        model.setRowCount(0);
        try {
            List<ENTITY_Promotion> list = dao.select();
            for (ENTITY_Promotion pro : list) {
                String t = "";
                if (pro.getStatus() == 1) {
                    t = "Đang diễn ra";
                } else if (pro.getStatus() == 0) {
                    t = "Đã kết thúc";
                } else if (pro.getStatus() == 2) {
                    t = "";
                }
                Object[] row = {
                    pro.getIDPro(),
                    pro.getName(),
                    pro.getDiscountPromo() + "%",
                    fomat.format(pro.getStartPromo()),
                    fomat.format(pro.getEndPromo()),
                    t};
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            dialogHelper.alert(this, "Lỗi truy Vấn dữ liệu");
        }
    }

    void setForm(ENTITY_Promotion pro) {
        lblID.setText(String.valueOf(pro.getIDPro()));
        txtName.setText(pro.getName());
        txtChietKhau.setText(String.valueOf(pro.getDiscountPromo()));
        txtNgayBatdau.setDate(pro.getStartPromo());
        txtNgayKetThuc.setDate(pro.getEndPromo());
        txtMoTa.setText(pro.getDescription());
    }

    ENTITY_Promotion getform() {
        ENTITY_Promotion pro = new ENTITY_Promotion();
        pro.setIDPro(Integer.valueOf(lblID.getText()));
        pro.setName(txtName.getText());
        pro.setDiscountPromo(Integer.valueOf(txtChietKhau.getText()));
        pro.setStartPromo(txtNgayBatdau.getDate());
        pro.setEndPromo(txtNgayKetThuc.getDate());
        pro.setDescription(txtMoTa.getText());
        return pro;
    }

    void clearForm() {
        ENTITY_Promotion pro = new ENTITY_Promotion();
        pro.setStartPromo(dateHelper.now());
        pro.setEndPromo(dateHelper.add(15));
        this.setForm(pro);
        this.row = -1;
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
    }

    void edit() {
        try {
            int ID = (int) tblGiamgia.getValueAt(row, 0);
            ENTITY_Promotion pro = dao.findById(String.valueOf(ID));
            if (pro != null) {
                this.setForm(pro);
            }
        } catch (Exception e) {
            e.printStackTrace();
            dialogHelper.alert(this, "Lỗi Rồi DM");
        }

    }

    void insert() {
        ENTITY_Promotion pro = this.getform();
        int p = JOptionPane.showConfirmDialog(this, "Bạn muốn Thêm Chương Trình này?", "Hệ thống quản lý Ƹ̵̡Ӝ̵̨̄Ʒ☆", JOptionPane.YES_NO_OPTION);
        if (p == JOptionPane.YES_OPTION) {
            try {
                dao.insert(pro);
                filltoTable();
                dialogHelper.alert(this, "Thêm Thành Công");
                clearForm();
            } catch (Exception e) {
                dialogHelper.alert(this, "Thêm Thất Bại");
                e.printStackTrace();
            }
        }
    }

    void update() {
        ENTITY_Promotion pro = this.getform();
        int p = JOptionPane.showConfirmDialog(this, "Bạn muốn Sửa Chương Trình này?", "Hệ thống quản lý Ƹ̵̡Ӝ̵̨̄Ʒ☆", JOptionPane.YES_NO_OPTION);
        if (p == JOptionPane.YES_OPTION) {
            try {
                dao.update(pro);
                filltoTable();
                dialogHelper.alert(this, "Sửa Thành Công");
                clearForm();
            } catch (Exception e) {
                dialogHelper.alert(this, "Sửa Thất Bại");
                e.printStackTrace();
            }
        }
    }

    void delete() {
        String ID = lblID.getText();
        int p = JOptionPane.showConfirmDialog(this, "Bạn muốn Xóa Chương Trình này?", "Hệ thống quản lý Ƹ̵̡Ӝ̵̨̄Ʒ☆", JOptionPane.YES_NO_OPTION);
        if (p == JOptionPane.YES_OPTION) {
            try {
                dao.delete(ID);
                filltoTable();
                dialogHelper.alert(this, "Xóa Thành Công");
                clearForm();
            } catch (Exception e) {
                dialogHelper.alert(this, "Xóa Thất Bại");
                e.printStackTrace();
            }
        }
    }

    void khoiphuc() {
        this.row = tblGiamgia.getSelectedRow();
        if (this.row >= 0) {
            int ID = (int) tblGiamgia.getValueAt(this.row, 0);
            ENTITY_Promotion pro = dao.findById(String.valueOf(ID));
            int p = JOptionPane.showConfirmDialog(this, "Bạn muốn Khôi Phục Chương Trình Giảm Giá này?", "Hệ thống quản lý Ƹ̵̡Ӝ̵̨̄Ʒ☆", JOptionPane.YES_NO_OPTION);
            if (p == JOptionPane.YES_OPTION) {
            }
            if (pro.getStatus() == 1) {
                dialogHelper.alert(this, "Chương Trình Này vẫn đang diễn ra");
            } else if (pro.getStatus() == 0) {
                try {
                    dao.khoiphuc(String.valueOf(ID));
                    filltoTable();
                    dialogHelper.alert(this, "Khôi Phục Thành Công");
                } catch (Exception e) {
                    dialogHelper.alert(this, "Thất Bại");
                    e.printStackTrace();
                }
            }
        }
    }

    void xoa() {
        this.row = tblGiamgia.getSelectedRow();
        if (this.row >= 0) {
            int ID = (int) tblGiamgia.getValueAt(this.row, 0);
            ENTITY_Promotion pro = dao.findById(String.valueOf(ID));
            int p = JOptionPane.showConfirmDialog(this, "Bạn muốn Kết Thúc Chương trình Khuyến Mại này?", "Hệ thống quản lý Ƹ̵̡Ӝ̵̨̄Ʒ☆", JOptionPane.YES_NO_OPTION);
            if (p == JOptionPane.YES_OPTION) {
                if (pro.getStatus() == 0) {
                    dialogHelper.alert(this, "Chương Trình đã kết thúc từ trước");
                } else if (pro.getStatus() == 1) {
                    try {
                        dao.delete2(String.valueOf(ID));
                        filltoTable();
                        dialogHelper.alert(this, "Đã kết thúc thành công");
                    } catch (Exception e) {
                        dialogHelper.alert(this, "Thất Bại");
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void Test() {
        menu.removeAll();
        JMenuItem item = new JMenuItem("Khôi Phục");
        JMenuItem item1 = new JMenuItem("Kết Thúc Chương Trình");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                khoiphuc();
            }
        });
        menu.add(item);
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoa();
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

        jpTB = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtMaBan = new javax.swing.JTextField();
        txtViTri = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        cbbKhu = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        rSButtonIconD1 = new rojerusan.RSButtonIconD();
        xoa = new rojerusan.RSButtonIconD();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblTable = new rojeru_san.complementos.RSTableMetro();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        txtNgayKetThuc = new com.toedter.calendar.JDateChooser();
        txtNgayBatdau = new com.toedter.calendar.JDateChooser();
        lblID = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtChietKhau = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblGiamgia = new rojeru_san.complementos.RSTableMetro();

        setBackground(new java.awt.Color(255, 153, 153));

        jpTB.setBackground(new java.awt.Color(255, 153, 153));
        jpTB.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpTB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpTBMouseClicked(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(0, 102, 51));
        btnThem.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/add-item.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(0, 102, 51));
        btnClear.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/clear_item.png"))); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(0, 102, 51));
        btnXoa.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/delete_item.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(0, 102, 51));
        btnSua.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/edit_item.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setText("Mã bàn");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("Vị Trí");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setText("Tìm Kiếm");

        txtViTri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtViTriActionPerformed(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/tu1.png"))); // NOI18N

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyTyped(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 153, 153));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel2MouseReleased(evt);
            }
        });

        cbbKhu.setEditable(true);
        cbbKhu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));
        cbbKhu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbbKhu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbKhuMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cbbKhuMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cbbKhuMouseReleased(evt);
            }
        });
        cbbKhu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbKhuActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel13.setText("Khu");

        rSButtonIconD1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/add-item.png"))); // NOI18N
        rSButtonIconD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconD1ActionPerformed(evt);
            }
        });

        xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/delete_item.png"))); // NOI18N
        xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(cbbKhu, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rSButtonIconD1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonIconD1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(cbbKhu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        tblTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã bàn", "Khu", "Vị trí", "Tình trạng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTable.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tblTable.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblTable.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblTable.setFillsViewportHeight(true);
        tblTable.setFuenteFilas(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblTable.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblTable.setFuenteHead(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tblTable.setRowHeight(30);
        tblTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTableMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblTableMouseReleased(evt);
            }
        });
        jScrollPane5.setViewportView(tblTable);

        javax.swing.GroupLayout jpTBLayout = new javax.swing.GroupLayout(jpTB);
        jpTB.setLayout(jpTBLayout);
        jpTBLayout.setHorizontalGroup(
            jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTBLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTBLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(199, 199, 199))
            .addGroup(jpTBLayout.createSequentialGroup()
                .addGroup(jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTBLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpTBLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel10))
                            .addGroup(jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jpTBLayout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpTBLayout.createSequentialGroup()
                                            .addComponent(jLabel9)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtMaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jpTBLayout.createSequentialGroup()
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addComponent(txtViTri, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(110, 110, 110)
                        .addGroup(jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem))
                        .addGap(18, 18, 18)
                        .addGroup(jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpTBLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jpTBLayout.setVerticalGroup(
            jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTBLayout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTBLayout.createSequentialGroup()
                        .addGroup(jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtMaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtViTri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(89, 89, 89))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTBLayout.createSequentialGroup()
                        .addGroup(jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(90, 90, 90)))
                .addGroup(jpTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/giamgia.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Tên Chương Trình");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Chiết Khấu (%)");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("Ngày Bắt Đầu");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("Ngày Kết Thúc");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setText("Mô Tả");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        txtNgayKetThuc.setDateFormatString("dd/MM/yyyy");

        txtNgayBatdau.setDateFormatString("dd/MM/yyyy");

        lblID.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblID.setForeground(new java.awt.Color(255, 0, 51));
        lblID.setText("00");

        txtChietKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChietKhauActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 102, 51));
        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 102, 51));
        jButton2.setText("Sửa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 51));
        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 102, 51));
        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 51, 255));
        jLabel8.setText("%");

        tblGiamgia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã", "Tên chương trình", "Chiết khấu", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGiamgia.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tblGiamgia.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblGiamgia.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblGiamgia.setFillsViewportHeight(true);
        tblGiamgia.setFuenteFilas(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblGiamgia.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblGiamgia.setRowHeight(30);
        tblGiamgia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGiamgiaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblGiamgiaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblGiamgiaMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tblGiamgia);
        if (tblGiamgia.getColumnModel().getColumnCount() > 0) {
            tblGiamgia.getColumnModel().getColumn(0).setMinWidth(100);
            tblGiamgia.getColumnModel().getColumn(0).setMaxWidth(150);
            tblGiamgia.getColumnModel().getColumn(2).setMinWidth(100);
            tblGiamgia.getColumnModel().getColumn(2).setMaxWidth(200);
            tblGiamgia.getColumnModel().getColumn(5).setMinWidth(120);
            tblGiamgia.getColumnModel().getColumn(5).setMaxWidth(200);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(432, 432, 432)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(42, 42, 42))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(41, 41, 41)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtChietKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8))
                                    .addComponent(txtNgayBatdau, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblID)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel1))
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(jLabel6)
                        .addComponent(jLabel5))
                    .addContainerGap(46, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(42, 42, 42)
                            .addComponent(lblID)
                            .addGap(36, 36, 36))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(47, 47, 47)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtChietKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(47, 47, 47)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(txtNgayBatdau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(53, 53, 53)
                            .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(53, 53, 53)
                            .addComponent(jLabel6)))
                    .addContainerGap(664, Short.MAX_VALUE)))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpTB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (Check.checkNullText(txtName)
                && Check.checkNullText(txtChietKhau)) {
            if (Check.checkName(txtName)) {
                insert();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (Check.checkNullText(txtName)
                && Check.checkNullText(txtChietKhau)) {
            if (Check.checkName(txtName)) {
                update();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        delete();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        clearForm();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtChietKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChietKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChietKhauActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (Check.checkNullText(txtViTri)) {
            if (Check.checkTable(txtViTri.getText())) {
                ENTITY_Area tt = (ENTITY_Area) cbbKhu.getSelectedItem();
                int vt = (Integer.parseInt(txtViTri.getText()));
                List<ENTITY_Table> list = tbdao.select_viTri(vt, tt.getIDArea());
                if (list.size() == 0) {
                    if (ThongBao.comfirm(this, "Bạn chắc chắn muốn thêm")) {

                        inserttb();
                    }
                } else {
                    List<ENTITY_Table> ct = tbdao.select_CheckTrung(vt);
                    //   ENTITY_Area tt = (ENTITY_Area) cbbKhu.getSelectedItem();
                    if (ct.size() == 0) {
                        if (ThongBao.comfirm(this, "Bạn chắc chắn muốn thêm")) {

                            tbdao.update_trung(String.valueOf(tt.getIDArea()), vt);
                            load();
                        }
                    } else {
                        ThongBao.alert(this, "Vị trí đã tồn tại");
                    }
                }
            }

        }

    }//GEN-LAST:event_btnThemActionPerformed
    void load() {
        daotb.fillTable(tblTable);
        xoaform();
        ThongBao.alert(this, "Thêm thành công");
    }
    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        xoaform();
        daotb.fillTable(tblTable);
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (ThongBao.comfirm(this, "Bạn chắc chắn muốn xóa bàn này")) {
            deletetb();

        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        ENTITY_Area tt = (ENTITY_Area) cbbKhu.getSelectedItem();
        int vt = (Integer.parseInt(txtViTri.getText()));
        List<ENTITY_Table> list = tbdao.select_viTri(vt, tt.getIDArea());
        if (Check.checkNullText(txtViTri)) {
            if (Check.checkTable(txtViTri.getText())) {
                if (list.size() == 0) {

                    if (ThongBao.comfirm(this, "Bạn chắc chắn muốn sửa")) {
                        updatetb();
                    }
                } else {
                    ThongBao.alert(this, "Vị trí đã tồn tại");
                }
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtViTriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtViTriActionPerformed

    }//GEN-LAST:event_txtViTriActionPerformed

    private void txtTimKiemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyTyped
        daotb.fillTableByID(tblTable, txtTimKiem);
    }//GEN-LAST:event_txtTimKiemKeyTyped

    private void cbbKhuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbKhuMouseClicked
        //  xoaKhu();
    }//GEN-LAST:event_cbbKhuMouseClicked

    private void cbbKhuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbKhuMousePressed
        // TODO add your handling code here:
        //        if (evt.isPopupTrigger()) {
        //            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        //        }
    }//GEN-LAST:event_cbbKhuMousePressed

    private void cbbKhuMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbKhuMouseReleased
        //        // TODO add your handling code here:
        //        if (evt.isPopupTrigger()) {
        //            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        //        }
    }//GEN-LAST:event_cbbKhuMouseReleased

    private void cbbKhuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbKhuActionPerformed
        // xoaKhu();
    }//GEN-LAST:event_cbbKhuActionPerformed

    private void rSButtonIconD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconD1ActionPerformed

        String khu = JOptionPane.showInputDialog(this, "Nhập khu bạn muốn thêm");
        List<ENTITY_Area> list = this.khu.findById(khu);
        if (list.size() == 0) {
            if (khu != null) {
                if (ThongBao.comfirm(this, "Bạn chắc chắn muốn thêm")) {

                    this.khu.insert(khu);
                    fillComboBoxKhu();
                    ThongBao.alert(this, "Thêm khu thành công!");
                }
            }
        } else {
            ThongBao.alert(this, "Khu đã tồn tại");
        }
    }//GEN-LAST:event_rSButtonIconD1ActionPerformed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
//        if (ThongBao.comfirm(this, "Bạn chắc chắn muốn xóa khu này")) {
//            xoaKhu();
//        }
    }//GEN-LAST:event_jPanel2MouseClicked
//    public void xoaKhu() {
//        menu1.removeAll();
//        JMenuItem item = new JMenuItem("Xóa khu");
//        item.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//
//                ENTITY_Area tt = (ENTITY_Area) cbbKhu.getSelectedItem();
//                if (tt != null) {
//                    try {
//                        khu.delete(String.valueOf(tt.getIDArea()));
//                        fillComboBoxKhu();
//                        ThongBao.alert(null, "Xóa thành công");
//                    } catch (SQLException ex) {
//                        ThongBao.alert(null, "Chưa cho chưa xóa những khu đã có hóa đơn, đợi bản cập nhập sau =]]");
//                        // Logger.getLogger(GUI_Table.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//
//            }
//        });
//        menu1.add(item);
//    }
    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        // TODO add your handling code here:
//        if (evt.isPopupTrigger()) {
//            menu.show(evt.getComponent(), evt.getX(), evt.getY());
//        }
    }//GEN-LAST:event_jPanel2MousePressed

    private void jPanel2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseReleased
        // TODO add your handling code here:
//        if (evt.isPopupTrigger()) {
//            menu.show(evt.getComponent(), evt.getX(), evt.getY());
//        }
    }//GEN-LAST:event_jPanel2MouseReleased

    private void jpTBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpTBMouseClicked

    }//GEN-LAST:event_jpTBMouseClicked

    private void tblGiamgiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGiamgiaMouseClicked
        // TODO add your handling code here:
        try {
            if (evt.getClickCount() == 1) {
                this.row = tblGiamgia.getSelectedRow();
                if (this.row >= 0) {
                    edit();
                    jButton1.setEnabled(false);
                    jButton2.setEnabled(true);
                    jButton3.setEnabled(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            dialogHelper.alert(this, "Lỗi :<");
        }
        Test();
    }//GEN-LAST:event_tblGiamgiaMouseClicked

    private void tblGiamgiaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGiamgiaMouseReleased
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblGiamgiaMouseReleased

    private void tblGiamgiaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGiamgiaMousePressed
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblGiamgiaMousePressed

    private void tblTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTableMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.row = tblTable.getSelectedRow();
            if (this.row >= 0) {
//            ENTITY_Area dd = (ENTITY_Area) cbbKhu.getSelectedItem();
//            System.out.println(""+dd.getIDArea());
                this.edittb();

                //   dao.fillTableIDArea(tblTable, String.valueOf(t.getIDArea()));
                btnThem.setEnabled(false);
                btnSua.setEnabled(true);
                btnXoa.setEnabled(true);
            }
        }
    }//GEN-LAST:event_tblTableMouseClicked

    private void tblTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTableMouseReleased
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            menu1.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblTableMouseReleased

    private void tblTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTableMousePressed
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            menu1.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblTableMousePressed

    private void xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaActionPerformed
        // TODO add your handling code here:
        ENTITY_Area tt = (ENTITY_Area) cbbKhu.getSelectedItem();
        if (tt != null) {
            if (ThongBao.comfirm(this, "Bạn chắc chắn muốn xóa khu "+tt.getAreaName())) {
                
                try {
                    khu.delete(String.valueOf(tt.getIDArea()));
                    fillComboBoxKhu();
                    ThongBao.alert(null, "Xóa thành công");
                } catch (SQLException ex) {
                    ThongBao.alert(null, "Chưa cho chưa xóa những khu đã có hóa đơn, đợi bản cập nhập sau =]]");
                    // Logger.getLogger(GUI_Table.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }//GEN-LAST:event_xoaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbKhu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel jpTB;
    private javax.swing.JLabel lblID;
    private rojerusan.RSButtonIconD rSButtonIconD1;
    private rojeru_san.complementos.RSTableMetro tblGiamgia;
    private rojeru_san.complementos.RSTableMetro tblTable;
    private javax.swing.JTextField txtChietKhau;
    private javax.swing.JTextField txtMaBan;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtName;
    private com.toedter.calendar.JDateChooser txtNgayBatdau;
    private com.toedter.calendar.JDateChooser txtNgayKetThuc;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtViTri;
    private rojerusan.RSButtonIconD xoa;
    // End of variables declaration//GEN-END:variables
}
