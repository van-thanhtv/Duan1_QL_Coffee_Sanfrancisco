/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_Models;

import javax.swing.JButton;

/**
 *
 * @author Tran Van Thanh
 */
public class BanButtons {

    private JButton btnBan;
    private String IDTalbe;
    private int Status;
    private int ten;
    private String TableGroup = null;

    public BanButtons() {
    }

    public BanButtons(JButton btnBan, String IDTalbe, int Status, int ten,String TableGroup) {
        this.btnBan = btnBan;
        this.IDTalbe = IDTalbe;
        this.Status = Status;
        this.ten = ten;
        this.TableGroup = TableGroup;
    }

    public String getTableGroup() {
        return TableGroup;
    }

    public void setBtnBan(JButton btnBan) {
        this.btnBan = btnBan;
    }

    public void setIDTalbe(String IDTalbe) {
        this.IDTalbe = IDTalbe;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public void setTen(int ten) {
        this.ten = ten;
    }

    public void setTableGroup(String TableGroup) {
        this.TableGroup = TableGroup;
    }
    
    public JButton getBtnBan() {
        return btnBan;
    }

    public String getIDTalbe() {
        return IDTalbe;
    }

    public int getStatus() {
        return Status;
    }

    public int getTen() {
        return ten;
    }


}
