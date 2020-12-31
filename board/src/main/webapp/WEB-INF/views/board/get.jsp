<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기 페이지</title>
<link rel="stylesheet" href="/resources/assets/css/board.css" />
</head>
<body>
	<!-- Header -->
	<%@ include file="../include/header.jsp" %>

	<!-- Banner -->
	<%@ include file="../include/banner.jsp" %>
	<div style="margin-bottom:20px;"></div>
	
	<!-- Main -->
	<%@ include file="./main/get_main.jsp" %>
	
	<hr />
	
	<!-- Footer -->
	<%@ include file="../include/footer.jsp" %>
	
	<!-- Scripts -->
	<%@ include file="../include/src.jsp" %>
</body>
<script src="/resources/assets/js/reply.js"></script>
<script>
	$('.file').on("click", function(e){
		e.preventDefault();
		$('#fileForm').find("input[name='filePath']").val($(this).attr("href"));
		$('#fileForm').find("input[name='fileName']").val($(this).text());
		console.log($(this).text());
		$('#fileForm').submit();
	});
</script>
<script>
	$('.register').on("click", function(e){
		e.preventDefault();
		
		$('.register-form').show();
		$(this).hide();
	});
	
	$('.cancel').on("click", function(e){
		e.preventDefault();
		
		$('.register').show();
		$('.register-form').hide();
	});
	$('.finish').on("click", function(e){
		e.preventDefault();
		
		var bno = "${board.bno}";
		var replyValue = $("textarea[name='reply']").val();
		var replyerValue = $("input[name='replyer']").val();
		
		replyService.add({
			reply : replyValue,
			replyer : replyerValue,
			bno : bno
		},
		function(result){
			alert(result + "등록 완료");
			location.reload();
		});
	});
	
	$(document).ready(function(){
		console.log("실행");
		var bnoValue = "${board.bno}";
		var replyUL = $('.replies');
		var replyPageFooter = $('.big-width');
		
		//현재 페이지 기본 값 1 설정
		var pageNum = 1;
		
		//화면 크기 지정
		var mql = window.matchMedia("(max-width: 918px)");
		//listener 설정
		mql.addListener(showReplyPage);
		//페이지번호 출력
		function showReplyPage(replyCnt){
			var str = "";
			var endNum = Math.ceil(pageNum / 10.0) * 10;
			var startNum = endNum - 9;
			var prev = startNum != 1;
			var next = false;
			var realEnd = Math.ceil(replyCnt / 10.0);
			//실제 마지막 페이지 구하기
			if(endNum * 10 >= replyCnt)
				endNum = realEnd;
			else 
				next = true;
			//JS 미디어 쿼리
			//페이지 이동 시 a태그의 class이름은 'changePage'로 설정합니다.
			//각 a태그의 href에는 이동할 페이지 번호만 작성해줍니다.
			
			if(mql.matches){

				if(pageNum > 1)
					str += "<a href='" + (pageNum - 1) + "' class='changePage'><code>&lt;</code></a>";
					
				str += "<code>" + pageNum + "</code>"; 
				
				if(pageNum < realEnd)
					str += "<a href='" + (pageNum + 1) + "' class='changePage'><code>&gt;</code></a>";
			}else {

				if(prev)
					str += "<a href='" + (startNum - 1) + "' class='changePage'><code>&lt;</code></a>";
				//페이지 번호 출력
				for(var i=startNum;i<=endNum;i++){
					//현재 페이지는 a태그 없이 
					if(i == pageNum)
						str += "<code>" + i + "</code>";
					//나머지 페이지는 a태그로
					else
						str += "<a href='" + i + "' class='changePage'><code>" + i + "</code></a>";
				}
				//다음 페이지로 이동 가능하다면 > 표시
				if(next)
					str += "<a href='" + (endNum + 1) + "' class='changePage'><code>&gt;</code></a>";
			}
			//태그 추가
			replyPageFooter.html(str);
			
			$(".changePage").on("click", function(e){
				e.preventDefault();
				//현재 pageNum 변경
				//+, - 연산 하기 위해 int로 형변환
				pageNum = parseInt($(this).attr('href'));

				showList(pageNum);
			});
		}

		showList();
		
		function showList(page){
			replyService.getList({
				bno : bnoValue,
				page : page || 1
				
			}, function(replyCnt, list){
				//댓글이 없으면 없다고 출력
				//있으면 그만큼 반복해서 html()로 출력
				if(list == null || list.length == 0){
					//댓글이 없음
					replyUL.html("댓글이 없습니다.");
					return;
				}
				
				var str = "";
				var i = 0;
				$(list).each(function(){
					str += "<div class='reply_area reply_" + i + "'>";
					str += "<li class='reply'>" + this.replyer +"</li>";
					str += "<li class='replyer'><textarea class='" + this.rno + "'name='content' rows='2' readonly>" + this.reply + "</textarea></li>";
					str += "<li class='replyDate'>" + this.replyDate;
					str += "<a href='"+ this.rno + "' class='remove'>삭제</a>";
					str += "<a href='" + this.rno + "' class='modify'>수정</a>";
					str += "<a href='" + list[i].rno + "' class='finish' style='display:none;'>수정 완료</a></li>";
					str += "</div>";
					i++;
				});  
				replyUL.html(str);
				showReplyPage(replyCnt);

				$('.modify').on("click",function(e){
					e.preventDefault();
					
					//댓글 번호 가져오기
					var rnoValue = $(this).attr("href");
					
					//댓글 내용 readonly 삭제
					$('textarea.'+rnoValue).removeAttr('readonly');
					//수정 버튼 hide()
					$(this).hide();
					//수정 완료 버튼 show()
					$(this).next().show();
					
				});
				
				//수정 완료 버튼
				$('.finish').on("click", function(e){
					e.preventDefault();
					
					var bnoValue = "${board.bno}";
					var rnoValue = $(this).attr("href");
					var reply = $('textarea.'+rnoValue).val();

					replyService.update({
						rno: rnoValue,
						bno: bnoValue,
						reply: reply
						},
						function(result){
							alert(result + "수정 완료");
							location.reload();
						});
				});
				//삭제버튼
				$('.remove').on("click",function(e){
					e.preventDefault();
					
					var rnoValue = $(this).attr("href");
					
					replyService.remove(rnoValue, function(result){
						alert(result + "삭제 완료");
						location.reload();
					});
				});
			});
		}
	});
</script>		
</html>