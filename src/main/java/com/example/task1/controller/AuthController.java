package com.example.task1.controller;


import com.example.task1.domain.Owner;
import com.example.task1.security.JwtTokenUtil;
import com.example.task1.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public AuthController() {
        System.out.println("AuthController instantiated!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        System.out.println("Login endpoint called");
        Owner owner = ownerRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        String token = jwtTokenUtil.generateToken(owner.getEmail());

        return ResponseEntity.ok().body(token);
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "AuthController is working!";
    }
}
