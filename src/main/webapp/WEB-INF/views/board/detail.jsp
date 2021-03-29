<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<div class="container">
	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	<c:if test="${board.user.id eq principal.user.id }">
	<a href="/board/updateForm/${board.id}" class="btn btn-info">수정</a>
	<button id="btn-delete" class="btn btn-danger">삭제</button>
	</c:if>
	<br>
	<br>
	<div>
		<h2>${board.title }</h2>
		no : <span id="id">${board.id }</span>&nbsp; writer : <span>${board.user.username }</span> &nbsp; date : <span>${board.createDate }</span>
	</div>
	<hr style="margin-top:10px;">
	<div>
		<div>${board.content }</div>
	</div>
	<hr>
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

