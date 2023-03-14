package ru.kuchumov.dbtaskthree.database.databaseUtils.sqlUtils;

public abstract class UsualPrintable implements Printable {
    @Override
    public void printQuery(StringBuilder query) {
        printQuery(query.toString());
    }

    @Override
    public void printQuery(String query) {
        System.out.println();
        System.out.println();
        System.out.println(query);
    }
}
