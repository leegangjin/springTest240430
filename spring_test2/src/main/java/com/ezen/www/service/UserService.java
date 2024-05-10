package com.ezen.www.service;

import java.util.List;

import com.ezen.www.domain.UserVO;

public interface UserService {

	int register(UserVO uvo);

	

	List<UserVO> getList();



	






	



	void updatename(UserVO uvo);



	void updatePw(UserVO uvo);



	void deleteAuth(String id);



	void deleteuser(String id);



	UserVO usercheck(String email);



	



	





	

}
