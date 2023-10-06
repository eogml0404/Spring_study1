package com.my0803.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //컨트롤러 용도의 객체 생성요청
@RequestMapping(value="/board") //member를 모두 거친다

public class BoardController {

	
	
	
	//가상경로
	@RequestMapping(value="/boardWrite.do")
	public String boardWrite() {
		
		
		//중간값
		return "/board/boardWrite";
	}

	
	
	
}
