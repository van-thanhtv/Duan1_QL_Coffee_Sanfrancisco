/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_Models;

/**
 *
 * @author Tran Van Thanh
 */
public class SPChiTiet {
    private String IDProduct,ProductName;
    private double Price;
    private String Image;
    private boolean Status;
    private String TypeName;
    private String Size;

    public SPChiTiet() {
    }

    public SPChiTiet(String IDProduct, String ProductName, double Price, String Image, boolean Status, String TypeName, String Size) {
        this.IDProduct = IDProduct;
        this.ProductName = ProductName;
        this.Price = Price;
        this.Image = Image;
        this.Status = Status;
        this.TypeName = TypeName;
        this.Size = Size;
    }

    public String getIDProduct() {
        return IDProduct;
    }

    public void setIDProduct(String IDProduct) {
        this.IDProduct = IDProduct;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String TypeName) {
        this.TypeName = TypeName;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String Size) {
        this.Size = Size;
    }
    
    
}
