package spring.test_html.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import spring.test_html.entity.Employee;
import spring.test_html.payload.DeleteObject;
import spring.test_html.payload.UpdateEmployee;
import spring.test_html.repository.EmployeeRepository;
import spring.test_html.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/error")
    public String getError(){
        return "notfound";
    }
    @GetMapping("/employees")
    public String getAllEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "index";
    }

    @GetMapping("/employees/{id}")
    public String getEmployeeById(@PathVariable int id, Model model) {
        employeeService.getEmployeeById(id)
                .ifPresent(employee -> model.addAttribute("employees", employee));
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return "redirect:/api/employees";
    }

    @PostMapping("/add")
    public String addEmployee(Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/api/employees";
    }

    @GetMapping("/add/{id}")
    public String addEmployee(@PathVariable("id") Integer id, Model model){
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee",employee.get());
        return "add";
    }

    @GetMapping("/update/{id}")
    public String getUpdateEmployee(@PathVariable("id") Integer id, Model model){
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee",employee.get());
        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable Integer id, UpdateEmployee updateEmployee) {
        Optional<Employee> optionalEmployee = employeeService.getEmployeeById(id);
        if (!optionalEmployee.isPresent()) return "redirect:/api/error";
        Employee employee = optionalEmployee.get();
        employee.setName(updateEmployee.getName());
        employee.setSurname(updateEmployee.getSurname());
        employee.setDepartment(updateEmployee.getDepartment());
        employee.setSalary(updateEmployee.getSalary());
        employeeService.saveEmployee(employee);
        return "redirect:/api/employees";

    }

}