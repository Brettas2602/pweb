<%@page import="br.edu.ifgoiano.repositorio.AtividadesRepositorio"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
	table, th, td {
		border: 1px solid black;
	}
</style>
</head>
<body>
	<%
		AtividadesRepositorio atividadeRespositorio = new AtividadesRepositorio();
		request.setAttribute("atividades", atividadeRespositorio.listAtividades());
	%>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>ID_Usuario</th>
				<th>Ação</th>
				<th>Data</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${atividades}" var="ativ">
				<tr>
					<td>${ativ.getId()}</td>
					<td>${ativ.getUsuario_id()}</td>
					<td>${ativ.getAcao()}</td>
					<td>${ativ.getData_hora()}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>