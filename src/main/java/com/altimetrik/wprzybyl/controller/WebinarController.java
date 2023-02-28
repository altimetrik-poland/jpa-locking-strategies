package com.altimetrik.wprzybyl.controller;

import com.altimetrik.wprzybyl.dto.StudentDto;
import com.altimetrik.wprzybyl.service.WebinarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebinarController {

    private final WebinarService webinarService;

    @GetMapping(path = "/student")
    public StudentDto getStudents() {
        return webinarService.getStudent();
    }

    @PostMapping(path = "/student")
    public StudentDto createStudent() {
        return webinarService.createStudent();
    }

    @PutMapping(path = "/update-student")
    public StudentDto updateStudent() {
        return webinarService.updateStudent();
    }

    @GetMapping(path = "/default-propagation")
    public void showDefaultPropagation() {
        webinarService.showDefaultPropagation();
    }

    @GetMapping(path = "/requires-new-propagation")
    public void showRequiresNewPropagation() {
        webinarService.showRequiresNewPropagation();
    }

    @GetMapping(path = "/pessimistic-locking")
    public void showPessimisticLocking() {
        webinarService.showPessimisticLocking();
    }

    @GetMapping(path = "/optimistic-locking")
    public void showOptimisticLocking() {
        webinarService.showOptimisticLocking();
    }

    @GetMapping(path = "/optimistic-locking-exception")
    public void showOptimisticLockingException() {
        webinarService.showOptimisticLockingException();
    }


}
