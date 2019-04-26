package com.notthemostcommon.creditcardpayoff;

import com.notthemostcommon.creditcardpayoff.User.AppUser;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

@TestConfiguration
public class AuthUserTestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService(){
        AppUser basicUser = new AppUser("Basic", "User",  "password", "user@company.com");

        return new InMemoryUserDetailsManager(Arrays.asList(basicUser));

    }
}
