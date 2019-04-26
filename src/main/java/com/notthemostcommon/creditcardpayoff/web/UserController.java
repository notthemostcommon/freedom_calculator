package com.notthemostcommon.creditcardpayoff.web;

import com.notthemostcommon.creditcardpayoff.User.AppUser;
import com.notthemostcommon.creditcardpayoff.User.dto.UserCreationDTO;
import com.notthemostcommon.creditcardpayoff.UserNotFoundException;
import com.notthemostcommon.creditcardpayoff.User.UserRepository;
import com.notthemostcommon.creditcardpayoff.util.DTO;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserController {

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

    @PutMapping("/{id}")
    ResponseEntity<?> replaceUser(@RequestBody AppUser newUser, @PathVariable Long id) throws URISyntaxException {

        AppUser updatedUser = repository.findById(id)
                .map(user -> {
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
        Resource<AppUser> resource = assembler.toResource(updatedUser);

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);

    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
