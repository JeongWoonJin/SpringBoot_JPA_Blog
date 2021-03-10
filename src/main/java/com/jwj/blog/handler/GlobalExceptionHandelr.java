package com.jwj.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.jwj.blog.dto.ResponseDto;

@ControllerAdvice //Exception이 발생하면 이 컨트롤러를 실행.
@RestController
public class GlobalExceptionHandelr {
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseDto<String> handleArgumentException(IllegalArgumentException e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	}
}
