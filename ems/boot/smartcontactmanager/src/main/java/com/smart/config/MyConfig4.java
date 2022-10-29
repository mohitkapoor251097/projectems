//package com.smart.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@Order(1)
//public class MyConfig4 {
//    @Bean
//    public UserDetailsService getUserDetailService1()
//    {
//        return new EmployeeDetailServiceImpl();
//    }
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder1()
//    {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider3() {
//        DaoAuthenticationProvider  daoAuthenticationProvider1=new  DaoAuthenticationProvider();
//
//        daoAuthenticationProvider1.setUserDetailsService(this.getUserDetailService1());
//        daoAuthenticationProvider1.setPasswordEncoder(passwordEncoder1());
//        return daoAuthenticationProvider1;
//
//    }
//    @Bean
//    public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
//        http.authenticationProvider(authenticationProvider3());
//
//        http.authorizeRequests().antMatchers("/employee/**").hasRole("EMPLOYEE").antMatchers("/**").permitAll().and().formLogin().loginPage("/sign").loginProcessingUrl("/emplogin").defaultSuccessUrl("/employee/home").and().csrf().disable();
//
//
//
//        return http.build();
//    }
//
//}
