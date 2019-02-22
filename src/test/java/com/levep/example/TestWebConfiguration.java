package com.levep.example;

import com.levelp.example.ApplicationConfiguration;
import com.levelp.example.SecurityConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.web.servlet.setup.MockMvcConfigurerAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan(
        basePackages = "com.levelp.example",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = ApplicationConfiguration.class)
)
@EnableWebMvc
@Import({TestDataConfiguration.class, SecurityConfiguration.class})
public class TestWebConfiguration extends MockMvcConfigurerAdapter {
}
