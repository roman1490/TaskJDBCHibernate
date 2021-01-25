package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    SessionFactory sessionFactory = getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (\n" +
                "  id INT NOT NULL AUTO_INCREMENT,\n" +
                "  name VARCHAR(45) NOT NULL,\n" +
                "  last_name VARCHAR(45) NOT NULL,\n" +
                "  age INT NULL,\n" +
                "  PRIMARY KEY (id),\n" +
                "  UNIQUE INDEX id (id ASC) VISIBLE)";
        try (Session session = sessionFactory.openSession()) {

            Transaction tx = session.beginTransaction();
            Query updateQuery = session.createNativeQuery(sql);
            updateQuery.executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (Session session = sessionFactory.openSession()) {

            Transaction tx = session.beginTransaction();
            Query updateQuery = session.createNativeQuery(sql);
            updateQuery.executeUpdate();
            tx.commit();
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            User user = new User();

            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);

            session.save(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            User user = session.get(User.class, id);

            session.delete(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List userList;

        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            userList = session.createQuery("FROM User", User.class).getResultList();
            transaction.commit();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE users";
        try (Session session = sessionFactory.openSession()) {

            Transaction tx = session.beginTransaction();
            Query updateQuery = session.createNativeQuery(sql);
            updateQuery.executeUpdate();
            tx.commit();
            session.close();
        }
    }
}
