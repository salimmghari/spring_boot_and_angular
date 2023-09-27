package com.software.spring_boot_and_angular.controllers;

import java.util.HashMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.software.spring_boot_and_angular.dao.User;
import com.software.spring_boot_and_angular.services.UserService;


@CrossOrigin(origins="http://localhost:4200", allowedHeaders={"Authorization", "Origin", "Content-Type", "Accept"})
@RestController
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping(value="/api/signup", consumes="application/json", produces="application/json")
    public ResponseEntity<HashMap<String, String>> signup(@RequestBody User request) {
        String token = service.signup(request);
        HashMap<String, String> response = new HashMap<String, String>();
        response.put("token", token);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value="/api/login", consumes="application/json", produces="application/json")
    public ResponseEntity<HashMap<String, String>> login(@RequestBody User request) {
        String token = service.login(request);
        if (!token.isEmpty()) {
            HashMap<String, String> response = new HashMap<String, String>();
            response.put("token", token);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping(value="/api/logout", consumes="application/json", produces="application/json")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("Authorization");
        if (service.logout(token)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
