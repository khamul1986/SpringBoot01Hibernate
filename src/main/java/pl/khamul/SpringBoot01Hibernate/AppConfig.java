package pl.khamul.SpringBoot01Hibernate;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan("pl.khamul")
@EnableTransactionManagement
public class AppConfig {
}
