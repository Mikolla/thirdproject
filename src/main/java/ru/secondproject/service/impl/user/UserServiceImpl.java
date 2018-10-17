package ru.secondproject.service.impl.user;

import ru.secondproject.dao.abstraction.user.UserDao;
import ru.secondproject.dao.impl.user.DaoFactory;
import ru.secondproject.model.User;
import ru.secondproject.service.abstraction.user.UserService;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl() {

        this.userDao = new DaoFactory().makeDao();
    }

    @Override
    public long saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public User getUserById(long id) {
       return userDao.getUserById(id);
    }

    @Override
    public void editUser(User user) {
        userDao.editUser(user);
    }

    @Override
    public void deleteUser(long id) {
         userDao.deleteUser(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
