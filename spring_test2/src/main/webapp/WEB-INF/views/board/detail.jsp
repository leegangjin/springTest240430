<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<jsp:include page="../layout/header.jsp" />

<div class="container-md">
	<h1>Board Detail Page</h1>
	<c:set value="${bdto.bvo }" var="bvo"></c:set>

	<div class="mb-3">
		<label for="n" class="form-label">bno</label> <input type="text"
			class="form-control" id="n" placeholder="bno" name="bno"
			value="${bvo.bno }" readonly="readonly">
	</div>
	<div class="mb-3">
		<label for="t" class="form-label">title</label> <input type="text"
			class="form-control" id="t" placeholder="title" name="title"
			value="${bvo.title }" readonly="readonly">
	</div>
	<div class="mb-3">
		<label for="w" class="form-label">writer</label> <input type="text"
			class="form-control" id="w" placeholder="writer" name="writer"
			value="${bvo.writer }" readonly="readonly">
	</div>
	<div class="mb-3">
		<label for="r" class="form-label">regdate</label> <input type="text"
			class="form-control" id="r" placeholder="reg_date" name="regDate"
			value="${bvo.regDate }" readonly="readonly">
	</div>
	<div class="mb-3">
		<label for="w" class="form-label">content</label>
		<textarea class="form-control" id="c" name="content"
			aria-label="With textarea" readonly="readonly">${bvo.content }</textarea>
	</div>

	<!-- file upload 표시라인 -->
	<c:set value="${bdto.flist }" var="flist" />
	<div class="mb-3">
		<ul class="list-group list-group-flush">
			<!-- 파일 개수만큼 li를 반복하여 파일 표시 타입이 1인경우만 표시 -->
			<!-- li => div => img -->
			<!--  => div => 파일 이름 , 작성일 span size -->
			<c:forEach items="${flist }" var="fvo">
				<li class="list-group-item"><c:choose>
						<c:when test="${fvo.fileType > 0 }">
							<div>
								<img alt="" src="/up/${fvo.saveDir}/${fvo.uuid}_${fvo.fileName}">
							</div>
						</c:when>
						<c:otherwise>
							<div>
								<!-- 일반 파일 다운로드 -->
								<!-- 파일 타입이 0인경우 아이콘 모양 하나 가져와서 넣기 -->
								<a href="/up/${fvo.saveDir}/${fvo.uuid}_${fvo.fileName}"
									download="${fvo.fileName}"> <svg
										xmlns="http://www.w3.org/2000/svg" width="20" height="20"
										fill="currentColor" class="bi bi-file-arrow-down"
										viewBox="0 0 16 16">
						  <path
											d="M8 5a.5.5 0 0 1 .5.5v3.793l1.146-1.147a.5.5 0 0 1 .708.708l-2 2a.5.5 0 0 1-.708 0l-2-2a.5.5 0 1 1 .708-.708L7.5 9.293V5.5A.5.5 0 0 1 8 5" />
						  <path
											d="M4 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zm0 1h8a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1" />
						</svg>
								</a>

							</div>
						</c:otherwise>
					</c:choose>
					<div>
						<!-- 파일이름 작성일 사이즈 -->
						<div>${fvo.fileName }</div>
						${fvo.regDate } <span class="badge rounded-pill text-bg-warning">${fvo.fileSize }Byte</span>


					</div></li>
			</c:forEach>
		</ul>
	</div>
	<br>
	<hr>

	<!-- Comment line -->

	<!-- 댓글 등록 라인 -->
	<div class="input-group mb-3">
		<span class="input-group-text" id="cmtWriter">${authNick }</span> <input
			type="text" id="cmtText" class="form-control"
			placeholder="댓글을 입력하세요." aria-label="Username"
			aria-describedby="basic-addon1">
		<button type="button" id="cmtAddBtn" class="btn btn-secondary">댓글
			등록</button>
	</div>

	<!-- 댓글 출력 라인 -->
	<ul class="list-group list-group-flush" id="cmtListArea">
		<li class="list-group-item">
			<div class="input-group mb-3">
				<div class="fw-bold">Writer</div>
				content
			</div> <span class="badge rounded-pill text-bg-info">regdate</span>
		</li>
	</ul>

	<!-- 댓글 더보기 버 -->
	<div>
		<button type="button" id="moreBtn" data-page="1"
			class="btn btn-outline-success" style="visibility: hidden">MORE
			+</button>
	</div>

	<!-- 모달  -->
	<div class="modal" id="myModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Writer</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<input type="text" id="cmtTextMod" class="form-control">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal" id="cmtModBtn">modify</button>
				</div>
			</div>
		</div>
	</div>

	<!-- <div class="accordion" id="accordionExample">
      <div class="accordion-item">
         <h2 class="accordion-header">
            <button class="accordion-button" type="button"
               data-bs-toggle="collapse" data-bs-target="#collapseOne"
               aria-expanded="true" aria-controls="collapseOne">cno,
               writer, reg_date</button>
         </h2>
         <div id="collapseOne" class="accordion-collapse collapse show"
            data-bs-parent="#accordionExample">
            <div class="accordion-body">
               <strong>댓글 내용표시</strong>
            </div>
         </div>
      </div>
   </div> -->

	<br>
	<hr>

	<%-- <c:if test="${bvo.writer eq ses.id }"> --%>
	<a href="/board/modify?bno=${bvo.bno }"><button type="button"
			class="btn btn-warning">수정</button></a> <a
		href="/board/remove?bno=${bvo.bno }"><button type="button"
			class="btn btn-danger" id="cmtDelBtn">삭제</button></a>
	<%-- </c:if> --%>

	<a href="/board/list"><button type="button" class="btn btn-primary">list</button></a>
	<br> <br> <br> <br> <br> <br>

</div>
<jsp:include page="../layout/footer.jsp" />

<script type="text/javascript">
	const bnoVal = `<c:out value="${bvo.bno}"/>`;
	/* const logVal = `<c:out value="${ses.id}"/>`; */
</script>
<script type="text/javascript" src="/re/js/boardDetailComment.js"></script>

<script type="text/javascript">
	spreadCommentList(bnoVal);
</script>








