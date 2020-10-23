package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Clinic;

import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.EmployeeDto;
import cz.upce.vetalmael.repository.UserRepository;
import cz.upce.vetalmael.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "employeeService")
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void addEmployee(EmployeeDto employeeDto) {
        User employee = new User();
        employee.setEmail(employeeDto.getEmail());
        employee.setFullName(employeeDto.getFullName());
        employee.setPassword(bCryptPasswordEncoder.encode(employeeDto.getPassword()));
        employee.setRoles(employeeDto.getRole().toString());
        Clinic clinic = new Clinic();
        clinic.setIdClinic(employeeDto.getClinicDto().getIdClinic());
        userRepository.save(employee);
    }

    @Override
    public void editEmployee(EmployeeDto employeeDto) {
        User employee = new User();
        employee.setIdUser(employeeDto.getIdEmployee());
        employee.setEmail(employeeDto.getEmail());
        employee.setFullName(employeeDto.getFullName());
        employee.setPassword(bCryptPasswordEncoder.encode(employeeDto.getPassword()));
        employee.setRoles(employeeDto.getRole().toString());
        Clinic clinic = new Clinic();
        clinic.setIdClinic(employeeDto.getClinicDto().getIdClinic());
        userRepository.save(employee);

    }

    @Override
    public void removeEmployee(int idEmployee) {
        userRepository.deleteById(idEmployee);
    }
}
