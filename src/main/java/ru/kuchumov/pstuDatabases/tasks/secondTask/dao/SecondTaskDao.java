package ru.kuchumov.pstuDatabases.tasks.secondTask.dao;

import jakarta.annotation.PostConstruct;
import ru.kuchumov.pstuDatabases.databaseUtils.dao.DataAccessObject;
import ru.kuchumov.pstuDatabases.databaseUtils.databaseResourceManager.DatabaseResourceManager;
import ru.kuchumov.pstuDatabases.databaseUtils.wrappers.StatementWrapper;
import ru.kuchumov.pstuDatabases.tasks.secondTask.sqlUtils.SecondTaskSqlUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SecondTaskDao implements DataAccessObject {

    DatabaseResourceManager databaseResourceManager;  // Для доступа и управлением к подключением к БД
    StatementWrapper mainStatement;  // Statement для выполнения всего
    SecondTaskSqlUtils sqlUtils;  // Делает sqlки

    public SecondTaskDao(DatabaseResourceManager databaseResourceManager, SecondTaskSqlUtils sqlUtils) {
        this.databaseResourceManager = databaseResourceManager;
        this.sqlUtils = sqlUtils;
    }

    @PostConstruct
    private void PostConstruct() {
        mainStatement = databaseResourceManager.getNewStatement();
    }

    @Override
    public void createTable() {
        mainStatement.execute(sqlUtils.getCreateTableQuery());
    }

    @Override
    public void tryToTruncateTable() {
        String query = sqlUtils.getTruncateTableQuery();
        try {
            mainStatement.executeWithException(query);
        } catch (SQLException ignored) {
        }
    }

    @Override
    public void dropTable() {
        mainStatement.execute(sqlUtils.getDropTableQuery());
    }

    public void fillTable() {
        mainStatement.execute(sqlUtils.getFillTableQuarry());
    }

    public ResultSet executeFirstSelectQuery() {
        return mainStatement.executeQuery(sqlUtils.getFirstSelectQuery());
    }

    public ResultSet executeSecondSelectQuery() {
        return mainStatement.executeQuery(sqlUtils.getSecondSelectQuery());
    }

    public ResultSet executeThirdSelectQuery() {
        return mainStatement.executeQuery(sqlUtils.getThirdSelectQuery());
    }
}
