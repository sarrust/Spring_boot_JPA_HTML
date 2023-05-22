package spring.test_html.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.test_html.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Override
    @Query(nativeQuery = true, value = "select * from employees order by id")
    List<Employee> findAll();
}
