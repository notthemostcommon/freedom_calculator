package com.notthemostcommon.creditcardpayoff.User;

import com.notthemostcommon.creditcardpayoff.Debts.Debt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String password;

    @NotEmpty
    private String username;


    public AppUser(String firstName, String lastName, String password, String username){
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
//        this.debts.forEach(x -> x.setAppUser(this));
    }
}