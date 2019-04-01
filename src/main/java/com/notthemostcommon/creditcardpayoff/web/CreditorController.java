package com.notthemostcommon.creditcardpayoff.web;

import com.notthemostcommon.creditcardpayoff.model.Creditor;
import com.notthemostcommon.creditcardpayoff.model.CreditorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CreditorController {

    private final Logger log = LoggerFactory.getLogger(CreditorController.class);
    private CreditorRepository repository;

    public CreditorController(CreditorRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/creditor")
    Collection<Creditor> creditors() {
        return repository.findAll();
    }

    @GetMapping("/creditor/{id}")
    ResponseEntity<?> getCreditor(@PathVariable Long id) {
        Optional<Creditor> group = repository.findById(id);
        return group.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/creditor")
    @Valid
    Creditor createCreditor(@Valid @RequestBody Creditor creditor) throws URISyntaxException {
        log.info("Request to create creditor: {}", creditor);
        System.out.println("creditor " + creditor);
        return repository.save(creditor);
//        return ResponseEntity.created(new URI("/api/creditor/" + result.getId()))
//                .body(result);
    }

    @PutMapping("/creditor")
    ResponseEntity<Creditor> updateCreditor(@Valid @RequestBody Creditor creditor) {
        log.info("Request to update creditor: {}", creditor);
        Creditor result = repository.save(creditor);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/creditor/{id}")
    public ResponseEntity<?> deleteCreditor(@PathVariable Long id) {
        log.info("Request to delete creditor: {}", id);
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
