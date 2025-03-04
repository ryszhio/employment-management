package np.com.rishabkarki.employment_management.services;

import np.com.rishabkarki.employment_management.dto.EmployeeDto;
import np.com.rishabkarki.employment_management.exceptions.EmployeeNotFoundException;
import np.com.rishabkarki.employment_management.exceptions.InvalidEmployeeDataException;
import np.com.rishabkarki.employment_management.model.Employee;
import np.com.rishabkarki.employment_management.repository.EmployeeRepository;
import np.com.rishabkarki.employment_management.specification.EmployeeSpecification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    private Employee createEmployee(EmployeeDto employeeDto) {
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

        return employeeRepository.save(new Employee(employeeDto));
    }

    public List<Employee> searchEmployee(Map<String, String> filters) {
        List<Employee> employees = employeeRepository.findAll(EmployeeSpecification.getFilteredEmployees(filters));

        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("Searched employee not found!");
        }

        return employees;
    }

    public List<Employee> createEmployees(List<EmployeeDto> employeeDtos) {
        return employeeDtos.stream()
                .map(this::createEmployee)
                .collect(Collectors.toList());
    }
}