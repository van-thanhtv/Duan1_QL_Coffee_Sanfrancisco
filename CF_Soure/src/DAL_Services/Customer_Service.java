/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_Services;

import BUS_Services.QLCustomer_Service;
import DAL_IServices.ICustomer_Service;
import DAL_Models.*;
import static Utils.Auth.user;
import Utils.JDBC;
import static Utils.JDBC.url;
import Utils.dialogHelper;
import Utils.mailHelper;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JTable;

/*
 * @author : ThongPro
 * @since : 11/6/2021 9:40 PM
 * @description :
 * @update :
 *
 * */
public class Customer_Service implements ICustomer_Service {

    String INSERT_SQL = "INSERT INTO [Customer]([IDCust], [CusName], [DateAdd], [DateEnd],[Phone],[Email],[Discount],[Status],[CCCD]) VALUES (?, ?, ?, ?,?,?,?,1,?)";
    String UPDATE_SQL = "UPDATE [Customer] SET [CusName]= ?, [DateAdd] = ?,[DateEnd]=?,[Phone]=?,[Email]=?,[Discount]=?,[CCCD]=? WHERE [IDCust]= ?";
    String DELETE_SQL = "UPDATE [Customer] SET [Status]=2 WHERE [IDCust]= ?";
    String SELECT_ALL_SQL = "SELECT * FROM [Customer] where [Status]=1 OR [Status]=0";
    String SELECT_BY_ID_SQL = "SELECT * FROM [Customer] WHERE [IDCust] = ?";
    String up = "Update [Customer] set Status=0 where DateEnd=?";

    @Override
    public void insert(ENTITY_Customer entity) {
        try {
            JDBC.update(INSERT_SQL,
                    entity.getIDCust(),
                    entity.getCusName(),
                    entity.getDateAdd(),
                    entity.getDateEnd(),
                    entity.getPhone(),
                    entity.getEmail(),
                    entity.getDiscount(),
                    entity.getCCCD());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ENTITY_Customer entity) {
        try {
            JDBC.update(UPDATE_SQL,
                    entity.getCusName(),
                    entity.getDateAdd(),
                    entity.getDateEnd(),
                    entity.getPhone(),
                    entity.getEmail(),
                    entity.getDiscount(),
                    entity.getCCCD(),
                    entity.getIDCust());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String IDCust) throws SQLException {
        JDBC.update(DELETE_SQL, IDCust);
    }

    @Override
    public List<ENTITY_Customer> select() {
        return this.SelectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public ENTITY_Customer findById(String IDCust) {
        List<ENTITY_Customer> list = this.SelectBySQL(SELECT_BY_ID_SQL, IDCust);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ENTITY_Customer> SelectBySQL(String sql, Object... args) {
        List<ENTITY_Customer> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                ENTITY_Customer customer = new ENTITY_Customer();
                customer.setIDCust(rs.getString("IDCust"));
                customer.setCusName(rs.getString("CusName"));
                customer.setDateAdd(rs.getDate("DateAdd"));
                customer.setDateEnd(rs.getDate("DateEnd"));
                customer.setPhone(rs.getString("Phone"));
                customer.setEmail(rs.getString("Email"));
                customer.setDiscount(rs.getInt("Discount"));
                customer.setStatus(rs.getInt("Status"));
                customer.setCCCD(rs.getString("CCCD"));
                list.add(customer);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void up(String DateEnd) throws SQLException {
        JDBC.update(up, DateEnd);
    }

    public void up1(String DateEnd) throws SQLException {
        String sql = "Update [Customer] set Status=1 where DateEnd=?";
        JDBC.update(sql, DateEnd);
    }

    public void giahan(ENTITY_Customer entity) throws SQLException {
        String sql = "Update [Customer] set DateEnd=? where IDCust=?";
        JDBC.update(sql,
                entity.getDateEnd(),
                entity.getIDCust());
    }

    //        private NumberFormat n = new DecimalFormat("#,###");
    private static String url = "data.txt";
    private static FileInputStream fileInputStream = null;
    private static BufferedReader bufferedReader = null;
    private static String user = "";
    private static String pass = "";

    public void sendmail(String message, JTable tbl, int row1) {
        try {
            fileInputStream = new FileInputStream(url);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line = bufferedReader.readLine();
            int i = 0;
            while (line != null) {
                if (i == 0) {
                    user = line.substring(line.indexOf(':') + 1);
                }
                if (i == 1) {
                    pass = line.substring(line.indexOf(':') + 1);
                }
//                System.out.println(line + ":" + i);
                i++;
                line = bufferedReader.readLine();
            }
            System.out.println(user);
            System.out.println(pass);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(mailHelper.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(mailHelper.class.getName())
                    .log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedReader.close();
                fileInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(mailHelper.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
        try {
                row1 = tbl.getSelectedRow();
                String ID = (String) tbl.getValueAt(row1, 0);
                ENTITY_Customer pro = findById(ID);
                String host = "smtp.gmail.com";
                String user = Customer_Service.user;
                String pass = Customer_Service.pass;
                String to = pro.getEmail();
                String subject = "Coffee SanFrancisco";
            
                boolean sessionDebug = false;
                //!.Tạo 1 dối tượng Properties
                Properties pros = new Properties();
                pros.put("mail.smtp.auth", "true");
                pros.put("mail.smtp.starttls.enable", "true");
                pros.put("mail.smtp.host", "smtp.gmail.com");//2.Chỉ ra máy chủ mail của gg
                pros.put("mail.smtp.port", 587);//3.Chỉ ra port : 587 Cổng vào ra dữ liệu
                pros.put("mail.smtp.starttls.required", "true");
                java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

                Session mailSession = Session.getInstance(pros,
                        new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, pass);//Tài khoản Gmail của bạn và pass
                    }
                }
                );
                mailSession.setDebug(sessionDebug);
                Message msg = new MimeMessage(mailSession);
                //Gán giá trị cho các thuộc tính đôi tượng msg
                msg.setFrom(new InternetAddress(user));//5.Từ địa chỉ Gmail nào gưởi đi
                //            InternetAddress[] address = {new InternetAddress(to)};
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));//Tù Gmail gưởi đến mai nào
                msg.setSubject(subject);//Tiêu đề thư
                msg.setText(message);//Nội dung thư
////            Transport.send(msg);
                Transport transport = mailSession.getTransport("smtp");
                transport.connect(host, user, pass);
                transport.sendMessage(msg, msg.getAllRecipients());
                transport.close();

                dialogHelper.alert(null, "Gửi Thành Công");
            }catch (Exception e) {
            dialogHelper.alert(null, "Lỗi");
            e.printStackTrace();
        }
        
    }
    }
