package com.smartroom.springServer.api_rest_controllers;

import com.smartroom.springServer.business_controllers.UserController;
import com.smartroom.springServer.documents.User;
import com.smartroom.springServer.dtos.TokenOutputDto;
import com.smartroom.springServer.dtos.UserCredentialDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('OPERATOR')")
@RestController
@RequestMapping(com.smartroom.springServer.api_rest_controllers.UserResource.USERS)
public class UserResource {

    public static final String USERS = "/users";
    public static final String TOKEN = "/token";
    public static final String EMAIL_ID = "/{email}";
    public static final String SEARCH = "/search";

    private UserController userController;

    @Autowired
    public UserResource(UserController userController) {
        this.userController = userController;
    }

    @PreAuthorize("authenticated")
    @PostMapping(value = TOKEN)
    public TokenOutputDto login(@AuthenticationPrincipal User user) {
        return userController.login(user.getEmail());
    }

    //
    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userController.createUser(user);
    }

    @GetMapping(value = EMAIL_ID)
    public User read(@PathVariable String email) {
        return userController.findByEmail(email);
    }

    @GetMapping
    public Iterable<User> readAll() {
        return userController.readAll();
    }

    @PutMapping(value = EMAIL_ID)
    public User updateUser(@PathVariable String email, @Valid @RequestBody User user) {
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
