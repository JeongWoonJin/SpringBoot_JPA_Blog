package com.jwj.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//RestController : 사용자의 요청에 Data를 응답해주는 컨트롤러로 선언!
@RestController
public class HttpControllerTest {
	
	//인터넷 브라우저 요청은 get방식으로만 가능하다.
	//http://localhost:8090/http/get(select)
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get 요청"+m.getId()+", "+m.getUsername()+", "+m.getPassword();
	}
	
	//http://localhost:8090/http/post(insert)
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) { //@RequestBody 로 오브젝트에 자동으로 맵핑되게끔 해준다.
		return "post 요청";
	}
	
	//http://localhost:8090/http/put(update)
	@PutMapping("/http/put")
	public String putTest() {
		return "put 요청";
	}
	
	//http://localhost:8090/http/delete(delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
