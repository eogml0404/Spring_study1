package com.my0803.myapp.controller;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.my0803.myapp.domain.CommentVo;
import com.my0803.myapp.service.CommentService;


@RestController   //ResponseBody + Controller
@RequestMapping(value="/comment")
public class CommentController {

	@Autowired
	private	CommentService cs;
	
	//가상경로
	//@ResponseBody //객체로 리턴
	@RequestMapping(value="/commentWrite.do",method=RequestMethod.POST)
	public JSONObject commentWrite(CommentVo cv) {
		
		String str = null;
		int value = 0;
		//str = "{\"value\":\""+value+"\"}";
		value = cs.commentinsert(cv);
		
		JSONObject js = new JSONObject();
		js.put("value", value);
		
		
		
		//중간값
		return js;
	}
	
	@RequestMapping(value="/{bidx}/commentList.do")
	public JSONObject commentList(@PathVariable("bidx") int bidx) {
		
		
		ArrayList<CommentVo> alist = cs.commentSelectAll(bidx);
		
		
		JSONObject js = new JSONObject();
		//제이슨으로 받는다.
		js.put("alist",alist);
		
		
		//중간값
		return js;
		
	}
	//pathVariable URL 경로에서 변수 값을 추출하는 데 사용되는 어노테이션입니다
	@RequestMapping(value="/{cidx}/commentDelete.do")
	public JSONObject commentDelete(@PathVariable("cidx")int cidx) {
		
		
		int value = cs.commentDelete(cidx);

		JSONObject js = new JSONObject();
		//제이슨으로 받는다.
		js.put("value",value);
		
		
		//중간값
		return js;
		
	}	
	
	
	
}
