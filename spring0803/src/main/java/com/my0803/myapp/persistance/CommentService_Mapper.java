package com.my0803.myapp.persistance;

import java.util.ArrayList;

import com.my0803.myapp.domain.CommentVo;

//마이바티스 메소드 정의
public interface CommentService_Mapper {

	public int commentinsert(CommentVo cv);
	
	public ArrayList<CommentVo> commentSelectAll(int bidx);
	
}
