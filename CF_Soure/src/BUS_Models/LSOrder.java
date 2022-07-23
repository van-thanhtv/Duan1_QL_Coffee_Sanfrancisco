/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_Models;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Tran Van Thanh
 */
public class LSOrder {
    private String IDOrder;
    private java.sql.Time TimeOder;
    private String NameEMP =null;
    private String CusName;
    private int Status;

    public LSOrder(String IDOrder, Date TimeOder, String NameEMP, String CusName, int Status) {
        this.IDOrder = IDOrder;
        this.TimeOder = (Time) TimeOder;
        this.NameEMP = NameEMP;
        this.CusName = CusName;
        this.Status = Status;
    }

    public LSOrder() {
    }

    public String getIDOrder() {
        return IDOrder;
    }

    public void setIDOrder(String IDOrder) {
        this.IDOrder = IDOrder;
    }

    public Time getTimeOder() {
        return TimeOder;
    }

    public void setTimeOder(Date TimeOder) {
        this.TimeOder = (Time) TimeOder;
    }

    public String getNameEMP() {
        return NameEMP;
    }

    public void setNameEMP(String NameEMP) {
        this.NameEMP = NameEMP;
    }

    public String getCusName() {
        return CusName;
    }

    public void setCusName(String CusName) {
        this.CusName = CusName;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }
    
    
}
