package com.smart.config;

import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@SuppressWarnings("deprecation")
@EnableWebSecurity
@Order(1)
public class MyConfig5  extends WebSecurityConfigurerAdapter{

    @Bean
    public UserDetailsService getUserDetailService1()
    {
        return new UserDetailServiceImpl();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider1() {
        DaoAuthenticationProvider  daoAuthenticationProvider1=new  DaoAuthenticationProvider();

        daoAuthenticationProvider1.setUserDetailsService(this.getUserDetailService1());
        daoAuthenticationProvider1.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider1;

    }

    //configure method...
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        auth.authenticationProvider(authenticationProvider1());

    }

    protected void configure(HttpSecurity http) throws Exception {

http.authorizeRequests().antMatchers("/user/**").hasRole("USER").antMatchers("/user/**").hasRole("USER").antMatchers("/**").permitAll().and().formLogin().loginPage("/signin").loginProcessingUrl("/dologin").defaultSuccessUrl("/user/index").and().csrf().disable();
//http.authorizeRequests().antMatchers("/employee/**").hasRole("Employee").antMatchers("/employee/**").permitAll().and().formLogin().loginPage("/employee/index").loginProcessingUrl("/emplogin").defaultSuccessUrl("/employee/home").and().csrf().disable();
     //   http.authorizeRequests().antMatchers("/employee/**").hasRole("EMPLOYEE").antMatchers("/**").permitAll().and().formLogin().loginPage("/sign").loginProcessingUrl("/emplogin").defaultSuccessUrl("/employee/home").and().csrf().disable();

    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationProvider1)
//            throws Exception {
//        http.authorizeRequests().antMatchers("/user/**").hasRole("USER").antMatchers("/user/**").hasRole("USER").antMatchers("/**").permitAll().and().formLogin().loginPage("/signin").loginProcessingUrl("/dologin").defaultSuccessUrl("/user/index").and().csrf().disable();
//
//        return http.build();
//    }

}
