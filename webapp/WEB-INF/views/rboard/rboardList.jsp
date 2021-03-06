<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<div id="aside">
			<h2>게시판</h2>
			<ul>
				<li><a href="">일반게시판</a></li>
				<li><a href="">댓글게시판</a></li>
			</ul>
		</div>
		<!-- //aside -->

		<div id="content">

			<div id="content-head">
				<h3>게시판</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>게시판</li>
						<li class="last">일반게시판</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="board">
				<div id="list">
					<form action="" method="">
						<div class="form-group text-right">
							<input type="text">
							<button type="submit" id=btn_search>검색</button>
						</div>
					</form>
					<table >
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>글쓴이</th>
								<th>조회수</th>
								<th>작성일</th>
								<th>관리</th>
								<th>groupNo</th>
								<th>orderNo</th>
								<th>depth</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.rbList}" var="rvo">
								<tr>
									<td>${rvo.no}</td>
									
									<!-- 가장 최상위 글 depth 0 나머지 밑으로 로직..-->
									<!-- &nbsp 공백 -->
									<td class="text-left">
										<a href="${pageContext.request.contextPath}/rboard/read?no=${rvo.no}">
										<c:if test="${rvo.depth eq 0}">
											${rvo.title}
										</c:if>
										
										</a>
									</td>
									

									<td>${rvo.name}</td>
									<td>${rvo.hit}</td>
									<td>${rvo.regDate}</td>
									<!-- 자신의 글에만 삭제버튼 if로그인 기록 같으면 보이게하기 -->
									<td>
										<c:if test="${rvo.userNo eq authUser.no}">
											<a href="${pageContext.request.contextPath}/rboard/delete?no=${rvo.no}">[삭제]</a>
										</c:if>
									</td>
									<th>${rvo.groupNo}</th>
									<th>${rvo.orderNo}</th>
									<th>${rvo.depth}</th>
								</tr>
							</c:forEach>
							
						</tbody>
					</table>
		
					<div id="paging">
						<ul>
							<li><a href="">◀</a></li>
							<li><a href="">1</a></li>
							<li><a href="">2</a></li>
							<li><a href="">3</a></li>
							<li><a href="">4</a></li>
							<li class="active"><a href="">5</a></li>
							<li><a href="">6</a></li>
							<li><a href="">7</a></li>
							<li><a href="">8</a></li>
							<li><a href="">9</a></li>
							<li><a href="">10</a></li>
							<li><a href="">▶</a></li>
						</ul>
						
						
						<div class="clear"></div>
					</div>
					<c:if test="${authUser != null}">
						<a id="btn_write" href="${pageContext.request.contextPath}/rboard/writeForm">글쓰기</a>
					</c:if>
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

</html>