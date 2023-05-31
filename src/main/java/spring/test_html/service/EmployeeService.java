package spring.test_html.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.test_html.entity.Employee;
import spring.test_html.payload.DeleteObject;
import spring.test_html.payload.SearchEmployee;
import spring.test_html.payload.SearchSalary;
import spring.test_html.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> getAllDeleteEmployees() {
        return employeeRepository.findByStatus("D");
    }

    public String saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return "Создано!";
    }

    public void deleteEmpId(DeleteObject deleteObject) {
        String[] split = deleteObject.getId().split(",");
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            list.add(Integer.parseInt(split[i]));
        }
        for (int i : list) {
            Optional<Employee> optionalEmployee = employeeRepository.findById(i);
            if (!optionalEmployee.isPresent()) System.out.println("Not found!!!");
            Employee employee = optionalEmployee.get();
            employee.setStatus("D");
            employeeRepository.save(employee);
        }
    }

    public void recoverEmployee(DeleteObject deleteObject) {
        String[] split = deleteObject.getId().split(",");
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            list.add(Integer.parseInt(split[i]));
        }
        for (int i : list) {
            Optional<Employee> optionalEmployee = employeeRepository.findById(i);
            if (!optionalEmployee.isPresent()) System.out.println("Not found!!!");
            Employee employee = optionalEmployee.get();
            employee.setStatus("AR");
            employeeRepository.save(employee);
        }
    }

    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> searchEmployee(SearchEmployee searchEmployee) {
        return employeeRepository.findBySearch(searchEmployee.getSearch());
    }

    public List<Employee> searchSalary(SearchSalary searchSalary) {
        return employeeRepository.findByBetweenSalary(searchSalary.getMin(), searchSalary.getMax());
    }

    public List<Employee> findStatus(String status) {
        return employeeRepository.findByEmployeeStatus(status);
    }
}
