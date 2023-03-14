package ru.kuchumov.dbtaskthree.database.databaseUtils.dao;

public interface DataAccessObject {
    void createTable();
    void tryToTruncateTable();
    void dropTable();
}
