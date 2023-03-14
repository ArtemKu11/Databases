package ru.kuchumov.pstuDatabases.databaseUtils.databaseResourceManager;

import jakarta.annotation.PreDestroy;
import ru.kuchumov.pstuDatabases.databaseUtils.exception.ExceptionList;
import ru.kuchumov.pstuDatabases.databaseUtils.wrappers.ConnectionWrapper;
import ru.kuchumov.pstuDatabases.databaseUtils.wrappers.StatementWrapper;

import java.util.ArrayList;
import java.util.List;

public abstract class UsualResourceManager implements DatabaseResourceManager {
    private final ConnectionWrapper connection;
    private final List<StatementWrapper> statements;

    public UsualResourceManager(ConnectionWrapper connection) {
        statements = new ArrayList<>();
        this.connection = connection;
    }


    @Override
    public synchronized StatementWrapper getNewStatement() {
        StatementWrapper statement = connection.createStatement();
        statements.add(statement);
        return statement;
    }

    @Override
    public ConnectionWrapper getConnection() {
        return connection;
    }

    @PreDestroy
    @Override
    public ExceptionList closeEverything() {
        ExceptionList exceptionList = getExceptionList();

        for (StatementWrapper statement : statements) {
            Exception exception = statement.close();
            if (exception != null) {
                exceptionList.addException(exception);
            }
        }

        Exception exception = connection.close();
        if (exception != null) {
            exceptionList.addException(exception);
        }

        return exceptionList;
    }

    protected abstract ExceptionList getExceptionList();
}
