<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE HTML>
<!--
	Drift by Pixelarity
	pixelarity.com | hello@pixelarity.com
	License: pixelarity.com/license
-->
<html>
	<head>
		<title>Untitled</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	</head>
	<body class="landing is-preload">

		<!-- Header -->
		<%@ include file="./include/header.jsp" %>

		<!-- Banner -->
		<%@ include file="./include/banner.jsp" %>
	
		<!-- Main -->
		<%@ include file="./index/main/index_main.jsp" %>
		
		<!-- CTA -->
		<%@ include file="./include/cta_section.jsp" %>
		
		<!-- Footer -->
		<%@ include file="./include/footer.jsp" %>

		<!-- Scripts -->
		<%@ include file="./include/src.jsp" %>
	</body>
</html>