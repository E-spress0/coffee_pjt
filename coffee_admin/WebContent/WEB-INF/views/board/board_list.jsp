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
	<div class="list" align="center">
	<br/>
		<form action="searchList" style="padding-top: 40px;">
<%-- 			<input type="hidden" name="curPage" value="${thisBoardInfo.nowPage}"> --%>
			<input type="hidden" name="curPage" value="1">
			<select class="sBox" name="sType">
				<option value="1" >제목</option>
				<option value="2">내용</option>
				<option value="3">제목 + 내용</option>
				<option value="4">작성자</option>
			</select>
			<input type="search" class="search" name="sData" value="${thisBoardInfo.word}">
			<input type="submit" class="sButton" value="검색">
		</form>
		<c:choose>
			<c:when test="${thisBoardInfo.listCnt ne 0}">
				<table border="1">
					<thead>
						<tr>
							<td class="sx_td">번호</td>
							<td class="xxx_td">제목</td>
							<td class="s_td">작성자</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${boardInfo}" var="board">
							<tr>
								<td>${board.seq}</td>
								<td>${board.title}</td>
								<td>${board.writer}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<button type="button" onclick="list(1);"><<</button>
				<button type="button" onclick="list(${thisBoardInfo.prevPage});"><</button>
				<c:set var="start" value="${thisBoardInfo.startPage}"/>
				<c:set var="end" value="${thisBoardInfo.endPage}"/>
				<c:forEach begin="${start}" end="${end}" varStatus="stat">
					<a href="#" onclick="list(${stat.index});">${stat.index}</a>
				</c:forEach>
				<button type="button"onclick="list(${thisBoardInfo.nextPage});">></button>
				<button type="button" onclick="list(${thisBoardInfo.lastPage});">>></button>
			</c:when>
			<c:otherwise>
				<span style="padding-top: 25px;">게시글이 없습니다.</span>
			</c:otherwise>
		</c:choose>
		<button type="button" onclick="javascript:location.href='/boardWrite'">글쓰기</button>
	</div>
</section>
<jsp:include page="../default_layout/footer.jsp"/>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	function list(page) {
		if("${thisBoardInfo.numberKey}" != "" && "${thisBoardInfo.word}" != ""){
			location.href = "/searchList?curPage="+page+"&sType=${thisBoardInfo.numberKey}&sData=${thisBoardInfo.word}";
		}else{
			location.href = "/board?curPage="+page;
		}
	}
	$(function() {
		if("${thisBoardInfo.numberKey}" != ""){
			$("select[name=sType] option[value=${thisBoardInfo.numberKey}]").attr("selected","selected");
		}
	});
</script>
</html>