<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
	table, th, td {
		border: 1px solid black;
	}
</style>
<body>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Email</th>
				<th>Senha</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${usuarios}" var="usu" varStatus="id">
				<tr class="${id.count % 2 == 0 ? 'table-primary' : 'table-secondary'}">
					<td>${usu.getId()}</td>
					<td>${usu.getNome()}</td>
					<td>${usu.getEmail()}</td>
					<td>${usu.getSenha()}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>