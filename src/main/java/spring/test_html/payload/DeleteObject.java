package spring.test_html.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteObject {
    private int id;
}
