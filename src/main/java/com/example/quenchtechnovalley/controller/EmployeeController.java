package com.example.quenchtechnovalley.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.quenchtechnovalley.exception.ResourceNotFoundException;
import com.example.quenchtechnovalley.model.Employee;
import com.example.quenchtechnovalley.EmployeeRepository;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    /**
     * The Employee controller.
     */

    @RestController
    @RequestMapping("/api/v1")
    public class EmployeeController {

        @Autowired
        private EmployeeRepository employeeRepository;

        /**
         * Get all Employee list.
         *
         * @return the list
         */
        @GetMapping("/employees")
        public List<Employee> getAllEmployees() {
            return employeeRepository.findAll();
        }

        /**
         * Gets employee by id.
         *
         * @param employeeId the employee id
         * @return the employees by id
         * @throws ResourceNotFoundException the resource not found exception
         */
        @GetMapping("/employees/{id}")
        public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
                throws ResourceNotFoundException {
            Employee employee =
                    employeeRepository
                            .findById(employeeId)
                            .orElseThrow(() -> new ResourceNotFoundException("Employee not found on :: " + employeeId));
            return ResponseEntity.ok().body(employee);
        }

        /**
         * Create Employee employee.
         *
         * @param employee the employeeId
         * @return the employee
         */
        @PostMapping("/employees")
        public Employee createEmployee(@Valid @RequestBody Employee employee) {
            return employeeRepository.save(employee);
        }

        /**
         * Update employee response entity.
         *
         * @param employeeId the employee id
         * @param employeeDetails the employee details
         * @return the response entity
         * @throws ResourceNotFoundException the resource not found exception
         */
        @PutMapping("/employees/{id}")
        public ResponseEntity<Employee> updateEmployee(
                @PathVariable(value = "id") Long employeeId, @Valid @RequestBody Employee employeeDetails)
                throws ResourceNotFoundException {

            Employee employee =
                    employeeRepository
                            .findById(employeeId)
                            .orElseThrow(() -> new ResourceNotFoundException("Employee not found on :: " + employeeId));

            employee.setEmail(employeeDetails.getEmail());
            employee.setLastName(employeeDetails.getLastName());
            employee.setFirstName(employeeDetails.getFirstName());
            employee.setAge(employeeDetails.getAge());
            employee.setGender(employeeDetails.getGender());
            employee.setAddress(employeeDetails.getAddress());
            final Employee updatedEmployee = employeeRepository.save(employee);
            return ResponseEntity.ok(updatedEmployee);
        }

        /**
         * Delete employee map.
         *
         * @param employeeId the employee id
         * @return the map
         * @throws Exception the exception
         */
        @DeleteMapping("/employee/{id}")
        public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws Exception {
            Employee employee =
                    employeeRepository
                            .findById(employeeId)
                            .orElseThrow(() -> new ResourceNotFoundException("Employee not found on :: " + employeeId));

            employeeRepository.delete(employee);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return response;
        }
    }

