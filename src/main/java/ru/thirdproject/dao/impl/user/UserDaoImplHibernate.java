package ru.thirdproject.dao.impl.user;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;
import ru.thirdproject.dao.abstraction.user.UserDao;
import ru.thirdproject.model.User;
import ru.thirdproject.util.SingleDBHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 */
public class UserDaoImplHibernate implements UserDao {
    private final SessionFactory sessionFactory;

    public UserDaoImplHibernate() {

        Configuration configuration = SingleDBHelper.getInstance().getConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    public long saveUser(User user) {
        Session session = sessionFactory.openSession();
        long id = (Long) session.save(new User(user.getName(), user.getLogin(), user.getPassword(), user.getRole()));
        session.close();
        return id;
    }

    public void editUser(User user) {
        Session session = sessionFactory.openSession();
        String hqledit = "UPDATE User set name = :paramName, login = :paramLogin, password = :paramPassword, role = :paramRole where id = :paramId";

        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(hqledit);
            query.setParameter("paramName", user.getName());
            query.setParameter("paramLogin", user.getLogin());
            query.setParameter("paramPassword", user.getPassword());
            query.setParameter("paramRole", user.getRole());
            query.setParameter("paramId", user.getId());
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        session.close();
    }

    public void deleteUser(long id)  {
        Session session = sessionFactory.openSession();
        String hqldel = "DELETE FROM User WHERE id = :paramId";
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(hqldel);
            query.setParameter("paramId", id);
            query.executeUpdate();
            transaction.commit();
        }  catch (Exception e) {
            transaction.rollback();
        }
        session.close();
    }

    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        List<User> users = null;
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("FROM User");
            users = query.list();
            transaction.commit();

        }  catch (Exception e) {
            transaction.rollback();
        }        session.close();
        return users;
    }

    @Override
    public User getUserByLogin(String login) {
        Session session = sessionFactory.openSession();
        User user = null;
        Transaction transaction = session.beginTransaction();
        String jpql = "SELECT u FROM User u WHERE u.login = :paramLogin";
        try {
        Query query = session.createQuery(jpql);
        query.setParameter("paramLogin", login);
        user = (User) query.uniqueResult();
            transaction.commit();

        }  catch (Exception e) {
            transaction.rollback();
        }        session.close();
        return user;
    }

    public User getUserById(long id) {
            Session session = sessionFactory.openSession();
            User user = (User) session.get(User.class, id);
            session.close();
            return user;
    }



    public void printConnectInfo() {
        try {
            SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) sessionFactory;
            Connection connection = sessionFactoryImpl.getConnectionProvider().getConnection();
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
