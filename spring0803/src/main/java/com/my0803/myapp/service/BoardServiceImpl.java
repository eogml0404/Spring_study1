package com.my0803.myapp.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my0803.myapp.domain.BoardVo;
import com.my0803.myapp.domain.SearchCriteria;
import com.my0803.myapp.persistance.BoardService_Mapper;

//마이바티스 구현하는 곳
//쿼리 구현하는곳 
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
	
	@Override
	public BoardVo boardContents(int bidx) {
		
		bsm.boardViewCnt(bidx);
		
		BoardVo bv = bsm.boardContents(bidx);
		
		return bv;
		
	}


	@Override
	public int boardModify(BoardVo bv) {
		int value = bsm.boardModify(bv);
		
		//System.out.println("value" + value);
		
		return value;
	}


	@Override
	public int boardDelete(int bidx,String pwd) {
		
		String bidxs = bidx+"";
		HashMap<String,String> hm = new HashMap<>();
		hm.put("bidx", bidxs);
		hm.put("pwd", pwd);
		int value = bsm.boardDelete(hm);
		
		return value;
	}


	@Override
	public int boardReply(BoardVo bv) {
		
		bsm.boardUpdateDepth(bv);
		int value = bsm.boardReply(bv);
		
		return value;
	}



	

}
