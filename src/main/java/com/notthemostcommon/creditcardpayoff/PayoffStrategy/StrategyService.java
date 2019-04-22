package com.notthemostcommon.creditcardpayoff.PayoffStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyService implements IStrategyService {

    @Autowired
    private StrategyRepository strategyRepository;

    @Override
    public List<PayoffStrategy> findAll() {
        List<PayoffStrategy> strategies = (List<PayoffStrategy>) strategyRepository.findAll();
        return strategies;
    }
}
