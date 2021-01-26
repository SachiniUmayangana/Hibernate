package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.ItemDAO;
import lk.ijse.hibernate.entity.Item;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public String getLastItemCode() {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        NativeQuery sqlQuery = session.createSQLQuery("select code from Item order by code desc limit 1");
        String id = (String) sqlQuery.uniqueResult();
        transaction.commit();

        session.close();
        return id;
    }

    @Override
    public boolean add(Item item) throws Exception {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(item);

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
    public boolean update(Item item) throws Exception {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(item);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public Item find(String id) throws Exception {

        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();
//

        Query query = session.createQuery("from Item where id = ?1");
        query.setParameter(1, "P001");
        Item item= (Item) query.uniqueResult();
        System.out.println(item);


//
        transaction.commit();

        session.close();

        return item;


    }

    @Override
    public List<Item> findAll() throws Exception {


        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Item ");

        List<Item> list=query.list();
        for (Item item: list
        ) {
            System.out.println(item);
        }

        transaction.commit();
        session.close();


        return list;
    }
}
