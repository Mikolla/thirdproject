package ru.secondproject.dao.executor;

import java.sql.*;

public class Executor {
    private  final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public long execUpdateWithKeys(String update) {
        long id = 0;
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            id = resultSet.getLong(1);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    public void execUpdate(String update) {
        try {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.execute(update);
            statement.close();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T execQuery(String query, ExecutorHelper<T> helper) {
        T result = null;

    try {
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        result = helper.help(resultSet);
        resultSet.close();
        statement.close();


        connection.commit();

    } catch (SQLException e) {
        e.printStackTrace();
        try {
            connection.rollback();
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {

            try {
                connection.setAutoCommit(true);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
    return result;
}

}
