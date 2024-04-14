/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bolsadeideas.springboot.app;

import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.core.userdetails.User;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.provisioning.InMemoryUserDetailsManager;
    import org.springframework.security.web.SecurityFilterChain;
/**
 *
 * @author ayosu
 */
@Configuration
public class SpringSegurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

         @Bean
        public UserDetailsService userDetailsService()throws Exception{
                    
            InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
            manager.createUser(User
                    .withUsername("user")
                    .password(passwordEncoder().encode("user"))
                    .roles("USER")
                    .build());
             manager.createUser(User
                        .withUsername("admin")
                        .password(passwordEncoder().encode("admin"))
                        .roles("ADMIN","USER")
                        .build());
            
            return manager;
        }
         @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     
          http.authorizeRequests().requestMatchers("/","/index", "/csss/**", "/js/**", "/imagenes/**", "/listar").permitAll()
                  .requestMatchers("/ver/**").hasAnyRole("USER")
                    .requestMatchers("/uploads/**").hasAnyRole("USER")
                    .requestMatchers("/form/**").hasAnyRole("ADMIN")
                    .requestMatchers("/eliminar/**").hasAnyRole("ADMIN")
                    .requestMatchers("/factura/**").hasAnyRole("ADMIN").anyRequest().authenticated().and()
                    .formLogin().loginPage("/login").permitAll()
                    .and()
                    .logout().permitAll().and().exceptionHandling().accessDeniedPage("/error_403");
             
                
           
        return http.build();
        }

}