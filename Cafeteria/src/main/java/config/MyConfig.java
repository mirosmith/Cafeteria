package config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class MyConfig {
	
	@Bean
	@Profile("mysql")
	@ConfigurationProperties("spring.datasource.mysql")
	public DataSourceProperties mysqlProperties() {
		
		return new DataSourceProperties();
		
	}

}
