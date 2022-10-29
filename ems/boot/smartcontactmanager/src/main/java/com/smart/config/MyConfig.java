//package com.smart.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@SuppressWarnings("deprecation")
//@Configuration
//@EnableWebSecurity
//public class MyConfig extends WebSecurityConfigurerAdapter{
//
//@Bean
//public UserDetailsService getUserDetailService()
//{
//	return new UserDetailServiceImpl();
//}
//
//	@Bean
//	public UserDetailsService getUserDetailService1()
//	{
//		return new EmployeeDetailServiceImpl();
//	}
//@Bean
//public BCryptPasswordEncoder passwordEncoder()
//{
//	return new BCryptPasswordEncoder();
//}
//
//
//@Bean
//public DaoAuthenticationProvider authenticationProvider() {
//	 DaoAuthenticationProvider  daoAuthenticationProvider=new  DaoAuthenticationProvider();
//
//	 daoAuthenticationProvider.setUserDetailsService(this.getUserDetailService());
//	 daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//	 return daoAuthenticationProvider;
//
//}
//
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider1() {
//		DaoAuthenticationProvider  daoAuthenticationProvider1=new  DaoAuthenticationProvider();
//
//		daoAuthenticationProvider1.setUserDetailsService(this.getUserDetailService1());
//		daoAuthenticationProvider1.setPasswordEncoder(passwordEncoder());
//		return daoAuthenticationProvider1;
//
//	}
//
////configure method...
//@Override
//protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//	auth.authenticationProvider(authenticationProvider());
//	auth.authenticationProvider(authenticationProvider1());
//
//}
//
////protected void configure1(HttpSecurity http) throws Exception {
////
////		// http.authorizeRequests().antMatchers("/employee/**").hasRole("ADMIN").antMatchers("/user/**").hasRole("USER").antMatchers("/**").permitAll().and().formLogin().loginPage("/signin").loginProcessingUrl("/dologin").defaultSuccessUrl("/user/index").and().csrf().disable();
////		http.authorizeRequests().antMatchers("/employee/**").hasRole("USER").antMatchers("/**").permitAll().and().formLogin().loginPage("/sign").loginProcessingUrl("/emplogin").defaultSuccessUrl("/employee/home").and().csrf().disable();
////	}
//
//
//
//@Override
//protected void configure(HttpSecurity http) throws Exception {
//	http.authorizeRequests().antMatchers("/employee/**").hasRole("EMPLOYEE").antMatchers("/**").permitAll().and().formLogin().loginPage("/sign").loginProcessingUrl("/emplogin").defaultSuccessUrl("/employee/home").and().csrf().disable();
//
//http.authorizeRequests().antMatchers("/employee/**").hasRole("USER").antMatchers("/user/**").hasRole("USER").antMatchers("/**").permitAll().and().formLogin().loginPage("/signin").loginProcessingUrl("/dologin").defaultSuccessUrl("/user/index").and().csrf().disable();
////http.authorizeRequests().antMatchers("/employee/**").hasRole("Employee").antMatchers("/employee/**").permitAll().and().formLogin().loginPage("/employee/index").loginProcessingUrl("/emplogin").defaultSuccessUrl("/employee/home").and().csrf().disable();
//	//http.authorizeRequests().antMatchers("/employee/**").hasRole("EMPLOYEE").antMatchers("/**").permitAll().and().formLogin().and().csrf().disable();
//
//}
//
//
//
//
//
//
//
//
//
//
//
//}
