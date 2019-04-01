package com.notthemostcommon.creditcardpayoff.model;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CreditorRepository extends JpaRepository<Creditor, Long> {

    Creditor findByCreditorName(String creditorName);


}
