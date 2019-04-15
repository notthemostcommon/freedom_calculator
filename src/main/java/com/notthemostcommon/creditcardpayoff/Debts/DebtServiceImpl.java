package com.notthemostcommon.creditcardpayoff.Debts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebtServiceImpl implements DebtService {

    @Autowired
    DebtRepository debtRepository;

    @Override
    public List<Debt> findAll() {
        return debtRepository.findAll();
    }

    @Override
    public List<Debt> findAllByUserId(Long id) {
        return debtRepository.findByAppUserId(id);
    }
}
