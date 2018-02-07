package configuration;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

@Configuration
@ComponentScan(basePackages = "service")
@Import(value = ConfigurationDao.class)
/**
 * Configuration on service layer.
 */
public class ConfigurationService {

    @Bean
    /**
     * Hibernate transaction manager configuration.
     */
    public HibernateTransactionManager transactionManager (SessionFactory sessionFactory){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}
