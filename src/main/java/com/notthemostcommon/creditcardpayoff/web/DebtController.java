package com.notthemostcommon.creditcardpayoff.web;

import com.notthemostcommon.creditcardpayoff.Calculator.DebtCalcService;
import com.notthemostcommon.creditcardpayoff.Debts.Debt;
import com.notthemostcommon.creditcardpayoff.Debts.DebtRepository;
import com.notthemostcommon.creditcardpayoff.Debts.DebtService;
import com.notthemostcommon.creditcardpayoff.User.AppUser;
import com.notthemostcommon.creditcardpayoff.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/debts")
public class DebtController {

    @Autowired
    DebtService debtService;

    @Autowired
    DebtCalcService debtCalcService;

    private DebtRepository debtRepository;
    private UserRepository userRepository;

    public DebtController(DebtRepository debtRepository, UserRepository userRepository) {
        this.debtRepository = debtRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public void addDebt(@RequestBody Debt debt, Principal principal) {
        String username = principal.getName();
        debt.setAppUser(userRepository.findByUsername(username));
        debtRepository.save(debt);
    }

    @GetMapping
    public ResponseEntity<?> getDebts(Principal principal) {
        Object userObj = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = userRepository.findByUsername(principal.getName());
        List<Debt> debtList = debtService.findAllByUserId(user.getId());
        Double totalBalance = debtCalcService.calculateTotal(principal, "balance");
        Double totalCreditLimit = debtCalcService.calculateTotal(principal, "creditLimit");
        Double totalMinPayment = debtCalcService.calculateTotal(principal, "minPayment");
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("debts", debtList);
        result.put("totalBalance", totalBalance);
        result.put("totalCreditLimit", totalCreditLimit);
        result.put("totalMinPayment", totalMinPayment);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public void editDebt(@PathVariable long id, @RequestBody Debt task) {
        Debt existingDebt = debtRepository.findById(id).get();
        Assert.notNull(existingDebt, "Task not found");
        existingDebt.setDebtName(task.getDebtName());
        debtRepository.save(existingDebt);
    }

    @DeleteMapping("/{id}")
    public void deleteDebt(@PathVariable long id) {
        Debt debtToDel = debtRepository.findById(id).get();
        debtRepository.delete(debtToDel);
    }


}
