package com.notthemostcommon.creditcardpayoff.User.dto;

import com.notthemostcommon.creditcardpayoff.User.AppUser;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

public class UserCreationDTOTest {

    private static final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void checkUserMapping(){

        UserCreationDTO newUser = new UserCreationDTO();
        newUser.setFirstName("test");
        newUser.setLastName("testerson");
        newUser.setPassword("password");
        newUser.setUsername("test@test.com");

        AppUser user = modelMapper.map(newUser, AppUser.class);
        assertEquals(newUser.getFirstName(), user.getFirstName());
        assertEquals(newUser.getLastName(), user.getLastName());
        assertEquals(newUser.getPassword(), user.getPassword());
        assertEquals(newUser.getUsername(), user.getUsername());

        UserUpdateDTO updateUser = new UserUpdateDTO();
        updateUser.setFirstName("newTest");
        updateUser.setLastName("testy");
        updateUser.setPassword("newPassword");
        updateUser.setUsername("tester@test.com");

        modelMapper.map(updateUser, user);
        assertEquals(updateUser.getFirstName(), user.getFirstName());
        assertEquals(updateUser.getLastName(), user.getLastName());
        assertEquals(updateUser.getPassword(), user.getPassword());
        assertEquals(updateUser.getUsername(), user.getUsername());

        UserUpdateDTO updatePartial = new UserUpdateDTO();
        updatePartial.setFirstName("johnny");

        modelMapper.map(updatePartial, user);
        assertEquals(updatePartial.getUsername(), user.getUsername());
        assertEquals(updatePartial.getFirstName(), user.getFirstName());
    }

}