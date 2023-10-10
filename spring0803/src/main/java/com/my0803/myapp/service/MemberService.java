package com.my0803.myapp.service;

import com.my0803.myapp.domain.MemberVo;

//DAO 역할을 하는 interface생성
public interface MemberService {
	//interface = 미완성된 메소드로 구성되어 있는 클래스가 아닌 클래스
	//클래스가 아닌 클래스
	//스프링에서 사용할 메소드 이름을 정의하는곳
	
	public int memberInsert(MemberVo mv);
	
	public MemberVo memberLogin(String memberId,String memberPwd);
	
	public MemberVo memberLogin2(String memberId);
	
}
