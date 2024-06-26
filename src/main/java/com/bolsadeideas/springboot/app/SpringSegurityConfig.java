/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bolsadeideas.springboot.app;

import com.bolsadeideas.springboot.app.auth.handler.LoginSuccesHandler;
import com.bolsadeideas.springboot.app.models.service.JpaUserDetailsService;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author ayosu
 */
@Configuration
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SpringSegurityConfig {

    @Autowired
    private LoginSuccesHandler successHandler;
    @Autowired
    private JpaUserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
   
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
        
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
       
     

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests().requestMatchers("/", "/index", "/csss/**", "/js/**", "/images/**", "/listar","/locale","/api/clientes/**").permitAll()
                //  .requestMatchers("/ver/**").hasAnyRole("USER")
                //    .requestMatchers("/uploads/**").hasAnyRole("USER")
                //    .requestMatchers("/form/**").hasAnyRole("ADMIN")
                //   .requestMatchers("/eliminar/**").hasAnyRole("ADMIN")
                //  .requestMatchers("/factura/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(successHandler)
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/error_403");

        return http.build();
    }

}
