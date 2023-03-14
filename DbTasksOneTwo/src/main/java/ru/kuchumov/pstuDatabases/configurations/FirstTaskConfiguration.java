package ru.kuchumov.pstuDatabases.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kuchumov.pstuDatabases.customProperties.annotations.ConditionalOnFirstTask;
import ru.kuchumov.pstuDatabases.databaseUtils.databaseResourceManager.DatabaseResourceManager;
import ru.kuchumov.pstuDatabases.databaseUtils.databaseResourceManager.UsualResourceManager;
import ru.kuchumov.pstuDatabases.databaseUtils.exception.ExceptionList;
import ru.kuchumov.pstuDatabases.databaseUtils.exception.UsualExceptionList;
import ru.kuchumov.pstuDatabases.databaseUtils.wrappers.ConnectionWrapper;
import ru.kuchumov.pstuDatabases.databaseUtils.wrappers.StatementWrapper;
import ru.kuchumov.pstuDatabases.databaseUtils.wrappers.UsualConnectionWrapper;
import ru.kuchumov.pstuDatabases.databaseUtils.wrappers.UsualStatementWrapper;
import ru.kuchumov.pstuDatabases.tasks.TaskResolver;
import ru.kuchumov.pstuDatabases.tasks.firstTask.dao.FirstTaskDao;
import ru.kuchumov.pstuDatabases.tasks.firstTask.resolver.FirstTaskResolver;
import ru.kuchumov.pstuDatabases.tasks.firstTask.sqlUtils.FirstTaskSqlUtils;
import ru.kuchumov.pstuDatabases.tasks.secondTask.dao.SecondTaskDao;
import ru.kuchumov.pstuDatabases.tasks.secondTask.resolver.SecondTaskResolver;
import ru.kuchumov.pstuDatabases.tasks.secondTask.sqlUtils.SecondTaskSqlUtils;

@Configuration
@ConditionalOnFirstTask
public class FirstTaskConfiguration {
    @Bean
    FirstTaskDao firstTaskDao() {
        return new FirstTaskDao(databaseResourceManager(), sqlUtils());
    }

    @Bean
    TaskResolver taskResolver() {
        return new FirstTaskResolver(firstTaskDao());
    }

    @Bean
    FirstTaskSqlUtils sqlUtils() {
        return new FirstTaskSqlUtils();
    }

    @Bean
    ConnectionWrapper connectionWrapper() {
        return new UsualConnectionWrapper() {
            @Override
            protected StatementWrapper getStatementWrapper() {
                return statementWrapper();
            }
        };
    }

    @Bean
    StatementWrapper statementWrapper() {
        return new UsualStatementWrapper();
    }

    @Bean
    DatabaseResourceManager databaseResourceManager() {
        return new UsualResourceManager(connectionWrapper()) {
            @Override
            protected ExceptionList getExceptionList() {
                return exceptionList();
            }
        };
    }

    @Bean
    ExceptionList exceptionList() {
        return new UsualExceptionList();
    }
}
