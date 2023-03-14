package ru.kuchumov.dbtaskthree.database.databaseUtils.sqlUtils;

/*
 * Создает типовые sql
 * */
public interface TypicalSqlUtils {

    String getCreateTableQuery();

    String getDropTableQuery();

    String getTruncateTableQuery();
}
