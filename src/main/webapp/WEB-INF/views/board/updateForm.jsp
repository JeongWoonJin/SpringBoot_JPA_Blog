<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<div class="container">
	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="title">Title</label> <input type="text" class="form-control" placeholder="Enter title" id="title" value="${board.title }">
			<input type="hidden" id="id" value="${board.id }">
		</div>
		<div class="form-group">
			<label for="content">Content:</label>
			<textarea class="form-control summernote" rows="5" id="content">${board.content }</textarea>
		</div>
	</form>
	<button id="btn-update" class="btn btn-primary">Update</button>
</div>

<script>
      $(".summernote").summernote({
        placeholder: 'Fill in the blank',
        tabsize: 2,
        height: 300
      });
</script>
<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>

