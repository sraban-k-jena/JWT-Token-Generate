package com.jt.jwt_token_generate.service;

import com.jt.jwt_token_generate.model.Student;

public interface StudentService {

    Student addStudent(Student student);

    String login(Student student);
}
