package com.example.junit5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.junit5.entity.Employee;
import com.example.junit5.exception.service.ServiceException;
import com.example.junit5.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	public List<Employee> getAllEmployees() throws ServiceException{
		return employeeService.getAllEmployees();
	}
	
	@PostMapping
	public Employee addEmployee(@RequestBody Employee employee) throws ServiceException {
		return employeeService.addEmployee(employee);
	}
	
	@GetMapping("/{employeeId}")
	public Employee getEmployeeById(@PathVariable long employeeId) throws ServiceException {
		return employeeService.getEmployeeById(employeeId);
	}
	
	@DeleteMapping("/{employeeId}")
	public String removeEmployee(@PathVariable long employeeId) throws ServiceException {
		return employeeService.removeEmployee(employeeId);
	}
}
