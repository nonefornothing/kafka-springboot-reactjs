package com.johan.springbootbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.johan.springbootbackend.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query(value = "Select * from employees WHERE (POSITION(LOWER(?1) in LOWER(concat(first_name,last_name))) > 0);", nativeQuery = true)
	List<Employee> findAllEmployeeBySearch(String inquiry);
	
}
