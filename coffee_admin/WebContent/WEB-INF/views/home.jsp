<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<h1>
		Hello world!  
	</h1>
	
	<P>  The time on the server is ${serverTime}. </P>
	user info is under line <hr>
	${userInfo}<hr>
	
	<form action="">
		<textarea rows="10" cols="10">113</textarea>
	</form>   
</body>
<script type="text/javascript">
test();
function test() {
	var test1 = 123;
	var test2 = "123";

	(test1 == test2)? alert("o") : alert("x");
	(test1 === test2)? alert("o") : alert("x");
}
</script>
</html>
