/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL_Models;

/*
* @author : ThongPro
* @since : 11/5/2021 10:50 PM
* @description : 
* @update : 
*
* */

public class ENTITY_BILL {
    private String IDOrder;
    private String IDProduct;
    private String IDTable;
    private int Quantity;
    private String Note;
    private String Reason;
    private boolean Status;

    public ENTITY_BILL() {
    }

    public String getIDOrder() {
        return IDOrder;
    }

    public void setIDOrder(String IDOrder) {
        this.IDOrder = IDOrder;
    }

    public String getIDProduct() {
        return IDProduct;
    }

    public void setIDProduct(String IDProduct) {
        this.IDProduct = IDProduct;
    }

    public String getIDTable() {
        return IDTable;
    }

    public void setIDTable(String IDTable) {
        this.IDTable = IDTable;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }
}
