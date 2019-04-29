package com.notthemostcommon.creditcardpayoff.web;

import com.notthemostcommon.creditcardpayoff.User.AppUser;
import com.notthemostcommon.creditcardpayoff.User.Payment.PaymentService;
import com.notthemostcommon.creditcardpayoff.User.dto.AdditionalPaymentUpdateDTO;
import com.notthemostcommon.creditcardpayoff.User.dto.UserCreationDTO;
import com.notthemostcommon.creditcardpayoff.User.dto.UserQueryDTO;
import com.notthemostcommon.creditcardpayoff.UserNotFoundException;
import com.notthemostcommon.creditcardpayoff.User.UserRepository;
import com.notthemostcommon.creditcardpayoff.util.DTO;
import com.notthemostcommon.creditcardpayoff.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    PaymentService paymentService;

    private final UserRepository repository;

    private BCryptPasswordEncoder bCrypt;

    private final UserResourceAssembler assembler;

    UserController(UserRepository repository, UserResourceAssembler assembler, BCryptPasswordEncoder bCrypt){
        this.repository = repository;
        this.assembler = assembler;
        this.bCrypt = bCrypt;
    }

    @GetMapping("/")
    Resources<Resource<AppUser>> all(){

        List<Resource<AppUser>> users = repository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(users,
                linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @PostMapping("/register")
    ResponseEntity<?> newUser(@DTO(UserCreationDTO.class) AppUser newUser) throws URISyntaxException {
        newUser.setPassword(bCrypt.encode(newUser.getPassword()));
        Resource<AppUser> resource = assembler.toResource(repository.save(newUser));
        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    @GetMapping("/{id}")
    Resource<AppUser> one(@PathVariable Long id) {

        AppUser user = repository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id));

        return assembler.toResource(user);
    }

    @PutMapping(value="/payment", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserQueryDTO> updatePayment(@RequestBody AdditionalPaymentUpdateDTO paymentUpdateDTO,
                                                      Principal principal){
        String username = principal.getName();
        Long userId = repository.findByUsername(username).getId(); 
        System.out.println("user id " + userId);
        return new ResponseEntity<>(paymentService.updatePayment(userId, paymentUpdateDTO), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
