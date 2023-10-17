package com.my0803.myapp.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.my0803.myapp.domain.BoardVo;
import com.my0803.myapp.domain.PageMaker;
import com.my0803.myapp.domain.SearchCriteria;
import com.my0803.myapp.service.BoardService;
import com.my0803.myapp.util.UploadFileUtiles;

@Controller //컨트롤러 용도의 객체 생성요청
@RequestMapping(value="/board") //member를 모두 거친다


	//1. 가상경로 : 컨트롤러 부터만들기 -> redirect : redirect로 페이지.do로 보냄 (파라미터로 받는 값 설정)


public class BoardController {
	
	@Autowired
	private BoardService bs;
	
	@Autowired(required=false)
	private PageMaker pm;
	
	@Resource(name="uploadPath")
	String uploadPath;
	
	
	//가상경로
	@RequestMapping(value="/boardWrite.do")
	public String boardWrite() {
		
	
		
		//중간값
		return "/board/boardWrite";
	}
	
	//가상경로
	@RequestMapping(value="/boardWriteAction.do")
	public String boardWriteAction(BoardVo bv,HttpSession session,@RequestParam("filename")MultipartFile filename) throws Exception{
		
		
		MultipartFile file = filename;
		String uploadedFileName ="";
		
		//파일이름이 없는게 아니면
		if(!file.getOriginalFilename().equals("")) {
			//업로드 시작하겠다.
			
			uploadedFileName = UploadFileUtiles.uploadFile(uploadPath, file.getOriginalFilename(),file.getBytes());
			
						
			
		}
		
		String ip = InetAddress.getLocalHost().getHostAddress();
		
		bv.setFilename2(uploadedFileName);
		System.out.println("파일이름: "+bv.getFilename2());
		bv.setIp(ip);		
		
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
	
	@RequestMapping(value="/boardContents.do")
	public String boardContents(@RequestParam("bidx")int bidx,Model model) {
		
		
		BoardVo bv = bs.boardContents(bidx);

		
		model.addAttribute("bv", bv);
	
		
		//중간값
		return "/board/boardContents";
	}	
	
	@RequestMapping(value="/boardModify.do")
	public String boardModify(@RequestParam("bidx") int bidx,Model model) {
		
		BoardVo bv = bs.boardContents(bidx);
		model.addAttribute("bv",bv);


		//중간값
		return "/board/boardModify";
	}	
	
	@RequestMapping(value="/boardModifyAction.do")
	public String boardModifyAction(BoardVo bv) {
		
		int value = bs.boardModify(bv);
		
		if(value == 1) {
			return "redirect:/board/boardContents.do?bidx="+bv.getBidx();
		}
		else {
			return "redirect:/board/boardModify.do?bidx="+bv.getBidx();
		}
		
		
	}
	@RequestMapping(value="/boardDelete.do")
	public String boardDelete(@RequestParam("bidx") int bidx,Model model) {
		
		BoardVo bv = bs.boardContents(bidx);
		model.addAttribute("bv",bv);
		


		//중간값
		return "/board/boardDelete";
	}	
	
	
	
	
	
	@RequestMapping(value="/boardDeleteAction.do")
	public String boardDeleteAction(BoardVo bv,Model model) {
		
		int bidx = bv.getBidx();
		String pwd = bv.getPwd();

		int value = bs.boardDelete(bidx,pwd);
		
		
		return "redirect:/board/boardList.do";
		
	}	
	
	@RequestMapping(value="/boardReply.do")
	public String boardReply(BoardVo bv,Model model) {
		
		
		//넘긴것을 화면으로 넘김 
		model.addAttribute("bv",bv);


		//중간값
		return "/board/boardReply";
	}	
	
	@RequestMapping(value="/boardReplyAction.do")
	public String boardReplyAction(BoardVo bv, HttpSession session) throws Exception {
		bv.setMidx(((Integer)session.getAttribute("midx")).intValue());
		String ip = InetAddress.getLocalHost().getHostAddress();
		bv.setIp(ip);
		bs.boardReply(bv);
		int bidx = bv.getBidx();
		
		return "redirect:/board/boardContents.do?bidx="+bidx;
	}
	
	
}
