package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.dto.EmployeeDto;
import cz.upce.vetalmael.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/add-employee")
    public void addClinic(@RequestBody EmployeeDto employeeDto) {
        employeeService.addEmployee(employeeDto);
    }

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/edit-employee")
    public void editClinic(@RequestBody EmployeeDto employeeDto) {
        employeeService.editEmployee(employeeDto);
    }

    @PostMapping("/remove-employee/{idEmployee}")
    public void removeEmployee(@PathVariable int idEmployee) {
        employeeService.removeEmployee(idEmployee);
    }
}
