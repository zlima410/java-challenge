package com.challenge.api.service;

import com.challenge.api.dto.CreateEmployeeRequest;
import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImpl;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeService {
    private final Map<UUID, Employee> employeeStore = new ConcurrentHashMap<>();

    public EmployeeService() {
        initializeMockData();
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeStore.values());
    }

    public Employee getEmployeeByUuid(UUID uuid) {
        Employee employee = employeeStore.get(uuid);
        if (employee == null) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with UUID: " + uuid);

        return employee;
    }

    public Employee createEmployee(CreateEmployeeRequest request) {
        EmployeeImpl employee = new EmployeeImpl();
        employee.setUuid(UUID.randomUUID());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setFullName((request.getFirstName() + " " + request.getLastName()).trim());
        employee.setSalary(request.getSalary());
        employee.setAge(request.getAge());
        employee.setJobTitle(request.getJobTitle());
        employee.setEmail(request.getEmail());
        employee.setContractHireDate(request.getContractHireDate());
        employee.setContractTerminationDate(request.getContractTerminationDate());

        employeeStore.put(employee.getUuid(), employee);
        return employee;
    }

    private void initializeMockData() {
        EmployeeImpl employee1 = new EmployeeImpl();
        employee1.setUuid(UUID.randomUUID());
        employee1.setFirstName("John");
        employee1.setLastName("Doe");
        employee1.setFullName("John Doe");
        employee1.setSalary(100000);
        employee1.setAge(30);
        employee1.setJobTitle("Senior Software Engineer");
        employee1.setEmail("john.doe@example.com");
        employee1.setContractHireDate(Instant.parse("2024-12-10T00:00:00Z"));
        employee1.setContractTerminationDate(null);
        employeeStore.put(employee1.getUuid(), employee1);

        EmployeeImpl employee2 = new EmployeeImpl();
        employee2.setUuid(UUID.randomUUID());
        employee2.setFirstName("Jane");
        employee2.setLastName("Smith");
        employee2.setFullName("Jane Smith");
        employee2.setSalary(100000);
        employee2.setAge(28);
        employee2.setJobTitle("Product Manager");
        employee2.setEmail("jane.smith@example.com");
        employee2.setContractHireDate(Instant.parse("2023-12-10T00:00:00Z"));
        employee2.setContractTerminationDate(null);
        employeeStore.put(employee2.getUuid(), employee2);

        EmployeeImpl employee3 = new EmployeeImpl();
        employee3.setUuid(UUID.randomUUID());
        employee3.setFirstName("Zachary");
        employee3.setLastName("Lima");
        employee3.setFullName("Zachary Lima");
        employee3.setSalary(90000);
        employee3.setAge(23);
        employee3.setJobTitle("Software Engineer");
        employee3.setEmail("zachary.lima@example.com");
        employee3.setContractHireDate(Instant.parse("2024-12-10T00:00:00Z"));
        employee3.setContractTerminationDate(Instant.parse("2025-12-10T00:00:00Z"));
        employeeStore.put(employee3.getUuid(), employee3);
    }
}
