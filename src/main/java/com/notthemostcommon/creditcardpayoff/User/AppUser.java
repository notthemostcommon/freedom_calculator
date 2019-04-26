package com.notthemostcommon.creditcardpayoff.User;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.notthemostcommon.creditcardpayoff.Debts.Debt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Column(unique=true)
    @NotEmpty
    private String password;

    @Column(unique = true)
    @NotEmpty
    @Email
    private String username;

    private float additionalPayment;

    @Formula("(select sum(d.interest_accrued) from DEBT d where d.app_user_id = id)")
    private BigDecimal totalInterestAccrued;

    private float minPaymentTotal;
    private float creditUsage;

    @OneToMany(mappedBy="appUser", fetch=FetchType.LAZY)
    @JsonManagedReference
    private Set<Debt> debts = new HashSet<Debt>(0);


    public AppUser(String firstName, String lastName, String password, String username){
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
    }
    public AppUser(Long id, String firstName, String lastName, String password, String username){
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}