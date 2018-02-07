package configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Web configuration file/
 */
@Configuration
@ComponentScan(basePackages = "controller")
@EnableWebMvc
@Import(value = ThymeleafConfiguration.class)
public class WebConfiguration {
}
