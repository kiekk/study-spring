<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<div class="table-wrapper" style="width:95%; margin:0 auto;">
	<h4>Default</h4>
	<table>
		<thead>
			<tr>
				<th class="bno">번호</th>
				<th class="title">제목</th>
				<th class="writer">작성자</th>
				<th class="regDate">작성일</th>
				<th class="updateDate">수정일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="board" items="${list}">
				<tr>
					<td class="bno">${board.bno}</td>
					<td class="title"><a class="goGet" href="${board.bno}">${board.title}</a></td>
					<td class="writer">${board.writer}</td>
					<td class="regDate">${board.regDate}</td>
					<td class="updateDate">${board.updateDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 검색 -->
	<form action="/board/list" method="get" id="searchForm">
		<div class="fiels">
			<div class="field">
				<div style="text-align:center;">
					<select name="type">
						<c:set var="type" value="${pageMaker.cri.type}"></c:set>
						<option value="" ${empty type? 'selected' : ''}>검색 기준</option>
						<option value="T" ${type == 'T' ? 'selected' : ''}>제목</option>
						<option value="C" ${type == 'C' ? 'selected' : ''}>내용</option>
						<option value="W" ${type == 'W' ? 'selected' : ''}>작성자</option>
						<option value="TC" ${type == 'TC' ? 'selected' : ''}>제목 또는 내용</option>
						<option value="TW" ${type == 'TW' ? 'selected' : ''}>제목 또는 작성자</option>
						<option value="TCW" ${type == 'TCW' ? 'selected' : ''}>전체 검색</option>
					</select>
					<input id="keyword" name="keyword" type="text" value="${pageMaker.cri.keyword}" />
					<input name="pageNum" type="hidden" value="${pageMaker.cri.pageNum}"/>
					<input name="amount" type="hidden" value="${pageMaker.cri.amount}"/>
					<a href="#" class="search button primary icon solid fa-search" style="vertical-align:middle;">검색</a>
				</div>
			</div>
		</div>
	</form>
	<a href="/board/register${pageMaker.cri.getListLink()}" class="button primary small" style="float:right;">Write</a>
</div>