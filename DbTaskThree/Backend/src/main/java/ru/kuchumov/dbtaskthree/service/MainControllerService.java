package ru.kuchumov.dbtaskthree.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.kuchumov.dbtaskthree.database.thirdTaskImplementation.dao.ThirdTaskDao;
import ru.kuchumov.dbtaskthree.database.thirdTaskImplementation.sqlUtils.ThirdTaskSqlUtils;
import ru.kuchumov.dbtaskthree.dto.QueryRequest;
import ru.kuchumov.dbtaskthree.dto.QueryResponse;
import ru.kuchumov.dbtaskthree.dto.QueryResult;

import java.util.List;

@Component
public class MainControllerService {

    private ThirdTaskDao dao;
    private ThirdTaskSqlUtils sqlUtils;

    public MainControllerService(ThirdTaskDao dao, ThirdTaskSqlUtils sqlUtils) {
        this.dao = dao;
        this.sqlUtils = sqlUtils;
    }

    public QueryResponse getQuery(String type) {
        String query = "";
        switch (type) {
            case "TRUNCATE":
                query = sqlUtils.getTruncateTableQuery();
                break;
            case "DROP":
                query = sqlUtils.getDropTableQuery();
                break;
            case "CREATE":
                query = sqlUtils.getCreateTableQuery();
                break;
            case "INSERT":
                query = sqlUtils.getFillTableQuarry();
                break;
            case "FIRST_SELECT":
                query = sqlUtils.getFirstSelectQuery();
                break;
            case "SECOND_SELECT":
                query = sqlUtils.getSecondSelectQuery();
                break;
            case "THIRD_SELECT":
                query = sqlUtils.getThirdSelectQuery();
                break;
            case "SELECT_ALL":
                query = sqlUtils.getSelectAllQuery();
                break;
        }

        return QueryResponse.builder()
                .httpStatus(HttpStatus.OK)
                .query(query)
                .build();
    }

    public ResponseEntity<QueryResult> executeQuery(QueryRequest queryRequest) {
        String query = queryRequest.getQuery();
        QueryResult queryResult = new QueryResult();
        if (query.toLowerCase().startsWith("select")) {
            queryResult.setResult(dao.executeSelectQuery(query));
        } else {
            queryResult.setResult(dao.executeNotSelectQuery(query));
        }
        ResponseEntity<QueryResult> responseEntity;
        if (queryResult.getResult().get(0).equals("Фатальная ошибка!")) {
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(queryResult);
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.OK).body(queryResult);
        }
        return responseEntity;
    }
}
