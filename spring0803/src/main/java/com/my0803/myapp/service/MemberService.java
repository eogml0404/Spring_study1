package com.my0803.myapp.service;

import com.my0803.myapp.domain.MemberVo;

//DAO 역할을 하는 interface생성
public interface MemberService {
	//interface = 미완성된 메소드로 구성되어 있는 클래스가 아닌 클래스
	
	public int memberInsert(MemberVo mv);
	
	
	
}
