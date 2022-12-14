<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>

	<form action="controller" method="post">
		<input type="hidden" name="command" value="login"><br>
		<input name="login"><br>
		<input type="password" name="password"><br>
		<input type="submit" value="Login"><br>
	</form>

	${errorMessage}
	<c:remove var="errorMessage" scope="session"/>

	
</body>
</html>
