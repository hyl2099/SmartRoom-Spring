package com.smartroom.springServer.business_controllers;

import com.smartroom.springServer.business_services.JwtService;
import com.smartroom.springServer.documents.Picture;
import com.smartroom.springServer.documents.Role;
import com.smartroom.springServer.documents.User;
import com.smartroom.springServer.dtos.TokenOutputDto;
import com.smartroom.springServer.dtos.UserCredentialDto;
import com.smartroom.springServer.dtos.UserDto;
import com.smartroom.springServer.dtos.UserMinimumDto;
import com.smartroom.springServer.repositories.UserRepository;

import com.smartroom.springServer.exceptions.ForbiddenException;
import com.smartroom.springServer.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    private JwtService jwtService;
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    //login 返回一个token
    //不支持反应式,no reactive driver for MySQl
    public TokenOutputDto login(String email) {
        if (userRepository.findByEmail(email) == null) {
            throw new EntityNotFoundException("the email :" + email.toString() + "Wrong, no entity.");
        }else{
            String[] roles = Arrays.stream(userRepository.findByEmail(email).getRoles()).map(Role::name).toArray(String[]::new);
            return new TokenOutputDto(jwtService.createToken(userRepository.findByEmail(email).getEmail(), userRepository.findByEmail(email).getUsername(), roles));
        }
    }

    //
    private User readAndValidate(String email, String claimEmail, List<String> claimRoles) {
        if (userRepository.findByEmail(email) == null) {
            throw new EntityNotFoundException("the email :" + email.toString() + "Wrong, no entity.");
        }else{
            //
            if(!this.isAuthorized(claimEmail, claimRoles, email)){
                throw new ForbiddenException("User email (" + email + ")");
            }else{
                return userRepository.findByEmail(email);
            }
        }
    }

    private boolean isAuthorized(String claimEmail, List<String> claimRoles, String userEmail) {
        if (claimEmail.equals(userEmail)) {
            return true;
        }else return false;
    }

    //查询数据库没有注册用户
    private Boolean noExistByEmail(String email) {
        if (userRepository.findByEmail(email) == null) {
            return false;
        }else return true;
    }

    //创建用户
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

//    public Iterable<User> readUser(String email, String claimEmail, List<String> claimRoles) {
//        return this.userRepository.findAllUsers();
//    }

    public Iterable<User> readAll() {
        return this.userRepository.findAll();
    }

    public User updateUser(String email, User user) {
        if (userRepository.findByEmail(email) == null) {
            throw new EntityNotFoundException("the email :" + email.toString() + "Wrong, no entity.");
        }
        user.setEmail(email);
        return this.userRepository.save(user);
    }

    public User changePassword(String email, UserCredentialDto userCredentialDto) {
        //to do
        return null;
    }


    public Flux<UserDto> findByEmailOrUsernameOrDniOrAddress(UserDto userDto) {
        //to do
        return null;
    }
}
