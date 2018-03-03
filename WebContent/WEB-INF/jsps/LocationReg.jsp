<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="../scripts/LocForm.js"></script>
<link href="../css/sample.css" rel="stylesheet"></link>
</head>
<body>
<%@include file="Master.jsp"%>
<h1>welcome to Location Register Page!!</h1>
<form name="locForm" action="insertLoc" method="post" onsubmit="return validateLoc();">
		<pre>
Id    : <input type="text" name="locId" /><span id="locIdErr" class="error"></span>
Name  : <input type="text" name="locName" /><span id="locNameErr" class="error"></span>
Type  : <input type="radio" name="locType" value="Urban" />Urban<input type="radio" name="locType" value="Rural" />Rural <span id="locTypeErr" class="error"></span>
 <input type="submit" value="Insert" />
</pre>
</form>
${msg}
</body>
</html>

