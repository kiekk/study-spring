<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	ifrmae {
		width:0;
		height:0;
		border:0;
	}
</style>
</head>
<body>
	<form id="form1" action="uploadForm" method="post" enctype="multipart/form-data" target="zeroFame">
		<input type="file" name="file"><input type="submit">
	</form>
	<iframe name="zeroFrame"></iframe>
</body>
<script>
	function addFilePath(msg){
		alert(msg);
		document.getElementById("form1").reset();
	}
</script>
</html>