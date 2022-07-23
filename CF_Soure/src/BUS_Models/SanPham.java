/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS_Models;

/**
 *
 * @author PC
 */
public class SanPham {
    private int IDType;
    private String IDProduct,ProductName;
    private float Price;
    private String Image;
    private int Status;
    private String TypeName;
    private String Size;

    public SanPham() {
    }

    public SanPham(String IDProduct, String ProductName, float Price, String Image, int Status, String TypeName, String Size) {
        this.IDProduct = IDProduct;
        this.ProductName = ProductName;
        this.Price = Price;
        this.Image = Image;
        this.Status = Status;
        this.TypeName = TypeName;
        this.Size = Size;
    }

    public int getIDType() {
        return IDType;
    }

    public void setIDType(int IDType) {
        this.IDType = IDType;
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

    public void setPrice(float Price) {
        this.Price = Price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
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

    @Override
    public String toString() {
        return Size;
    }
}
