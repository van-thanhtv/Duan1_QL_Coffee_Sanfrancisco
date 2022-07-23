/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_Models;

import java.util.Date;

/*
 * @author : ThongPro
 * @since : 11/5/2021 11:00 PM
 * @description :
 * @update :
 *
 * */
public class ENTITY_Customer {
    private String IDCust;
    private String CusName;
    private Date DateAdd;
    private Date DateEnd;
    private String Phone;
    private String Email;
    private int Discount;
    private int Status;
    private String CCCD;

    public ENTITY_Customer() {
    }

    public String getIDCust() {
        return IDCust;
    }

    public void setIDCust(String IDCust) {
        this.IDCust = IDCust;
    }

    public String getCusName() {
        return CusName;
    }

    public void setCusName(String cusName) {
        CusName = cusName;
    }

    public Date getDateAdd() {
        return DateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        DateAdd = dateAdd;
    }

    public Date getDateEnd() {
        return DateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        DateEnd = dateEnd;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getDiscount() {
        return Discount;
    }

    public void setDiscount(int discount) {
        Discount = discount;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

 

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }
    

}