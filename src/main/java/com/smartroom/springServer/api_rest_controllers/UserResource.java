package com.smartroom.springServer.api_rest_controllers;


import com.smartroom.springServer.business_controllers.UserController;
import com.smartroom.springServer.dtos.TokenOutputDto;
import com.smartroom.springServer.dtos.UserCredentialDto;
import com.smartroom.springServer.dtos.UserDto;
import com.smartroom.springServer.dtos.UserMinimumDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<TokenOutputDto> login(@AuthenticationPrincipal User activeUser) {
        //to do
        return null;
    }

    //
    @PostMapping
    public Mono<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        //to do
        return null;
    }

    @GetMapping(value = EMAIL_ID)
    public Mono<UserDto> read(@PathVariable String email) {
        //to do
        return null;
    }

    @GetMapping
    public Flux<UserMinimumDto> readAll() {
        //to do
        return null;
    }

    @PutMapping(value = EMAIL_ID)
    public Mono<UserDto> updateUser(@PathVariable String email, @Valid @RequestBody UserDto userDto) {
        //to do
        return null;
    }

    @PatchMapping(value = "/password" + EMAIL_ID)
    public Mono<UserDto> changePassword(@PathVariable String email, @Valid @RequestBody UserCredentialDto userCredentialDto) {
        //to do
        return null;
    }

    @PatchMapping(value = EMAIL_ID)
    public Mono<UserDto> updateRoles(@PathVariable String email, @Valid @RequestBody UserMinimumDto userMinimumDto) {
        //to do
        return null;
    }

    @GetMapping(value = SEARCH)
    public Flux<UserDto> findByMobileOrUsernameOrDniOrAddress(@RequestParam(required = false) String email,
                                                              @RequestParam(required = false) String username,
                                                              @RequestParam(required = false) String dni,
                                                              @RequestParam(required = false) String address) {

        //to do
        return null;
    }

}
