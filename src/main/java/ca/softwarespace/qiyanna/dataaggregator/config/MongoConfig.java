package ca.softwarespace.qiyanna.dataaggregator.config;

import com.mongodb.MongoClientURI;
import java.util.Objects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
public class MongoConfig {

  private final Environment env;

  public MongoConfig(Environment env) {
    this.env = env;
  }

  @Bean
  public MongoDbFactory mongoDbFactory() {
    return new SimpleMongoDbFactory(new MongoClientURI(
        Objects.requireNonNull(env.getProperty("spring.data.mongodb.uri"))));
  }

  @Bean
  public MongoTemplate mongoTemplate() {
    return new MongoTemplate(mongoDbFactory());
  }
}
