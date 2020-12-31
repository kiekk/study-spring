<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- Header -->
	<%@ include file="../include/header.jsp" %>

	<!-- Banner -->
	<%@ include file="../include/banner.jsp" %>
	<div style="margin-bottom:20px;"></div>
	
	<!-- Main -->
	<%@ include file="./main/register_main.jsp" %>
	
	<hr />
	
	<!-- Footer -->
	<%@ include file="../include/footer.jsp" %>
	
	<!-- Scripts -->
	<%@ include file="../include/src.jsp" %>
</body>
<script>
	var registerForm = $('#registerForm');
	
	$('.write').on("click", function(e){
		e.preventDefault();
		if(registerForm.find("input[name='title']").val() == ""){
			alert("제목을 입력하세요.");
			return false;
		}
		if(registerForm.find("textarea[name='content']").val() == ""){
			alert("내용을 입력하세요.");
			return false;
		}
		if(registerForm.find("input[name='writer']").val() == ""){
			alert("작성자를 입력하세요.");
			return false;
		}
		
		registerForm.submit();
	});
</script>
</html>