package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.EmployeeDto;
import cz.upce.vetalmael.service.EmployeeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import java.util.List;

import static cz.upce.vetalmael.config.SwaggerConfig.SWAGGER_AUTH_KEY;

@RestController
@RequestMapping("/employees")
@SecurityRequirement(name = SWAGGER_AUTH_KEY)
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/clinic/{idClinic}")
    public ResponseEntity<User> addEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable int idClinic) {
        return ResponseEntity.ok(employeeService.addEmployee(employeeDto, idClinic));
    }

    @Transactional(rollbackOn = Exception.class)
    @PutMapping("/{idEmployee}/clinic/{idClinic}")
    public ResponseEntity<User> editEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable int idClinic, @PathVariable int idEmployee) {
        return ResponseEntity.ok(employeeService.editEmployee(employeeDto,idEmployee, idClinic));
    }

    @DeleteMapping("/{idEmployee}")
    public ResponseEntity<?> removeEmployee(@PathVariable int idEmployee) {
        try {
            employeeService.removeEmployee(idEmployee);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/clinic/{idClinic}")
    public ResponseEntity<List<User>> getMedicinesInClinic(@PathVariable int idClinic) {
        return ResponseEntity.ok(employeeService.getClinicEmployees(idClinic));
    }

    @GetMapping("/{idEmployee}")
    public ResponseEntity<User> getEmployee(@PathVariable int idEmployee){
        return ResponseEntity.ok(employeeService.getEmployee(idEmployee));
    }

    @GetMapping
    public ResponseEntity<List<User>> getEmployees(){
        return ResponseEntity.ok(employeeService.getEmployees());
    }
}
