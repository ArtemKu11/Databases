package ru.kuchumov.pstuDatabases.databaseUtils.databaseResourceManager;

import ru.kuchumov.pstuDatabases.databaseUtils.exception.ExceptionList;
import ru.kuchumov.pstuDatabases.databaseUtils.wrappers.ConnectionWrapper;
import ru.kuchumov.pstuDatabases.databaseUtils.wrappers.StatementWrapper;

/*
 * Для удобного управления соединением с БД
 * */
public interface DatabaseResourceManager {
    StatementWrapper getNewStatement();

    ConnectionWrapper getConnection();

    ExceptionList closeEverything();
}
