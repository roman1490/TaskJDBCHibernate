package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDaoHib = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userDaoHib.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoHib.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoHib.saveUser(name,lastName, age);
    }

    public void removeUserById(long id) {
        userDaoHib.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDaoHib.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoHib.cleanUsersTable();
    }
}
