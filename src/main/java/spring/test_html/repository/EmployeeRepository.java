package spring.test_html.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.test_html.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Override
    @Query(nativeQuery = true, value = "select * from employees where status != 'D' order by id")
    List<Employee> findAll();

    List<Employee> findByStatus(String status);


    @Query(nativeQuery = true, value = "select * from employees where LOWER(first_name) like %?1% or LOWER(last_name) like %?1%" +
            " or LOWER(department_id) like %?1%")
    List<Employee> findBySearch(String search);

}
