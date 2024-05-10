<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<jsp:include page="../layout/header.jsp"></jsp:include>
<div class="container-md">
	<h1>Board Register Page</h1>

	<form action="/board/insert" method="post" enctype="multipart/form-data"
		>
		<div class="mb-3">
			<label for="formGroupExampleInput" class="form-label">Title</label> <input
				type="text" class="form-control" name="title" id="t"
				placeholder="Title">
		</div>
		 <sec:authentication property="principal.uvo.nickName" var="authNick" />
		<div class="mb-3">
			<label for="formGroupExampleInput" class="form-label">Writer</label>
			<input type="text" class="form-control" name="writer" id="w" value="${authNick }"
			 readonly="readonly">
		</div>
		
		
		<div class="mb-3">
			<label class="input-group-text">content</label>
			<textarea class="form-control" name="content"
				aria-label="With textarea"></textarea>
		</div>
		<!--  파일 입력 라인 추가 -->
		 <div class="mb-3">
			<label for="file" class="form-label">files...</label>
			<input type="file" class="form-control" name="files" id="file"	multiple="multiple" style ="display : none"> <br>
			<button type="button" class="btn btn-info" id="trigger">FileUpload...</button>
		</div>
		
	<!-- 	파일 목록 표시라인 -->
		
		<div class="mb-3" id="fileZone">
		
		</div> 
		
		
		<button type="submit" class="btn btn-primary" id="regBtn">등록</button>
	</form>
</div>
 <script type="text/javascript" src="/re/js/boardRegister.js">

</script> 

<jsp:include page="../layout/footer.jsp"></jsp:include>



