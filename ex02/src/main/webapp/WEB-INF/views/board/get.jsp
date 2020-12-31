<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Board</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<link rel="stylesheet" href="/resources/assets/css/main.css" />
		<link rel="stylesheet" href="/resources/assets/css/board.css" />
	</head>
	<body class="is-preload">
		<!-- Main -->
	<div id="main">
		<div class="wrapper">
			<div class="inner">

				<!-- Elements -->
				<header class="major">
					<h1>Board</h1>
					<p>게시글 상세보기</p>
				</header>
				<!-- Table -->
				<h3>
					<a href="/board/list${cri.getListLink()}" class="button small">목록
						보기</a>
				</h3>
				<div class="content">
					<div class="form">
						<form method="post" action="/board/remove">
							<input type="hidden" name="pageNum" value="${cri.pageNum} " /> <input
								type="hidden" name="amount" value="${cri.amount} " /> <input
								type="hidden" name="type" value="${cri.type} " /> <input
								type="hidden" name="keyword" value="${cri.keyword} " />
							<div class="fields">
								<div class="field">
									<h4>번호</h4>
									<input name="bno" type="text" value="${board.bno}" readonly />
								</div>
								<div class="field">
									<h4>제목</h4>
									<input name="title" type="text" value="${board.title}" readonly />
								</div>
								<div class="field">
									<h4>내용</h4>
									<textarea name="content" rows="6" style="resize: none" readonly>${board.content}</textarea>
								</div>
								<div class="field">
									<h4>작성자</h4>
									<input name="writer" type="text" value="${board.writer}" readonly />
								</div>
							</div>
							<ul class="actions special">
								<li><input type="button" class="button" value="수정"
									onclick="location.href='/board/modify${cri.getListLink()}&bno=${board.bno}'" />
									<input type="submit" class="button" value="삭제" /></li>
							</ul>
							<ul class="icons">
								<li style="display: block">
									<span class="icon solid fa-envelope"></span> 
									<strong>댓글</strong>
								</li>
							</ul>
							<a href="#" style="width: 100%" class="button primary small register">댓글 등록</a>
							<div class="fields register-form" style="display: none">
								<div class="field">
									<h4>작성자</h4>
									<input name="replyer" placeholder="Replyer" type="text" />
								</div>
								<div class="field">
									<h4>댓글</h4>
									<textarea name="reply" rows="6" placeholder="Reply" style="resize: none"></textarea>
								</div>
								<div style="text-align: center" class="field regBtn">
									<a href="#" class="button primary small finish">등록</a> 
									<a href="#" class="button primary small cancel">취소</a>
								</div>
							</div>
							<ul class="icons" style="margin-top:10px;">
								<li style="display: block">
									<span class="icon solid fa-envelope"></span> 
									<strong>등록된 댓글</strong>
								</li>
							</ul>
							<ul class="replies" style="list-style:none;">
								
							</ul>		
							<div class="big-width" style="text-align:center;">
								
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Scripts -->
			<script src="/resources/assets/js/jquery.min.js"></script>
			<script src="/resources/assets/js/jquery.dropotron.min.js"></script>
			<script src="/resources/assets/js/browser.min.js"></script>
			<script src="/resources/assets/js/breakpoints.min.js"></script>
			<script src="/resources/assets/js/util.js"></script>
			<script src="/resources/assets/js/main.js"></script>
			<script src="/resources/assets/js/reply.js"></script>
			<script>
				/* $(document).ready(function(){
					console.log(replyService.add);
				}); */
				
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
					var pageNum = pageNum || 1;
					
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
					  /*console.log("startNum : " + startNum);
						console.log("endNum : " + endNum); */
						//페이지 이동 시 a태그의 class이름은 'changePage'로 설정합니다.
						//각 a태그의 href에는 이동할 페이지 번호만 작성해줍니다.
						
						if(mql.matches){
							//918px보다 작거나 같을 때 들어옵니다.
							//현재 페이지만 나타내고, 이전과 다음으로 -1, +1씩 이동하도록 구현
							if(pageNum > 1)
								str += "<a href='" + (pageNum - 1) + "' class='changePage'><code>&lt;</code></a>";
								
							str += "<code>" + pageNum + "</code>"; 
							
							if(pageNum < realEnd)
								str += "<a href='" + (pageNum + 1) + "' class='changePage'><code>&gt;</code></a>";
						}else {
							//919px 이상일 때 들어옵니다.
							//현재 페이지를 제외한 다른 페이지는 a태그로 클릭 가능
							//10개씩 페이지를 구성합니다.(1~10, 11~21,...)
							//이전 페이지로 이동 가능하다면 < 표시
							//console.log(prev);
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
							//console.log("pageNum : " + pageNum);
							showList(pageNum);
						});
					}
					//첫 페이지 로딩 시에는 게시글 번호만 전달, page는 replyService.getList()에서 default 값 설정
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
							
  							/* for(var i=0, len = list.length || 0; i < len; i++){
								str += "<li>";
								str += "<strong>" + list[i].replyer + "</strong>";
								str += "<p class='reply" + list[i].rno + "'>" + list[i].reply + "</p>";
								str += "<div style='text-align:right;'>";
								str += "<a href='" + list[i].rno + "' class='modify'>수정</a>";
								str += "<a href='" + list[i].rno + "' class='finish' style='display:none;'>수정 완료</a>";
								str += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
								str += "<a href='" + list[i].rno + "' class='remove'>삭제</a>";
								str += "</div>";
 								str += "</li>";
							} */
							
						/* 	for(var i=0;i<list.length;i++){
								str += "<div class='reply_area reply_" + i + "'>";
								str += "<li class='reply'>" + list[i].replyer +"</li>";
								str += "<li class='replyer'><textarea name='content' rows='2' readonly>" + list[i].reply + "</textarea></li>";
								str += "<li class='replyDate'>" + list[i].replyDate;
								str += "<a href='"+ list[i].rno + "' class='deleteReply'>삭제</a>"
								 + "<a href='" + i + "' class='modifyReply'>수정</a></li>";
								str += "</div>";
							} */
							var i = 0;
							$(list).each(function(){
								str += "<div class='reply_area reply_" + i + "'>";
								str += "<li class='reply'>" + this.replyer +"</li>";
								str += "<li class='replyer'><textarea class='" + this.rno + "'name='content' rows='2' readonly>" + this.reply + "</textarea></li>";
								str += "<li class='replyDate'>" + replyService.displayTime(this.replyDate);
								str += "<a href='"+ this.rno + "' class='remove'>삭제</a>";
								str += "<a href='" + this.rno + "' class='modify'>수정</a>";
								str += "<a href='" + list[i].rno + "' class='finish' style='display:none;'>수정 완료</a></li>";
								str += "</div>";
								i++;
							});  
							replyUL.html(str);
							showReplyPage(replyCnt);
							
							//수정버튼
							/* $('.modify').on("click",function(e){
								e.preventDefault();
								
								var rnoValue = $(this).attr("href");
								var replyTag = $(".reply" + rnoValue);
								
								replyTag.html("<textarea class='" + rnoValue + "'>" + replyTag.text() + "</textarea>");
								$(this).hide();
								
								var finishs = $(".finish");
								for(var i=0;i<finishs.length;i++){
									if(finishs[i].getAttribute("href") == rnoValue){
										finishs[i].style.display = "inline";
										break;
									}
								}
							}); */
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
								
								var rnoValue = $(this).attr("href");
								var reply = $('textarea.'+rnoValue).val();

								replyService.update({
									rno: rnoValue,
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
				//댓글 목록 테스트
/* 				var bnoValue = "${board.bno}";
				
				replyService.getList({bno : bnoValue, page : 1}, 
						function(total, list){
							console.log(total);
							console.log(list);
						}
				); */
			</script>		
	</body>
</html>