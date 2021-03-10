package com.jwj.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwj.blog.model.RoleType;
import com.jwj.blog.model.User;
import com.jwj.blog.repository.UserRepository;


@RestController
public class DummyControllerTest {
	
	@Autowired //의존성 주입. DI
	private UserRepository userRepsitory;
	
	@DeleteMapping("dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepsitory.deleteById(id);
		} catch (Exception e) {
			return e+" / 해당 아이디가 존재하지 않습니다.";
		}
		
		return "아이디 "+id+"의 정보가 삭제되었습니다.";
	}
	
	@Transactional //함수 종료시 자동 commit 실행.
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id : "+id);
		System.out.println("password : "+requestUser.getPassword());
		System.out.println("email : "+requestUser.getEmail());
		//람다식
		User user = userRepsitory.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 아이디가 존재하지 않습니다.");
		});
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
//		userRepsitory.save(user);
		//더티체킹을 이용한 update
		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepsitory.findAll();
	}
	
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 1, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		Page<User> pagingUser = userRepsitory.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		User user = userRepsitory.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
			}
		});
		return user;
	}
	
	//http body에 username, password, email 데이터 가지고 request
	@PostMapping("/dummy/join")
	public String join(User u) {
		System.out.println("username : "+u.getUsername());
		System.out.println("password : "+u.getPassword());
		System.out.println("email : "+u.getEmail());
		u.setRole(RoleType.USER);
		userRepsitory.save(u);
		return "회원가입이 완료되었습니다. 반갑습니다.";
	}
}
