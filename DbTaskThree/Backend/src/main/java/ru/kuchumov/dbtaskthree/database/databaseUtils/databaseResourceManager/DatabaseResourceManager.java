package ru.kuchumov.dbtaskthree.database.databaseUtils.databaseResourceManager;


import ru.kuchumov.dbtaskthree.database.databaseUtils.exception.ExceptionList;
import ru.kuchumov.dbtaskthree.database.databaseUtils.wrappers.StatementWrapper;
import ru.kuchumov.dbtaskthree.database.databaseUtils.wrappers.ConnectionWrapper;

/*
 * Для удобного управления соединением с БД
 * */
public interface DatabaseResourceManager {
    StatementWrapper getNewStatement();

    ConnectionWrapper getConnection();

    ExceptionList closeEverything();
}
