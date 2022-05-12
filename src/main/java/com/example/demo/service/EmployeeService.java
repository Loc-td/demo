package com.example.demo.service;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.EmployeeEntity;
import com.example.demo.repository.EmployeeDSLRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeDSLRepository employeeDSLRepository;

    public List<EmployeeEntity> getEmployee(){
        return employeeRepository.findAll();
    }

    public EmployeeEntity updateEmployee(String employeeId, EmployeeDto dto){
        employeeRepository.findById(Integer.valueOf(employeeId)).ifPresent(entity -> {
            entity.setFirstName(dto.getFirstName());
            entity.setLastName(dto.getLastName());
            entity.setEmail(dto.getEmail());
            employeeRepository.save(entity);
        });

        return employeeRepository.findById(Integer.valueOf(employeeId)).orElseThrow(RuntimeException::new);
    }

    public List<EmployeeEntity> listEmployeeOfDepartment(String deptId){
        return employeeDSLRepository.listEmployeeOfDepartment(deptId);
    }

    public List<String> searchEmployee(EmployeeDto dto){
        return employeeDSLRepository.searchEmployee(dto);
    }


}
