package com.example.junit5.service.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.junit5.entity.Employee;
import com.example.junit5.exception.service.ServiceException;
import com.example.junit5.exception.service.custom.EmployeeNotFound;
import com.example.junit5.repository.EmployeeRepository;
import com.example.junit5.service.EmployeeService;

//@MockitoSettings(strictness = Strictness.STRICT_STUBS)
//@RunWith(JUnitPlatform.class)
//@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@DisplayName("when running employee service")
public class EmployeServiceImplTest {

	@TestConfiguration
	static class TestConfig{
		@Bean
		public EmployeeService employeeService() {
			return new EmployeServiceImpl();
		}
	}
	
	@Autowired
	private EmployeeService employeeService;
	
	@MockBean
	private EmployeeRepository employeeRepo;
	
	@Nested
	@DisplayName("when removing employee")
	class RemoveEmployeeTest{
		
		@Test
		@DisplayName("Employee Removed Sucess")
		void removeEmployeeTest() throws ServiceException {
			long employeeId=1L;
			Employee employee= getOneEmployeeStub(employeeId,"Ayush",24500);
			when(employeeRepo.findById(employeeId)).thenReturn(Optional.of(employee));
			assertEquals("Employee Removed",employeeService.removeEmployee(employeeId),"should return sucess message");
		}
		
	}
	
	@Test
	@DisplayName("Employee Not found while delete")
	void removeEmployeeInvalid() {
		//when(employeeRepo.findById(1L).orElse(null)).thenReturn(null);
		assertThrows(EmployeeNotFound.class,()->{employeeService.removeEmployee(1L);});
	}
	
	@Test
	@DisplayName("Testing Spring App Context")
	void test() {
		System.out.println("Spring Context");
		System.out.println(employeeService.toString());
		System.out.println(employeeRepo.toString());
	}
	
	@Test
	void addEmployeeTest() throws ServiceException {
		Employee employee=getOneEmployeeStub(1L,"Jatin Chadah",24500);
		when(employeeRepo.save(employee)).thenReturn(employee);
		assertNotNull(employeeService.addEmployee(employee));
	}
	
	@Test
	void getEmployeeByIdTest() throws ServiceException {
		long employeeId=1L;
		Employee employee=new Employee(employeeId,"Ayush",24500.0);
		when(employeeRepo.findById(employeeId)).thenReturn(Optional.of(employee));
		assertNotNull(employeeService.getEmployeeById(employeeId));
	}
	
	@Test
	void getAllEmployeesTest() throws ServiceException {
		List<Employee>employees= new ArrayList<>();
		employees.add(getOneEmployeeStub(1L,"Ayush",24500));
		employees.add(getOneEmployeeStub(2L,"Jatin",24500));
		when(employeeRepo.findAll()).thenReturn(employees);
		assertAll(
				()-> assertNotNull(employeeService.getAllEmployees()),
				()-> assertEquals(2,employeeService.getAllEmployees().size())
				);
	}

	private Employee getOneEmployeeStub(long id, String name, int salary) {
		return new Employee(id, name, salary);
	}

}
