package com.jt.jwt_token_generate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jt.jwt_token_generate.model.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {
    Optional<Student> findByUsername(String username);
}
