<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageNum" value="${pageMaker.cri.pageNum}" />

<!-- 모바일 -->
<div class="small-width" style="text-align:center;">
	<c:if test="${pageNum > 1}">
		<a class="changePage button small pageNum" href="${pageNum - 1}">&lt;</a>
	</c:if>
	<span class="button primary small pageNum">${pageNum}</span>
	<c:if test="${pageNum < pageMaker.realEnd}">
		<a class="changePage button small pageNum" href="${pageNum + 1}">&gt;</a>
	</c:if>											
</div>

<!-- 데스크탑 -->
<div class="big-width" style="text-align:center;">
	<c:if test="${pageMaker.prev}">
		<a class="changePage button small pageNum" href="${pageMaker.startPage - 1}">&lt;&lt;</a>
	</c:if>
	<c:if test="${pageNum > 1}">
		<a class="changePage button small pageNum" href="${pageNum - 1}">&lt;</a>
	</c:if>
	<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
		<c:choose>
			<c:when test="${num eq pageNum}">
				<span class="button primary small pageNum">${num}</span>
			</c:when>
			<c:otherwise>
				<a class="changePage button small pageNum" href="${num}">${num}</a>														
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${pageNum < pageMaker.realEnd}">
		<a class="changePage button small pageNum" href="${pageNum + 1}">&gt;</a>
	</c:if>						
	<c:if test="${pageMaker.next}">
		<a class="changePage button small pageNum" href="${pageMaker.endPage + 1}">&gt;&gt;</a>
	</c:if>
</div>

<form id="actionForm" action="/board/list" method="GET">
	<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}" />
	<input type="hidden" name="amount" value="${pageMaker.cri.amount}" />
	<input type="hidden" name="type" value="${pageMaker.cri.type}" />
	<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}" />
</form>