/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.*;

/*
 * @author : ThongPro
 * @since : 11/6/2021 12:21 AM
 * @description :
 * @update :
 *
 * */
public class JDBC {
    public static String url = "jdbc:sqlserver://localhost:1433;databaseName=Coffee"; // đường dẫn
    public static String username = "SA"; // user sql
    public static String password = "Thanhtvph15016"; //pass sql
    //câu lệnh sql statement (có đối số hoặc không)
    // args mảng đối số của câu lệnh sql (có hoặc không)
    public static PreparedStatement preparedStatement(String sql, Object... args) throws SQLException {
        Connection con = DriverManager.getConnection(url, username, password); // tạo kết nối với sql
        PreparedStatement ps;
        if (sql.trim().startsWith("{")) { // nếu bắt đầu = { thì là lời gọi thủ tục
            ps = con.prepareCall(sql); //  thủ tực lưu
        } else {
            ps = con.prepareStatement(sql); //câu lệnh sql
        }
        //Set cho VD : ManV,HoTen...
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i + 1, args[i]);
        }
        return ps; //ps đã được truyền đối số
    }

    // dùng để thêm , sửa ,xóa
    public static int update(String sql, Object... args) throws SQLException {
        try {
            // thuc thi ps đã được truyền tham số ở hàm trên
            PreparedStatement ps = preparedStatement(sql, args);
            try {
                return ps.executeUpdate(); //sử dụng để thêm sửa xóa
            } finally {
                ps.getConnection().close();// đóng kết nối
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    // Dùng để truy vấn dữ liệu
    //ResultSet được trả về khi truy vấn dữ liệu
    public static ResultSet query(String sql, Object... args) throws SQLException {
        // thuc thi ps đã được truyền tham số ở hàm trên
        PreparedStatement ps = preparedStatement(sql, args);
        try {
            return ps.executeQuery(); //truy vấn
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
            
        }
    }

    // Truy vấn 1 giá trị
    public static Object value(String sql, Object... args) {
        try {
            ResultSet rs = query(sql, args); // thực thi query đc truyển tham số ở hàm trên
            if (rs.next()) { // đọc các bản ghi
                return rs.getObject(0); // trả về giá trị đầu tiên
            }
            rs.getStatement().getConnection().close(); // đóng kết nối
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

