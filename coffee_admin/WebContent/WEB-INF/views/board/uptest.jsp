<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<h1>파일 업로드</h1>
	<form action="fileupload", method="post" enctype="multipart/form-data">
	    <input type="file", name="file" placeholder="파일 선택" /><br/>
	    <input type="submit" value="업로드">
	</form>
	<h1>파일 업로드2</h1>
	<form action="fileuploads", method="post" enctype="multipart/form-data">
	    <input type="file", name="files" placeholder="파일 선택" multiple="multiple"/><br/>
	    <input type="submit" value="업로드">
	</form>
</body>
</html>