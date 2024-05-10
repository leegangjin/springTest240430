<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<jsp:include page="../layout/header.jsp" />
<div class="container-md">

	<table class="table table-hover">
		<thead>
			<tr>
				<th>Email</th>
				<th>NickName</th>
				<th>PassWord</th>
				<th>regDate</th>
				<th>lastLogin</th>				
				<th>authList</th>				
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${ulist }" var="uvo">
				<tr>
					<td>${uvo.email }</td>				
					<td>${uvo.nickName }</td>
					<td>${uvo.pwd }</td>
					<td>${uvo.regDate }</td>
					<td>${uvo.lastLogin }</td>
					<c:forEach items="${uvo.authList }" var="a">
					
					<td>${a.auth }</td>
					</c:forEach>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>





</div>


<jsp:include page="../layout/footer.jsp" />