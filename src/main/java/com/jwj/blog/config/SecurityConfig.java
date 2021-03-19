package com.jwj.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jwj.blog.config.auth.PrincipalDetailService;

//빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 해주는 것.
@Configuration
@EnableWebSecurity //시큐리티 필터 추가
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean //IoC가 된다. 리턴 값을 스프링이 관리한다.
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티가 대신 로그인을 해주는데 password를 가로채기 할때
	//해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	//같은해쉬로 암호화 해서 DB에 있는 해쉬랑 비교하게끔 해줘야함.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.csrf().disable() //csrf토큰 비활성화 * 테스트시
		.authorizeRequests()
			.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**")
			.permitAll()
			.anyRequest()
			.authenticated()
		.and()
			.formLogin()
			.loginPage("/auth/loginForm")
			.loginProcessingUrl("/auth/loginProc")
			.defaultSuccessUrl("/");
			// 인증이 되지않은 모든 요청을 loginPage로 연결해주고, loginProcessingUrl의 주소로 가서 인증처리를 해주고, 성공시 defaultSuccessUrl의 주소로 이동한다.
	}
}
