<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@include file="Master.jsp" %>
<h1>Customer "${CustomerId}" Items</h1>
<table border="1">
<tr>
 <th>Id</th><th>Name</th><th>Cost</th>
</tr>
<c:forEach items="${itemList}" var="itm">
<tr>
<td><c:out value="${itm.itemId}"/></td>
<td><c:out value="${itm.itemName}"/></td>
<td><c:out value="${itm.itemCost}"/></td>
</tr>
</c:forEach>
</table>
</body>
</html>