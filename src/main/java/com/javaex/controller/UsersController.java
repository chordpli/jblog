package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UsersService;
import com.javaex.vo.UsersVo;

@Controller
@RequestMapping("/user")
public class UsersController {
	
	@Autowired
	private UsersService uService;
	
	// 로그인 양식
	@GetMapping("/loginForm")
	public String loginForm() {
		System.out.println("UsersController > loginForm()");
		
		return "user/loginForm";
	}
	
	// 로그인 진행
	@PostMapping("/login")
	public String login(@ModelAttribute UsersVo usersVo, HttpSession session) {
		System.out.println("UserController > login()");
		
		UsersVo authUser = uService.login(usersVo);
		
		if(authUser != null) {
			session.setAttribute("authUser", authUser);
			return "redirect:../";
		}else {
			System.out.println("로그인에 실패하였습니다.");
			return "redirect:./loginform?result=fail";
		}
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println("UserController > logout()");
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:../";
	}
	
	// 회원가입 양식
	@GetMapping("/joinForm")
	public String joinForm() {
		System.out.println("UserController > joinForm()");
		
		return "user/joinForm";
	}
	
	// 아이디 중복 확인
	@ResponseBody
	@PostMapping("/idCheck")
	public String idCheck(@RequestBody String id) {
		String result = uService.idCheck(id);
		
		return result;
	}
	
	
	// 회원 가입 진행
	@PostMapping("/join")
	public String join(@ModelAttribute UsersVo userVo) {	// ModelAtribute를 사용하여 
		System.out.println("UserController > join()");		// join폼 안의 UserVo에 해당되는 부분들을 다 가지고옴
		uService.joinUser(userVo);
		
		return "redirect:./joinSuccess";
	}

	// 회원 가입 성공
	@GetMapping("/joinSuccess")
	public String joinSuccess() {
		System.out.println("UserController > joinSuccess");
		
		return "user/joinSuccess";
	}
	

}
