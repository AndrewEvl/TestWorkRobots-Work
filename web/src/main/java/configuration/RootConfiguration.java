package configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ConfigurationService.class)
@ComponentScan
/**
 * Root configuration file.
 */
public class RootConfiguration {
}
