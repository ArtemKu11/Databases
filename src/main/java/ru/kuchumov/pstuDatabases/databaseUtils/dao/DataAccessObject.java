package ru.kuchumov.pstuDatabases.databaseUtils.dao;

public interface DataAccessObject {
    void createTable();
    void tryToTruncateTable();
    void dropTable();
}
