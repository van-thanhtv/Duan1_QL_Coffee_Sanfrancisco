/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import DAL_Models.ENTITY_ADMIN;
import DAL_Models.ENTITY_Employee;

/**
 *
 * @author phamd
 */
public class Auth {
        public static ENTITY_Employee user = null;
        public static ENTITY_ADMIN admin = null;
    public static void clear(){
        Auth.user=null;
        Auth.admin=null;
    }
    public static boolean isLogin(){
        return Auth.user!=null;        
    }
    public static boolean isAdmin(){
        return Auth.admin!=null;
    }
//    public static boolean isManager(){
//        return Auth.isLogin()&&user.isVaiTro();
//    }
}
