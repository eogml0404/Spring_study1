package com.my0803.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //컨트롤러 용도의 객체 생성요청

public class MemberController {

	
	//가상경로
	@RequestMapping(value="/member/memberJoin.do")
	public String memberJoin() {
		
		
		
		return "/member/memberJoin";
	}

	
	//가상경로
	@RequestMapping(value="/member/memberLogin.do")
	public String memberLogin() {
		
		
		
		return "/member/memberLogin";
	}
	
	
}
