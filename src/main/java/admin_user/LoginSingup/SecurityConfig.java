package admin_user.LoginSingup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .requestMatchers("/signup", "/monthly-gift", "/images/**", "/css/**", "/js/**").permitAll() // Allow access to signup and static resources
            .anyRequest().authenticated() // Require authentication for all other requests
                .and()
            .formLogin()
                .loginPage("/login") // Redirect to the login page if not authenticated
                .permitAll()
                .defaultSuccessUrl("/") // Redirect to this URL after successful login
                .failureUrl("/login?error=true")
                .and()
            .logout()
                .permitAll()
                .logoutSuccessUrl("/") // Redirect to the login page after logout
                .invalidateHttpSession(true) // Invalidate session on logout
                .clearAuthentication(true) // Clear authentication data
            .and()
            .sessionManagement()
                .invalidSessionUrl("/login"); // Redirect to the login page for invalid sessions
        
        return http.build();
    }
}
