package com.khaled.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.khaled.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

	Optional<Employee> findByEmpName(String empName);

}
