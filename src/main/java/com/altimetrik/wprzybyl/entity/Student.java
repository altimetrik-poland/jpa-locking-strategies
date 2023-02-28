package com.altimetrik.wprzybyl.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "STUDENT")
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="STUDENT_NAME", length=50, nullable=false)
    private String name;

    @Version
    private Integer version;

}
