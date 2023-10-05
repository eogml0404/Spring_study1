package com.my0803.myapp.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.sound.midi.SysexMessage;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller //컨트롤러 용도의 빈으로 등록한다는 뜻
public class HomeController {
	
	
	

	//@Resource(name="db") //객체참조변수 이름으로 찾는다
	
	//@Inject //객체 주입
	
	@Autowired //메모리공간안에 같은 타입가입객체를찾는다.
	DriverManagerDataSource dmds;
	
	//@Autowired
	//SqlSession sqlSession; //멤버변수에 주입을 시킨다(dependency inject - DI)
	
	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	//가상경로
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "/views/home";
	}
	
	//가상경로
	@RequestMapping(value = "/introduction.do", method = RequestMethod.GET)
	public String introduction() {
		
		System.out.println("dmds 조수값?" + dmds);
		
		return "/views/introduction";
	}
}
