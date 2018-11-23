<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/asset/css/default_css.css">
<title>게시판</title>
</head>
<body>
<jsp:include page="../default_layout/header.jsp"/>
<jsp:include page="../default_layout/nav.jsp"/>
<section>
	<div align="center">
		<table>
			<thead>
				<tr>
					<td class="ss_td">번호</td>
					<td class="n_td">제목</td>
					<td class="s_td">작성자</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${boardInfo}" var="board">
					<tr>
						<td>${board.seq}</td>
						<td>${board.writer}</td>
						<td>${board.title}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</section>
<jsp:include page="../default_layout/footer.jsp"/>
</body>
</html>