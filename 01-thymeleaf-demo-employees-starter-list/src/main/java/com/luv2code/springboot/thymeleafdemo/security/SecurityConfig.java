package com.luv2code.springboot.thymeleafdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails john = User.builder()
//                .username("john")
//                .password("{noop}123")
//                .roles("EMPLOYEE")
//                .build();
//        UserDetails mary = User.builder()
//                .username("mary")
//                .password("{noop}123")
//                .roles("EMPLOYEE", "MANAGER")
//                .build();
//        UserDetails susan = User.builder()
//                .username("susan")
//                .password("{noop}123")
//                .roles("EMPLOYEE", "MANAGER", "ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(john, mary, susan);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/employees/delete").hasAnyRole("MANAGER", "ADMIN")
                                .anyRequest()
                                .authenticated())
                .formLogin(form -> form.loginPage("/showMyLoginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
                )
                .logout(logout -> logout.permitAll()
                )
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied"));
        return http.build();
    }
}
