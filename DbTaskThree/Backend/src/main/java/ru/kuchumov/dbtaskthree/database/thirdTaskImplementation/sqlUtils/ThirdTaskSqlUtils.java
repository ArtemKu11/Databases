package ru.kuchumov.dbtaskthree.database.thirdTaskImplementation.sqlUtils;


import ru.kuchumov.dbtaskthree.database.databaseUtils.sqlUtils.TypicalSqlUtils;
import ru.kuchumov.dbtaskthree.database.databaseUtils.sqlUtils.UsualPrintable;

import java.util.List;

public class ThirdTaskSqlUtils extends UsualPrintable implements TypicalSqlUtils {
    @Override
    public String getCreateTableQuery() {
        String query = """
                CREATE TABLE IF NOT EXISTS Table_Kuchumov
                (id BIGINT PRIMARY KEY,
                ФИО VARCHAR(255),
                Город VARCHAR(255),
                Улица VARCHAR(255),
                Дом INT,
                Квартира INT,
                Стаж INT,
                ЗП INT,
                Номер_телефона VARCHAR(255))""";
        printQuery(query);
        return query;
    }

    @Override
    public String getDropTableQuery() {
        String query =  "DROP TABLE IF EXISTS Table_Kuchumov";
        printQuery(query);
        return query;
    }

    @Override
    public String getTruncateTableQuery() {
        String query = "TRUNCATE TABLE Table_Kuchumov";
        printQuery(query);
        return query;
    }

    public String getFillTableQuarry() {
        List<String> names = List.of("Кучумов А.М.", "Иванов А.М.", "Петров А.М.", "Сидоров А.М.", "Ковган А.М.",
                "Иванченко А.М.", "Ломов А.М.", "Виноградов А.М.", "Усмане А.М.", "Ташкинов А.М.", "Воложин А.М.", "Володин А.М.",
                "Грибоедов А.М.", "Салоедов А.М.", "Мясоедов А.М.", "Раскольников А.М.", "Достоевский А.М.", "Идиотов А.М.",
                "Листков А.М.", "Бумажкин А.М.");
        String city = "Пермь";
        List<String> streets = List.of("Академика Королева", "Академика Веденеева", "Ленина", "Куйбышева", "Крисанова",
                "Луначарского", "Екатерининская", "Петропавловская", "Окулова", "Монастырская", "Плеханова", "Хохрякова",
                "Пермская", "Комсомольский проспект", "Пушкина", "Белинского", "Уральская", "Ким", "Бульвар Гагарина", "Докучаева");

        StringBuilder query = new StringBuilder("INSERT INTO Table_Kuchumov VALUES \n");
        for (int i = 1; i < 21; i++) {
            query.append("(")
                    .append(i).append(", \"")
                    .append(names.get(i - 1)).append("\", \"")
                    .append(city).append("\", \"")
                    .append(streets.get(i - 1)).append("\", ")
                    .append((int) (Math.random() * 100)).append(", ")
                    .append((int) (Math.random() * 100)).append(", ")
                    .append((int) (Math.random() * 20)).append(", ")
                    .append((int)(Math.random() * (150001-15000) ) + 15000).append(", ")
                    .append((long) (Math.random() * (89990000000L - 89020000000L)) + 89020000000L)
                    .append(")");
            if (i != 20) {
                query.append(",\n");
            }
        }
        printQuery(query);
        return query.toString();
    }

    public String getFirstSelectQuery() {
        String query = "SELECT id, ФИО, Номер_телефона, ЗП FROM Table_Kuchumov";
        printQuery(query);
        return query;
    }

    public String getSecondSelectQuery() {
        String query = "SELECT id, ФИО, Город, Улица, Дом FROM Table_Kuchumov\n" +
                "ORDER BY 3 ASC, 4 ASC, 5 ASC";
        printQuery(query);
        return query;
    }

    public String getThirdSelectQuery() {
        String query = "SELECT * FROM Table_Kuchumov\n" +
                "WHERE Стаж > 4";
        printQuery(query);
        return query;
    }

    public String getSelectAllQuery() {
        String query = "SELECT * FROM Table_Kuchumov";
        printQuery(query);
        return query;
    }
}
