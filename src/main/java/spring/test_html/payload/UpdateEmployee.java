package spring.test_html.payload;

import lombok.Data;

@Data
public class UpdateEmployee {
    private int id;
    private String name;
    private String surname;
    private String department;
    private int salary;
}
