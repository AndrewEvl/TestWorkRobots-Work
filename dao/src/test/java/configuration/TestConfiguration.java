package configuration;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

/**
 * Configuration connection to H2 database for tests.
 */
@Configuration
@Import(ConfigurationDao.class)
public class TestConfiguration {

    /**
     * Path to the database
     */
    @Value("${jdbc.url}")
    private String url;

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
     * Settings Hibernate transaction manager for tests.
     *
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
