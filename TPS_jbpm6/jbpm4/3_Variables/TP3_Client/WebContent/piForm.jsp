<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.jbpm.api.ProcessDefinition"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulaire de création d'une instance</title>
<link href="stylesheet/theme.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="menu.jsp" />

<%ProcessDefinition pdf = ((ProcessDefinition)request.getAttribute("pdf")); %>
<form action="createInstance.jbpm" method="post">
<input type="hidden" name="pdf" value="<%=pdf.getId()%>"/>
<table>
<tr>
<td>Définition :</td><td><%=pdf.getName() %></td></tr>
<tr>
<td>Clé de l'instance :</td><td><input type="text" name="pInstName"/></td></tr>
<tr>
<td colspan="2" align="right"><input value="Créer une instance" type="submit"/></td></tr>
</table>
</form>
</body>
</html>