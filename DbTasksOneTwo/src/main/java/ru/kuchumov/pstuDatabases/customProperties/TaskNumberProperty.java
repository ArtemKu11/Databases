package ru.kuchumov.pstuDatabases.customProperties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("custom-properties")
public class TaskNumberProperty {
     TaskNumbers taskNumber;

     enum TaskNumbers {
         FIRST, SECOND;
     }
}
