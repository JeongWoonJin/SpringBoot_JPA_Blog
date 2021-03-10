package com.jwj.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getter setter
@AllArgsConstructor //생성자
@NoArgsConstructor // Bean 생성자
@Builder // 필요한 값만 골라서 선언할 수 있게해줌.
public class Member {
	private int id;
	private String username;
	private String password;
	private String email;
}
