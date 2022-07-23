/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import static java.awt.Color.pink;
import static java.awt.Color.white;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Tran Van Thanh
 */
public class utilityHelper {
    public static String getRank(double diem) {//Hỗ trợ sếp loại điểm
        String xepLoai = "Xuất sắc";
        if (diem < 0) {
            xepLoai = "Chưa nhập";
        } else if (diem < 3) {
            xepLoai = "Kém";
        } else if (diem < 5) {
            xepLoai = "Yếu";
        } else if (diem < 6.5) {
            xepLoai = "Trung bình";
        } else if (diem < 7.5) {
            xepLoai = "Khá";
        } else if (diem < 9) {
            xepLoai = "Giỏi";
        }
        return xepLoai;
    }

    public static boolean checkMaNV(JTextField txt) {// check mã nhân viên   1-10 kí tự a-z, A-Z, 0-9
        txt.setBackground(white);
        String id = txt.getText();
        String rgx = "[a-zA-Z0-9]{1,15}";
        if (id.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(pink);
            dialogHelper.alert(txt.getRootPane(), txt.getName() + " Phải có 1-15 kí tự\nchữ hoa, thường không dấu hoặc số.");
            return false;
        }
    }

    public static boolean checkMaNH(JTextField txt) {//Check mã người học đúng 7 kí tự a-z, A-Z, 0-9
        txt.setBackground(white);
        String id = txt.getText();
        String rgx = "[a-zA-Z0-9]{7}";
        if (id.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(pink);
            dialogHelper.alert(txt.getRootPane(), txt.getName() + " Phải có đúng 7 kí tự\nchữ thường, chữ hoa hoặc số");
            return false;
        }
    }

    public static boolean checkMaCD(JTextField txt) {//Check mã chuên đề     đúng 5 kí tự  a-z, A-Z, 0-9
        txt.setBackground(white);
        String id = txt.getText();
        String rgx = "[a-zA-Z0-9]{5}";
        if (id.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(pink);
            dialogHelper.alert(txt.getRootPane(), txt.getName() + " Phải có đúng 5 kí tự\nchữ thường, chữ hoa hoặc số");
            return false;
        }
    }

    public static boolean checkPass(JPasswordField txt) {//pass từ 3-16 kí tự
        txt.setBackground(white);
        if (txt.getPassword().length > 2 && txt.getPassword().length < 17) {
            return true;
        } else {
            txt.setBackground(pink);
            dialogHelper.alert(txt.getRootPane(), txt.getName() + " phải có từ 3-16 kí tự.");
            return false;
        }
    }

    public static boolean checkDate(JTextField txt) {//Check định dạng ngày
        txt.setBackground(white);
        String id = txt.getText();
        String rgx = "\\d{1,2}/\\d{1,2}/\\d{4}";
        if (id.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(pink);
            dialogHelper.alert(txt.getRootPane(), txt.getName() + " không đúng định dạng Date.");
            return false;
        }
    }

    //gồm các ký tự chữ đấu cách
    //từ 3-25 kí tự
    public static boolean checkName(JTextField txt) {
        txt.setBackground(white);
        String id = txt.getText();
        String rgx = "^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]{3,25}$";
        if (id.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(pink);
            dialogHelper.alert(txt.getRootPane(), txt.getName() + " phải là tên tiếng việt hoặc không đấu\ntừ 3-25 kí tự");
            return false;
        }
    }

    public static boolean checkTenCD(JTextField txt) {//Check Tên Chuyên Đề bất kì kí tự nào từ 3-50 kí tự
        txt.setBackground(white);
        String id = txt.getText();
        String rgx = ".{3,50}";
        if (id.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(pink);
            dialogHelper.alert(txt.getRootPane(), txt.getName() + " phải từ 3-50 kí tự.");
            return false;
        }
    }

    public static boolean checkMoTaCD(JTextArea txt) {//Check Mô tả chuyên đề từ 3-255 kí tự
        txt.setBackground(white);
        String id = txt.getText();
        String rgx = ".{3,255}";
        if (id.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(pink);
            dialogHelper.alert(txt.getRootPane(), txt.getName() + " phải từ 3-255 kí tự.");
            return false;
        }
    }

    public static boolean checkSDT(JTextField txt) {//Check SĐT Số 0 đứng đầu và 10 đến 11 số
        txt.setBackground(white);
        String id = txt.getText();
        String rgx = "0\\d{9,10}";
        if (id.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(pink);
            dialogHelper.alert(txt.getRootPane(), txt.getName() + " phải gồm 10 số hoặc 11 số\nđúng các đầu số của nhà mạng.");
            return false;
        }
    }

    public static boolean checkEmail(JTextField txt) {
        txt.setBackground(white);
        String id = txt.getText();
        String rgx = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";
        if (id.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(pink);
            dialogHelper.alert(txt.getRootPane(), txt.getName() + " không đúng định dạng Email");
            return false;
        }
    }

    public static boolean checkThoiLuong(JTextField txt) {    //Check thời lượng giờ là int >0
        txt.setBackground(white);
        try {
            int hour = Integer.parseInt(txt.getText());
            if (hour >= 0) {
                return true;
            } else {
                txt.setBackground(pink);
                dialogHelper.alert(txt.getRootPane(), txt.getName() + " phải lớn hơn bằng 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            txt.setBackground(pink);
            dialogHelper.alert(txt.getRootPane(), txt.getName() + " phải là số nguyên.");
            return false;
        }
    }

    public static boolean checkHocPhi(JTextField txt) {//Check học phí là float >0
        txt.setBackground(white);
        try {
            float hp = Float.parseFloat(txt.getText());
            if (hp >= 0) {
                return true;
            } else {
                txt.setBackground(pink);
                dialogHelper.alert(txt.getRootPane(), txt.getName() + " phải là lớn hơn bằng 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            txt.setBackground(pink);
            dialogHelper.alert(txt.getRootPane(), txt.getName() + " phải là số thực.");
            return false;
        }
    }

    public static boolean checkDiem(JTextField txt) {//Check điểm nằm trong khoảng 0-10 nếu là để trống thì là chưa nhập
        txt.setBackground(white);
        if (txt.getText().equals("")) {
            return true;
        } else {
            try {
                float hp = Float.parseFloat(txt.getText());
                if ((hp >= 0 && hp <= 10) || txt.getText().equals("")) {
                    return true;
                } else {
                    txt.setBackground(pink);
                    dialogHelper.alert(txt.getRootPane(), txt.getName() + " phải là trong khoảng 0-10 hoặc chưa nhập.");
                    return false;
                }
            } catch (NumberFormatException e) {
                txt.setBackground(pink);
                dialogHelper.alert(txt.getRootPane(), txt.getName() + " phải là số thực.");
                return false;
            }
        }
    }

    public static boolean checkNullText(JTextField txt) {
        txt.setBackground(white);
        if (txt.getText().trim().length() > 0) {
            return true;
        } else {
            txt.setBackground(pink);
            dialogHelper.alert(txt.getRootPane(), "Không được để trống " + txt.getName());
            return false;
        }
    }

    public static boolean checkNullText(JTextArea txt) {
        txt.setBackground(white);
        if (txt.getText().trim().length() > 0) {
            return true;
        } else {
            txt.setBackground(pink);
            dialogHelper.alert(txt.getRootPane(), "Không được để trống " + txt.getName());
            return false;
        }
    }

    public static boolean checkNullPass(JPasswordField txt) {
        txt.setBackground(white);
        if (txt.getPassword().length > 0) {
            return true;
        } else {
            txt.setBackground(pink);
            dialogHelper.alert(txt.getRootPane(), "Không được để trống Pass Ƹ̵̡Ӝ̵̨̄Ʒ☆" + txt.getName());
            return false;
        }
    }
}
