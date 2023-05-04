package com.tasm.db.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.tasm.enums.DBTypeEnum;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.tasm", entityManagerFactoryRef = "multiEntityManager", transactionManagerRef = "multiTransactionManager")
public class PersistenceConfiguration {
	
	private final String PACKAGE_SCAN = "com.tasm";

	@Primary
	@Bean(name = "mainDataSourceProperties")
	@ConfigurationProperties("app.datasource.dbtrxtasm")
	public DataSourceProperties mainDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Primary
	@Bean(name = "mainDataSource")
	@ConfigurationProperties("app.datasource.dbtrxtasm.configuration")
	public DataSource mainDataSource() {
		return mainDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Bean(name = "multiRoutingDataSource")
	public DataSource multiRoutingDataSource() {
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(DBTypeEnum.DB_TRX_TASM, mainDataSource());

		MultiRoutingDataSource multiRoutingDataSource = new MultiRoutingDataSource();
		multiRoutingDataSource.setDefaultTargetDataSource(mainDataSource());
		multiRoutingDataSource.setTargetDataSources(targetDataSources);
		return multiRoutingDataSource;
	}

	@Bean(name = "multiEntityManager")
	public LocalContainerEntityManagerFactoryBean multiEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(multiRoutingDataSource());
		em.setPackagesToScan(PACKAGE_SCAN);
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(hibernateProperties());
		return em;
	}

	@Bean(name = "multiTransactionManager")
	public PlatformTransactionManager multiTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(multiEntityManager().getObject());
		return transactionManager;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
//		properties.put("hibernate.show_sql", true);
//		properties.put("hibernate.format_sql", true);
//		properties.put("hibernate.use_sql_comments", true);
		properties.put("hibernate.jdbc.batch_size", 48);
		properties.put("hibernate.proc.param_null_passing", true);
		return properties;
	}
	
}