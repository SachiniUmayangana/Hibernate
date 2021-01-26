package lk.ijse.hibernate.dao.custom.impl;


import lk.ijse.hibernate.dao.custom.CustomerDAO;
import lk.ijse.hibernate.entity.Customer;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(Customer customer) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();
//

        session.save(customer);

        transaction.commit();

        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        session.delete(id);

        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public boolean update(Customer customer) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(customer);

        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public Customer find(String s) throws Exception {

        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();
//

        Query query = session.createQuery("from Customer where id = ?1");
        query.setParameter(1, "C005");
        Customer customer= (Customer) query.uniqueResult();
        System.out.println(customer);


//
        transaction.commit();

        session.close();

        return customer;
    }

    @Override
    public List<Customer> findAll() throws Exception {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Customer ");

        List<Customer> list=query.list();
        for (Customer customer: list
             ) {
            System.out.println(customer);
        }

        transaction.commit();
        session.close();
        return list;


}


    @Override
    public String getLastCustomerID() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        NativeQuery sqlQuery = session.createSQLQuery("select id from Customer order by id desc limit 1");
        String id = (String) sqlQuery.uniqueResult();
        transaction.commit();

        session.close();
        return id;
    }
}