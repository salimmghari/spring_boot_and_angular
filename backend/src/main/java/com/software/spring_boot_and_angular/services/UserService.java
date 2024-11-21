package com.software.spring_boot_and_angular.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.software.spring_boot_and_angular.security.JwtTokenProvider;
import com.software.spring_boot_and_angular.dao.UserRepository;
import com.software.spring_boot_and_angular.dao.User;


@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository repository;

    public String signup(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        return jwtTokenProvider.generateToken(user.getUsername());
    }

    public String login(User user) {
        User userFromDatabase = repository.findByUsername(user.getUsername());
        if (passwordEncoder.matches(user.getPassword(), userFromDatabase.getPassword())) {
            return jwtTokenProvider.generateToken(user.getUsername());
        }
        return "";
    }

    public boolean logout(String token) {
        if (jwtTokenProvider.validateToken(token)) {
            jwtTokenProvider.invalidateToken(token);
            return true;
        }
        return false;
    }
}
