package com.altimetrik.wprzybyl.mapper;

import com.altimetrik.wprzybyl.dto.StudentDto;
import com.altimetrik.wprzybyl.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentDto mapToDto(Student student) {
        return StudentDto.builder()
                .name(student.getName())
                .build();
    }
}
