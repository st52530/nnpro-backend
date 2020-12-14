package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Clinic;

import cz.upce.vetalmael.model.Role;
import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.EmployeeDto;
import cz.upce.vetalmael.repository.UserRepository;
import cz.upce.vetalmael.service.ClinicService;
import cz.upce.vetalmael.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service(value = "employeeService")
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User addEmployee(EmployeeDto employeeDto, int idClinic) {
        if (employeeDto.getRole() == Role.CLIENT) {
            throw new IllegalArgumentException("Employee cant be client");
        }

        User employee = new User();
        employee.setEmail(employeeDto.getEmail());
        employee.setUsername(employeeDto.getUsername());
        employee.setFullName(employeeDto.getFullName());
        employee.setPassword(bCryptPasswordEncoder.encode(employeeDto.getPassword()));
        employee.setRoles(employeeDto.getRole().toString());
        employee.setAddress(employeeDto.getAddress());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        Clinic clinic = clinicService.getClinic(idClinic);
        if (clinic == null) {
            throw new IllegalArgumentException("Clinic not exists");
        }

        employee.setWorkplace(clinic);
        return userRepository.save(employee);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User editEmployee(EmployeeDto employeeDto, int idEmployee, int idClinic) {
        User employee = userRepository.findById(idEmployee).orElse(null);
        if (employee == null) {
            throw new IllegalArgumentException("User not exists");
        }
        employee.setEmail(employeeDto.getEmail());
        employee.setUsername(employeeDto.getUsername());
        employee.setFullName(employeeDto.getFullName());
        employee.setPassword(bCryptPasswordEncoder.encode(employeeDto.getPassword()));
        employee.setRoles(employeeDto.getRole().toString());
        employee.setAddress(employeeDto.getAddress());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        Clinic clinic = clinicService.getClinic(idClinic);
        if (clinic == null) {
            throw new IllegalArgumentException("Clinic not exists");
        }
        employee.setWorkplace(clinic);
        return userRepository.save(employee);
    }

    @Override
    public void removeEmployee(int idEmployee) {
        userRepository.deleteById(idEmployee);
    }

    @Override
    public List<User> getClinicEmployees(int idClinic) {
        return userRepository.findAllByWorkplace_IdClinic(idClinic);
    }

    @Override
    public List<User> getEmployees() {
        return userRepository.findAllByRolesContainingOrRolesContainingOrRolesContaining(Role.VETERINARY.getAuthority(), Role.VETERINARY_TECHNICIAN.getAuthority(), Role.ADMINISTRATOR.getAuthority());
    }

    @Override
    public User getEmployee(int idEmployee) {
        return userRepository.getOne(idEmployee);
    }
}
