package com.altimetrik.wprzybyl.service;

import com.altimetrik.wprzybyl.dto.StudentDto;
import com.altimetrik.wprzybyl.entity.Student;
import com.altimetrik.wprzybyl.mapper.StudentMapper;
import com.altimetrik.wprzybyl.repository.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.hibernate.StaleObjectStateException;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WebinarService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    private final StudentService studentService;

    private final EntityManager entityManager;


    @Transactional
    public void showDefaultPropagation() {
        Student student = studentRepository.findById(1L).orElseThrow(() -> new IllegalStateException("no student"));
        student.setName("Mathew");
        studentRepository.save(student);
        try {
            studentService.changeNameException(student, "Garry");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Transactional
    public void showRequiresNewPropagation() {
        Student student = studentRepository.findById(1L).orElseThrow(() -> new IllegalStateException("no student"));
        student.setName("Andrew");
        studentRepository.save(student);
        try {
            studentService.changeNameExceptionRequiresNew(student, "Harry");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public StudentDto getStudent() {
        return studentMapper.mapToDto(entityManager.find(Student.class, 1L));
    }

    @Transactional
    public StudentDto createStudent() {
        Student student = new Student();
        student.setName("Adam");
        return studentMapper.mapToDto(studentRepository.save(student));
    }

    @Transactional
    public StudentDto updateStudent() {
        Student student = entityManager.find(Student.class, 1L, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        student.setName("Owen");
        studentRepository.save(student);
        return studentMapper.mapToDto(student);
    }

    @Transactional
    public void showOptimisticLockingException() {
        try {
            Student student = entityManager.find(Student.class, 1L, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
            student.setName("Mike");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            studentRepository.save(student);
        } catch (ObjectOptimisticLockingFailureException e) {
            System.out.println(e.getIdentifier());
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    @Transactional
    public void showPessimisticLocking() {
        Student student = entityManager.find(Student.class, 1L, LockModeType.PESSIMISTIC_WRITE);
        student.setName("Bob");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        studentRepository.save(student);
    }


    @Transactional
    public void showOptimisticLocking() {
        Student student = entityManager.find(Student.class, 1L, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        student.setName("Will");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        studentRepository.save(student);
    }
}

