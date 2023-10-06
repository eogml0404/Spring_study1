package com.my0803.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.my0803.myapp.domain.MemberVo;
import com.my0803.myapp.service.MemberService;



@Controller //컨트롤러 용도의 객체 생성요청
@RequestMapping(value="/member") //member를 모두 거친다

public class MemberController {

	@Autowired
	MemberService ms;
	
	
	//가상경로
	@RequestMapping(value="/memberJoin.do")
	public String memberJoin() {
		
		
		//중간값
		return "/member/memberJoin";
	}

	//가상경로
	@RequestMapping(value="/memberJoinAction.do")
	public String memberJoinAction(MemberVo mv) {
		
		
		//String memberid = request.getParameter("memberId"); -> 기존 jsp
		//System.out.println("아이디 값은?" + mv.getMemberId());
		
		//처리하는 입력 로직작성
		String birth = mv.getMemberYear()+mv.getMemberMonth()+mv.getMemberDay();
		
		mv.setMemberBirth(birth);
		
		int value = ms.memberInsert(mv);
		
		//중간값
		return "redirect:/"; //포워드방식이 아닌 sendRedirect 방식이다.
	}

	
	
	
	//가상경로
	@RequestMapping(value="/memberLogin.do")
	public String memberLogin() {
		
		
		//중간값
		return "/member/memberLogin";
	}
	
	
}
