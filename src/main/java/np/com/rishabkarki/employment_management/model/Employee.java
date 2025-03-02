package np.com.rishabkarki.employment_management.model;

import jakarta.persistence.*;
import np.com.rishabkarki.employment_management.dto.EmployeeDto;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate joinedDate;

    @Column(nullable = false)
    private BigDecimal salary;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Position position;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Department department;

    public Employee(EmployeeDto employeeDto) {
        this.name = employeeDto.name();
        this.joinedDate = employeeDto.joinedDate() != null ? employeeDto.joinedDate() : LocalDate.now();
        this.salary = employeeDto.salary() != null ? employeeDto.salary() : BigDecimal.valueOf(10000);
        this.position = employeeDto.position() != null ? employeeDto.position() : Position.INTERN;
        this.department = employeeDto.department() != null ? employeeDto.department() : Department.GENERAL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDate joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", joinedDate=" + joinedDate +
                ", salary=" + salary +
                ", position=" + position +
                ", department=" + department +
                '}';
    }
}
