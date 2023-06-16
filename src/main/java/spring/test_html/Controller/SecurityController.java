package spring.test_html.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class SecurityController {
    @GetMapping("/signin")
    public String getUser() {
        return "signin";
    }

}
