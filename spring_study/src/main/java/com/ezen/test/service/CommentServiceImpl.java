package com.ezen.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.test.domain.CommentVO;
import com.ezen.test.repository.CommentDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j

public class CommentServiceImpl implements CommentService{

	private final CommentDAO cdao;
	private final BoardDAO bdao;

	@Override
	public int post(CommentVO cvo) {
		log.info("comment serivce in");
		return cdao.post(cvo);
	}

	@Override
	public List<CommentVO> getList(int bno) {
		return cdao.getList(bno);
	}

	@Override
	public int modify(CommentVO cvo) {
		return cdao.update(cvo);
	}

	@Override
	public int delete(int cno) {
		return cdao.delete(cno);
	}
	
}
