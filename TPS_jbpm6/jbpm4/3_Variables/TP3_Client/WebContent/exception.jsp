<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.jbpm.api.ProcessDefinition"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page d'erreur</title>
<link href="stylesheet/theme.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="menu.jsp" />

<% Exception e = (Exception)request.getAttribute("exception");  %>
Exception : <%= e%><br/>
Cause : <%= e.getCause()%><br/>



</body>
</html>