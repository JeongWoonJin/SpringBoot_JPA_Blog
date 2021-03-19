package com.jwj.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwj.blog.model.Board;
import com.jwj.blog.model.User;
import com.jwj.blog.repository.BoardRepository;

//Bean에 등록해줌. IoC를 해줌.
@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	public void 글쓰기(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
}
