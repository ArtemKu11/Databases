package ru.kuchumov.dbtaskthree.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kuchumov.dbtaskthree.database.databaseUtils.databaseResourceManager.DatabaseResourceManager;
import ru.kuchumov.dbtaskthree.database.databaseUtils.databaseResourceManager.UsualResourceManager;
import ru.kuchumov.dbtaskthree.database.databaseUtils.exception.ExceptionList;
import ru.kuchumov.dbtaskthree.database.databaseUtils.exception.UsualExceptionList;
import ru.kuchumov.dbtaskthree.database.databaseUtils.wrappers.ConnectionWrapper;
import ru.kuchumov.dbtaskthree.database.databaseUtils.wrappers.StatementWrapper;
import ru.kuchumov.dbtaskthree.database.databaseUtils.wrappers.UsualConnectionWrapper;
import ru.kuchumov.dbtaskthree.database.databaseUtils.wrappers.UsualStatementWrapper;
import ru.kuchumov.dbtaskthree.database.thirdTaskImplementation.dao.ThirdTaskDao;
import ru.kuchumov.dbtaskthree.database.thirdTaskImplementation.sqlUtils.ThirdTaskSqlUtils;

@Configuration
public class ThirdTaskConfiguration {
    @Bean
    ThirdTaskDao secondTaskDao() {
        return new ThirdTaskDao(databaseResourceManager(), sqlUtils());
    }

    @Bean
    ThirdTaskSqlUtils sqlUtils() {
        return new ThirdTaskSqlUtils();
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
