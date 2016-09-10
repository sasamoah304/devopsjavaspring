package com.devopsjavaspring.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by stephenasamoah on 9/9/16.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.devopsjavaspring.backend.persistence.repositories")
@EntityScan(basePackages = "com.devopsjavaspring.backend.persistence.domain.backend")
@EnableTransactionManagement
@PropertySource("file:///${user.home}/.devopsbuddy/application-common.properties")
public class ApplicationConfig {
}
