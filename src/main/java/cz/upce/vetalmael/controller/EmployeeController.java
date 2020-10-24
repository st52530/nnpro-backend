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

import static cz.upce.vetalmael.config.SwaggerConfig.SWAGGER_AUTH_KEY;

@RestController
@RequestMapping("/employee")
@SecurityRequirement(name = SWAGGER_AUTH_KEY)
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Transactional(rollbackOn = Exception.class)
    @PostMapping
    public ResponseEntity<User> addEmployee(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.addEmployee(employeeDto));
    }

    @Transactional(rollbackOn = Exception.class)
    @PutMapping
    public ResponseEntity<User> editEmployee(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.editEmployee(employeeDto));
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
}
