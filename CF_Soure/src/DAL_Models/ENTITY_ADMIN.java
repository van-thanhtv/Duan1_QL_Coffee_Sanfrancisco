/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_Models;

/*
* @author : ThongPro
* @since : 11/5/2021 11:14 PM
* @description : 
* @update : 
*
* */
public class ENTITY_ADMIN {
private String Username;
private String Password;
private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public ENTITY_ADMIN() {
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
