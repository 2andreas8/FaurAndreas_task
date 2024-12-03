package com.example.task1.service;

import com.example.task1.domain.Owner;
import com.example.task1.repository.OwnerRepository;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Owner authenticate(String name, String password) {
        return ownerRepository.findByNameAndPassword(name, password)
                .orElseThrow(()-> new IllegalArgumentException("Invalid credentials"));
    }
}
