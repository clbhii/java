<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form:form action="add" method="post" modelAttribute="person">
    id:<form:input path="user.id"/><form:errors path="user.id"/><br>
    name:<form:input path="user.name"/><form:errors path="user.name"/><br>
    birth:<form:input path="user.birth"/><form:errors path="user.birth"/>
    <input type="submit" value="submit">
</form:form>

</body>
</html>