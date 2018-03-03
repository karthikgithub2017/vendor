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
<h1>Welcome to Vendor RegPage!!</h1>
<form action="insertVen" method="post" enctype="multipart/form-data">
<pre>
Id	: <input type="text" name="venId" placeholder="Enter Id"/>
Name	: <input type="text" name="venName"/>
Email	: <input type="text" name="venMail"/>
Mobile	: <input type="text" name="mobile"/>
Address : <textarea name="address"></textarea>
Location: <select name="loc.locId">
           <option value="-1">--select--</option>
           <c:forEach items="${locListObj}" var="loc">
            <option value="${loc.locId}"><c:out value="${loc.locName}"/></option>
           </c:forEach>
          </select>
File    : <input type="file" name="fObj"/>          
<input type="submit" value="Insert"/>
</pre>
</form>
${msg}<br/>
</body>
</html>