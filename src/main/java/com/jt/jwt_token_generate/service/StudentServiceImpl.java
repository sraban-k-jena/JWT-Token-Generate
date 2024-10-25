package com.jt.jwt_token_generate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jt.jwt_token_generate.model.Student;
import com.jt.jwt_token_generate.repository.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    public StudentRepo studentRepo;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public JwtService jwtService;

    @org.springframework.beans.factory.annotation.Autowired(required = true)
    public AuthenticationManager authenticationManager;

    @Override
    public Student addStudent(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepo.save(student);
    }

    @Override
    public String login(Student student) {

        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(student.getUsername(), student.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(student.getUsername());
        }

        return null;

    }

}
