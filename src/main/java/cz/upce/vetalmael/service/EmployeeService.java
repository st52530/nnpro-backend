package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.dto.EmployeeDto;

public interface EmployeeService {

    void addEmployee(EmployeeDto employeeDto);
    void editEmployee(EmployeeDto employeeDto);
    void removeEmployee(int idEmployee);
}
