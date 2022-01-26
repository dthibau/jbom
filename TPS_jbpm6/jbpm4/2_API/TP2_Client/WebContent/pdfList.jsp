<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.jbpm.api.ProcessDefinition"%>
<%@page import="java.util.List"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des définitions</title>
<link href="stylesheet/theme.css" rel="stylesheet" type="text/css" />
</head>
<body>
<br/><br/>
<h1>Définitions disponibles</h1>
<br/><br/>
<div align="center">
<table class="dr-table" width="50%">
<tr class="dr-table-subheader"><th colspan="3" class="dr-table-subheadercell">Définitions</th></tr>
<% 
List<ProcessDefinition> pdfList = (List<ProcessDefinition>)request.getAttribute("PDF_LIST");
for (ProcessDefinition pdf : pdfList ) {%>
<tr><td class="dr-table-cell"><%=pdf.getName() %> - <%= pdf.getVersion() %></td>
<td class="dr-table-cell"><a href="showInstanceForm.jbpm?pdf=<%=pdf.getId()%>">Démarrer une instance</a></td>
<td class="dr-table-cell"><a href="listProcess.jbpm?pdf=<%=pdf.getId()%>">Visualiser les instances</a></td>
</tr>
<%} %>
</table>
</div>
</body>
</html>