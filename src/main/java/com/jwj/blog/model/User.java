package com.jwj.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //User 클래스가 MySQL에 자동으로 테이블이 생성됨.
//@DynamicInsert //insert시 null인 필드를 제외한 커리문은 만들어준다. 
public class User {

	@Id //Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에 연결된 DB의 넘버링 전략을 사용하겠다는 의미.
	private int id; //시퀀스, auto_increment로 자동 생성되게끔
	
	@Column(nullable = false, length = 30, unique = true)
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;
	 
	@Column(nullable = false, length = 50)
	private String email;
	
	//@ColumnDefault("'user'") //큰따옴표 안에 작은 따옴표 잊지않기.
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 쓰는게 좋다. 오타방지에 좋음.(도메인을 세팅할 수 있다.) // admin, user, manager
	
	@CreationTimestamp //시간이 자동입력됨.
	private Timestamp createDate;
}
