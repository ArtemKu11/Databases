package ru.kuchumov.dbtaskthree.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class QueryResult {
    private List<String> result;
}
