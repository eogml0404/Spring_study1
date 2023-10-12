package com.my0803.myapp.service;

import java.util.ArrayList;

import com.my0803.myapp.domain.BoardVo;
import com.my0803.myapp.domain.SearchCriteria;

//게시판 메소드를 정의해놓은 인터페이스
public interface BoardService {

	
	public int boardInsert(BoardVo bv);
	
	public ArrayList<BoardVo> boardSelectAll(SearchCriteria  scri);
	
	public int boardTotalCount(SearchCriteria  scri);
	
}
