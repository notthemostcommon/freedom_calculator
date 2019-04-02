package com.notthemostcommon.creditcardpayoff.model;


import com.notthemostcommon.creditcardpayoff.User.AppUser;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "creditor_info")
public class Creditor {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String creditorName;
    private float rate;
    private float balance;
    private float limit;
    private float minPayment;
    @ManyToOne(cascade=CascadeType.PERSIST)
    private AppUser user;

}
