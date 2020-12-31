<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.fileDrop {
		width:100%;
		height: 200px;
		border:1px dotted blue;
	}
	small {
		margin-left: 3px;
		font-weight: bold;
		color: gray;
	}
</style>
</head>
<body>
	<h3>Ajax File Upload</h3>
	<div class="fileDrop"></div>
	<div class="uploadedList"></div> 
</body>
<script src="/resources/assets/js/jquery.min.js"></script>
<script>

	function getImageLink(fileName){
		if(!checkImageType(fileName)){
			return;
		}
		var front = fileName.substr(0,12);
		var end = fileName.substr(14);
		
		return front + end;
	}
	
	function getOriginalName(fileName){
		if(checkImageType(fileName)){
			return;
		}
		var idx = fileName.indexOf("_") + 1;
		return fileName.substr(idx);
	}

	function checkImageType(fileName){
	   var pattern = /jpg$|gif$|png$|jpen$/i;
	   //i는 대소문자 구분 X
	   return fileName.match(pattern);
	}
	
	$(".uploadedList").on("click", "small", function(event){
		var that = $(this);
		
		$.ajax({
			url:"deleteFile",
			type:"post",
			data:{fileName:$(this).attr("data-src")},
			dataType:"text",
			success:function(result){
				if(result == 'deleted'){
					alert("deleted");
					that.parent("div").remove();
				}
			}
		});
	});
	
	$(".fileDrop").on("dragenter dragover", function(e){
		e.preventDefault();
	});
	$(".fileDrop").on("drop", function(e){
		e.preventDefault();
		
		var files = e.originalEvent.dataTransfer.files;
		
		var file = files[0];
		
		//console.log(file);
		
		var formData = new FormData();
		
		formData.append("file", file);
		
		$.ajax({
			url: '/uploadAjax',
			data: formData,
			dataType: 'text',
			processData: false,
			contentType: false,
			type: 'POST',
			success: function(data){

				var str = "";
				
				if(checkImageType(data)){
					str = "<div><a href='displayFile?fileName=" + getImageLink(data) + 
							"'><img src='displayFile?fileName=" + data + "'/></a>" +
							"<small data-src=" + data + ">X</div>";
				}else {
					str = "<div><a href='displayFile?fileName=" + data + "'>" + getOriginalName(data) + "</a>" +
							"<small data-src=" + data + ">X</div>";
				}
				$(".uploadedList").append(str);
			}

		});
	});
</script>
</html>