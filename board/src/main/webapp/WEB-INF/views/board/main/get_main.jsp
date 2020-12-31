<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="width:80%; margin:0 auto;">
	<h2>상세 보기</h2>
	<form method="post" action="/board/remove" id="removeForm">
		<div class="row gtr-uniform">
			<input type="hidden" name="pageNum"	value="${cri.pageNum} "/>
			<input type="hidden" name="amount" value="${cri.amount} "/>
			<input type="hidden" name="type" value="${cri.type}" />
			<input type="hidden" name="keyword" value="${cri.keyword}" />
			<h4 style="width:100%">NO</h4>
			<div class="col-6 col-12-small">
				<input type="text" name="bno" id="name" value="${board.bno }" placeholder="Title" readonly/>
			</div>
			<h4 style="width:100%;">Title</h4>
			<div class="col-6 col-12-small">
				<input type="text" name="title" id="name" value="${board.title }" placeholder="Title" readonly/>
			</div>
			<h4 style="width:100%;">Content</h4>
			<div class="col-12">
				<textarea name="content" id="message" placeholder="Enter your message" rows="6" readonly>${board.content }</textarea>
			</div>
			<h4 style="width:100%;">Writer</h4>
			<div class="col-6 col-12-small">
				<input type="text" name="writer" id="name" value="${board.writer }" placeholder="Name" readonly/>
			</div>
			<div style="width:100%;"></div>
			<c:forEach var="file" items="${files}">
				<a class="file" href="${file.filePath}">${file.fileName}</a>
			</c:forEach>
			<div class="col-12">
				<ul class="actions">
					<a href="/board/modify${cri.getListLink()}&bno=${board.bno}" class="button primary small write">Modify</a>
					<input type="submit" class="button primary small write" value="삭제"/>
					<a href="/board/list${cri.getListLink()}" class="button small">목록</a>
				</ul>
			</div>
		</div>
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
	<form action="/board/fileDownload" id="fileForm">
		<input type="hidden" name="fileName" value=""/>
		<input type="hidden" name="filePath" value=""/>
	</form>
</div>