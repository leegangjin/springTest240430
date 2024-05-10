package com.ezen.www.security;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ezen.www.domain.UserVO;
import com.ezen.www.repository.UserDAO;

public class CustomAuthUserService implements UserDetailsService {

	
	@Inject
	private UserDAO udao;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// username : 로그인을 시도한 email
		//UserDetails : 인증용객체
		// username이 DB에 있는지 확인하여 인증용 객체를 리턴 받으면됨.
		UserVO uvo = udao.selectEmail(username);
		if(uvo==null) {
			throw new UsernameNotFoundException(username);
		}
		uvo.setAuthList(udao.selectAuths(username));
		
//		log.info(">>>userDetails>>{}",uvo);
		
		return new AuthUser(uvo);
	}

}
