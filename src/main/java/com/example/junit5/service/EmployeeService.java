package com.example.junit5.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.junit5.entity.Employee;
import com.example.junit5.exception.service.ServiceException;

@Service
public interface EmployeeService {

	public List<Employee> getAllEmployees() throws ServiceException;
	public Employee addEmployee(Employee employee) throws ServiceException;
	public Employee getEmployeeById(long employeeId) throws ServiceException;
	public String removeEmployee(long employeeId) throws ServiceException;
}
