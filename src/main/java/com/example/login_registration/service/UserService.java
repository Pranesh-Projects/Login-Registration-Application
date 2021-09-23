package com.example.login_registration.service;

import com.example.login_registration.model.User;
import com.example.login_registration.web.dto.UserRegistrationDTO;

public interface UserService {

    User save (UserRegistrationDTO userRegistrationDTO);

}
