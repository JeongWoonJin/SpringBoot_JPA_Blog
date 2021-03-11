package com.jwj.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jwj.blog.model.User;

//DAO
//자동으로 Bean으로 등록됨.
//@Repository 생략가능
public interface UserRepository extends JpaRepository<User, Integer>{
	//JPA Naming 쿼리
	//SELECT * FROM user WHERE username = ? AND password = ?;
	User findByUsernameAndPassword(String username, String password);
	
//	@Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2;", nativeQuery = true)
//	User login(String username, String password);
}
