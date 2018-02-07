package configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "controller")
@EnableWebMvc
@Import(value = ThymeleafConfiguration.class)
/**
 * Web configuration file/
 */
public class WebConfiguration {
}
