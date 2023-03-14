package ru.kuchumov.pstuDatabases.tasks.firstTask.resolver;

import ru.kuchumov.pstuDatabases.tasks.TaskResolver;
import ru.kuchumov.pstuDatabases.tasks.firstTask.dao.FirstTaskDao;
import ru.kuchumov.pstuDatabases.tasks.secondTask.dao.SecondTaskDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FirstTaskResolver implements TaskResolver {
    private final FirstTaskDao dao;  // Взаимодействие с БД

    public FirstTaskResolver(FirstTaskDao dao) {
        this.dao = dao;
    }

    @Override
    public void resolveTask() {
        dao.dropTable();
        dao.createTable();
        dao.fillTable();

        printResultSet(dao.executeFirstSelect());
//        dao.dropTable();


//        printResultSet(dao.executeFirstSelectQuery());
//        printResultSet(dao.executeSecondSelectQuery());
//        printResultSet(dao.executeThirdSelectQuery());


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
