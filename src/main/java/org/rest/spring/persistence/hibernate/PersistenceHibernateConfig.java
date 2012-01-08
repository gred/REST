package org.rest.spring.persistence.hibernate;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// @Configuration
@Profile( "hibernate" )
@EnableTransactionManagement
public class PersistenceHibernateConfig{
	
	@Value( "${jdbc.driverClassName}" )
	private String driverClassName;
	
	@Value( "${jdbc.url}" )
	private String url;
	
	@Value( "${hibernate.dialect}" )
	String hibernateDialect;
	
	@Value( "${hibernate.show_sql}" )
	boolean hibernateShowSql;
	
	@Value( "${hibernate.hbm2ddl.auto}" )
	String hibernateHbm2ddlAuto;
	
	// beans
	
	@Bean
	public LocalSessionFactoryBean alertsSessionFactoryBean(){
		final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource( this.restDataSource() );
		sessionFactory.setPackagesToScan( new String[ ] { "org.rest" } );
		sessionFactory.setHibernateProperties( this.hibernateProperties() );
		
		return sessionFactory;
	}
	@Bean
	public DataSource restDataSource(){
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName( this.driverClassName );
		dataSource.setUrl( this.url );
		dataSource.setUsername( "restUser" );
		dataSource.setPassword( "restmy5ql" );
		
		return dataSource;
	}
	@Bean
	public HibernateTransactionManager transactionManager(){
		final HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory( this.alertsSessionFactoryBean().getObject() );
		
		return txManager;
	}
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	// util
	
	final Properties hibernateProperties(){
		return new Properties(){
			{
				this.put( "persistence.dialect", PersistenceHibernateConfig.this.hibernateDialect );
				this.put( "hibernate.hbm2ddl.auto", PersistenceHibernateConfig.this.hibernateHbm2ddlAuto );
				this.put( "hibernate.show_sql", PersistenceHibernateConfig.this.hibernateShowSql );
			}
		};
	}
	
}
