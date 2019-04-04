package com.notthemostcommon.creditcardpayoff.Debts;

import java.util.List;

public interface DebtService {

    List<Debt> findAll();

    List<Debt> findAllByUserId(Long id);


}
