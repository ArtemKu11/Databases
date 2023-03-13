package ru.kuchumov.pstuDatabases.tasks.secondTask.resolver;

import ru.kuchumov.pstuDatabases.tasks.TaskResolver;
import ru.kuchumov.pstuDatabases.tasks.secondTask.dao.SecondTaskDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SecondTaskResolver implements TaskResolver {
    private final SecondTaskDao dao;  // Взаимодействие с БД

    public SecondTaskResolver(SecondTaskDao dao) {
        this.dao = dao;
    }

    @Override
    public void resolveTask() {
        dao.tryToTruncateTable();
        dao.dropTable();
        dao.createTable();
        dao.fillTable();

        printResultSet(dao.executeFirstSelectQuery());
        printResultSet(dao.executeSecondSelectQuery());
        printResultSet(dao.executeThirdSelectQuery());

//        dao.tryToTruncateTable();
//        dao.dropTable();
    }

    private void printResultSet(ResultSet resultSet) {
        System.out.println("Результат:\n");
        try {
            int columnCount = resultSet.getMetaData().getColumnCount();
            int rowCounter = 0;
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + " ");
                }
                ++rowCounter;
                System.out.println();
            }
            System.out.println("Строк: " + rowCounter);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
