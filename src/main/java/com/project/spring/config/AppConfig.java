package com.project.spring.config;

import java.util.Properties;

import org.hibernate.Hibernate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import static org.hibernate.cfg.Environment.*;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = { 
			@ComponentScan("com.project.spring.dao"), 
			@ComponentScan("com.project.spring.service") 
		})

public class AppConfig {
	
	private Environment env;

	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		Properties props = new Properties();
		
		//setting JDBC
		props.put(DRIVER, env.getProperty("hiberante.connection.driver_class"));
		props.put(URL, env.getProperty("hiberante.connection.url"));
		props.put(USER, env.getProperty("hiberante.connection.user"));
		props.put(PASS, env.getProperty("hiberante.connection.password"));
		
		//setting hibernate
		props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
		props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
		
		//setting c3p0
		props.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
		props.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
		props.put(C3P0_ACQUIRE_INCREMENT, env.getProperty("hibernate.c3p0.acquire_increment"));
		props.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
		props.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));
		
		factoryBean.setHibernateProperties(props);
		factoryBean.setPackagesToScan("com.project.spring.model");
		
		return factoryBean;
		
	}
	
	public HibernateTransactionManager getTransactionManager(){
		
			HibernateTransactionManager transactionManager = new HibernateTransactionManager();
			transactionManager.setSessionFactory(getSessionFactory().getObject());
			return transactionManager;
			
		}
	}
