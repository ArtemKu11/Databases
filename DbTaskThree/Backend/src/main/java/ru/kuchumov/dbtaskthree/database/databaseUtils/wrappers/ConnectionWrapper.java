package ru.kuchumov.dbtaskthree.database.databaseUtils.wrappers;

/*
 * Обертка над Connection
 * */
public interface ConnectionWrapper {
    StatementWrapper createStatement();

    void setAutoCommit(boolean autoCommit);

    void commit();

    Exception close();
}
