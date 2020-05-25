package com.smartroom.springServer.api_rest_controllers;


import com.smartroom.springServer.business_controllers.UserController;
import com.smartroom.springServer.documents.SmartUser;
import com.smartroom.springServer.dtos.TokenOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.User;

import javax.validation.Valid;


@RestController
@RequestMapping(UserResource.USERS)
public class UserResource {

    public static final String USERS = "/users";
    public static final String TOKEN = "/token";
    public static final String EMAIL_ID = "/{email}";
    public static final String SEARCH = "/search";
    public static final String ALL = "/all";

    private UserController userController;

    @Autowired
    public UserResource(UserController userController) {
        this.userController = userController;
    }

    @PreAuthorize("authenticated")
    @PostMapping(value = TOKEN)
    public TokenOutputDto login(@AuthenticationPrincipal User activeUser) {
        return userController.login(activeUser.getUsername());
    }


    //
    @PostMapping
    public SmartUser createUser(@Valid @RequestBody SmartUser user) {
        return userController.createUser(user);
    }

    @GetMapping(value = EMAIL_ID)
    public SmartUser read(@PathVariable String email) {
        return userController.findByEmail(email);
    }

    @GetMapping(value = ALL)
    public Iterable<SmartUser> readAll() {
        return userController.readAll();
    }

    @PutMapping(value = EMAIL_ID)
    public SmartUser updateUser(@PathVariable String email, @Valid @RequestBody SmartUser user) {
        return userController.updateUser(email, user);
    }

//    @PatchMapping(value = "/password" + EMAIL_ID)
//    public User changePassword(@PathVariable String email, @Valid @RequestBody UserCredentialDto userCredentialDto) {
//        return null;
//    }


//    @GetMapping(value = SEARCH)
//    public User findByMobileOrUsernameOrDniOrAddress(@RequestParam(required = false) String email,
//                                                              @RequestParam(required = false) String username) {
//        User user = new User();
//        user.setEmail(email);
//        user.setUsername(username);
//        return this.userController.findByEmailOrUsername(user);
//    }

}
