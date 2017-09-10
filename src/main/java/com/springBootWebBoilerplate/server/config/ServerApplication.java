package com.springBootWebBoilerplate.server.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.springBootWebBoilerplate.server" })
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.springBootWebBoilerplate.server")
@EntityScan("com.springBootWebBoilerplate.server.entities")
@EnableJpaRepositories({"com.springBootWebBoilerplate.server.entities","com.springBootWebBoilerplate.server.dao"})
public class ServerApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ServerApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ServerApplication.class, args);
    }

}