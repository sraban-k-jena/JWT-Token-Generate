package com.jt.jwt_token_generate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.jt.jwt_token_generate.model.Student;
import com.jt.jwt_token_generate.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class StudentController {

    @Autowired
    public StudentService service;

    @GetMapping("/")
    public ResponseEntity<?> getAllDetails(HttpServletRequest request) {

        String id = request.getSession().getId();
        return new ResponseEntity<>("My name is Sravan :" + id, HttpStatus.OK);

    }

    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student Stduent) {
        Student student2 = service.addStudent(Stduent);
        return new ResponseEntity<>(student2, HttpStatus.CREATED);
    }

    @PostMapping("/log")
    public ResponseEntity<?> login(@RequestBody Student student) {
        String token = service.login(student);
        return new ResponseEntity<>(token, HttpStatus.OK);

    }
}
