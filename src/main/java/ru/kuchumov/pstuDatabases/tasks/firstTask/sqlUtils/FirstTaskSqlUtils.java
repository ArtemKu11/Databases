package ru.kuchumov.pstuDatabases.tasks.firstTask.sqlUtils;

import ru.kuchumov.pstuDatabases.databaseUtils.sqlUtils.UsualPrintable;

import java.util.LinkedList;
import java.util.List;

public class FirstTaskSqlUtils extends UsualPrintable {

    private List<String> groupNames = List.of("РИС-19-1б", "РИС-20-1б", "РИС-21-1б", "РИС-22-1б",
            "АСУ-19-1б", "АСУ-20-1б", "АСУ-21-1б", "АСУ-22-1б", "ИТП-19-1б", "ИТП-20-1б");
    private List<String> studentsFamilyNames = List.of("Кучумов", "Иванов", "Петров", "Сидоров", "Ковган",
            "Иванченко", "Ломов", "Виноградов", "Усмане", "Ташкинов", "Воложин", "Володин",
            "Грибоедов", "Салоедов", "Мясоедов", "Раскольников", "Достоевский", "Идиотов",
            "Листков", "Бумажкин");

    private List<String> subjectNames = List.of("ООП", "БД", "Python", "Нейросети", "Моделирование", "3D-графика",
            "Проектирование инфраструктуры", "Игры", "Java", "C++");

    private List<String> marks = List.of("отл", "хор", "удовл", "неуд");
    private List<String> controls = List.of("зачет", "экзамен", "рейтинг");


    public LinkedList<String> getLargeCreateTableQuery() {
        LinkedList<String> query = new LinkedList<>();
        query.add(getGroupStudentsCreateTableQuery());
        query.add(getStudentsCreateTableQuery());
        query.add(getSubjectsCreateTableQuery());
        query.add(getSuccessCreateTableQuery());
        printQuery(query);
        return query;
    }

    public LinkedList<String> getLargeDropTableQuery() {
        LinkedList<String> query = new LinkedList<>();
        query.add("DROP TABLE IF EXISTS Успеваемость_Кучумов");
        query.add("DROP TABLE IF EXISTS Дисциплины_Кучумов");
        query.add("DROP TABLE IF EXISTS Студенты_Кучумов");
        query.add("DROP TABLE IF EXISTS Гр_студентов_Кучумов");
        printQuery(query);
        return query;
    }

    public LinkedList<String> getLargeFillTableQuery() {
        LinkedList<String> query = new LinkedList<>();
        query.add(getGroupStudentsInsertTableQuery());
        query.add(getStudentsInsertTableQuery());
        query.add(getSubjectsInsertTableQuery());
        query.add(getSuccessInsertTableQuery());
        printQuery(query);
        return query;
    }

    private String getGroupStudentsCreateTableQuery() {
        return """
                CREATE TABLE IF NOT EXISTS Гр_студентов_Кучумов
                (
                КодГруппы BIGINT PRIMARY KEY AUTO_INCREMENT,
                Название VARCHAR(255),
                Курс INT,
                Семестр INT
                )""";
    }

    private String getStudentsCreateTableQuery() {
        return """
                CREATE TABLE IF NOT EXISTS Студенты_Кучумов
                (
                КодСтудента BIGINT PRIMARY KEY AUTO_INCREMENT,
                КодГруппы BIGINT,
                Фамилия VARCHAR(255),
                Имя VARCHAR(255),
                Отчетсво VARCHAR(255),
                Пол VARCHAR(255),
                ДатаРожд DATE,
                МестоРожд TEXT,
                FOREIGN KEY (КодГруппы) REFERENCES Гр_студентов_Кучумов (КодГруппы)
                )""";
    }

    private String getSubjectsCreateTableQuery() {
        return """
                CREATE TABLE IF NOT EXISTS Дисциплины_Кучумов
                (
                КодДисциплины BIGINT PRIMARY KEY AUTO_INCREMENT,
                Название VARCHAR(255),
                КолЧасов INT
                )""";
    }

    private String getSuccessCreateTableQuery() {
        return """
                CREATE TABLE IF NOT EXISTS Успеваемость_Кучумов
                (
                КодОценки BIGINT PRIMARY KEY AUTO_INCREMENT,
                КодДисциплины BIGINT,
                КодСтудента BIGINT,
                Оценка VARCHAR(255),
                ВидКонтроля VARCHAR(255),
                FOREIGN KEY (КодДисциплины) REFERENCES Дисциплины_Кучумов (КодДисциплины),
                FOREIGN KEY (КодСтудента) REFERENCES Студенты_Кучумов (КодСтудента)
                )""";
    }


    private String getGroupStudentsInsertTableQuery() {

        List<Integer> courses = List.of(4, 3, 2, 1, 4, 3, 2, 1, 4, 3);
        List<Integer> sem = List.of(8, 6, 4, 2, 8, 6, 3, 1, 7, 5);

        StringBuilder query = new StringBuilder("INSERT Гр_студентов_Кучумов (Название, Курс, Семестр) VALUES\n");
        for (int i = 0; i < groupNames.size(); i++) {
            query
                    .append("(")
                    .append("\"").append(groupNames.get(i)).append("\", ")
                    .append(courses.get(i)).append(", ")
                    .append(sem.get(i))
                    .append(")");
            if (i != groupNames.size() - 1) {
                query.append(",\n");
            }
        }
        return query.toString();
    }

    private String getStudentsInsertTableQuery() {

        List<String> names = List.of("Артем", "Иван", "Петр", "Василий");
        List<String> batyaNames = List.of("Марселевич", "Иванович", "Петрович", "Васильевич");
        List<String> dates = List.of("2000-06-28", "2001-06-28", "2002-06-28", "2003-06-28");
        List<String> cities = List.of("Пермь", "Москва", "Екатеринбург", "Тюмень", "Вологда", "Сургут", "Губаха");


        StringBuilder query = new StringBuilder("INSERT Студенты_Кучумов " +
                "(КодГруппы, Фамилия, Имя, Отчетсво, Пол, ДатаРожд, МестоРожд) VALUES\n");
        for (int i = 0; i < studentsFamilyNames.size(); i++) {
            query
                    .append("(")
                    .append(rnd(1, 10)).append(", ")
                    .append("\"").append(studentsFamilyNames.get(i)).append("\", ")
                    .append("\"").append(getRandom(names)).append("\", ")
                    .append("\"").append(getRandom(batyaNames)).append("\", ")
                    .append("\"").append("Мужской").append("\", ")
                    .append("\"").append(getRandom(dates)).append("\", ")
                    .append("\"").append(getRandom(cities)).append("\"")
                    .append(")");
            if (i != studentsFamilyNames.size() - 1) {
                query.append(",\n");
            }
        }
        return query.toString();

    }

    private String getSubjectsInsertTableQuery() {

        StringBuilder query = new StringBuilder("INSERT Дисциплины_Кучумов " +
                "(Название, КолЧасов) VALUES\n");

        for (int i = 0; i < subjectNames.size(); i++) {
            query
                    .append("(")
                    .append("\"").append(subjectNames.get(i)).append("\", ")
                    .append(rnd(100, 200))
                    .append(")");
            if (i != subjectNames.size() - 1) {
                query.append(",\n");
            }
        }

        return query.toString();
    }

    private String getSuccessInsertTableQuery() {
        StringBuilder query = new StringBuilder("INSERT Успеваемость_Кучумов " +
                "(КодДисциплины, КодСтудента, Оценка, ВидКонтроля) VALUES\n");
        for (int i = 0; i < studentsFamilyNames.size(); i++) {
            for (int j = 0; j < subjectNames.size(); j++) {
                query
                        .append("(")
                        .append(j + 1).append(", ")
                        .append(i + 1).append(", ");
                        if (List.of(1, 2, 3, 4).contains(i + 1)) {  // id тех, у кого все отл
                            query.append("\"").append("отл").append("\", ");
                        } else {
                            query.append("\"").append(getRandom(marks)).append("\", ");
                        }
                        query
                            .append("\"").append(getRandom(controls)).append("\"")
                            .append(")");
                if (i != studentsFamilyNames.size() - 1 || j != subjectNames.size() - 1) {
                    query.append(",\n");
                }
            }
        }
        return query.toString();
    }

    public String getFirstSelectQuery() {
        String query = "" +
                "SELECT Студенты_Кучумов.Фамилия FROM Студенты_Кучумов, Успеваемость_Кучумов\n" +
                "WHERE \n" +
                "(\n" +
                "SELECT count(Успеваемость_Кучумов.Оценка) FROM Успеваемость_Кучумов\n" +
                "WHERE (Студенты_Кучумов.КодСтудента=Успеваемость_Кучумов.КодСтудента) and \n" +
                "(Успеваемость_Кучумов.Оценка='отл')\n" +
                ") =\n" +
                "(\n" +
                "SELECT count(Успеваемость_Кучумов.Оценка) FROM Успеваемость_Кучумов\n" +
                "WHERE (Студенты_Кучумов.КодСтудента=Успеваемость_Кучумов.КодСтудента)\n" +
                ")\n" +
                "GROUP BY Студенты_Кучумов.Фамилия";

//        String query = "" +
//                "SELECT Ст_К.Фамилия FROM Студенты_Кучумов AS Ст_К\n" +
//                "INNER JOIN Успеваемость_Кучумов ON Ст_К.КодСтудента = Успеваемость_Кучумов.КодСтудента\n" +
//                "WHERE Успеваемость_Кучумов.Оценка = \"отл\"\n" +
//                "GROUP BY Ст_К.КодСтудента\n" +
//                "HAVING COUNT(Успеваемость_Кучумов.Оценка) = (SELECT COUNT(Успеваемость_Кучумов.Оценка) FROM Студенты_Кучумов\n" +
//                "INNER JOIN Успеваемость_Кучумов ON Студенты_Кучумов.КодСтудента = Успеваемость_Кучумов.КодСтудента\n" +
//                "WHERE Студенты_Кучумов.КодСтудента = Ст_К.КодСтудента\n" +
//                "GROUP BY Студенты_Кучумов.Фамилия)";
        printQuery(query);
        return query;
    }

    private int rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    private int rndIndex(List<String> list) {
        int min = 0;
        int max = list.size() - 1;
        return rnd(min, max);
    }

    private String getRandom(List<String> list) {
        int i = rndIndex(list);
        return list.get(i);
    }

    private void printQuery(List<String> queries) {
        queries.forEach(s -> {
            System.out.println("\n");
            System.out.println(s);
        });
    }
}
