<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div style="width:80%; margin:0 auto;">
	<h2>Write</h2>
	<form method="post" action="/board/register" id="registerForm" enctype="multipart/form-data">
		<div class="row gtr-uniform">
			<h4 style="width:100%;">Title</h4>
			<div class="col-6 col-12-small">
				<input type="text" name="title" id="name" value="" placeholder="Title" />
			</div>
			<h4 style="width:100%;">Content</h4>
			<div class="col-12">
				<textarea name="content" id="message" placeholder="Enter your message" rows="6"></textarea>
			</div>
			<h4 style="width:100%;">Writer</h4>
			<div class="col-6 col-12-small">
				<input type="text" name="writer" id="name" value="" placeholder="Name" />
			</div>
			<div style="width:100%;"></div>
			<input type="file" name="file">
			<input type="file" name="file">
			<input type="file" name="file">
			<div class="col-12">
				<ul class="actions">
					<input type="submit" class="button primary small write" value="Write"/>
					<a href="/board/list${cri.getListLink()}" class="button small">목록</a>
				</ul>
			</div>
		</div>
	</form>
</div>