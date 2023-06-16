package spring.test_html.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import spring.test_html.repository.EmployeeRepository;

@Configuration
public class SecurityConfig  {

    @Autowired
    EmployeeRepository employeeRepository;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().authenticated()
                ).formLogin(form -> form.loginPage("/api/signin").permitAll());
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager () {
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }

}
