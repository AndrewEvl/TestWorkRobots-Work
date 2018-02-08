package service;


import configuration.ConfigurationService;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

/**
 * Add settings to testing class.New tests should be inherited from this class.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ConfigurationService.class)
@Transactional
public abstract class BaseTest {
}
