package com.ezen.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.www.domain.UserVO;
import com.ezen.www.repository.CommentDAO;
import com.ezen.www.repository.UserDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
	
	private final UserDAO udao;

	@Override
	@Transactional
	public int register(UserVO uvo) {
		// 권한 추가
		int isOk = udao.insert(uvo);
		return udao.insertAuthInit(uvo.getEmail());
	}

	

	@Override
	public List<UserVO> getList() {
		List<UserVO> userList = udao.getList();
		for(UserVO uvo : userList) {
			uvo.setAuthList(udao.selectAuths(uvo.getEmail()));
		}
		return userList;
	}


	



	@Override
	public void updatename(UserVO uvo) {
		
		udao.updateName(uvo);
		
	}



	@Override
	public void updatePw(UserVO uvo) {
		// TODO Auto-generated method stub
		
		udao.updatePw(uvo);
		
	}



	@Override
	public void deleteAuth(String id) {
		// TODO Auto-generated method stub
		udao.deleteAuth(id);
	}



	@Override
	public void deleteuser(String id) {
		// TODO Auto-generated method stub
		udao.deleteUser(id);
		
	}



	@Override
	public UserVO usercheck(String email) {
		// TODO Auto-generated method stub
		return udao.usercheck(email);
	}








	








	
	

}
