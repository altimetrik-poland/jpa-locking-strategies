package com.altimetrik.wprzybyl.repository;

import com.altimetrik.wprzybyl.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
