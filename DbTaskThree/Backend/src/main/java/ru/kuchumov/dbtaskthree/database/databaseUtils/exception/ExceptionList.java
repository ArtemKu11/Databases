package ru.kuchumov.dbtaskthree.database.databaseUtils.exception;

/*
 * Для хранения возможных ошибок при закрытии n Statement'ов
 * */
public interface ExceptionList {
    void addException(Exception e);

    void printAll();

    void printAllAndThrowRuntimeException();

    boolean isEmpty();
}
