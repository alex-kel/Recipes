package ru.dz.receipts.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.orm.jpa.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@ComponentScan("ru.dz.receipts")
@EnableTransactionManagement
@EnableJpaRepositories("ru.dz.receipts.repository")
@EntityScan(basePackages = "ru.dz.receipts.domain")
class ApplicationConfiguration {

    public static void main(String[] args) {
        new SpringApplication(ApplicationConfiguration).run(args);
    }
}