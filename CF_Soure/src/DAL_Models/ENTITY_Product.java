/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_Models;

/*
* @author : ThongPro
* @since : 11/5/2021 1:37 AM
* @description : 
* @update : 
*
* */
public class ENTITY_Product {
    private String IDProduct;
    private String ProductName;
    private float Price;
    private String Image;
    private int Status;
    private int IDType;

    public ENTITY_Product() {
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

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

 

    public int getIDType() {
        return IDType;
    }

    public void setIDType(int IDType) {
        this.IDType = IDType;
    }
}
