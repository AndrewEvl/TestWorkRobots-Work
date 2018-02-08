package configuration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Test configuration for service layer.
 */
@Configuration
@ComponentScan(basePackages = "java")
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class TestConfigurationService {

    /**
     * Path to the database
     */
    @Value("${jdbc.url}")
    private String url;

    /**
     * Connection driver
     */
    @Value("${jdbc.driver}")
    private String driver;

    /**
     * Username for connection database
     */
    @Value("${jdbc.username}")
    private String username;

    /**
     * Password for connection database
     */
    @Value("${jdbc.password}")
    private String password;

    /**
     * Dialect of the database
     */
    @Value("${hibernate.dialect}")
    private String dialect;

    /**
     * Display SQL queries in the console
     */
    @Value("${hibernate.show_sql}")
    private String showSql;

    /**
     * Format SQL queries
     */
    @Value("${hibernate.format_sql}")
    private String formatSql;

    /**
     * Policy of working with the database
     */
    @Value("${hibernate.creation_policy}")
    private String creationPolicy;

    /**
     * Setting up a connection with the test database.
     *
     * @return - dataSource.
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    /**
     * Settings sessionFactory and scan package.
     *
     * @return - sessionFactory
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("service");
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        return sessionFactoryBean;
    }

    /**
     * Settings Hibernate for connection with the test database.
     *
     * @return - Hibernate properties
     */
    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", dialect);
        properties.setProperty("hibernate.show_sql", showSql);
        properties.setProperty("hibernate.format_sql", formatSql);
        properties.setProperty("hibernate.hbm2ddl.auto", creationPolicy);
        return properties;
    }

    /**
     * Settings Hibernate transaction manager for tests.
     * @param sessionFactory - sessionFactory Hibernate
     * @return - transactionManager.
     */
    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}
