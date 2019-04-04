package com.notthemostcommon.creditcardpayoff.web;

import com.notthemostcommon.creditcardpayoff.Debts.Debt;
import com.notthemostcommon.creditcardpayoff.Debts.DebtRepository;
import com.notthemostcommon.creditcardpayoff.Debts.DebtService;
import com.notthemostcommon.creditcardpayoff.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/debts")
public class DebtController {

    @Autowired
    DebtService debtService;

    private DebtRepository debtRepository;
    private UserRepository userRepository;

    public DebtController(DebtRepository debtRepository, UserRepository userRepository) {
        this.debtRepository = debtRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public void addDebt(@RequestBody Debt debt) {
        debtRepository.save(debt);
    }

    @GetMapping
    public ResponseEntity<?> getDebts(Principal principal) {
        System.out.println("getdebts >>>>>>>>>> " + principal.getName());
        List<Debt> result = debtService.findAll();
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
