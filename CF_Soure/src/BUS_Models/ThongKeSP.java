/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_Models;

/**
 *
 * @author phamd
 */
public class ThongKeSP {
    private String ProductName;
    private int soLuong;
    private float gia;
    private boolean status;
    private String lyDo;
    private int discont;

    public int getDiscont() {
        return discont;
    }

    public void setDiscont(int discont) {
        this.discont = discont;
    }
    

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }
    

    public ThongKeSP() {
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    
}
