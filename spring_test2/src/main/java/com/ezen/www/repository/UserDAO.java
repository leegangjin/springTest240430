package com.ezen.www.repository;

import java.util.List;

import com.ezen.www.domain.AuthVO;
import com.ezen.www.domain.UserVO;

public interface UserDAO {

	int insert(UserVO uvo);

	int insertAuthInit(String email);

	UserVO selectEmail(String username);

	List<AuthVO> selectAuths(String username);

	int updateLastLogin(String authEmail);

	

	List<UserVO> getList();

	int update(UserVO uvo);

	

	

	void updateName(UserVO uvo);

	void updatePw(UserVO uvo);

	void deleteAuth(String id);

	void deleteUser(String id);

	UserVO usercheck(String email);

	

	

	

	



}
