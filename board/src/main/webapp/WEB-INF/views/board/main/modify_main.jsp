<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="width:80%; margin:0 auto;">
	<h2>수정 하기</h2>
	<form method="post" action="/board/modify" id="actionForm" enctype="multipart/form-data">
		<div class="row gtr-uniform">
			<input type="hidden" name="pageNum"	value="${cri.pageNum} "/>
			<input type="hidden" name="amount" value="${cri.amount} "/>
			<input type="hidden" name="type" value="${cri.type}" />
			<input type="hidden" name="keyword" value="${cri.keyword}" />
			<h4 style="width:100%">NO</h4>
			<div class="col-6 col-12-small">
				<input type="text" name="bno" id="name" value="${board.bno }" placeholder="Title" readonly/>
			</div>
			<h4 style="width:100%;">*Title</h4>
			<div class="col-6 col-12-small">
				<input type="text" name="title" id="name" value="${board.title }" placeholder="Title"/>
			</div>
			<h4 style="width:100%;">*Content</h4>
			<div class="col-12">
				<textarea name="content" id="message" placeholder="Enter your message" rows="6">${board.content }</textarea>
			</div>
			<h4 style="width:100%;">Writer</h4>
			<div class="col-6 col-12-small">
				<input type="text" name="writer" id="name" value="${board.writer }" placeholder="Name" readonly/>
			</div>
			<div style="width:100%;"></div>
			<input type="file" name="file">
			<input type="file" name="file">
			<input type="file" name="file">
			<div class="col-12">
				<ul class="actions">
					<input type="submit" class="button primary small write" value="수정완료"/>
					<a href="/board/list${cri.getListLink() }" class="button small">목록</a>
				</ul>
			</div>
		</div>
	</form>
</div>