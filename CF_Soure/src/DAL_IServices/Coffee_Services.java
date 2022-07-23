package DAL_IServices;

import java.util.ArrayList;

abstract public class Coffee_Services<E,K> {
    abstract public void insert(E entity); //Thêm
    abstract public void update(E entity); //Cập nhật
    abstract public void delete(K id); //Xóa
    abstract public ArrayList<E> selectAll();//Truy vấn danh sách đối tượng
    abstract public E selectByid(K id);//Truy vấn theo ID trả về đối tượng chứa thông tin bản ghi đó
    //truy vấn lấy về 1 tập ResultSet rồi trả ResultSet đó vào 1 List
    abstract protected ArrayList<E> selectBySql(String sql, Object... args);
}
