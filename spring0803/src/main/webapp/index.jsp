<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
기본페이지입니다.

<a href="<%=request.getContextPath()%>/index.do">헬로 월드 페이지</a>
<a href="<%=request.getContextPath()%>/introduction.do">자소서 페이지</a>
<a href="<%=request.getContextPath()%>/member/memberJoin.do">회원가입 페이지</a>


</body>
</html>