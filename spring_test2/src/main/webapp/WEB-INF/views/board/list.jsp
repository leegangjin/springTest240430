<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp" />

<div class="container-md">

	<h1>Board List Page</h1>

	<!-- 검색라인 -->
	<form class="d-flex" action="/board/list" method="get">
		<div class="input-group mb-3">
			<select name="type" class="form-select form-select-sm"
				aria-label="Small select example">
				<option ${ph.pgvo.type == null ? 'selected' : '' } selected>Open
					this select menu</option>
				<option ${ph.pgvo.type eq 't' ? 'selected' : '' } value="t">게시글</option>
				<option ${ph.pgvo.type eq 'w' ? 'selected' : '' } value="w">작성자</option>
				<option ${ph.pgvo.type eq 'c' ? 'selected' : '' } value="c">내용</option>
				<option ${ph.pgvo.type eq 'tc' ? 'selected' : '' } value="tc">제목+내용</option>
				<option ${ph.pgvo.type eq 'wc' ? 'selected' : '' } value="wc">작성자+내용</option>
				<option ${ph.pgvo.type eq 'tw' ? 'selected' : '' } value="tw">제목+작성자</option>
				<option ${ph.pgvo.type eq 'twc' ? 'selected' : '' } value="twc">전체</option>
			</select> <input name="keyword" type="text" class="form-control"
				aria-label="Text input with dropdown button" placeholder="search..."
				value="${ph.pgvo.keyword}"> <input type="hidden"
				name="pageNo" value="1"> <input type="hidden" name="qty"
				value="10">
			<button type="submit" class="btn btn-outline-dark position-relative">
				검색 <span
					class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
					${ph.totalCount } <span class="visually-hidden">unread
						messages</span>
				</span>
			</button>
		</div>
	</form>


	<table class="table table-hover">
		<thead>
			<tr>
				<th>#</th>
				<th>제목</th>
				<th>작성자</th>
				<th>내용</th>
				<th>날짜</th>
				<th>조회수</th>
				<th>댓글수</th>
				<th>파일수</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${list }" var="bvo">
				<tr>
					<td>${bvo.bno }</td>
					<td><a href="/board/detail?bno=${bvo.bno }">${bvo.title }</a></td>
					<td>${bvo.writer }</td>
					<td>${bvo.content }</td>
					<td>${bvo.regDate }</td>
					<td>${bvo.readCount }</td>
					<td>${bvo.cmtQty }</td>
					<td>${bvo.hasFile }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


	<!-- 페이지네이션 -->

	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">

			<c:if test="${ph.prev }">
				<li class="page-item"${ph.prev  eq false ? 'disabled' : '' }><a class="page-link"
					href="/board/list?pageNo=${ph.startPage-1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a></li>
			</c:if>


			<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
				<li class="page-item ${ph.pgvo.pageNo eq i ? 'active' : '' }"><a class="page-link"
					href="/board/list?pageNo=${i }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">
						${i } </a></li>
			</c:forEach>

			<c:if test="${ph.next }">
				<li class="page-item ${ph.next eq false ? 'disabled' : ''}" ><a class="page-link"
					href="/board/list?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</c:if>
		</ul>
	</nav>


	<jsp:include page="../layout/footer.jsp" />
</div>
