package service;


import configuration.ConfigurationService;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ConfigurationService.class)
@Transactional
public abstract class BaseTest {
}
