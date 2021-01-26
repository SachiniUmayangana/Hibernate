package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.ItemDTO;

import java.util.List;

public interface ItemBO extends SuperBO {

    public boolean addItem(ItemDTO itemDTO)throws Exception;

    public boolean deleteItem(String id) throws Exception;

    public boolean updateItem(ItemDTO itemDTO) throws Exception;

    public String newItemId()throws Exception;

    public List<ItemDTO> findAll() throws Exception;

    ItemDTO findItem(String id) throws Exception;




}
