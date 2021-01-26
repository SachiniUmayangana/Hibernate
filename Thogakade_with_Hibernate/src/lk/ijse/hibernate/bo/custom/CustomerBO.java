package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.CustomerDTO;

import java.util.List;


public interface CustomerBO extends SuperBO {
    public boolean addCustomer(CustomerDTO customer)throws Exception;

    public boolean updateCustomer(CustomerDTO customer)throws Exception;


    public boolean deleteCustomer(String id) throws Exception;

    public String newCustomerID()throws Exception;

    public List<CustomerDTO> findAll() throws Exception;

    CustomerDTO findCustomer(String id) throws Exception;



}