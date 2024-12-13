package com.example.task1.repository;

import com.example.task1.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Owner findByName(String name);
    Optional<Owner> findByNameAndPassword(String name, String password);
    Optional<Owner> findByEmailAndPassword(String email, String password);
}
