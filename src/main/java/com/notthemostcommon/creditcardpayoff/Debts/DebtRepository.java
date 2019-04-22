package com.notthemostcommon.creditcardpayoff.Debts;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DebtRepository extends JpaRepository<Debt, Long> {


    List<Debt> findAllById(Long id);

    List<Debt> findByAppUserId(Long userId);
}
