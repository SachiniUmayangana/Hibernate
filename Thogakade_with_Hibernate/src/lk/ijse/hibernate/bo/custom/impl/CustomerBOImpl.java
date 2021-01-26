package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.CustomerBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.DAOType;
import lk.ijse.hibernate.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.hibernate.dto.CustomerDTO;
import lk.ijse.hibernate.entity.Customer;

import java.util.ArrayList;
import java.util.List;


public class CustomerBOImpl implements CustomerBO {

    CustomerDAOImpl customerDAO = DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);

    @Override
    public boolean addCustomer(CustomerDTO customer) throws Exception {
        return customerDAO.add(new Customer(customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getSalary())
        );
    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return customerDAO.delete(id);
    }

    @Override
    public String newCustomerID() throws Exception {

        String lastID = customerDAO.getLastCustomerID();



        int newID = Integer.parseInt(lastID.substring(1, 4)) + 1;

        if(newID < 10){
            return "C00"+newID;
        }else if(newID < 100){
            return "C0"+newID;
        }else{
            return "C"+newID;
        }

    }

    @Override
    public List<CustomerDTO> findAll() throws Exception {

            ArrayList<Customer> all = (ArrayList<Customer>) customerDAO.findAll();
            ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
            for (Customer c : all) {
                allCustomers.add(new CustomerDTO(c.getId(), c.getName(), c.getAddress(), c.getSalary()));

            }
            return allCustomers;
    }



    @Override
    public CustomerDTO findCustomer(String id) throws Exception {

        Customer find=customerDAO.find(id);
        return new CustomerDTO(find.getId(),find.getName(),find.getAddress(),find.getSalary());

    }

    public boolean updateCustomer(CustomerDTO customer) throws Exception {
        return customerDAO.update(new Customer(customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getSalary()));
    }
}