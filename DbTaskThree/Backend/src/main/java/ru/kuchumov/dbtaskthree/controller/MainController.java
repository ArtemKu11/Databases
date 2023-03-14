package ru.kuchumov.dbtaskthree.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kuchumov.dbtaskthree.dto.QueryRequest;
import ru.kuchumov.dbtaskthree.dto.QueryResponse;
import ru.kuchumov.dbtaskthree.dto.QueryResult;
import ru.kuchumov.dbtaskthree.service.MainControllerService;

@RestController
public class MainController {
    private MainControllerService service;

    public MainController(MainControllerService service) {
        this.service = service;
    }

    @GetMapping("/get_query")
    QueryResponse getQuery(@RequestParam String type) {
        return service.getQuery(type);
    }

    @PostMapping("/execute")
    ResponseEntity<QueryResult> executeQuery(@RequestBody QueryRequest queryRequest) {
        return service.executeQuery(queryRequest);
    }
}
