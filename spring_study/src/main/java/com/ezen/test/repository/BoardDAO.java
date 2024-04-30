package com.ezen.test.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.test.domain.BoardVO;
import com.ezen.test.domain.PagingVO;

@Mapper
public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO getDetail(int bno);

	int update(BoardVO bvo);

	void remove(int bno);

	void updateRead_count(int bno);

	int getTotal(PagingVO pgvo);

	int selectBno();



	void cmtCountUpdate();

	void fileCountUpdate();

	



}
