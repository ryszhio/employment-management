package np.com.rishabkarki.employment_management.specification;

import np.com.rishabkarki.employment_management.model.Employee;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeSpecification {

    public static Specification<Employee> getFilteredEmployees(Map<String, String> filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters.containsKey("id")) {
                predicates.add(criteriaBuilder.equal(root.get("id"), filters.get("id")));
            }

            if (filters.containsKey("name")) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + filters.get("name") + "%"));
            }

            if (filters.containsKey("dateBefore")) {
                LocalDate localDate = LocalDate.parse(filters.get("dateBefore"));
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("joinedDate"), localDate));
            }

            if (filters.containsKey("dateAfter")) {
                LocalDate localDate = LocalDate.parse(filters.get("dateAfter"));
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("joinedDate"), localDate));
            }

            if (filters.containsKey("salaryGreaterThan")) {
                BigDecimal salary = BigDecimal.valueOf(Long.parseLong(filters.get("salaryGreaterThan")));
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("salary"), salary));
            }

            if (filters.containsKey("salarySmallerThan")) {
                BigDecimal salary = BigDecimal.valueOf(Long.parseLong(filters.get("salarySmallerThan")));
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("salary"), salary));
            }

            if (filters.containsKey("position")) {
                predicates.add(criteriaBuilder.like(root.get("position"), "%" + filters.get("position").toUpperCase() + "%"));
            }

            if (filters.containsKey("department")) {
                predicates.add(criteriaBuilder.like(root.get("department"), "%" + filters.get("department").toUpperCase() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
