/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_Models;

import java.util.Date;

/*
* @author : ThongPro
* @since : 11/5/2021 1:37 AM
* @description : 
* @update : 
*
* */
public class ENTITY_Employee {
    private String UsernameEMP;
    private String Password;
    private String NameEMP;
    private String Phone;
    private Date Birthday;
    private String Address;
    private Boolean Sex;
    private String Email;
    private String Image;
    private Boolean Status;

    public ENTITY_Employee() {
    }

    public String getUsernameEMP() {
        return UsernameEMP;
    }

    public void setUsernameEMP(String usernameEMP) {
        UsernameEMP = usernameEMP;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNameEMP() {
        return NameEMP;
    }

    public void setNameEMP(String nameEMP) {
        NameEMP = nameEMP;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Boolean getSex() {
        return Sex;
    }

    public void setSex(Boolean sex) {
        Sex = sex;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }
}
