package com.jwj.blog.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.jwj.blog.model.Board;


public interface BoardRepository extends JpaRepository<Board, Integer>{

}
