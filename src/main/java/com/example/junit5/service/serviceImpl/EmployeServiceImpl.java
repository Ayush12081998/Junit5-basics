package com.example.junit5.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.junit5.entity.Employee;
import com.example.junit5.exception.service.ServiceException;
import com.example.junit5.exception.service.custom.EmployeeNotFound;
import com.example.junit5.repository.EmployeeRepository;
import com.example.junit5.service.EmployeeService;

@Service
public class EmployeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Override
	public List<Employee> getAllEmployees() throws ServiceException {
		return employeeRepo.findAll();
	}

	@Override
	public Employee addEmployee(Employee employee) throws ServiceException {
		return employeeRepo.save(employee);
	}

	@Override
	public Employee getEmployeeById(long employeeId) throws ServiceException {
		Employee employee= employeeRepo.findById(employeeId).orElse(null);
		if(employee!=null)
			return employee;
		else
			throw new EmployeeNotFound("No Such Employee exist");
	}

	@Override
	public String removeEmployee(long employeeId) throws ServiceException {
		Employee employee= employeeRepo.findById(employeeId).orElse(null);
		if(employee!=null) {
			employeeRepo.deleteById(employeeId);
			return "Employee Removed";
		}
		else
			throw new EmployeeNotFound("No Such Employee exist");
	}

}
