package com.example.login_registration.service;

import com.example.login_registration.dto.UserRegistrationDTO;
import com.example.login_registration.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDTO userRegistrationDTO);

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}
