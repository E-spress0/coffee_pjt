<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="resources/asset/css/default_css.css" type="text/css" media="screen">
</head>
<body>
	<tiles:insertAttribute name="header"/>
	<tiles:insertAttribute name="nav"/>
	<div id="container">
		<tiles:insertAttribute name="section"/>
	</div>
	<tiles:insertAttribute name="footer"/>
</body>
</html>