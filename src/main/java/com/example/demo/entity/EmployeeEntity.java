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
@Table(name="employee")
public class EmployeeEntity {
    @Id
    @Column(name = "employee_id", nullable = false, length = 10)
    private Integer employeeId;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name="department_id", nullable = false, length = 10)
    private Integer departmentId;
}
