package com.jwj.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob //대용량 데이터
	private String content; //섬머노트 라이브러리 <html>태그가 섞여서 디자인 됨.
	
	//@ColumnDefault("0") //디폴트 값 입력
	private int count; //조회수
	
	@ManyToOne(fetch = FetchType.EAGER) //Many : Board, User : One
	@JoinColumn(name = "userId") //FK 설정.
	private User user; //DB는 오브젝트를 저장 x. FK, 자바는 오브젝트 저장 o.
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) //연관관계의 주인이 아님.
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
}
