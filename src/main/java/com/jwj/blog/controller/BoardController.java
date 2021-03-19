package com.jwj.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BoardController {

	@GetMapping({"", "/"})
	public String index() { //@AuthenticationPrincipal PrincipalDetail principal<-세션 접근하는 방법
		return "index";
	}
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
