package com.tnicacio.algamoney.services;

import com.tnicacio.algamoney.repositories.UserRepository;
import com.tnicacio.algamoney.entities.User;
import com.tnicacio.algamoney.services.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public User authenticated() {
        try {
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            return userRepository.findByEmail(userName);
        } catch(Exception e) {
            throw new UnauthorizedException("Invalid user");
        }
    }
}
