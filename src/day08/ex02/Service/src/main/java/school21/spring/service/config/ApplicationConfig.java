package school21.spring.service.config;

import com.zaxxer.hikari.HikariConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import school21.spring.service.JdbcManager.HikDataSource;

@Configuration
@PropertySource("classpath:db.properties")
public class ApplicationConfig {

  @Value("${db.url}")
  private String url;

  @Value("${db.user}")
  private String user;

  @Value("${db.password}")
  private String password;

  @Bean
  public HikariConfig hikariConfig() {
    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setJdbcUrl(url);
    hikariConfig.setUsername(user);
    hikariConfig.setPassword(password);

    return hikariConfig;
  }
}
