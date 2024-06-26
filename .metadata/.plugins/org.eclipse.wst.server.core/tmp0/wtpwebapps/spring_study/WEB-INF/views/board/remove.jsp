
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<jsp:include page="../layout/header.jsp"></jsp:include>
<div class="container-md">
<h1>Board remove Page</h1>

<form action="/board/remove" method="post">
	<div class="mb-3">
		<label for="formGroupExampleInput" class="form-label">bno</label> <input
			type="text" class="form-control" name="bno" id="n" value="${bvo.bno }" readonly="readonly"
			placeholder="bno">
	</div>
	<div class="mb-3">
		<label for="formGroupExampleInput" class="form-label">Title</label> <input
			type="text" class="form-control" name="title" id="t" value="${bvo.title }" readonly="readonly"
			placeholder="Title">
	</div>
	<div class="mb-3">
		<label for="formGroupExampleInput" class="form-label">Writer</label> <input
			type="text" class="form-control" name="writer" id="w" value="${bvo.writer }" readonly="readonly"
			placeholder="Writer">
	</div>
	<div class="mb-3">
		<label for="formGroupExampleInput" class="form-label">reg_date</label> <input
			type="text" class="form-control" name="reg_date" id="r" value="${bvo.reg_date }" readonly="readonly"
			placeholder="reg_Date">
	</div>
	<div class="mb-3">
		<label class="input-group-text">content</label>
		<textarea class="form-control" name="content" readonly="readonly"
			aria-label="With textarea">${bvo.content }</textarea> 
	</div>
	
	
	<a href="/board/list"><button type="button" class="btn btn-primary">list</button></a>
	<a href="/board/remove"><button type="button" class="btn btn-danger">삭제</button></a>
</form>
</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>



