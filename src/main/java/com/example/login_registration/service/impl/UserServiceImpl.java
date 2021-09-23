package com.example.login_registration.service.impl;

import com.example.login_registration.model.Role;
import com.example.login_registration.model.User;
import com.example.login_registration.repository.UserRepository;
import com.example.login_registration.service.UserService;
import com.example.login_registration.web.dto.UserRegistrationDTO;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

//    'Constructor' based injection is better than 'Field' based ie ( @Autowired )
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDTO userRegistrationDTO) {
        User user = new User(userRegistrationDTO.getFirstName(),userRegistrationDTO.getLastName(),
                userRegistrationDTO.getEmail(),userRegistrationDTO.getPassword(),
                Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }
}
