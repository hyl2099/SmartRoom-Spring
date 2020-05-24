package com.smartroom.springServer.data_services;


import com.smartroom.springServer.repositories.UserRepository;
import com.smartroom.springServer.documents.SmartUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String email) {
        SmartUser user = userRepository.findByEmail(email);
        return this.userBuilder(user.getEmail(), user.getPassword());
    }

    private org.springframework.security.core.userdetails.User userBuilder(String mobile, String password) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(mobile, password, true,true,
                true, true, authorities);
    }
}
