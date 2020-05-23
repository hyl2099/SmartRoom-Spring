package com.smartroom.springServer.business_controllers;

import com.smartroom.springServer.business_services.JwtService;
import com.smartroom.springServer.documents.User;
import com.smartroom.springServer.dtos.TokenOutputDto;

import com.smartroom.springServer.exceptions.ForbiddenException;
import com.smartroom.springServer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityNotFoundException;
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
    public TokenOutputDto login(String email) {
        if (userRepository.findByEmail(email) == null) {
            throw new EntityNotFoundException("the email :" + email.toString() + "Wrong, no entity.");
        }else{
            return new TokenOutputDto(jwtService.createToken(userRepository.findByEmail(email).getEmail(), userRepository.findByEmail(email).getUsername()));
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


    public Iterable<User> readAll() {
        return this.userRepository.findAll();
    }

    public User updateUser(String email, User user) {
        if (userRepository.findByEmail(email) == null) {
            throw new EntityNotFoundException("the email :" + email.toString() + "Wrong, no entity.");
        }else{
            user.setEmail(email);
            return this.userRepository.save(user);
        }
    }

//    public User changePassword(String email, UserCredentialDto userCredentialDto) {
//        if (userRepository.findByEmail(email) == null) {
//            throw new EntityNotFoundException("the email :" + email.toString() + "Wrong, no entity.");
//        }else{
//
//        }
//    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

//    public User findByEmailOrUsername(User user) {
//        return this.userRepository.findByEmailOrUsername(user);
//    }
}
