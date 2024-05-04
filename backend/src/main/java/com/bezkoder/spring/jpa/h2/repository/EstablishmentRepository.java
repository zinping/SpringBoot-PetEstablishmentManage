package com.bezkoder.spring.jpa.h2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.jpa.h2.model.Establishment;

public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {
    List<Establishment> findByUserid(String userid);
    // List<Establishment> findByEmailAndPassword(String email, String password);
    List<Establishment> findByTitleContainingIgnoreCase(String title);
    
} 