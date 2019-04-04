package com.notthemostcommon.creditcardpayoff.web;

import com.notthemostcommon.creditcardpayoff.User.AppUser;
import com.notthemostcommon.creditcardpayoff.User.UserRepository;
import com.notthemostcommon.creditcardpayoff.model.Creditor;
import com.notthemostcommon.creditcardpayoff.model.CreditorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CreditorController {

    private final Logger log = LoggerFactory.getLogger(CreditorController.class);
    private CreditorRepository repository;
    private UserRepository userRepository;

    public CreditorController(CreditorRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

//    @GetMapping("/creditor")
//    Collection<Creditor> creditors(Principal principal) {
//        return repository.findAllByUserId(principal.getName());
//    }

    @GetMapping("/creditor/{id}")
    ResponseEntity<?> getCreditor(@PathVariable Long id) {
        Optional<Creditor> group = repository.findById(id);
        return group.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/creditor")
    ResponseEntity<Creditor> createCreditor(@Valid @RequestBody Creditor creditor, Principal principal) throws URISyntaxException {
        log.info("Request to create creditor: {}", creditor);
        String username = principal.getName();
        AppUser user = userRepository.findByUsername(username);
//        creditor.setUser(user);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userInfo = auth.getName();
        System.out.println("userInfo >>>>>>>>>>>>>>> " + userInfo);


        Creditor result = repository.save(creditor);
        return ResponseEntity.created(new URI("/api/creditor" + user.getId()))
                .body(result);

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
