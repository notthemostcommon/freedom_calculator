package com.notthemostcommon.creditcardpayoff.User;

import org.springframework.security.core.userdetails.User;

public class TestUserBuilder {


    public static AppUser buildUser(){
        AppUser user = new UserBuilder()
                .withId(1L)
                .withFirstName("TestFN")
                .withLastName("TestLN")
                .withUsername("Test@test.com")
                .withPassword("Password")
                .build();
        return user;

    }
}
