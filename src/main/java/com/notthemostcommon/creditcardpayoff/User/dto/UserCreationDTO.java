package com.notthemostcommon.creditcardpayoff.User.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserCreationDTO {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Column(unique=true)
    @NotEmpty
    private String password;

    @Column(unique = true)
    @NotEmpty
    private String username;}
