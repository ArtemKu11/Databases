package ru.kuchumov.pstuDatabases.databaseUtils.wrappers;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class UsualConnectionWrapper implements ConnectionWrapper {
    private Connection connection;
    @Value("${custom-properties.database.url}")
    private String url;
    @Value("${custom-properties.database.username}")
    private String username;
    @Value("${custom-properties.database.password}")
    private String password;

    @PostConstruct
    private void postConstruct() {
        try {
            connection = DriverManager.getConnection(url, username, password);
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
    public StatementWrapper createStatement() {
        try {
            StatementWrapper statementWrapper = getStatementWrapper();
            statementWrapper.setStatement(connection.createStatement());
            return statementWrapper;
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
    public void setAutoCommit(boolean autoCommit) {
        try {
            connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            Exception exception = close();
            if (exception != null) {
                System.out.println(exception.getMessage());
                exception.printStackTrace();
            }
            throw new RuntimeException(e);
        }
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            Exception exception = close();
            if (exception != null) {
                System.out.println(exception.getMessage());
                exception.printStackTrace();
            }
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    @Override
    public Exception close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                return e;
            }
        }
        return null;
    }

    protected abstract StatementWrapper getStatementWrapper();
}
