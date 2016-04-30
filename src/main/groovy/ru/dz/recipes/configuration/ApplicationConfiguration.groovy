package ru.dz.recipes.configuration

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.orm.jpa.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@ComponentScan("ru.dz.recipes")
@EnableTransactionManagement
@EnableJpaRepositories("ru.dz.recipes.repository")
@EntityScan(basePackages = "ru.dz.recipes.domain")
class ApplicationConfiguration {

    public static void main(String[] args) {
        new SpringApplication(ApplicationConfiguration).run(args);
    }
}