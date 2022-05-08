package com.example.demo.Controller;

import com.example.demo.Entity.Employee;
import com.example.demo.Password.Password;
import com.example.demo.Repository.EmployeeRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @SneakyThrows
    @PostMapping("/empCreate")
    public ResponseEntity<Employee> empCreate(@RequestParam("name") String name, @RequestParam("user") String user, @RequestParam("password") String password) {

        Employee employee = new Employee();
        String message = "";
        String x = Password.getSalt().toString();
        String y = Password.hashSHA256(password + x);

        employee.setEmpName(name);
        employee.setEmpUserName(user);
        employee.setEmpPassword(y);
        employee.setEmpSalt(x);
        employeeRepository.save(employee);

        message = name + " your account created";
        return new ResponseEntity(message, HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public Boolean getLogin(@RequestParam("user") String user, @RequestParam("password") String password) {

        String x = employeeRepository.findAllByEmpUserName(user).getEmpSalt();
        String y = Password.hashSHA256(password + x);
        String z = employeeRepository.findAllByEmpUserName(user).getEmpPassword();
        if (y.equals(z)) {
            return true;
        } else {
            return false;
        }
    }

}