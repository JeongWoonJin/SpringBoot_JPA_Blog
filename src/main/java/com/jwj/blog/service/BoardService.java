package com.jwj.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
				});
		
	}
	
	@Transactional
	public void 글삭제(int id) {
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public void 글수정(int id, Board  requestBoard) {
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
				});
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		System.out.println(board.getTitle());
		System.out.println(board.getContent());
	}
}
