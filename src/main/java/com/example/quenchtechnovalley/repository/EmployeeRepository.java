package com.example.quenchtechnovalley.repository;

import com.example.quenchtechnovalley.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    /**
     * The interface User repository.
     */
    @Repository
    public interface EmployeeRepository extends JpaRepository<Employee, Long> {}


