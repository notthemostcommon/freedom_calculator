package com.notthemostcommon.creditcardpayoff.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadUserDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            log.info("preloading " + repository.save(new User((long) 1, "John", "Doe")));
            log.info("preloading " + repository.save(new User( "Jane", "Smith")));
        };
    }
}
