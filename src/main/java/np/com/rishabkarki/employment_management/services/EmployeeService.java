package np.com.rishabkarki.employment_management.services;

import np.com.rishabkarki.employment_management.dto.EmployeeDto;
import np.com.rishabkarki.employment_management.exceptions.EmployeeNotFoundException;
import np.com.rishabkarki.employment_management.exceptions.InvalidEmployeeDataException;
import np.com.rishabkarki.employment_management.model.Department;
import np.com.rishabkarki.employment_management.model.Employee;
import np.com.rishabkarki.employment_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(EmployeeDto employeeDto) {
        if (employeeDto.name() == null || employeeDto.name().trim().isEmpty())
            throw new InvalidEmployeeDataException("No name provided !");
        Employee employee = new Employee(employeeDto);
        employeeRepository.save(employee);
        System.out.println(employee);
        return employee;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Integer id) {
        if (employeeRepository.findById(id).isEmpty())
            throw new EmployeeNotFoundException("Employee not found with id: " + id);

        return employeeRepository.findById(id).get();
    }

    public Employee updateEmployee(Integer id, EmployeeDto employeeDto) {
        if (!employeeRepository.existsById(id))
            throw new EmployeeNotFoundException("Employee not found with id: " + id);

        if (employeeDto.name() == null || employeeDto.name().trim().isEmpty())
            throw new InvalidEmployeeDataException("No name provided !");

        return employeeRepository.save(new Employee(employeeDto));
    }

    public List<Employee> searchByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Employee> searchBySalaryGreaterThan(BigDecimal salary) {
        return employeeRepository.findBySalaryGreaterThan(salary);
    }

    public List<Employee> searchBySalaryLessThan(BigDecimal salary) {
        return employeeRepository.findBySalaryLessThan(salary);
    }

    public List<Employee> searchByJoinedBefore(LocalDate date) {
        return employeeRepository.findByJoinedDateBefore(date);
    }

    public List<Employee> searchByJoinedAfter(LocalDate date) {
        return employeeRepository.findByJoinedDateAfter(date);
    }

    public List<Employee> searchByPosition(String position) {
        return employeeRepository.findByPositionIgnoreCase(position);
    }

    public List<Employee> searchByDepartment(String department) {
        return employeeRepository.findByDepartmentIgnoreCase(department);
    }

    public List<Employee> searchBySalaryBetween(BigDecimal minSalary, BigDecimal maxSalary) {
        return employeeRepository.findBySalaryBetween(minSalary, maxSalary);
    }

    public List<Employee> searchByJoinedBetween(LocalDate startDate, LocalDate endDate) {
        return employeeRepository.findByJoinedDateBetween(startDate, endDate);
    }
}