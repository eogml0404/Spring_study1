package com.my0803.myapp.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my0803.myapp.domain.MemberVo;
import com.my0803.myapp.persistance.MemberService_Mapper;

@Service
public class MemberServiceImpl implements MemberService {

	private MemberService_Mapper msm;

	@Autowired	//마이바티스 객체를 주입 받는다.(타입으로 메모리에서 객체를 찾는다.)
	public MemberServiceImpl(SqlSession sqlSession) {
		this.msm = sqlSession.getMapper(MemberService_Mapper.class);
	}
	
	


	@Override
	public int memberInsert(MemberVo mv) {
		
		//mybatis에서 사용하는 메소드를 정의해놓은 인터페이스를 부른다.
	
		int value =  msm.memberInsert(mv);
		
		return value;
	
	}


	@Override
	public MemberVo memberLogin(String memberId, String memberPwd) {

		MemberVo mv = null;
		
		HashMap<String,String> hm = new HashMap<String,String>();
		hm.put("memberId",memberId);
		hm.put("memberPwd",memberPwd);
		
		
		//마이바티스와 연동할 준비
		//마이바티스에서 사용할 메소드를 정의 해놓은 인터페이스
		
		

		mv = msm.memberLogin(hm);
		
		
		
		
		return mv;
	}
	

}
