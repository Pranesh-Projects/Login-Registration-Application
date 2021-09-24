package com.example.login_registration.service.impl;

import com.example.login_registration.dto.UserRegistrationDTO;
import com.example.login_registration.model.Role;
import com.example.login_registration.model.User;
import com.example.login_registration.repository.UserRepository;
import com.example.login_registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    //    'Constructor' based injection is better than 'Field' based ie ( @Autowired )
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDTO dto) {
        User user = new User(dto.getFirstName(), dto.getLastName(),
                dto.getEmail(), encoder.encode(dto.getPassword()),
                Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);     // 'username' is 'email'
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole_name())).collect(Collectors.toList());
    }
}
