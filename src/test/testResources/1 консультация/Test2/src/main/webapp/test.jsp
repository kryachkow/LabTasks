<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<body>

	<sql:query var="rs" dataSource="jdbc/TestDB" scope="request">
		SELECT * FROM users
	</sql:query>

	<c:forEach var="row" items="${rs.rows}">
		${row.id} ${row.login}<br />
	</c:forEach>

</body>
</html>