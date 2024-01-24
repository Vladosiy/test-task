package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/","/signin").permitAll()
                .and().authorizeRequests().anyRequest().authenticated()
                .and().formLogin().loginPage("/")
                .and().authorizeRequests().antMatchers(HttpMethod.GET, "/api/**").hasRole("USER")
                .and().authorizeRequests().antMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                .and().authorizeRequests().antMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
                .and().authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                .and().cors().and().csrf().ignoringAntMatchers("/api/**")
                .and().httpBasic();
    }
}
