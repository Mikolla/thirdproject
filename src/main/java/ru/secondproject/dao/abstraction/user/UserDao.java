package ru.secondproject.dao.abstraction.user;



import ru.secondproject.model.User;

import java.util.List;

public interface UserDao {

    long saveUser(User user);

    User getUserById(long id);

    void editUser(User user);

    void deleteUser(long id);

    List<User> getAllUsers();

}
