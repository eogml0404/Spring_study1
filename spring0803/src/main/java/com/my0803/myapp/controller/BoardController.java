package com.my0803.myapp.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.my0803.myapp.domain.BoardVo;
import com.my0803.myapp.domain.PageMaker;
import com.my0803.myapp.domain.SearchCriteria;
import com.my0803.myapp.service.BoardService;

@Controller //컨트롤러 용도의 객체 생성요청
@RequestMapping(value="/board") //member를 모두 거친다

public class BoardController {
	
	@Autowired
	private BoardService bs;
	
	@Autowired(required=false)
	private PageMaker pm;
	
	//가상경로
	@RequestMapping(value="/boardWrite.do")
	public String boardWrite() {
		
	
		
		//중간값
		return "/board/boardWrite";
	}
	
	//가상경로
	@RequestMapping(value="/boardWriteAction.do")
	public String boardWriteAction(BoardVo bv,HttpSession session) {
		

	
//		System.out.println("bv" + bv);
//		System.out.println("제목" + bv.getSubject());
//		System.out.println("내용" + bv.getContents());
//		System.out.println("작성자" + bv.getWriter());
//		System.out.println("비번" + bv.getPwd());
		int midx = (int)session.getAttribute("midx");
		bv.setMidx(midx);
		
		int value = bs.boardInsert(bv);
		
		//System.out.println("입력여부" + value);
		
		//중간값
		return "redirect:/index.jsp";
	}
	
	@RequestMapping(value="/boardList.do")
	public String boardList(SearchCriteria scri, Model model) {
		// 게시판 글 목록을 가져와서 페이지에 표시
		
		int totalCount = bs.boardTotalCount(scri);  // 검색 조건에 맞는 게시글의 총 개수를 가져옴
		pm.setScri(scri);  // PageMaker에 검색 조건을 설정
		pm.setTotalCount(totalCount);  // PageMaker에 총 게시글 개수를 설정
		
		ArrayList<BoardVo> alist =  bs.boardSelectAll(scri);  // 검색 조건에 맞는 게시글 목록을 가져옴
		model.addAttribute("alist", alist);  // 모델에 게시글 목록을 추가
		model.addAttribute("pm", pm);  // 모델에 PageMaker를 추가
		
		// 게시판 글 목록 화면으로 이동
		return "/board/boardList";
	}
	
}
