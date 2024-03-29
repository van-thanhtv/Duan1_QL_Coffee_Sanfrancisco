/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI2;

import BUS_IServices.IQLMenu_Service;
import BUS_Models.SanPham;
import BUS_Services.QLMenu_Service;
import DAL_Models.ENTITY_ProductType;
import Utils.Check;
import Utils.ThongBao;
import Utils.XImage;
import Utils.dialogHelper;
import static java.awt.Color.pink;
import static java.awt.Color.white;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author phamd
 */
public class GUI_Menu extends javax.swing.JPanel {
     private NumberFormat n = new DecimalFormat("#,###");
    JPopupMenu menu = new JPopupMenu("Popup");
    int row = -1;
    JFileChooser fileChooser = new JFileChooser();
    private IQLMenu_Service qlsp;
    QLMenu_Service dao = new QLMenu_Service();

    /**
     * Creates new form GUI_Menu
     */
    public GUI_Menu() {
        initComponents();
        this.qlsp = (IQLMenu_Service) new QLMenu_Service();
//        dao.select();
        tblSanPham.getColumnModel().getColumn(0).setMinWidth(0);
        tblSanPham.getColumnModel().getColumn(0).setMaxWidth(0);
        lblType.setVisible(false);
        init();
//        dao.taoIDType(lblType);
    }
    
    void init() {
        dao.fillToTable(tblSanPham);
        dao.selectTypeName();
        dao.loadComboTypeName(cboLoai);
        lblID.setVisible(false);
        dao.taoID(lblID);
        this.clear();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblHinh = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        cboLoai = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btn1 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cboSize = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        lblType = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new rojeru_san.complementos.RSTableMetro();

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Tên sản phẩm");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setText("Giá sản phẩm");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel4.setText("Loại  sản phẩm");

        lblHinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });

        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        cboLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cafe", "Nước canh", "Nước mắm" }));
        cboLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboLoaiMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cboLoaiMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cboLoaiMouseReleased(evt);
            }
        });
        cboLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(0, 102, 51));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/add-item.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(0, 102, 51));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/edit_item.png"))); // NOI18N
        btnSua.setText("Sửa ");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(0, 102, 51));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/delete_item.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnMoi.setBackground(new java.awt.Color(0, 102, 51));
        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/clear_item.png"))); // NOI18N
        btnMoi.setText("Clear");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/cuthong1.png"))); // NOI18N

        btn1.setBackground(new java.awt.Color(0, 102, 51));
        btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/first.png"))); // NOI18N
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btn3.setBackground(new java.awt.Color(0, 102, 51));
        btn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/next.png"))); // NOI18N
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        btn2.setBackground(new java.awt.Color(0, 102, 51));
        btn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/back.png"))); // NOI18N
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        btn4.setBackground(new java.awt.Color(0, 102, 51));
        btn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/last.png"))); // NOI18N
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel7.setText("Tìm kiếm");

        cboSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSizeActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setText("Size");

        lblID.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblID.setForeground(new java.awt.Color(51, 255, 51));
        lblID.setText("jLabel9");

        lblType.setText("jLabel9");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/add-cart.png"))); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên sản phẩm", "Size", "Giá", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tblSanPham.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblSanPham.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblSanPham.setFuenteFilas(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblSanPham.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblSanPham.setFuenteHead(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        tblSanPham.setRowHeight(30);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSanPhamMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(119, 119, 119)
                                .addComponent(lblID))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addGap(36, 36, 36)
                                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(18, 18, 18)
                                                .addComponent(cboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(32, 32, 32)
                                                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(lblType)
                                .addGap(18, 18, 18)
                                .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnMoi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnThem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(399, 399, 399)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(689, 689, 689)
                        .addComponent(btn1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn2)
                        .addGap(18, 18, 18)
                        .addComponent(btn3)
                        .addGap(18, 18, 18)
                        .addComponent(btn4))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1644, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(286, 286, 286)
                        .addComponent(lblType))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblID)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel4)
                                                .addComponent(cboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(29, 29, 29)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel1)
                                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(36, 36, 36)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel3)
                                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(btnThem)
                                .addGap(28, 28, 28)
                                .addComponent(btnSua)
                                .addGap(37, 37, 37)
                                .addComponent(btnXoa)
                                .addGap(42, 42, 42)
                                .addComponent(btnMoi))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn2)
                    .addComponent(btn1)
                    .addComponent(btn3)
                    .addComponent(btn4))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        dao.chonAnh(lblHinh);
    }//GEN-LAST:event_lblHinhMouseClicked

    private void cboLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiActionPerformed
        
        if (cboLoai.getSelectedItem() != null) {
            if (!cboLoai.getSelectedItem().equals(cboLoai.getSelectedItem().toString())) {
                ENTITY_ProductType sp = (ENTITY_ProductType) cboLoai.getSelectedItem();
                lblType.setText("" + sp.getIDType());
                if (sp != null) {
                    dao.loadComboSize(cboSize, String.valueOf(sp.getTypeName()), lblType);
                }
            }
        }

    }//GEN-LAST:event_cboLoaiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (Check.checkNullText(txtTen)
                && Check.checkNullText(txtGia)) {
            if (Check.checkso3(txtGia)) {
                SanPham sp = this.getForm();
                try {
                    int p = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn thêm sản phẩm này?", "Hệ thống quản lý Ƹ̵̡Ӝ̵̨̄Ʒ☆", JOptionPane.YES_NO_OPTION);
                    if (p == JOptionPane.YES_OPTION) {
                        dao.insert(sp);
                        dao.fillToTable(tblSanPham);
                        dialogHelper.alert(this, "Thêm Sản Phẩm Thành Công");
                        clear();
                        dao.taoID(lblID);
                    }
                } catch (Exception e) {
                    dialogHelper.alert(this, "Lỗi Khi Thêm");
                    e.printStackTrace();
                }
                
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (Check.checkNullText(txtTen)
                && Check.checkNullText(txtGia)) {
            if (Check.checkso3(txtGia)) {
                SanPham sp = getForm();
                try {
                    int p = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn Sửa sản phẩm này?", "Hệ thống quản lý Ƹ̵̡Ӝ̵̨̄Ʒ☆", JOptionPane.YES_NO_OPTION);
                    if (p == JOptionPane.YES_OPTION) {
                        dao.updateSP(sp);
                        dao.fillToTable(tblSanPham);
                        clear();
                        dialogHelper.alert(this, "Sửa Thành Công");
                        dao.taoID(lblID);
                    }
                } catch (Exception e) {
                    dialogHelper.alert(this, "Sửa Thất Bại");
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        String IDProduct = lblID.getText();
        SanPham sp = dao.findById(IDProduct);
        int p = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn Xóa Sản Phẩm này?", "Hệ thống quản lý Ƹ̵̡Ӝ̵̨̄Ʒ☆", JOptionPane.YES_NO_OPTION);
        if (p == JOptionPane.YES_OPTION) {
            try {
                dao.delete(IDProduct);
                dao.fillToTable(tblSanPham);
                dialogHelper.alert(this, "Xoá Thành Công");
                clear();
                dao.taoID(lblID);
            } catch (Exception e) {
                dialogHelper.alert(this, "Lỗi Khi Xóa");
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        clear();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        prev();
    }//GEN-LAST:event_btn2ActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed

    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        dao.timSP(txtTimKiem, tblSanPham);
    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void cboSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSizeActionPerformed
        
        if (cboSize.getSelectedItem() != null) {
            if (!cboSize.getSelectedItem().equals(cboSize.getSelectedItem().toString())) {
                SanPham sp = (SanPham) cboSize.getSelectedItem();
                lblType.setText("" + sp.getIDType());
            }
        }
    }    
//GEN-LAST:event_cboSizeActionPerformed

    private void tblSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMousePressed
        if (evt.isPopupTrigger()) {
            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblSanPhamMousePressed
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        GUI2.JDialog_LoaiSP table = new GUI2.JDialog_LoaiSP(null, true, cboLoai);
        table.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked

    }//GEN-LAST:event_jButton2MouseClicked

    private void cboLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLoaiMouseClicked
        this.row = tblSanPham.getSelectedRow();
        this.row = -1;
    }//GEN-LAST:event_cboLoaiMouseClicked

    private void cboLoaiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLoaiMousePressed

    }//GEN-LAST:event_cboLoaiMousePressed

    private void cboLoaiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboLoaiMouseReleased

    }//GEN-LAST:event_cboLoaiMouseReleased

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        first();
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        last();
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        next();
    }//GEN-LAST:event_btn3ActionPerformed

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void tblSanPhamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseReleased
        // TODO add your handling code here:
        if (evt.isPopupTrigger()) {
            menu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblSanPhamMouseReleased

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        try {
            if (evt.getClickCount() == 1) {
                this.row = tblSanPham.getSelectedRow();
                System.out.println(row);
                if (this.row >= 0) {
                    this.edit();
                    btnThem.setEnabled(false);
                    btnSua.setEnabled(true);
                    btnXoa.setEnabled(true);
                    if (cboSize.getSelectedItem() == null) {
                        cboSize.addItem("");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Test();
    }//GEN-LAST:event_tblSanPhamMouseClicked
    
    private void setForm(SanPham sp) {
        lblID.setText(sp.getIDProduct());
        txtTen.setText(sp.getProductName());
        txtGia.setText(n.format(sp.getPrice()).replace(",",""));
        cboLoai.getModel().setSelectedItem(sp.getTypeName());
        cboSize.getModel().setSelectedItem(sp.getSize());
        if (sp.getImage() != null) {
            lblHinh.setToolTipText(sp.getImage()); //lay ra ten file trong tooltip 
            lblHinh.setIcon(XImage.read(sp.getImage())); //doc file trong tooltip va hien thi len lable
        } else {
            lblHinh.setIcon(XImage.read("no_image.jpg"));
        }
        lblType.setText(String.valueOf(sp.getIDType()));
//       cboLoai.setToolTipText(String.valueOf(sp.getIDType()));
    }
    
    SanPham getForm() {
        SanPham sp = new SanPham();
        sp.setIDProduct(lblID.getText());
        sp.setProductName(txtTen.getText());
        sp.setPrice(Float.valueOf(txtGia.getText()));
        if (cboSize.getSelectedItem() == null) {
        } else {
            sp.setSize((String) cboSize.getSelectedItem().toString());
        }
        sp.setTypeName((String) cboLoai.getSelectedItem().toString());
        sp.setImage(lblHinh.getToolTipText());
        sp.setIDType(Integer.valueOf(lblType.getText()));
        return sp;
    }
    
    public void Test() {
        menu.removeAll();
        JMenuItem item = new JMenuItem("Xem chi tiết");
        JMenuItem item1 = new JMenuItem("Khôi Phục");
        JMenuItem item2 = new JMenuItem("Không sử dụng");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUI2.JDialog_Xemchitiet table = new GUI2.JDialog_Xemchitiet(null, true, tblSanPham);
                table.setVisible(true);
            }
        });
        menu.add(item);
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                khoiphuc();
            }
            
        });
        menu.add(item1);
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoa();
            }
        });
        menu.add(item2);
    }
    
    void edit() {
        try {
            cboSize.removeAllItems();
            String ID = (String) tblSanPham.getValueAt(this.row, 0); //lay giá trị hàng hiện tại & cột 0
            SanPham sp = dao.findById(ID); //lay thong tin sp tuong ung trong csdl
//            cboSize.removeAllItems();
            if (sp != null) {
                this.setForm(sp);
            }
        } catch (Exception e) {
            dialogHelper.alert(this, "Lỗi");
            e.printStackTrace();
        }
        
    }
    
    void clear() {
        SanPham sp = new SanPham();
        this.setForm(sp);
//        if (cboLoai.getSelectedItem()!=null) {
            cboLoai.setSelectedIndex(0);
//        }
        lblHinh.setToolTipText(sp.getImage());
        lblHinh.setIcon(XImage.read("no_image.jpg"));
        dao.taoID(lblID);
        this.row = -1;
        btnThem.setEnabled(true);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
    }
    
    void khoiphuc() {
        this.row = tblSanPham.getSelectedRow();
        if (this.row >= 0) {
            String IDProduct = (String) tblSanPham.getValueAt(this.row, 0);
            SanPham sp = dao.findById(IDProduct);
            int p = JOptionPane.showConfirmDialog(this, "Bạn muốn Khôi Phục Sản Phẩm này?", "Hệ thống quản lý Ƹ̵̡Ӝ̵̨̄Ʒ☆", JOptionPane.YES_NO_OPTION);
            if (p == JOptionPane.YES_OPTION){
            if (sp.getStatus() == 1) {
                dialogHelper.alert(this, "Sản Phẩm này vẫn đang được sử dụng");
            } else if(sp.getStatus()==0)  {
                try {
                    dao.khoiphuc(IDProduct);
                    dao.fillToTable(tblSanPham);
                    dialogHelper.alert(this, "Khôi Phục Sản Phẩm Thành Công");
                } catch (Exception e) {
                    dialogHelper.alert(this, "Thất Bại");
                    e.printStackTrace();
                }
            }
        }
    }
    }
    
    void xoa() {
        this.row = tblSanPham.getSelectedRow();
        if (this.row >= 0) {
            String IDProduct = (String) tblSanPham.getValueAt(this.row, 0);
            SanPham sp = dao.findById(IDProduct);
            int p = JOptionPane.showConfirmDialog(this, "Sản Phẩm Này Không Còn Sử Dụng", "Hệ thống quản lý Ƹ̵̡Ӝ̵̨̄Ʒ☆", JOptionPane.YES_NO_OPTION);
            if (p == JOptionPane.YES_OPTION){
            if (sp.getStatus() == 0) {
                dialogHelper.alert(this, "Sản Phẩm Đang Không sử dụng mà");
            } else if(sp.getStatus()==1) {
                try {
                    dao.ketthuc(IDProduct);
                    dao.fillToTable(tblSanPham);
                    dialogHelper.alert(this, "Cập Nhật Thành Công");
                } catch (Exception e) {
                    dialogHelper.alert(this, "Thất Bại");
                    e.printStackTrace();
                }
            }
        }
    }
    }
    
    void first() {
        this.row = 0;
        this.edit();
    }
    
    void next() {
        if (this.row < tblSanPham.getRowCount() - 1) {
            this.row++;
            this.edit();
        }
    }
    
    void prev() {
        if (this.row > 0) {
            this.row--;
            this.edit();
        }
    }
    
    void last() {
        this.row = tblSanPham.getRowCount() - 1;
        this.edit();
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboLoai;
    private javax.swing.JComboBox<String> cboSize;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblType;
    private rojeru_san.complementos.RSTableMetro tblSanPham;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
