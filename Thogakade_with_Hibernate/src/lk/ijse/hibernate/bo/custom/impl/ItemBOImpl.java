package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.ItemBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.DAOType;
import lk.ijse.hibernate.dao.custom.impl.ItemDAOImpl;
import lk.ijse.hibernate.dto.ItemDTO;
import lk.ijse.hibernate.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemDAOImpl itemDAO= DAOFactory.getInstance().getDAO(DAOType.ITEM);


    @Override
    public boolean addItem(ItemDTO item) throws Exception {
        return itemDAO.add(new Item(item.getCode(),
                item.getDescription(),
                item.getUnitPrice(),
                item.getQtyOnHand()));
    }

    @Override
    public boolean deleteItem(String id) throws Exception {
        return itemDAO.delete(id);
    }

    @Override
    public boolean updateItem(ItemDTO item) throws Exception {
        return itemDAO.update(new Item(item.getCode(),
                item.getDescription(),
                item.getUnitPrice(),
                item.getQtyOnHand()));
    }

    @Override
    public String newItemId() throws Exception {
        String lastID = itemDAO.getLastItemCode();



        int newID = Integer.parseInt(lastID.substring(1, 4)) + 1;

        if(newID < 10){
            return "P00"+newID;
        }else if(newID < 100){
            return "P0"+newID;
        }else{
            return "P"+newID;
        }
    }

    @Override
    public List<ItemDTO> findAll() throws Exception {
        ArrayList<Item> all = (ArrayList<Item>) itemDAO.findAll();
        ArrayList<ItemDTO> allCustomers = new ArrayList<>();
        for (Item c : all) {
            allCustomers.add(new ItemDTO(c.getCode(), c.getDescription(), c.getUnitPrice(), c.getQtyOnHand()));

        }
        return allCustomers;
    }

    @Override
    public ItemDTO findItem(String id) throws Exception {
        Item find=itemDAO.find(id);
        return new ItemDTO(find.getCode(),find.getDescription(),find.getUnitPrice(),find.getQtyOnHand());

    }
}
