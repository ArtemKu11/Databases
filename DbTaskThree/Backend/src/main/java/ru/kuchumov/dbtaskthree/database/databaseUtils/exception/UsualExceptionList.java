package ru.kuchumov.dbtaskthree.database.databaseUtils.exception;

import java.util.LinkedList;

public class UsualExceptionList implements ExceptionList {

    private final LinkedList<Exception> exceptions = new LinkedList<>();

    @Override
    public void addException(Exception e) {
        exceptions.add(e);
    }

    @Override
    public void printAll() {
        exceptions.forEach(e -> {
            System.out.println(e.getMessage());
            e.printStackTrace();
        });
    }

    @Override
    public void printAllAndThrowRuntimeException() {
        printAll();
        throw new RuntimeException();
    }

    @Override
    public boolean isEmpty() {
        return exceptions.isEmpty();
    }
}
