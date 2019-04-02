package com.notthemostcommon.creditcardpayoff.web;

import com.notthemostcommon.creditcardpayoff.User.AppUser;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.stereotype.Component;

@Component
public class UserResourceAssembler implements ResourceAssembler<AppUser, Resource<AppUser>> {


    @Override
    public Resource<AppUser> toResource(AppUser user) {
        return new Resource<>(user,
                linkTo(methodOn(UserController.class).one(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).all()).withRel("users"));
    }
}
