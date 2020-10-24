package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.EmployeeDto;

public interface EmployeeService {

    User addEmployee(EmployeeDto employeeDto, int idClinic);
    User editEmployee(EmployeeDto employeeDto,int idEmployee, int idClinic);
    void removeEmployee(int idEmployee);
}
