package ru.thirdproject.service.abstraction.user;

import ru.thirdproject.model.User;

import java.util.List;

public interface UserService {
    long saveUser(User user);

    User getUserById(long id);

    void editUser(User user);

    void deleteUser(long id);

    List<User> getAllUsers();
}
