package configuration;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

/**
 * Configuration of service layer.
 */
@Configuration
@ComponentScan(basePackages = "service")
@Import(value = ConfigurationDao.class)
public class ConfigurationService {

    /**
     * Settings Hibernate transaction manager.
     * @param sessionFactory - sessionFactory Hibernate
     * @return - transactionManager.
     */
    @Bean
    public HibernateTransactionManager transactionManager (SessionFactory sessionFactory){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}
