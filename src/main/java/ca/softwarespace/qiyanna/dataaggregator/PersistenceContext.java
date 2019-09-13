package ca.softwarespace.qiyanna.dataaggregator;


import javax.sql.DataSource;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({"ca.softwarespace.qiyanna.dataaggregator.models.generated.tables"})
@EnableTransactionManagement
public class PersistenceContext {

  private final Environment environment;

  @Autowired
  public PersistenceContext(Environment environment) {
    this.environment = environment;
  }

  @Bean
  public DataSource dataSource() {
    PGSimpleDataSource dataSource = new PGSimpleDataSource();
    dataSource.setUrl(environment.getRequiredProperty("QIYANNA_CONFIG_DATABASE_URL"));
    dataSource.setUser(environment.getRequiredProperty("QIYANNA_CONFIG_DATABASE_USERNAME"));
    dataSource.setPassword(environment.getRequiredProperty("QIYANNA_CONFIG_DATABASE_PASSWORD"));

    return dataSource;
  }

  @Bean
  public TransactionAwareDataSourceProxy transactionAwareDataSource() {
    return new TransactionAwareDataSourceProxy(dataSource());
  }

  @Bean
  public DataSourceTransactionManager transactionManager() {
    return new DataSourceTransactionManager(dataSource());
  }

  @Bean
  public DataSourceConnectionProvider connectionProvider() {
    return new DataSourceConnectionProvider(transactionAwareDataSource());
  }

  @Bean
  public ExceptionTranslator exceptionTransformer() {
    return new ExceptionTranslator();
  }

  @Bean
  public DefaultDSLContext dsl() {
    return new DefaultDSLContext(configuration());
  }

  @Bean
  public DefaultConfiguration configuration() {
    DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
    jooqConfiguration.set(connectionProvider());
    jooqConfiguration.set(new DefaultExecuteListenerProvider(exceptionTransformer()));

    SQLDialect dialect = SQLDialect.POSTGRES;
    jooqConfiguration.set(dialect);
    return jooqConfiguration;
  }
}
