package com.notthemostcommon.creditcardpayoff.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;


public interface CreditorRepository extends JpaRepository<Creditor, Long> {

    Creditor findByCreditorName(String creditorName);

//    Collection<Creditor> findAllByUserId(String id);


}
