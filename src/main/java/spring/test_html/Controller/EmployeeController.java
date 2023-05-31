package spring.test_html.Controller;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.test_html.entity.Employee;
import spring.test_html.payload.DeleteObject;
import spring.test_html.payload.SearchEmployee;
import spring.test_html.payload.SearchSalary;
import spring.test_html.payload.UpdateEmployee;
import spring.test_html.repository.EmployeeRepository;
import spring.test_html.service.EmployeeService;

import java.util.Optional;

@Controller
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/error")
    public String getError() {
        return "notfound";
    }

    @GetMapping("/employees")
    public String getAllEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "index";
    }

    @GetMapping("/deleteusers")
    public String getAllDeleteEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllDeleteEmployees());
        return "deleteusers";
    }

    @GetMapping("/employees/{id}")
    public String getEmployeeById(@PathVariable int id, Model model) {
        employeeService.getEmployeeById(id)
                .ifPresent(employee -> model.addAttribute("employees", employee));
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployeeById(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return "redirect:/api/employees";
    }

    @GetMapping("/add")
    public String addEmployeePage() {
        return "add";
    }

    @PostMapping("/add")
    public String addEmployee(Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/api/employees";
    }

    @GetMapping("/add/{id}")
    public String addEmployee(@PathVariable("id") Integer id, Model model) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee.get());
        return "add";
    }

    @GetMapping("/update/{id}")
    public String getUpdateEmployee(@PathVariable("id") Integer id, Model model) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee.get());
        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable Integer id, UpdateEmployee updateEmployee) {
        Optional<Employee> optionalEmployee = employeeService.getEmployeeById(id);
        if (!optionalEmployee.isPresent()) {
            return "redirect:/api/error";
        }
        Employee employee = optionalEmployee.get();
        if (employee.getName().equals(updateEmployee.getName()) &&
                employee.getSurname().equals(updateEmployee.getSurname()) &&
                employee.getDepartment().equals(updateEmployee.getDepartment()) &&
                employee.getSalary() == updateEmployee.getSalary()) {
            employeeService.saveEmployee(employee);
            return "redirect:/api/employees";
        } else {
            employee.setName(updateEmployee.getName());
            employee.setSurname(updateEmployee.getSurname());
            employee.setDepartment(updateEmployee.getDepartment());
            employee.setSalary(updateEmployee.getSalary());
            employee.setStatus("E");
            employeeService.saveEmployee(employee);
            return "redirect:/api/employees";
        }
    }

    @PostMapping("/delete")
    public String deleteEmpId(DeleteObject deleteObject) {
        if (deleteObject.getId().isEmpty()) return "redirect:/api/employees";
        employeeService.deleteEmpId(deleteObject);
        return "redirect:/api/employees";
    }

    @PostMapping("/deleteusers")
    public String recoverEmployee(DeleteObject deleteObject) {
        if (deleteObject.getId().isEmpty()) return "redirect:/api/deleteusers";
        employeeService.recoverEmployee(deleteObject);
        return "redirect:/api/deleteusers";
    }

    @PostMapping("/search")
    public String getEmployeeByName(SearchEmployee searchEmployee, Model model) {
        model.addAttribute("employees", employeeService.searchEmployee(searchEmployee));
        return "index";
    }

    @GetMapping("/employees/A")
    public String findStatusA(Model model) {
        model.addAttribute("employees", employeeService.findStatus("A"));
        return "index";
    }
    @GetMapping("/employees/E")
    public String findStatusE(Model model) {
        model.addAttribute("employees", employeeService.findStatus("E"));
        return "index";
    }
    @GetMapping("/employees/AR")
    public String findStatusAR(Model model) {
        model.addAttribute("employees", employeeService.findStatus("AR"));
        return "index";
    }

    @PostMapping("/searchsalary")
    public String findBetweenSalary(SearchSalary searchSalary, Model model) {
        model.addAttribute("employees", employeeService.searchSalary(searchSalary));
        return "index";
    }

}