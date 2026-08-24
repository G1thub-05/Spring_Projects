package in.digeshwar.multidbapplication.config;


import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = "in.digeshwar.multidbapplication.h2.repository",
		entityManagerFactoryRef = "h2EntityManagerFactory",
		transactionManagerRef = "h2TransactionManager"
)
public class H2DatabaseConfig {

	// Creates H2 database connection
	@Primary
	@Bean(name = "h2DataSource")
	@ConfigurationProperties(prefix = "app.datasource.h2")
	public DataSource h2DataSource() {
		return DataSourceBuilder
				.create()
				.type(HikariDataSource.class)
				.build();
	}


	// Creates EntityManagerFactory for H2 entities
	@Primary
	@Bean(name = "h2EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory(
			EntityManagerFactoryBuilder builder,
			@Qualifier("h2DataSource") DataSource dataSource) {

		// ✅ Add JPA properties explicitly
		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

		return builder
				.dataSource(dataSource)
				.packages("in.digeshwar.multidbapplication.h2.entity")
				.persistenceUnit("h2")
				.properties(properties) // Add JPA/Hibernate properties here
				.build();
	}


	// Manages H2 database transactions
	@Primary
	@Bean(name = "h2TransactionManager")
	public PlatformTransactionManager h2TransactionManager(
			@Qualifier("h2EntityManagerFactory")
			EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}