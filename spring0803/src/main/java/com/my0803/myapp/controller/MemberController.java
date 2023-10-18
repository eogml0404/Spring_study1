package com.my0803.myapp.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.my0803.myapp.domain.MemberVo;
import com.my0803.myapp.service.MemberService;



@Controller //컨트롤러 용도의 객체 생성요청
@RequestMapping(value="/member") //member를 모두 거친다

public class MemberController {

	@Autowired //객체주입요청
	MemberService ms;
	
	@Autowired //빈에 등록된 암호화 모듈 객체를 주입해 달라고 요청
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
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
		
		//암호화 시킨 거
		String memberPwd2 = bCryptPasswordEncoder.encode(mv.getMemberPwd());
		//암호화된 비밀번호 세팅
		mv.setMemberPwd(memberPwd2);
		
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
			HttpServletRequest request,
			RedirectAttributes rttr) {
		
		
		//기존방식
		//MemberVo mv = ms.memberLogin(memberId, memberPwd);
		MemberVo mv = ms.memberLogin2(memberId);
		//암호 비교방식 - 아이디에 해당하는 비밀번호를 가지고와서 날 것과 비교
		System.out.println(mv.getMemberPwd());
		String path = "";	
		if(mv != null && bCryptPasswordEncoder.matches(memberPwd, mv.getMemberPwd())) {
			rttr.addAttribute("midx",mv.getMidx());
			rttr.addAttribute("memberName",mv.getMemberName());
			
	
			//세이브경로가 없으면 그 경로로 가게끔
			if(request.getSession().getAttribute("saveUrl")!=null) {
				
				// 경로가 /spring/spring 으로 나오기때문에 빼줘야함
				path = (String)request.getSession().getAttribute("saveUrl").toString().substring(request.getContextPath().length()+1);
				
			}else {
				path = "index.jsp";
			}
			
		}else {
			
			path = "member/memberLogin.do";
			
		}
		
		//중간값
			return "redirect:/" + path;
		
	}
	
	@RequestMapping(value="/memberLogout.do")
	public String memberLogout(HttpSession session) {
		session.removeAttribute("midx");
		session.removeAttribute("memberName");
		
		//세션무효화하고 종료
		session.invalidate();
		
		
		//중간값
		return "redirect:/index.jsp";
	}
	
	
	//responsebody를 쓰면 경로가 아니라 객체를 리턴 할 수 있음 원래 경로만 리턴가능
	@ResponseBody
	@RequestMapping(value="/memberIdCheck.do")
	public String memberIdCheck(String memberId) {
		
		String str = null;
		
		//member/memberIdCheck.do?memberId=test88 를 경로를 치면 아이디가 존재하므로 value는 1 출력
		int value = ms.memberIdCheck(memberId);
		
		str = "{\"value\":\""+value+"\"}";
		
		
		
		//Json파일 형태로 리턴해줘야함
		return str;
	}
	
	@RequestMapping(value="/memberList.do")
	public String memberList(Model model) {
		
		ArrayList<MemberVo> alist =	ms.memberList();

		
		model.addAttribute("alist", alist);
	
		return "member/memberList";
	}		
	
	
}
