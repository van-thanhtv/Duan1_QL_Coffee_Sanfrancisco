package DAL_Models;
/*
* @author : ThongPro
* @since : 11/5/2021 11:09 PM
* @description :
* @update :
*
* */

public class ENTITY_Area {
    private int IDArea;
    private String AreaName;
    private int MaxTable;

    @Override
    public String toString() {
        return  AreaName;
    }

    
    public ENTITY_Area() {
    }

    public int getIDArea() {
        return IDArea;
    }

    public void setIDArea(int IDArea) {
        this.IDArea = IDArea;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public int getMaxTable() {
        return MaxTable;
    }

    public void setMaxTable(int maxTable) {
        MaxTable = maxTable;
    }
    
}
