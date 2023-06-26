package com.monaschinas.readr.platform.cucumber;

import com.monaschinas.readr.platform.ReadrApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@CucumberContextConfiguration
@SpringBootTest(classes = ReadrApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = ReadrApplication.class, loader = SpringBootContextLoader.class)
public class CucumberSpringConfiguration {
}
