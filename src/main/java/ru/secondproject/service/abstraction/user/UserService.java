package ru.secondproject.service.abstraction.user;

import ru.secondproject.model.User;

import java.util.List;

public interface UserService {
    long saveUser(User user);

    User getUserById(long id);

    void editUser(User user);

    void deleteUser(long id);

    List<User> getAllUsers();
}
