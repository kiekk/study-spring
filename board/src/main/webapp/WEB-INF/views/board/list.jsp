<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list 페이지</title>
<link rel="stylesheet" href="/resources/assets/css/board.css"/>
</head>
<body>
	<!-- Header -->
	<%@ include file="../include/header.jsp" %>

	<!-- Banner -->
	<%@ include file="../include/banner.jsp" %>
	<div style="margin-bottom:20px;"></div>
	
	<!-- Main -->
	<%@ include file="./main/list_main.jsp" %>
	
	<hr />
	<!-- Pagination -->
	<%@ include file="../include/pagination.jsp" %>
	
	<!-- Footer -->
	<%@ include file="../include/footer.jsp" %>
	
	<!-- Scripts -->
	<%@ include file="../include/src.jsp" %>
</body>
<script>

	var actionForm = $('#actionForm');
	var searchForm = $('#searchForm');
	
	function searchCheck() {
		//console.log("테스트 : " + !searchForm.find("option:selected").val());
		//searchForm.find("option:selected").val()
		//사용자가 선택한 select option의 값을 가지고 있습니다.
		//만약 조건식으로 사용하게 되면, 값이 없을 때 false 값이 있을 때는 true입니다.
		if (!searchForm.find("option:selected").val()) {
			alert('검색 종류를 선택하세요.');
			return false;
		}
		if (!searchForm.find("input[name='keyword']").val()) {
			alert('키워드를 입력하세요.');
			return false;
		}

		searchForm.find("input[name='pageNum']").val("1");
		searchForm.submit();
	}

	$("#keyword").keydown(function(key) {
		if (key.keyCode == 13) {
			searchCheck();
		}
	});
	
	$('a.changePage').on("click", function(e) {
		e.preventDefault();
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		actionForm.submit();
	});

	$('.goGet').on(
			"click",
			function(e) {
				e.preventDefault();
				actionForm.append("<input type='hidden' name='bno' value='"
						+ $(this).attr("href") + "'>");
				actionForm.attr("action", "/board/get");
				actionForm.submit();
			})

	$('#searchForm a').on("click", function(e) {
		e.preventDefault();
		searchCheck();
	})

	/* alert("${result}"); */
	result = "${result}"
	$(document).ready(function() {
		if (result == '' || history.state) {
			return;
		}

		if (parseInt(result) > 0) {
			alert("게시글 " + parseInt(result) + "번이 등록되었습니다.");
		}

		history.replaceState({}, null, null);
	});
</script>
</html>