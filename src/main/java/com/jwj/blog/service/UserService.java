package com.jwj.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwj.blog.model.User;
import com.jwj.blog.repository.UserRepository;

//Bean에 등록해줌. IoC를 해줌.
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void 회원가입(User user) {
			userRepository.save(user);
	}
}
