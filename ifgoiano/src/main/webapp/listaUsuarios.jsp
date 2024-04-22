<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		<c:forEach items="${usuarios}" var="usu">
			<tr>
				<td>Id: ${usu.getId()}</td>
				<td>Nome: ${usu.getNome()}</td>
				<td>Email: ${usu.getEmail()}</td>
				<td>senha: ${usu.getSenha()}</td>
				<td>Data de Nascimento: ${usu.getDataNascimento()}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>