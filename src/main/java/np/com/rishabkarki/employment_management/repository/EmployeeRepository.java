package np.com.rishabkarki.employment_management.repository;

import np.com.rishabkarki.employment_management.model.Department;
import np.com.rishabkarki.employment_management.model.Employee;
import np.com.rishabkarki.employment_management.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByNameContainingIgnoreCase(String name);

    @Query("SELECT e FROM Employee e WHERE LOWER(e.department) LIKE LOWER(CONCAT('%', :department, '%'))")
    List<Employee> findByDepartmentIgnoreCase(@Param("position") String position);

    @Query("SELECT e FROM Employee e WHERE LOWER(e.position) LIKE LOWER(CONCAT('%', :position, '%'))")
    List<Employee> findByPositionIgnoreCase(@Param("position") String position);

    List<Employee> findBySalaryGreaterThan(BigDecimal salary);

    List<Employee> findBySalaryLessThan(BigDecimal salary);

    List<Employee> findByJoinedDateAfter(LocalDate date);

    List<Employee> findByJoinedDateBefore(LocalDate date);

    List<Employee> findBySalaryBetween(BigDecimal minSalary, BigDecimal maxSalary);

    List<Employee> findByJoinedDateBetween(LocalDate startDate, LocalDate endDate);
}