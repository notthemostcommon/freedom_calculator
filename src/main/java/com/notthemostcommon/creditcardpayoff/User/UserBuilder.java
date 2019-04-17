package com.notthemostcommon.creditcardpayoff.User;

public class UserBuilder {

    Long id;
    String firstName;
    String lastName;
    String password;
    String username;


    public UserBuilder withId(Long id){
        this.id = id;
        return this;
    }

    public UserBuilder withFirstName(String f_name){
        this.firstName = f_name;
        return this;
    }

    public UserBuilder withLastName(String l_name){
        this.lastName = l_name;
        return this;
    }

    public UserBuilder withPassword(String password){
        this.password = password;
        return this;
    }

    public UserBuilder withUsername(String username){
        this.username = username;
        return this;
    }

    public AppUser build() { return new AppUser(id, firstName,lastName, password, username); };
}

