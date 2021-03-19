package com.jwj.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwj.blog.model.User;
import com.jwj.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	
	//스프링이 로그인 요청을 가로챌 때, username, password변수 2개를 가로채는데,
	//password부분 처리는 알아서 함.
	//username이 DB에 있는지만 확인해주면 됨.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username).orElseThrow(()->{
			return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username);
		});
		return new PrincipalDetail(principal);
		//시큐리티 세션에 유저 정보가 저장된다.
		//PrincipalDetailService를 만든 이유는 기존 시큐리티에서 리턴되는 아이디(user)와 패스워드(콘솔창에 뜬 패스워드) 값이 아닌,
		//우리가 입력한 User객체의 값으로 시큐리티 세션에 저장하기 위해서.
	}
}
