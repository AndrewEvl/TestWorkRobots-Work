package configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Configuration for connection with the database.
 */

@Configuration
@ComponentScan(basePackages = {"dao"})
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class ConfigurationDao {
    /** Path to the database*/
    @Value("${jdbc.url}")
    private String url;
    /** Connection driver */
    @Value("${jdbc.driver}")
    private String driver;
    /** Username for connection database*/
    @Value("${jdbc.username}")
    private String username;
    /** Password for connection database*/
    @Value("${jdbc.password}")
    private String password;
    /** Dialect of the database*/
    @Value("${hibernate.dialect}")
    private String dialect;
    /** Display SQL queries in the console*/
    @Value("${hibernate.show_sql}")
    private String showSql;
    /** Format SQL queries*/
    @Value("${hibernate.format_sql}")
    private String formatSql;
    /** Policy of working with the database*/
    @Value("${hibernate.creation_policy}")
    private String creationPolicy;

    /** Setting up a connection with the database*/
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    /** Settings sessionFactory and scan package*/
    @Bean
    public LocalSessionFactoryBean sessionFactory (){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("entity");
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        return sessionFactoryBean;
    }

    /** Settings Hibernate for connection with the database*/
    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", dialect);
        properties.setProperty("hibernate.show_sql", showSql);
        properties.setProperty("hibernate.format_sql", formatSql);
        properties.setProperty("hibernate.hbm2ddl.auto", creationPolicy);
        return properties;
    }


}
