package ru.kuchumov.pstuDatabases.databaseUtils.sqlUtils;

import java.util.LinkedList;

/*
 * Создает типовые sql
 * */
public interface TypicalSqlUtils {

    String getCreateTableQuery();

    String getDropTableQuery();

    String getTruncateTableQuery();
}
