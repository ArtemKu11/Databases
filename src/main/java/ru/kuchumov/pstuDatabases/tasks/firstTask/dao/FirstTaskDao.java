package ru.kuchumov.pstuDatabases.tasks.firstTask.dao;

import jakarta.annotation.PostConstruct;
import ru.kuchumov.pstuDatabases.databaseUtils.dao.DataAccessObject;
import ru.kuchumov.pstuDatabases.databaseUtils.databaseResourceManager.DatabaseResourceManager;
import ru.kuchumov.pstuDatabases.databaseUtils.wrappers.StatementWrapper;
import ru.kuchumov.pstuDatabases.tasks.firstTask.sqlUtils.FirstTaskSqlUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FirstTaskDao implements DataAccessObject {
    DatabaseResourceManager databaseResourceManager;  // Для доступа и управлением к подключением к БД
    StatementWrapper mainStatement;  // Statement для выполнения всего
    FirstTaskSqlUtils sqlUtils;  // Делает sqlки

    public FirstTaskDao(DatabaseResourceManager databaseResourceManager, FirstTaskSqlUtils sqlUtils) {
        this.databaseResourceManager = databaseResourceManager;
        this.sqlUtils = sqlUtils;
    }

    @PostConstruct
    private void PostConstruct() {
        mainStatement = databaseResourceManager.getNewStatement();
    }

    @Override
    public void createTable() {
        sqlUtils.getLargeCreateTableQuery().forEach(mainStatement::addBatch);
        mainStatement.executeBatch();
    }

    @Override
    public void tryToTruncateTable() {
        throw new RuntimeException("TRUNCATE TABLE не предусмотрен");
    }

    @Override
    public void dropTable() {
        sqlUtils.getLargeDropTableQuery().forEach(mainStatement::addBatch);
        mainStatement.executeBatch();
    }

    public void fillTable() {
        sqlUtils.getLargeFillTableQuery().forEach(mainStatement::addBatch);
        mainStatement.executeBatch();
    }

    public ResultSet executeFirstSelect() {
        return mainStatement.executeQuery(sqlUtils.getFirstSelectQuery());
    }

}
