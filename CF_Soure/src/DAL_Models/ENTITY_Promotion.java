/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_Models;

import java.util.Date;

/**
 *
 * @author PC
 */
public class ENTITY_Promotion {
    private int IDPro;
    private String name;
    private int DiscountPromo;
    private Date StartPromo;
    private Date EndPromo;
    private String Description;
    private int Status;
    
    public ENTITY_Promotion() {
    }

    public int getIDPro() {
        return IDPro;
    }

    public void setIDPro(int IDPro) {
        this.IDPro = IDPro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscountPromo() {
        return DiscountPromo;
    }

    public void setDiscountPromo(int DiscountPromo) {
        this.DiscountPromo = DiscountPromo;
    }

    public Date getStartPromo() {
        return StartPromo;
    }

    public void setStartPromo(Date StartPromo) {
        this.StartPromo = StartPromo;
    }

    public Date getEndPromo() {
        return EndPromo;
    }

    public void setEndPromo(Date EndPromo) {
        this.EndPromo = EndPromo;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }


    
    
}
