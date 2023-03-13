package ru.kuchumov.pstuDatabases.databaseUtils.wrappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Обертка над Statement
 * */
public interface StatementWrapper {
    void setStatement(Statement statement);
    ResultSet executeQuery(String sql);

    boolean execute(String sql);

    int executeUpdate(String sql);
    void addBatch(String sql);
    int[] executeBatch();

    boolean executeWithException(String sql) throws SQLException;

    Exception close();
}
