package com.my0803.myapp.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my0803.myapp.domain.BoardVo;
import com.my0803.myapp.domain.SearchCriteria;
import com.my0803.myapp.persistance.BoardService_Mapper;

@Service
public class BoardServiceImpl implements BoardService{

	private BoardService_Mapper bsm;
	
	@Autowired
	public BoardServiceImpl(SqlSession sqlSession) {
		
		this.bsm = sqlSession.getMapper(BoardService_Mapper.class);
		
	}
	
	
	@Override
	public int boardInsert(BoardVo bv) {
		
		
		int value = bsm.boardInsert(bv);
		
		int bidx = bv.getBidx();
		//System.out.println("입력값은" + bidx);
		bsm.boardOriginBidxUpdate(bidx);
		
		return value;
	}

	@Override
	public ArrayList<BoardVo> boardSelectAll(SearchCriteria scri) {
		
		int value = (scri.getPage()-1)*15;		
		scri.setPage(value);		
		ArrayList<BoardVo> alist = bsm.boardSelectAll(scri);
		
		return alist;
	}

	@Override
	public int boardTotalCount(SearchCriteria scri) {
		
		int value = bsm.boardTotalCount(scri);
		
		return value;
	}
}
