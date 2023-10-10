package com.my0803.myapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.my0803.myapp.domain.MemberVo;
import com.my0803.myapp.service.MemberService;



@Controller //컨트롤러 용도의 객체 생성요청
@RequestMapping(value="/member") //member를 모두 거친다

public class MemberController {

	@Autowired //객체주입요청
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
	
	//로그인버튼을 누르면 처리하고 메인으로 이동하는 메소드를 만들어보세요
	//로그인이 되지 않았으면 다시 로그인페이지로 가게처리
	
	//가상경로
	@RequestMapping(value="/memberLoginAction.do")
	public String memberLoginAction(@RequestParam("memberId")String memberId,@RequestParam("memberPwd")String memberPwd,
			HttpSession session) {
		
		MemberVo mv = ms.memberLogin(memberId, memberPwd);
		
		if(mv != null) {
		session.setAttribute("midx",mv.getMidx());
		//System.out.println("회원번호는?" + mv.getMidx());
		//System.out.println("회원아이디는?" + mv.getMemberId());
		//System.out.println("회원이름은?" + mv.getMemberName());
		

			
		}
		
		//중간값
		return "redirect:/index.jsp"; 
	}
	
	@RequestMapping(value="/memberLogout.do")
	public String memberLogout(HttpSession session) {
		session.removeAttribute("midx");
		session.removeAttribute("memberName");
		
		session.invalidate();
		
		
		//중간값
		return "redirect:/index.jsp";
	}
	
}
