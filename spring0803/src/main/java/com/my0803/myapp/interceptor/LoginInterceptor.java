package com.my0803.myapp.interceptor;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



//로그인한후에 세션에 회원번호와 회원이름을 담는다.
public class LoginInterceptor extends HandlerInterceptorAdapter {
		
	@Override	//핸들러(메소드) 이후에 처리기능
	public void postHandle(HttpServletRequest request, HttpServletResponse response,Object handler,ModelAndView modelAndView) throws Exception{
				
	 String midx = (String) modelAndView.getModel().get("midx");	 
	 String memberName = (String) modelAndView.getModel().get("memberName");
	
	 modelAndView.getModel().clear(); 
	
	 if(midx != null){
		
		 request.getSession().setAttribute("midx", midx);		
		 request.getSession().setAttribute("memberName", memberName);		
		}	
	}
	 @Override	//핸들러(메소드)에 들어가기전에 세션을 깨끗하게 정리한다.
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
			
		 HttpSession session = request.getSession(false);		 
		 
		 if(session.getAttribute("midx") != null){
			 session.removeAttribute("midx");		
			 session.removeAttribute("memberName");			 
		 }		 
		 return true;		
	 	}
	 }
