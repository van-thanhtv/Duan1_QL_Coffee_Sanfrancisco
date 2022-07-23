/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_Services;

import DAL_IServices.IEmployee_Service;
import DAL_Models.*;
import Utils.Check;
import Utils.JDBC;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Employee_Service implements IEmployee_Service {

    String INSERT_SQL = "INSERT INTO [Employee]([UsernameEMP], [Password], [NameEMP],[Phone],[Birthday],[Address],[Sex],[Email],[Image],[Status]) VALUES (?, ?, ?, ?,?,?,?,?,?,0)";
    String UPDATE_SQL = "UPDATE [Employee] SET  [NameEMP] = ?,[Phone]=?,[Birthday]=?,[Address]=?,[Sex]=?,[Email]=?,[Image]=? WHERE [UsernameEMP]= ?";
    String DELETE_SQL = "UPDATE [Employee] SET [Status]=1 WHERE [UsernameEMP]= ? ";
    String SELECT_ALL_SQL = "SELECT * FROM [Employee] where [Status]=0";
    String SELECT_BY_ID_SQL = "SELECT * FROM [Employee] WHERE [UsernameEMP] = ? and [Status]=0";
    String SELECT_BY_ID = "SELECT * FROM [Employee] WHERE [UsernameEMP] = ? and [Status]=1";
    String check = "UPDATE [Employee] SET [NameEMP] = ?,[Phone]=?,[Birthday]=?,[Address]=?,[Sex]=?,[Email]=?,[Image]=?,[Password]=?,[Status]=0 WHERE [UsernameEMP]= ? And [Status]=1";
    String UPDATE_MK = "UPDATE [Employee] SET [Password]=? where [UsernameEMP] = ? and [Status]=0";

    public void checkTrung(ENTITY_Employee entity) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        try {
            JDBC.update(check,
                    entity.getNameEMP(),
                    entity.getPhone(),
                    entity.getBirthday(),
                    entity.getAddress(),
                    entity.getSex(),
                    entity.getEmail(),
                    entity.getImage(),
                    entity.getPassword(),
                    //maHoa(entity.getPassword()),
                    entity.getUsernameEMP());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMK(String pass, String maNV) {
        try {
            JDBC.update(UPDATE_MK, pass, maNV);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(ENTITY_Employee entity) {
        try {
            JDBC.update(INSERT_SQL,
                    entity.getUsernameEMP(),
                    // maHoa(entity.getPassword()),
                    entity.getPassword(),
                    entity.getNameEMP(),
                    entity.getPhone(),
                    entity.getBirthday(),
                    entity.getAddress(),
                    entity.getSex(),
                    entity.getEmail(),
                    entity.getImage()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ENTITY_Employee entity) {
        try {
            JDBC.update(UPDATE_SQL,
                    entity.getNameEMP(),
                    entity.getPhone(),
                    entity.getBirthday(),
                    entity.getAddress(),
                    entity.getSex(),
                    entity.getEmail(),
                    entity.getImage(),
                    entity.getUsernameEMP());
        } catch (SQLException ex) {
            Logger.getLogger(Employee_Service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String UsernameEMP) throws SQLException {
        JDBC.update(DELETE_SQL, UsernameEMP);
    }

    @Override
    public List<ENTITY_Employee> select() {
        return this.SelectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public ENTITY_Employee findById(String UsernameEMP) {
        List<ENTITY_Employee> list = this.SelectBySQL(SELECT_BY_ID_SQL, UsernameEMP);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public ENTITY_Employee selectByID(String id) {
        List<ENTITY_Employee> list = this.SelectBySQL(SELECT_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ENTITY_Employee> SelectBySQL(String sql, Object... args) {
        List<ENTITY_Employee> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                ENTITY_Employee employee = new ENTITY_Employee();
                employee.setUsernameEMP(rs.getString("UsernameEMP"));
                employee.setPassword(rs.getString("Password"));
                employee.setNameEMP(rs.getString("NameEMP"));
                employee.setPhone(rs.getString("Phone"));
                employee.setBirthday(rs.getDate(5));
                employee.setAddress(rs.getString("Address"));
                employee.setSex(rs.getBoolean("Sex"));
                employee.setEmail(rs.getString("Email"));
                employee.setImage(rs.getString("Image"));
                //   employee.setStatus(rs.getBoolean(rs.getByte("Status")));
                list.add(employee);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
            
        }
    }

    public void update1(ENTITY_Employee entity) {

        String UPDATE1 = "UPDATE [Employee] SET [Password]=?, [NameEMP] = ?,[Phone]=?,[Birthday]=?,[Address]=?,[Sex]=?,[Email]=?,[Image]=? WHERE [UsernameEMP]= ?";
        try {
            JDBC.update(UPDATE1,
                    entity.getPassword(),
                    entity.getNameEMP(),
                    entity.getPhone(),
                    entity.getBirthday(),
                    entity.getAddress(),
                    entity.getSex(),
                    entity.getEmail(),
                    entity.getImage(),
                    entity.getUsernameEMP());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//             public String maHoa(String srcText) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//        String enrText;
//
//        MessageDigest mh = MessageDigest.getInstance("MD5");//Lớp thực hiện mã hóa
//        byte[] srcTextBytes = srcText.getBytes("UTF-8");
//        byte[] enrTextBytes = mh.digest(srcTextBytes);
//
//        BigInteger bigInt = new BigInteger(1, enrTextBytes);//byte mã hóa đc chuyển sang chuổi số hệ 16 nhờ lớp
//        enrText = bigInt.toString(16);
//
//        return enrText;
//    }
}
