package com.ezen.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ezen.www.security.CustomAuthUserService;
import com.ezen.www.security.LoginFailureHandler;
import com.ezen.www.security.LoginSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	//비밀번호 암호화 객체 PasswordEncoder 빈 생성
	@Bean
	public PasswordEncoder bcPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//SuccessHandler 객체 빈 생성 => 사용자 커스텀 객체
	@Bean
	public AuthenticationSuccessHandler authSuccessHandler() {
		return new LoginSuccessHandler(); //아직 생성 안함
	}
	
	//FailureHandler 객체 빈 생성 => 사용자 커스텀 객체
	@Bean
	public AuthenticationFailureHandler authFailureHandler() {
		return new LoginFailureHandler(); //아직 안만듬
	}
	
	
	//userDetail 객체 빈 생성 => 사용자 커스텀 생성
	@Bean
	public UserDetailsService customUserService() {
		return new CustomAuthUserService();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 인증되는 객체로 설정
		auth.userDetailsService(customUserService()).passwordEncoder(bcPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 화면에서 설정되는 권한에 따른 주소 맵핑 설정
		//csrf();
		http.csrf().disable();
		
		//권하 승인요청
		//antMatchers : 접근을 허용하는 값(경로)
		//permitAll : 누구나 접근 가능한 경로
		
		//authenticated() : 인증된 사용자만 가능한 경로
		//auth => hasRole : 권한 확인
		//SER,ADMIN,MANAGER
		http.authorizeRequests()
		.antMatchers("/member/list").hasRole("ADMIN")
		.antMatchers("/","/board/list","/board/detail","/comment/**","/up/**","/re/**","/user/register","/user/login","/user/check/*").permitAll()
		.anyRequest().authenticated();
		
		//커스텀 로그인 페이지 구성
        http.formLogin()
        .usernameParameter("email")
        .passwordParameter("pwd")
        .loginPage("/user/login")
        .successHandler(authSuccessHandler())
        .failureHandler(authFailureHandler());
        
        
        //로그아웃 페이지 반드시 method="post"
        http.logout()
        .logoutUrl("/user/logout")
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
        .logoutSuccessUrl("/");
        

        
	}

}
