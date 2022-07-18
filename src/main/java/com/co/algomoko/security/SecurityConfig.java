//
//package com.co.algomoko.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//
//@EnableWebSecurity
//public class SecurityConfig {
//
//	@Autowired
//  
//  @Bean //회원가입시 비번 암호화에 필요한 bean 등록 public BCryptPasswordEncoder
//  passwordEncoder() { return new BCryptPasswordEncoder(); }
//
//	// @Bean //실제 인증을 한 이후에 인증이 완료되면 Authentication객체를 반환을 위한 bean등록 // public
//  DaoAuthenticationProvider authenticationProvider(아직안만듬 homeService) { //
//  DaoAuthenticationProvider authenticationProvider = new
//  DaoAuthenticationProvider(); //
//  authenticationProvider.setUserDetailsService(homeService); //
//  authenticationProvider.setPasswordEncoder(passwordEncoder()); // return
//  authenticationProvider; // }
//  
//  //현재설정, 모든 접근 허가
//
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/**").permitAll();
//		return http.build();
//	}
//}
