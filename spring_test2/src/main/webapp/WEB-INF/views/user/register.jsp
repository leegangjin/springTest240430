<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>    

    <jsp:include page="../layout/header.jsp" />

<div  class="container-md">
<h1>User join Page</h1>

<form action="/user/register" method="post">



<div class="mb-3">
  <label for="e" class="form-label">E-Mail</label>
  <input type="email" class="form-control" id="e" placeholder="E-mail입력..." name="email">
  <button type="button" id="usercheck">중복체크</button>
</div>
<div class="mb-3">
  <label for="p" class="form-label">PW</label>
  <input type="password" class="form-control" id="p" placeholder="Password 입력..." name="pwd">
</div>
<div class="mb-3">
  <label for="n" class="form-label">닉네임</label>
  <input type="text" class="form-control" id="n" placeholder="이름 입력..." name="nickName">
   
</div>





<button type="submit" class="btn btn-primary">회원가입</button>


</form>
</div>


<script type="text/javascript" src="/re/js/usercheck.js">

</script>

<jsp:include page="../layout/footer.jsp" />