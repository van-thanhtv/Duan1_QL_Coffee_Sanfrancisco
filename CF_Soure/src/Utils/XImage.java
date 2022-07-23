/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author PC
 */
public class XImage {
    public static final Image APP_ICON;
    static {
        // Tải biểu tượng ứng dụng 
        //CÁCH TẢI ẢNH TỪ TRONG PROJECT
        //icon là thư mục con của src
        //Logo cho ứng dụng
        String file = "/ICON/logo1.png";      //icon là thư mục con của src
        APP_ICON = new ImageIcon(XImage.class.getResource(file)).getImage();        
    }
    public static Image getAppIcon(){ //Lấy ảnh làm icon 
        URL url =  XImage.class.getResource("File anh o day....");
       return new ImageIcon(url).getImage();
    }
    public static void save(File src){ 
        File dst = new File("logos",src.getName());
        if (!dst.getParentFile().exists()) {
            dst.getParentFile().mkdirs(); // tạo thư mục logo nếu chưa tồn tại
        }
        try {
            Path from = Paths.get(src.getAbsolutePath()); //lay duong dan
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from,to,StandardCopyOption.REPLACE_EXISTING); // copy file vào thư mục logo
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static ImageIcon read(String fileName){
        File path = new File("logos",fileName);
        return new ImageIcon(new ImageIcon(path.getAbsolutePath()).getImage().getScaledInstance(280, 270,Image.SCALE_DEFAULT));
    }
    public static ImageIcon read1(String fileName){
        File path = new File("logos",fileName);
        return new ImageIcon(new ImageIcon(path.getAbsolutePath()).getImage().getScaledInstance(80, 80,Image.SCALE_DEFAULT));
    }
}
