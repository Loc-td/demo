package com.example.demo.repository;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.EmployeeEntity;
import com.example.demo.entity.QDepartmentEntity;
import com.example.demo.entity.QEmployeeEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EmployeeDSLRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<EmployeeEntity> listEmployeeOfDepartment(String deptId){

        JPAQuery<?> qr = new JPAQuery<>(entityManager);
        QEmployeeEntity qEmployee = QEmployeeEntity.employeeEntity;
        QDepartmentEntity qDepartment = QDepartmentEntity.departmentEntity;

        qr.from(qEmployee).leftJoin(qDepartment).on(qEmployee.departmentId.eq(qDepartment.id));
        qr.where(qDepartment.id.eq(Integer.valueOf(deptId)));
        qr.orderBy(qEmployee.firstName.asc());

        return qr.select(Projections.fields(EmployeeEntity.class
                , qEmployee.employeeId
                , qEmployee.firstName
                , qEmployee.lastName
                , qEmployee.email
                , qEmployee.departmentId)).fetch();
    }

    public List<String> searchEmployee(EmployeeDto dto) {
        JPAQuery<?> qr = new JPAQuery<>(entityManager);
        QEmployeeEntity qEmployee = QEmployeeEntity.employeeEntity;

        qr.from(qEmployee);
        qr.where(qEmployee.employeeId.eq(dto.getEmployeeId()));
        if(!StringUtils.isNullOrEmpty(dto.getFirstName())) {
            qr.where(qEmployee.firstName.eq(dto.getFirstName()));
        }
        if(!StringUtils.isNullOrEmpty(dto.getLastName())){
            qr.where(qEmployee.lastName.eq(dto.getLastName()));
        }

        return qr.select(qEmployee.firstName).fetch();
    }
}
