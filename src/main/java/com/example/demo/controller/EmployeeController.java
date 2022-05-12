package com.example.demo.controller;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.EmployeeEntity;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeEntity> getEmployee(){
        return employeeService.getEmployee();
    }

    @GetMapping("/byDepartment/{departmentId}")
    public List<EmployeeEntity> listEmployeeOfDepartment(@PathVariable("departmentId") String departmentId){
        return employeeService.listEmployeeOfDepartment(departmentId);
    }

    @PutMapping("/{employeeId}")
    public EmployeeEntity updateEmployee(@PathVariable("employeeId") String employeeId
                                        , @RequestBody EmployeeDto dto){
        return employeeService.updateEmployee(employeeId, dto);
    }

    @PostMapping("/searchEmployee")
    public List<String> searchEmployee(@RequestBody EmployeeDto dto){
        return employeeService.searchEmployee(dto);
    }

    @GetMapping("/testApiCall")
    public List<String> testApiCall() {
        //test
        List<String> testList = new ArrayList<String>();
        testList.add("abc");
        testList.add("def");
        testList.add("xyz");
        return testList;
    }
}
