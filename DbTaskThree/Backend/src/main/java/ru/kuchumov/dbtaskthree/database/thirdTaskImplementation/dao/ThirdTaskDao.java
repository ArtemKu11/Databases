package ru.kuchumov.dbtaskthree.database.thirdTaskImplementation.dao;

import jakarta.annotation.PostConstruct;
import ru.kuchumov.dbtaskthree.database.thirdTaskImplementation.sqlUtils.ThirdTaskSqlUtils;
import ru.kuchumov.dbtaskthree.database.databaseUtils.dao.DataAccessObject;
import ru.kuchumov.dbtaskthree.database.databaseUtils.databaseResourceManager.DatabaseResourceManager;
import ru.kuchumov.dbtaskthree.database.databaseUtils.wrappers.StatementWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThirdTaskDao {

    DatabaseResourceManager databaseResourceManager;  // Для доступа и управлением к подключением к БД
    StatementWrapper mainStatement;  // Statement для выполнения всего
    ThirdTaskSqlUtils sqlUtils;  // Делает sqlки

    public ThirdTaskDao(DatabaseResourceManager databaseResourceManager, ThirdTaskSqlUtils sqlUtils) {
        this.databaseResourceManager = databaseResourceManager;
        this.sqlUtils = sqlUtils;
    }

    @PostConstruct
    private void PostConstruct() {
        mainStatement = databaseResourceManager.getNewStatement();
    }


    public List<String> executeNotSelectQuery(String query) {
        List<String> resultLines = new ArrayList<>();
        try {
            mainStatement.executeWithException(query);
            resultLines.add("Успешно!");
            return resultLines;
        } catch (SQLException e) {
            resultLines.add("Фатальная ошибка!");
            resultLines.add("Текст ошибки:");
            resultLines.add(e.getMessage());
            return resultLines;
        }
    }

    public List<String> executeSelectQuery(String query) {
        List<String> resultLines = new ArrayList<>();
        try {
            ResultSet resultSet = mainStatement.executeQueryWithException(query);
            addColumnNames(resultSet, resultLines);
            addColumnContent(resultSet, resultLines);
            return resultLines;
        } catch (SQLException e) {
            resultLines.add("Фатальная ошибка!");
            resultLines.add("Текст ошибки:");
            resultLines.add(e.getMessage());
            return resultLines;
        }
    }

    private void addColumnNames(ResultSet resultSet, List<String> resultLines) throws SQLException {
        int columnCount = resultSet.getMetaData().getColumnCount();

        StringBuilder line = new StringBuilder();  // Добавить названия колонок
        for (int i = 1; i <= columnCount; i++) {
            line.append(resultSet.getMetaData().getColumnName(i)).append(" ");
        }

        line = new StringBuilder(line.substring(0, line.length() - 1));
        resultLines.add(line.toString());
    }

    private void addColumnContent(ResultSet resultSet, List<String> resultLines) throws SQLException {
        int columnCount = resultSet.getMetaData().getColumnCount();

        while (resultSet.next()) {  // Добавить содержимое колонок
            StringBuilder line = new StringBuilder();
            for (int i = 1; i <= columnCount; i++) {
                line.append(resultSet.getString(i)).append(" ");
            }
            line = new StringBuilder(line.substring(0, line.length() - 1));
            resultLines.add(line.toString());
        }
    }
}
