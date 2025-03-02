package np.com.rishabkarki.employment_management.dto;

import np.com.rishabkarki.employment_management.model.Department;
import np.com.rishabkarki.employment_management.model.Position;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EmployeeDto(
        String name,
        LocalDate joinedDate,
        BigDecimal salary,
        Position position,
        Department department
) { }
