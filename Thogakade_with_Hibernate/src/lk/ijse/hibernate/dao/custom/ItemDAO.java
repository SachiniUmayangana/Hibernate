package lk.ijse.hibernate.dao.custom;


import lk.ijse.hibernate.dao.SuperDAO;
import lk.ijse.hibernate.entity.Item;


public interface ItemDAO extends SuperDAO<Item , String> {
    public String getLastItemCode()throws Exception;
}
