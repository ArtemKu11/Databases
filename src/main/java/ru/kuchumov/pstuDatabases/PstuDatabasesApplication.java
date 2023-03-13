package ru.kuchumov.pstuDatabases;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import ru.kuchumov.pstuDatabases.customProperties.TaskNumberProperty;
import ru.kuchumov.pstuDatabases.tasks.TaskResolver;

@SpringBootApplication
@EnableConfigurationProperties(TaskNumberProperty.class)
public class PstuDatabasesApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PstuDatabasesApplication.class, args);

        context.getBean(TaskResolver.class).resolveTask();
    }

}
