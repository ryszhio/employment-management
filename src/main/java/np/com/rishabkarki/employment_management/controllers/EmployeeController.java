package np.com.rishabkarki.employment_management.controllers;

import np.com.rishabkarki.employment_management.dto.EmployeeDto;
import np.com.rishabkarki.employment_management.exceptions.EmployeeNotFoundException;
import np.com.rishabkarki.employment_management.model.Employee;
import np.com.rishabkarki.employment_management.repository.EmployeeRepository;
import np.com.rishabkarki.employment_management.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/create")
    public ResponseEntity<List<Employee>> createEmployee(@RequestBody List<EmployeeDto> employeeDtos) {
        return ResponseEntity.ok(employeeService.createEmployees(employeeDtos));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employeeList = employeeService.getAllEmployees();
        return ResponseEntity.ok(employeeList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        if (!employeeRepository.existsById(id))
            throw new EmployeeNotFoundException("Employee not found with id: " + id);

        employeeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployee(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok(employeeService.searchEmployee(params));
    }
}