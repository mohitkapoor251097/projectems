package com.smart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SuppressWarnings("deprecation")

@EnableWebSecurity
@Configuration
public class MyConfig2  extends  WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService getUserDetailService()
    {
        return new EmployeeDetailServiceImpl();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder1()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider  daoAuthenticationProvider=new  DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(this.getUserDetailService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder1());
        return daoAuthenticationProvider;

    }

    //configure method...
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        auth.authenticationProvider(authenticationProvider());

    }

    protected void configure(HttpSecurity http) throws Exception {

//	http.authorizeRequests().antMatchers("/employee/**").hasRole("USER").antMatchers("/emplogin/**").hasRole("USER").antMatchers("/**").permitAll().and().formLogin().loginPage("/signin").loginProcessingUrl("/dologin").defaultSuccessUrl("/user/index").and().csrf().disable();
//http.authorizeRequests().antMatchers("/employee/**").hasRole("Employee").antMatchers("/employee/**").permitAll().and().formLogin().loginPage("/employee/index").loginProcessingUrl("/emplogin").defaultSuccessUrl("/employee/home").and().csrf().disable();
        http.authorizeRequests().antMatchers("/employee/**").hasRole("EMPLOYEE").antMatchers("/**").permitAll().and().formLogin().loginPage("/sign").loginProcessingUrl("/emplogin").defaultSuccessUrl("/employee/index").and().csrf().disable();

    }

}
