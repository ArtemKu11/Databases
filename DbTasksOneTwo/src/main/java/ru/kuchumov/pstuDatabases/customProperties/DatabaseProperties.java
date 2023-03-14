package ru.kuchumov.pstuDatabases.customProperties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Data
@ConfigurationProperties("custom-properties.database")
public class DatabaseProperties {
    String url;
    String username;
    String password;
}
