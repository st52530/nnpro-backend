package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.EmployeeDto;

public interface EmployeeService {

    User addEmployee(EmployeeDto employeeDto);
    User editEmployee(EmployeeDto employeeDto);
    void removeEmployee(int idEmployee);
}
