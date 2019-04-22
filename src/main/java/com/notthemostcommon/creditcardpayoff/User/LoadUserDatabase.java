//package com.notthemostcommon.creditcardpayoff.User;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@Slf4j
//public class LoadUserDatabase {
//
//    @Bean
//    CommandLineRunner initDatabase(UserRepository repository) {
//        return args -> {
//            log.info("preloading " + repository.save(new AppUser( "John", "Doe","1234", "johndoe@mail.com" )));
//            log.info("preloading " + repository.save(new AppUser( "Jane", "Smith", "abcd", "janesmith@mail.com")));
//        };
//    }
//}
