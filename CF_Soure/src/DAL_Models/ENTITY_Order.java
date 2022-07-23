/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_Models;

import Utils.Auth;
import Utils.dateHelper;
import java.util.Date;

/*
* @author : ThongPro
* @since : 11/5/2021 1:37 AM
* @description : 
* @update : 
*
* */
public class ENTITY_Order {
    private String IDOrder;
    private Date DateOrder = dateHelper.now();
    private Date TimeOrder = dateHelper.timeNow();
    private String UsernameEMP = Auth.user==null ? null : Auth.user.getUsernameEMP();
    private String IDCust = null;
    private String NamePromo = null;
    private int Status = 1;
    private int DiscountOrder = 0;

    public int getDiscountOrder() {
        return DiscountOrder;
    }

    public void setDiscountOrder(int DiscountOrder) {
        this.DiscountOrder = DiscountOrder;
    }

    public ENTITY_Order() {
    }

    public String getIDOrder() {
        return IDOrder;
    }

    public String getNamePromo() {
        return NamePromo;
    }

    public void setNamePromo(String NamePromo) {
        this.NamePromo = NamePromo;
    }

    public void setIDOrder(String IDOrder) {
        this.IDOrder = IDOrder;
    }

    public Date getDateOrder() {
        return DateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        DateOrder = dateOrder;
    }

    public Date getTimeOrder() {
        return TimeOrder;
    }

    public void setTimeOrder(Date timeOrder) {
        TimeOrder = timeOrder;
    }

    public String getUsernameEMP() {
        return UsernameEMP;
    }

    public void setUsernameEMP(String usernameEMP) {
        UsernameEMP = usernameEMP;
    }

    public String getIDCust() {
        return IDCust;
    }

    public void setIDCust(String IDCust) {
        this.IDCust = IDCust;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
