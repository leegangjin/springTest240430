package com.ezen.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class BoardVO {

	
	//카멜 표기법으로 
	
	private int bno;   //번호
	private String title;  //제목	
	private String writer; //작성자
	private String content; //내용
	private String isDel; 
 	private String regDate; //날짜
	private int readCount; //조호수
	private int cmtQty; //댓글수
	private int hasFile; //파일수
	
}
