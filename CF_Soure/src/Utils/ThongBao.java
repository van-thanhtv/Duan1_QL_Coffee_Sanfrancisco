/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class ThongBao {
        public static  void alert(Component parent,String message){ // đưa ra thông báo
        JOptionPane.showMessageDialog(parent, message, "Cafe Sanfransisco", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static  boolean comfirm(Component parent,String message){ // lựa chọnYesorNO
       int result = JOptionPane.showConfirmDialog(parent, message, "Cafe Sanfransisco", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
     return result==JOptionPane.YES_NO_OPTION;
    }
    
    public static  void prompt(Component parent,String message){ //Nhập thông tin
        JOptionPane.showMessageDialog(parent, message, "Cafe Sanfransisco", JOptionPane.INFORMATION_MESSAGE);
    }
}
