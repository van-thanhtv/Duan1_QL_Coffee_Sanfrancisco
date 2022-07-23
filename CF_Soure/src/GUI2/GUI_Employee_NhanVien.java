/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI2;

import BUS_IServices.IQLEmployee_Service;
import BUS_Services.QLEmployee_Service;
import DAL_Models.ENTITY_Employee;
import DAL_Services.Employee_Service;
import Utils.Auth;
import Utils.Check;
import Utils.ThongBao;
import Utils.XImage;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import static java.awt.Color.pink;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author notak
 */
public class GUI_Employee_NhanVien extends javax.swing.JPanel {
    
    IQLEmployee_Service dao;
    Employee_Service nv = new Employee_Service();
    int row = 0;
    
    public GUI_Employee_NhanVien() {
        initComponents();
        dao = (IQLEmployee_Service) new QLEmployee_Service();
        dao.fillTable(tblEmployee);
        resetForm();
        
    }
    
    ENTITY_Employee getModel() {
        ENTITY_Employee tbl = new ENTITY_Employee();
        tbl.setNameEMP(txtHoTen.getText());
        tbl.setPhone(txtSDT.getText());
        tbl.setBirthday(txtNgaySinh.getDate());
        tbl.setAddress(txtDiaChi.getText());
        tbl.setUsernameEMP(txtUserName.getText());
        tbl.setPassword(new String(txtPassWord.getPassword()));
        tbl.setEmail(txtEmail.getText());
        tbl.setSex(rdoNam.isSelected() ? true : false);
        tbl.setImage(lblAnh.getToolTipText());
        return tbl;
    }
    
    void resetForm() {
        this.txtHoTen.setText("");
        this.txtSDT.setText("");
        this.txtNgaySinh.setDate(now());
        this.txtDiaChi.setText("");
        this.txtEmail.setText("");
        this.txtUserName.setText("");
        this.txtPassWord.setText("");
        this.rdoNam.setSelected(true);
        this.txtUserName.setEditable(true);
        this.txtPassWord.setEditable(true);
        this.lblAnh.setIcon(XImage.read("no_image.jpg"));
        btnThem.setEnabled(true);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
    }
    
    public Date now() {
        return new Date();
    }
    
    void edit() {
        try {
            String maNV = (String) tblEmployee.getValueAt(row, 6);
            ENTITY_Employee nv = this.nv.findById(maNV);
            
            this.setform(nv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    void setform(ENTITY_Employee nv) {
        try {
            String manv = (String) tblEmployee.getValueAt(this.row, 6);
            txtHoTen.setText(nv.getNameEMP());
            txtSDT.setText(nv.getPhone());
            txtNgaySinh.setDate(nv.getBirthday());
            txtDiaChi.setText(nv.getAddress());
            txtEmail.setText(nv.getEmail());
            txtUserName.setText(manv);
            txtPassWord.setText("*****");
            if (nv.getSex()) {
                rdoNam.setSelected(true);
            } else {
                rdoNu.setSelected(true);
            }
            if (nv.getImage() != null) {
                lblAnh.setToolTipText(nv.getImage()); //lay ra ten file trong tooltip 
                lblAnh.setIcon(XImage.read(nv.getImage())); //doc file trong tooltip va hien thi len lable
            } else {
                lblAnh.setIcon(XImage.read("no_image.jpg"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        lblAnh = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        rdoNu = new javax.swing.JRadioButton();
        rdoNam = new javax.swing.JRadioButton();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        txtPassWord = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        btnChupAnh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmployee = new rojeru_san.complementos.RSTableMetro();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Họ Tên");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Email");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setText("Số ĐT");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel4.setText("Ngày Sinh");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setText("Giới Tính");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel6.setText("Địa Chỉ");

        btnThem.setBackground(new java.awt.Color(0, 102, 51));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/add_KH.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(0, 102, 51));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/edit.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(0, 102, 51));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/remove-icon.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(0, 102, 51));
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/clear.png"))); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        lblAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/no_image.jpg"))); // NOI18N
        lblAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhMouseClicked(evt);
            }
        });

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        rdoNu.setText("Nữ");

        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        rdoNam.setText("Nam");

        txtNgaySinh.setDateFormatString("dd/MM/yyyy");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel8.setText("Username");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel9.setText("PassWord");

        txtUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserNameActionPerformed(evt);
            }
        });

        txtPassWord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassWordActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/QLNV.PNG"))); // NOI18N
        jLabel7.setText("QLNV");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/vuong1.gif"))); // NOI18N

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/vuong1.gif"))); // NOI18N

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/vuong1.gif"))); // NOI18N

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/vuong1.gif"))); // NOI18N

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/vuong1.gif"))); // NOI18N

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/vuong1.gif"))); // NOI18N

        btnChupAnh.setBackground(new java.awt.Color(0, 102, 51));
        btnChupAnh.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnChupAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/red-camera-icon.png"))); // NOI18N
        btnChupAnh.setText("Chụp ảnh");
        btnChupAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChupAnhMouseClicked(evt);
            }
        });
        btnChupAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChupAnhActionPerformed(evt);
            }
        });

        tblEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Họ & tên", "Số điện thoại", "Ngày sinh", "Địa chỉ", "Giới tính", "Email", "Tài khoản", "Mật khẩu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEmployee.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tblEmployee.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblEmployee.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblEmployee.setFillsViewportHeight(true);
        tblEmployee.setFuenteFilas(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblEmployee.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblEmployee.setFuenteHead(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tblEmployee.setRowHeight(30);
        tblEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmployeeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmployee);

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/vuong1.gif"))); // NOI18N

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/vuong1.gif"))); // NOI18N

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/vuong1.gif"))); // NOI18N

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/vuong1.gif"))); // NOI18N

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/vuong1.gif"))); // NOI18N

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/vuong1.gif"))); // NOI18N

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/vuong1.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel31)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel30)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel26)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel27)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel32)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel25))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel1))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                                    .addComponent(txtHoTen)
                                    .addComponent(txtEmail))))
                        .addGap(112, 112, 112)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel11)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel12)
                        .addGap(51, 51, 51)
                        .addComponent(jLabel22)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel23)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel24)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel28)
                        .addGap(158, 158, 158)
                        .addComponent(btnChupAnh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(30, 30, 30)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(11, 11, 11)
                                                .addComponent(rdoNu)
                                                .addGap(43, 43, 43)
                                                .addComponent(rdoNam))
                                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtPassWord, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel3))
                                                .addGap(8, 8, 8)))
                                        .addGap(29, 29, 29)
                                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem))
                .addContainerGap(198, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnClear, btnSua, btnThem, btnXoa});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(62, 62, 62)
                                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(67, 67, 67)
                                        .addComponent(jLabel6)
                                        .addGap(27, 27, 27)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(rdoNu)
                                                    .addComponent(rdoNam))
                                                .addGap(37, 37, 37)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(txtPassWord, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel9)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(28, 28, 28)
                                                .addComponent(jLabel5)))))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel2)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel4))
                                            .addGap(1, 1, 1)))
                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jLabel25)
                            .addComponent(jLabel24)
                            .addComponent(jLabel32)
                            .addComponent(jLabel27)
                            .addComponent(jLabel26)
                            .addComponent(jLabel28)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel30)
                            .addComponent(jLabel29)))
                    .addComponent(btnChupAnh, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnClear, btnSua, btnThem, btnXoa});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (ThongBao.comfirm(this, "Bạn chắc chắn muốn thêm nhân viên này ?")) {
            if (Check.checkNullText(txtSDT)
                    && Check.checkNullText(txtDiaChi)
                    && Check.checkNullText(txtEmail)
                    && Check.checkNullText(txtHoTen)
                    && Check.checkNullText(txtUserName)
                    && Check.checkNullPass(txtPassWord)) {
                if (Check.checkName(txtHoTen)
                        && Check.checkPass(txtPassWord)
                        && Check.checkEmail(txtEmail)
                        && Check.checkSDT(txtSDT)) {
                    try {
                        if (themtrung(txtUserName) == 1) {
                            return;
                        }
                        insert();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                }
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed
    void insert() {
        try {
            ENTITY_Employee tbl = getModel();
            dao.insertMAEMPLOYEE(tbl);
            dao.fillTable(tblEmployee);
            resetForm();
            ThongBao.alert(this, "Thêm Mới thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int themtrung(JTextField t) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (nv.selectByID(t.getText()) != null) {
            ENTITY_Employee nv = getModel();
            this.nv.checkTrung(nv);
            dao.fillTable(tblEmployee);
            resetForm();
            ThongBao.alert(this, "Thêm thành công");
            return 1;
        } else {
            if (nv.findById(t.getText()) == null) {
                return 2;
            } else {
                t.setBackground(pink);
                ThongBao.alert(this, "Mã đã bị tồn tại.");
                return 3;
            }
        }
    }
    private void lblAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhMouseClicked
        dao.chonAnh(lblAnh);
    }//GEN-LAST:event_lblAnhMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        resetForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
//        if (Auth.isAdmin()) {
        if (ThongBao.comfirm(this, "Bạn có chắc muốn xóa nhân viên này ?")) {
            delete();
        }
//       }
    }//GEN-LAST:event_btnXoaActionPerformed
    void delete() {
        try {
            this.row = tblEmployee.getSelectedRow();
            String ma = (String) tblEmployee.getValueAt(row, 6);
            dao.deleteEmployee(ma);
            dao.fillTable(tblEmployee);
            resetForm();
            ThongBao.alert(this, "Xóa thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (ThongBao.comfirm(this, "Bạn chắc chắn muốn sửa thông tin ?")) {
            if (Check.checkNullText(txtSDT)
                    && Check.checkNullText(txtDiaChi)
                    && Check.checkNullText(txtEmail)
                    && Check.checkNullText(txtHoTen)
                    && Check.checkNullText(txtUserName)) {
                
            if (Check.checkNullText(txtHoTen)
                    && Check.checkEmail(txtEmail)
                    && Check.checkNullText(txtEmail)
                    && Check.checkName(txtHoTen)
                    && Check.checkSDT(txtSDT)) {
                update();
            }
            }
        }
        
    }//GEN-LAST:event_btnSuaActionPerformed
    void update() {
        try {
            ENTITY_Employee tbl = new ENTITY_Employee();
            tbl.setNameEMP(txtHoTen.getText());
            tbl.setPhone(txtSDT.getText());
            tbl.setBirthday(txtNgaySinh.getDate());
            tbl.setAddress(txtDiaChi.getText());
            tbl.setSex(rdoNam.isSelected() ? true : false);
            tbl.setEmail(txtEmail.getText());
            tbl.setImage(lblAnh.getToolTipText());
            String ma = (String) tblEmployee.getValueAt(row, 6);
            tbl.setUsernameEMP(ma);
            dao.updateMAEMPLOYEE(tbl);
            dao.fillTable(tblEmployee);
            resetForm();
            ThongBao.alert(this, "Cập nhập thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void txtUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserNameActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnChupAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChupAnhActionPerformed
        
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        JDialog_ChupAnh ok = new JDialog_ChupAnh(null, true, lblAnh);
//        ok.thongpro();
        ok.setVisible(true);
        lblAnh.getToolTipText();
        ok.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                System.out.println("Tat webcam");
                ok.webSource.release();
                ok.dispose();
            }
        });
    }//GEN-LAST:event_btnChupAnhActionPerformed

    private void btnChupAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChupAnhMouseClicked
//        JDialog_ChupAnh ok=new JDialog_ChupAnh(null,true);
//        ok.setVisible(true);
    }//GEN-LAST:event_btnChupAnhMouseClicked

    private void txtPassWordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassWordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassWordActionPerformed

    private void tblEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmployeeMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.row = tblEmployee.getSelectedRow();
            if (this.row >= 0) {
                this.edit();
                txtPassWord.setEditable(false);
                txtUserName.setEditable(false);
                btnThem.setEnabled(false);
                btnSua.setEnabled(true);
                btnXoa.setEnabled(true);
            }
        }
    }//GEN-LAST:event_tblEmployeeMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChupAnh;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private rojeru_san.complementos.RSTableMetro tblEmployee;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JPasswordField txtPassWord;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
