package com.notthemostcommon.creditcardpayoff.User.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@Setter
public class UserUpdateDTO {

    @Id
    @NotNull
    private Long id;

    private String firstName;

    private String lastName;

    private String password;

    private String username;
}
