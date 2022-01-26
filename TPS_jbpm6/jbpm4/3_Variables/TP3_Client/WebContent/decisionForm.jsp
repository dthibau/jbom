<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.jbpm.api.ProcessDefinition"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Démarrage du processus decision</title>
<link href="stylesheet/theme.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="menu.jsp" />

<div align="center">
<form action="startDecision.jbpm" method="post">

<table>
<tr>
<td>Nom :</td><td><input type="text" name="nom"/></td></tr>
<tr><td>Type d'opération :</td><td><select name="operation"><option value="débit"/>Débit<option value="credit">Crédit</option></select></td></tr>
<tr>
<td>Montant :</td><td><input type="text" name="amount"/></td></tr>
<tr>
<td colspan="2" align="right"><input value="Démarrer l'instance" type="submit"/></td></tr>
</table>
</form>
</div>
</body>
</html>