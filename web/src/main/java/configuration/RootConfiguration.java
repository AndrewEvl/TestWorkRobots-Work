package configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Root configuration file.
 */
@Configuration
@Import(ConfigurationService.class)
@ComponentScan
public class RootConfiguration {
}
