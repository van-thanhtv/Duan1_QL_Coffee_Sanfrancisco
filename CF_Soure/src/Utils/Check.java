/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import static java.awt.Color.pink;
import static java.awt.Color.white;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author phamd
 */
public class Check {

    public static boolean checkNullText(JTextField txt) {
        txt.setBackground(white);
        if (txt.getText().trim().length() > 0) {
            return true;
        } else {
            txt.setBackground(pink);
            ThongBao.alert(txt.getRootPane(), "Không được để trống ");
            return false;
        }
    }

    public static boolean checkCode(String txt) {
        // txt.setBackground(white);
        // String id = txt.getText;
        String rgx = "[0-9]{5,6}";
        if (txt.matches(rgx)) {
            return true;
        } else {
            // txt.setBackground(pink);
            ThongBao.alert(null, " phải là số");
            return false;
        }
    }

    public static boolean checkTable(String txt) {
        // txt.setBackground(white);
        // String id = txt.getText;
        String rgx = "[0-9]{1,2}";
        if (txt.matches(rgx)) {
            return true;
        } else {
            // txt.setBackground(pink);
            ThongBao.alert(null, " phải là số");
            return false;
        }
    }

    public static boolean checkPass(JPasswordField txt) {
        txt.setBackground(white);
        if (txt.getPassword().length > 2 && txt.getPassword().length < 17) {
            return true;
        } else {
            txt.setBackground(Color.RED);
            ThongBao.alert(txt.getRootPane(), txt.getName() + "Password phải có từ 3-16 kí tự.");
            return false;
        }
    }

    public static boolean checkNullPass(JPasswordField txt) {
        txt.setBackground(white);
        if (txt.getPassword().length > 0) {
            return true;
        } else {
            txt.setBackground(pink);
            ThongBao.alert(txt.getRootPane(), "Password không được để trống ");
            return false;
        }
    }

    public static boolean isValidDate(String inDate) {

        if (inDate == null) {
            return false;
        }

        //set the format to use as a constructor argument
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if (inDate.trim().length() != dateFormat.toPattern().length()) {
            return false;
        }

        dateFormat.setLenient(false);

        try {
            //parse the inDate parameter
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    //định dạng dd/MM/yyyy (hoặc d/M/yyyy nếu là số 0 đứng trước)
    public static boolean checkDate(String txt) {
        String rgx = "\\d{1,2}/\\d{1,2}/\\d{4}";
        if (txt.matches(rgx)) {
            return true;
        } else {
            dialogHelper.alert(null, " không đúng định dạng Date.");
            return false;
        }
    }

    public static boolean checkName(JTextField txt) {
        txt.setBackground(white);
        String id = txt.getText();
        String rgx = "^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]{3,25}$";
        if (id.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(pink);
            ThongBao.alert(txt.getRootPane(), "Tên phải là tên tiếng việt hoặc không đấu\ntừ 3-25 kí tự");
            return false;
        }
    }

    //gồm 10 số 
    //các đầu 3 số của nhà mạng
    public static boolean checkSDT(JTextField txt) {
        txt.setBackground(Color.WHITE);
        String id = txt.getText();
        String rgx = "(086|096|097|098|032|033|034|035|036|037|038|039|089|090|093|070|079|077|078|076|088|091|094|083|084|085|081|082|092|056|058|099|059)[0-9]{7}";
        if (id.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(pink);
            ThongBao.alert(txt.getRootPane(), "SDT phải gồm 10 số.");
            return false;
        }
    }

    public static boolean checkEmail(JTextField txt) {
        txt.setBackground(white);
        String id = txt.getText();
        String rgx = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";//Biểu Thúc chính quy;
        if (id.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(Color.RED);
            ThongBao.alert(txt.getRootPane(), "Email không đúng định dạng");
            return false;
        }
    }

    public static boolean checkso(String txt) {
        // txt.setBackground(white);
        // String id = txt.getText;
        String rgx = "\\d+";
        if (txt.matches(rgx)) {
            return true;
        } else {
            // txt.setBackground(pink);
            ThongBao.alert(null, " Số ngày gia hạn phải là số");
            return false;
        }
    }

    public static boolean checkso2(JTextField txt) {
        // txt.setBackground(white);
        // String id = txt.getText;
        String rgx = "\\d+";
        String id = txt.getText();
        if (id.matches(rgx)) {
            return true;
        } else {
            // txt.setBackground(pink);
            ThongBao.alert(null, "Chiết Khấu Phải Là Số");
            return false;
        }
    }

    public static boolean checkso3(JTextField txt) {
        // txt.setBackground(white);
        // String id = txt.getText;
        String rgx = "\\d+";
        String id = txt.getText();
        if (id.matches(rgx)) {
            return true;
        } else {
            // txt.setBackground(pink);
            ThongBao.alert(null, "Giá Phải Là Số");
            return false;
        }
    }
        public static boolean Checkso4(JTextField txt) {
        // txt.setBackground(white);
        // String id = txt.getText;
        if (Integer.parseInt(txt.getText())>0&&Integer.parseInt(txt.getText())<=100) {
            return true;
        } else {
            // txt.setBackground(pink);
            ThongBao.alert(null, "Chiết khấu phải >0 và <100");
            return false;
        }
    }
}
