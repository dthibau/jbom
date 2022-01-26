<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="org.jbpm.api.ProcessDefinition"%>
<%@page import="java.util.List"%>
<%@page import="org.jbpm.api.ProcessInstance"%>
<%@page import="org.jbpm.api.model.OpenExecution"%>
<%@page import="org.jbpm.api.model.Transition"%>
<%@page import="java.util.Map"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des instances </title>
<link href="stylesheet/theme.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="menu.jsp" />
<h1>Instances démarrées pour le process <%=request.getParameter("pdf") %></h1>
<br/><br/>
<div align="center">
<table  class="dr-table" width="50%">
<tr class="dr-table-subheader"><th class="dr-table-subheader-cell">Nom - date de démarrage</th><th class="dr-table-subheader-cell">Action possible</th></tr>
<% 
List<ProcessInstance> pinstList = (List<ProcessInstance>)request.getAttribute("pInstList");
Map<ProcessInstance,List<Transition>> transitionsMap = (Map<ProcessInstance,List<Transition>>)request.getAttribute("transitionsMap");
for (ProcessInstance pInst : pinstList ) {%>
<tr><td class="dr-table-cell"><%=pInst.getKey() %> - Démarré / </td>
<td class="dr-table-cell">
<% if ( pInst.isEnded() ) { %>
Terminé 
<%} else { %>
Activity : <%=pInst.findActiveActivityNames()%> 
<% for (Transition t : transitionsMap.get(pInst) ) { %>
<br/><a href="signal.jbpm?pInstId=<%=pInst.getId()%>&transitionName=<%=t.getName()%>&pdf=<%=request.getParameter("pdf") %>"><%=t.getName() %></a>
<%} %>
</td>
<% } %>
</tr>
<%} %>
</table>
</div>
</body>
</html>