package com.altimetrik.wprzybyl.service;

import com.altimetrik.wprzybyl.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentService {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void changeNameExceptionRequiresNew(Student student, String name) {
        throw new RuntimeException("change name exception!");
    }
    @Transactional
    public void changeNameException(Student student, String name) {
        throw new RuntimeException("change name exception!");
    }

}
