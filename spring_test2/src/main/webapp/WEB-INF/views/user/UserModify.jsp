<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

    <jsp:include page="../layout/header.jsp" />

<div  class="container-md">
<h1>User Modify Page</h1>



<form action="/user/UserModify" method="post">

        <sec:authorize access="isAuthenticated()">
        <sec:authentication property="principal.uvo.email" var="authEmail" />
        <sec:authentication property="principal.uvo.nickName" var="authNick" />
        
       
        
 <div class="mb-3">
  <label for="e" class="form-label">E-Mail</label>
  <input type="email" class="form-control" id="e" placeholder="E-mail입력..." name="email" value="${authEmail }">
</div>


<div class="mb-3">
  <label for="p" class="form-label">PassWord</label>
  <input type="password" class="form-control" id="p" placeholder="Password 입력..." name="pwd">
</div>
<div class="mb-3">
  <label for="n" class="form-label">NickName</label>
  <input type="text" class="form-control" id="n" placeholder="이름 입력..." name="nickName" value="${authNick }">
</div>




<button type="submit" class="btn btn-primary">수정</button>
<a href="/user/remove?email=${authEmail }">
<button type="button" class="btn btn-danger" onclick="removePopup()">회원탈퇴</button> </a>

        </sec:authorize>
</form>

</div>
        
        



<jsp:include page="../layout/footer.jsp" />


<script type="text/javascript">

function removePopup() {
	
	if(confirm("삭제를 정말로 하시겠습니까?")){	
		
	}else{
		event.preventDefault();
	}
	
}

</script>










