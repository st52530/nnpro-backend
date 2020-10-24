package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Clinic;

import cz.upce.vetalmael.model.Role;
import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.EmployeeDto;
import cz.upce.vetalmael.repository.UserRepository;
import cz.upce.vetalmael.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service(value = "employeeService")
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User addEmployee(EmployeeDto employeeDto, int idClinic) {
        if (employeeDto.getRole() != Role.ADMINISTRATOR || employeeDto.getRole() != Role.CLIENT) {
            User employee = new User();
            employee.setEmail(employeeDto.getEmail());
            employee.setUsername(employeeDto.getUsername());
            employee.setFullName(employeeDto.getFullName());
            employee.setPassword(bCryptPasswordEncoder.encode(employeeDto.getPassword()));
            employee.setRoles(employeeDto.getRole().toString());
            Clinic clinic = new Clinic();
            clinic.setIdClinic(idClinic);
            employee.setWorkplace(clinic);
            return userRepository.save(employee);
        }
        return null;
    }

    @Override
    public User editEmployee(EmployeeDto employeeDto, int idEmployee, int idClinic) {
        if (employeeDto.getRole() != Role.ADMINISTRATOR || employeeDto.getRole() != Role.CLIENT) {
            User employee = new User();
            employee.setIdUser(idEmployee);
            employee.setEmail(employeeDto.getEmail());
            employee.setUsername(employeeDto.getUsername());
            employee.setFullName(employeeDto.getFullName());
            employee.setPassword(bCryptPasswordEncoder.encode(employeeDto.getPassword()));
            employee.setRoles(employeeDto.getRole().toString());
            Clinic clinic = new Clinic();
            clinic.setIdClinic(idClinic);
            employee.setWorkplace(clinic);
            return userRepository.save(employee);
        }
        return null;

    }

    @Override
    public void removeEmployee(int idEmployee) {
        userRepository.deleteById(idEmployee);
    }
}
