package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@SuperBuilder
@Table(name="department")
public class DepartmentEntity {

    @Id
    @Column(name = "id", nullable = false, length = 10)
    private Integer id;

    @Column(name = "department_name", nullable = false, length = 100)
    private String departmentName;
}
