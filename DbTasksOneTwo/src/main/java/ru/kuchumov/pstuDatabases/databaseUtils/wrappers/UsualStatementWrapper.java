package ru.kuchumov.pstuDatabases.databaseUtils.wrappers;

import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Setter
public class UsualStatementWrapper implements StatementWrapper {

    private Statement statement;


    @Override
    public ResultSet executeQuery(String sql) {
        try {
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            Exception exception = close();
            if (exception != null) {
                System.out.println(exception.getMessage());
                exception.printStackTrace();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean execute(String sql) {
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            Exception exception = close();
            if (exception != null) {
                System.out.println(exception.getMessage());
                exception.printStackTrace();
            }
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public int executeUpdate(String sql) {
        try {
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            Exception exception = close();
            if (exception != null) {
                System.out.println(exception.getMessage());
                exception.printStackTrace();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean executeWithException(String sql) throws SQLException {
        return statement.execute(sql);
    }

    @Override
    public void addBatch(String sql) {
        try {
            statement.addBatch(sql);
        } catch (SQLException e) {
            Exception exception = close();
            if (exception != null) {
                System.out.println(exception.getMessage());
                exception.printStackTrace();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public int[] executeBatch() {
        try {
            return statement.executeBatch();
        } catch (SQLException e) {
            Exception exception = close();
            if (exception != null) {
                System.out.println(exception.getMessage());
                exception.printStackTrace();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public Exception close() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                return e;
            }
        }
        return null;
    }
}
