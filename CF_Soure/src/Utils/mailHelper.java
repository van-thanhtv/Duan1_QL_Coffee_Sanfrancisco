/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JTextField;

/**
 *
 * @author Tran Van Thanh
 */
public class mailHelper {

    private static String url = "data.txt";
    // Đọc dữ liệu từ File với BufferedReader
    private static FileInputStream fileInputStream = null;
    private static BufferedReader bufferedReader = null;
    private static String user = "";
    private static String pass = "";
    private static int randumCode;

    public static int sendcode(JTextField txt) {
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
//                System.out.println(line+":"+i);
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
            Random random = new Random();
            randumCode = random.nextInt(999999);//tạo 1 số ngẫu nhiên từ 0 đến 999999            
            String host = "smtp.gmail.com";
            String user = mailHelper.user;
            String pass = mailHelper.pass;
            String to = txt.getText();
            String subject = "Reseting Code";
            String message = "Your confirmation code is " + randumCode;
            boolean sessionDebug = false;
            //!.Tạo 1 dối tượng Properties
            Properties pros = new Properties();
            pros.put("mail.smtp.auth", "true");
            pros.put("mail.smtp.starttls.enable", "true");
            pros.put("mail.smtp.host", "smtp.gmail.com");//2.Chỉ ra máy chủ mail của gg
            pros.put("mail.smtp.port", 587);//3.Chỉ ra port : 587 Cổng vào ra dữ liệu
            pros.put("mail.smtp.starttls.required", "true");
            pros.put("mail.smtp.ssl.trust", "smtp.gmail.com");
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
//            Gán giá trị cho các thuộc tính đôi tượng msg
            msg.setFrom(new InternetAddress(user));//5.Từ địa chỉ Gmail nào gưởi đi
//            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));//Tù Gmail gưởi đến mai nào
            msg.setSubject(subject);//Tiêu đề thư
            msg.setText(message);//Nội dung thư
//            Transport.send(msg);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
            dialogHelper.alert(null, "Tài khoản gmail không hoạt động");
            return 0;
        }
        return randumCode;
    }
}
