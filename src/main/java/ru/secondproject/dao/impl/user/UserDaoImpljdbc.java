package ru.secondproject.dao.impl.user;

import ru.secondproject.dao.abstraction.user.UserDao;
import ru.secondproject.dao.executor.Executor;
import ru.secondproject.dao.executor.ExecutorHelper;
import ru.secondproject.model.User;
import ru.secondproject.util.SingleDBHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpljdbc implements UserDao {
    private final Executor executor;
    private Connection connection = null;


    public UserDaoImpljdbc() {
        try {
            connection = SingleDBHelper.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        this.executor = new Executor(connection);
    }

    @Override
    public long saveUser(User user) {

        String query = String.format("insert into users (name, login, password) values ('%s', '%s', '%s')", user.getName(), user.getLogin(), user.getPassword());
        long id = executor.execUpdateWithKeys(query);
        return id;

    }

    @Override
    public User getUserById(long id) {
        String query = String.format("SELECT * FROM users where id='%s'", id);
        User user = executor.execQuery(query, new ExecutorHelper<User>() {
            @Override
            public User help(ResultSet resultSet) throws SQLException {
                resultSet.next();
                User user = new User(
                        resultSet.getLong(1),
                        resultSet.getString(3),
                        resultSet.getString(2),
                        resultSet.getString(4));
                return user;
            }
        });
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        String query = "SELECT * FROM users";
        List<User> users = executor.execQuery(query, new ExecutorHelper<List<User>>() {
            @Override
            public List<User> help(ResultSet resultSet) throws SQLException {
                List<User> userList = new ArrayList<>();
                while (resultSet.next()) {
                    User user = new User(
                            resultSet.getLong(1),
                            resultSet.getString(3),
                            resultSet.getString(2),
                            resultSet.getString(4));
                    userList.add(user);
                }
                return userList;
            }
        });
        return users;
    }

    @Override
    public void editUser(User user) {
        String query = String.format("update users set name='%s', login='%s', password='%s' where id='%s'",
                user.getName(), user.getLogin(), user.getPassword(), user.getId());
        executor.execUpdate(query);
    }

    @Override
    public void deleteUser(long id) {
        String query = String.format("delete from users where id='%s'", id);
        executor.execUpdate(query);
    }

}
