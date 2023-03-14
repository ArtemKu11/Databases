package ru.kuchumov.dbtaskthree.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class QueryResponse {
    private HttpStatus httpStatus;
    private String query;
}
