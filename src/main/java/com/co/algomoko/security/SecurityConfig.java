package com.co.algomoko.security;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.co.algomoko.user.service.LoginFailureHandler;
import com.co.algomoko.user.service.LoginSuccessHandler;
import com.co.algomoko.user.service.UserLoginService;

import lombok.AllArgsConstructor;


@AllArgsConstructor

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{


		
		private UserLoginService userLoginService;
		
		private final LoginFailureHandler failureHandler;
			
		private final LoginSuccessHandler successHandler;
		
		//private PasswordEncoder passwordEncoder;	
		
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
						
	    		 
    

   
    @Override
      public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/","/logout","/css/**", "/js/**", "/img/**","/favicon.ico", "/resources/**", "/error");
      }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
        .csrf().disable()
        
        .authorizeRequests()
	        .antMatchers("/contents/**").permitAll()
	        .antMatchers("/loginForm").permitAll()
	        .antMatchers("/login").permitAll()

	        //.antMatchers("/diary/**").hasRole("u1")
	        //.antMatchers("/diary/**").hasAuthority("u1")
	        .antMatchers("/diary/**").hasAnyAuthority("u1","u0")
	        
	        .antMatchers("/Admin/**").hasAuthority("u0")
	        

        .and()

        .formLogin()
            .loginPage("/loginForm").loginProcessingUrl("/loginForm/login")
            .usernameParameter("mid").passwordParameter("mpw")
            .successHandler(successHandler)
            .defaultSuccessUrl("/main").permitAll()
            //권한 관련 오류처리
        
        //로그아웃 설정, 로그 아웃 후 세션 제거
        .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/main")
            .invalidateHttpSession(true)
        .and().exceptionHandling().accessDeniedPage("/denied");
        

    }
   
//   @Bean
//   public AuthenticationFailureHandler failureHandler() {
//	   return new LoginFailureHandler() ;
//   }
   
   
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userLoginService)
           .passwordEncoder(passwordEncoder());
    }
   
    
 }



//package com.co.algomoko.security;
//
//
//
//import com.co.algomoko.user.service.LoginSuccessHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.co.algomoko.user.service.UserLoginService;
//
//import lombok.AllArgsConstructor;
//
//@Configuration
//
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	private UserLoginService userLoginService;
//
//
////	private LoginSuccessHandler successHandler;
////	private LoginFailureHandler failureHandler;
//
//	//로그인 암호화 기능 빈
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Bean
//	public DaoAuthenticationProvider daoAuthenticationProvider() {
//		// DaoAuthenticationProvider : id와 password로 인증할 수 있도록 하는 구현체
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setPasswordEncoder(passwordEncoder);
//		provider.setUserDetailsService(userLoginService);
//		return provider;
//	}
//
//	//상기한 경로의 기능들은 허가 해줌
//	@Override
//	public void configure(WebSecurity web) {
//		web.ignoring().antMatchers("/","/logout","/css/**", "/js/**", "/img/**","/favicon.ico", "/resources/**", "/error");
//	}
//	//로그인 ,로그아웃 접근권한
//	@Override
//	protected void configure(HttpSecurity http) throws Exception{
//		http
//				.csrf().disable()
//
//				.authorizeRequests()
//				.antMatchers("/contents/**").permitAll()
//				.antMatchers("/loginForm").permitAll()
//				.antMatchers("/login").permitAll()
//				.and()
//
//				.formLogin()
//				.loginPage("/loginForm").loginProcessingUrl("/login")
//				.usernameParameter("mid").passwordParameter("mpw")
////				.successHandler(successHandler).failureHandler(failureHandler)
//				.defaultSuccessUrl("/main").failureUrl("/error")
//				//권한 관련 오류처리
//				.and()
//				.exceptionHandling().accessDeniedPage("/error")
//				//로그아웃 설정, 로그 아웃 후 세션 제거
//				.and()
//				.logout().logoutUrl("/logout").logoutSuccessUrl("/main")
//				.invalidateHttpSession(true);
//
//
//	}
//	//로그인+암호화된 비밀번호
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userLoginService)
//				.passwordEncoder(passwordEncoder());
//
//
//	}
//
//}