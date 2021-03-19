package com.jwj.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jwj.blog.model.User;

import lombok.Data;

//스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고,
//완료가 되면 UserDetails 타입의 오브젝트를 스프링 시큐리티의 고유한 세션 저장소에 저장을 해준다.
@Data
public class PrincipalDetail implements UserDetails {
	private User user; // 객체를 품고 있는것을 콤포지션. extends한것을 상속.

	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지 여부를 리턴.(true : 만료안됨.)
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	// 계정 잠금여부(true : 안잠김.)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호 만료여부(true : 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정이 활성화 됐는지 여부(true : 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}

	// 계정의 권한
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> collectors = new ArrayList<>();
//		collectors.add(new GrantedAuthority(){
//
//			@Override
//			public String getAuthority() {
//				// TODO Auto-generated method stub
//				return "ROLE_"+user.getRole(); //스프링에서 롤 받을때 규칙 ROLE_
//			}
//		}); 
//		이걸 람다식으로 아래와 같이 표시.
		collectors.add(()->{
			return "ROLE_" + user.getRole();
		});

		return collectors;
	}
}
