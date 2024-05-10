package com.ezen.www.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.domain.UserVO;
import com.ezen.www.handler.PagingHandler;
import com.ezen.www.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user/**")
public class UserController {
	private final UserService usv;
	private final BCryptPasswordEncoder bcEncoder;

	// controller mappoing과 jsp경로가 같으면 void가능.

	@GetMapping("/register")
	public void register() {
	}

	@PostMapping("/register")
	public String register(UserVO uvo) {
		log.info(">>>uvo>>{}", uvo);
		uvo.setPwd(bcEncoder.encode(uvo.getPwd()));
		int isOk = usv.register(uvo);
		return "index";
	}

	@GetMapping("/login")
	public void login() {
	}

	@PostMapping("/login") // 에러메세지를 받는 일
	public String loginPost(HttpServletRequest request, RedirectAttributes re) {
		// 로그인 실패시 다시 로그인 페이지로 돌아와 오류 메세지 전송
		// 다시 로그인 유도
		log.info(">>> errMsg >>{}", request.getAttribute("errMsg"));
		re.addAttribute("email", request.getAttribute("email"));
		re.addAttribute("errMsg", request.getAttribute("errMsg"));
		
		

		return "redirect:/user/login";
	}


	@GetMapping("/userlist")
	public String list(Model m) {
		List<UserVO> ulist = usv.getList();
		
		m.addAttribute("ulist",ulist);
		

		return "/user/userlist";

	}
	@GetMapping("/UserModify")
	public void modify() {}
	
	
	
	@PostMapping("/UserModify")
	public String UserModify(UserVO uvo,HttpServletRequest request, HttpServletResponse response, Principal principal) {
		String email = principal.getName();
		uvo.setEmail(email);
		
		log.info(">>> uvo >>{}",uvo);
		log.info(">>> principal >>{}",principal);
		if(uvo.getPwd() == null || uvo.getPwd().length() == 0) {
			usv.updatename(uvo);
		}else {
			String setPwd = bcEncoder.encode(uvo.getPwd());
			uvo.setPwd(setPwd);
			usv.updatePw(uvo);
		}
		
		
		logout(request, response);
	
		

		return "redirect:/";
	}
	
	@GetMapping("/remove")
	public String udelete(Principal principal,HttpServletRequest request, HttpServletResponse response){
		String id = principal.getName(); //id email		
		
		usv.deleteAuth(id);
		usv.deleteuser(id);
		
		
		
		
		logout(request, response);
		return "redirect:/user/logout";
		
		
	}
	
	@ResponseBody
	@GetMapping("/check/{email}")
	public String check(@PathVariable(value="email",required = false)String email) {
		log.info(email);
		UserVO uvo = usv.usercheck(email);
		return(uvo == null) ? "0" : "1";
		
	
		
	}
	
	
	
	
	
	private void logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication =
				SecurityContextHolder
				.getContext()
				.getAuthentication();
		new SecurityContextLogoutHandler().logout(request, response, authentication);
	}
	

}
