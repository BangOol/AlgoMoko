//package com.co.algomoko.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.co.algomoko.user.service.UserService;
//
//@Configuration
//  
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//	
//	@Autowired
//	private UserService userService;
//	
//	@Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//	@Override
//	  public void configure(WebSecurity web) { 
//	    web.ignoring().antMatchers("/css/**", "/js/**", "/img/**","/favicon.ico", "/resources/**", "/error");
//	  }
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception{
//		http
//		.authorizeRequests()
//		.antMatchers("/contents").permitAll()
//		.antMatchers("/loginForm").permitAll()
//		.antMatchers("/login").permitAll()
//		.and()
//		
//		.formLogin()
//			.loginPage("/loginForm").loginProcessingUrl("/login")
//			.usernameParameter("mid").passwordParameter("mpw")
//			.defaultSuccessUrl("/main").failureUrl("/error")
//			//권한 관련 오류처리
//		.and()
//			.exceptionHandling().accessDeniedPage("/error")
//		//로그아웃 설정, 로그 아웃 후 세션 제거
//		.and()
//			.logout().logoutUrl("/logout").logoutSuccessUrl("/main")
//			.invalidateHttpSession(true)
//		.and()
//			.csrf().disable();	
//		
//	}
//	
//	@Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userService)
////        	.passwordEncoder(userService.passwordEncoder());
//    }
//	
// }
 
