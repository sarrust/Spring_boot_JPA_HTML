package spring.test_html.payload;

import lombok.Data;

import java.util.Objects;

@Data
public class UpdateEmployee {
    private int id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateEmployee that = (UpdateEmployee) o;
        return id == that.id && salary == that.salary && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(department, that.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, department, salary);
    }

    private String surname;
    private String department;
    private int salary;
    private String status;
}
