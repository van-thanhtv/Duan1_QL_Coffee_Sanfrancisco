package DAL_Models;
/*
* @author : ThongPro
* @since : 11/5/2021 11:09 PM
* @description : 
* @update : 
*
* */

public class ENTITY_ProductType {
    private int IDType;
    private String TypeName;
    private String Size;
    private boolean Status;

    public ENTITY_ProductType() {
    }

    public int getIDType() {
        return IDType;
    }

    public void setIDType(int IDType) {
        this.IDType = IDType;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    @Override
    public String toString() {
        return TypeName;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }



}
