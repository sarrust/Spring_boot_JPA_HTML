package spring.test_html.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spring.test_html.entity.Employee;
import spring.test_html.payload.UpdateEmployee;
import spring.test_html.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public String saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return "Создано!";
    }

    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }
    public void deleteEmployee(int id) {

        employeeRepository.deleteById(id);
    }


}
