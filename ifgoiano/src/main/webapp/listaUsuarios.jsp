<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="br.edu.ifgoiano.entidade.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="br.edu.ifgoiano.repositorio.UsuarioRepositorio"%>
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
	<%
		if (session.getAttribute("usuario") == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else if (request.getAttribute("usuarios") == null){
			UsuarioRepositorio repositorio = new UsuarioRepositorio();
			request.setAttribute("usuarios", repositorio.listUsuarios());
		}
	%>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Email</th>
				<th colspan="2"></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<form action="pesquisar">
					<td></td>
					<td><input type="text" name="pesquisaNome"></td>
					<td><input type="text" name="pesquisaEmail"></td>
					<td><button type="submit">Pesquisar</button></td>
				</form>
			</tr>
			<c:forEach items="${usuarios}" var="usu">
				<tr>
					<td>${usu.getId()}</td>
					<td>${usu.getNome()}</td>
					<td>${usu.getEmail()}</td>
					<td>
						<form action="editar" method="get">
							<button type="submit" name="editar" value="${usu.getId()}">Editar</button>
						</form>
					</td>
					<td>
						<form action="excluir" method="get" onSubmit="return confirm('Tem certeza que deseja excluir esse usuário?')">
							<button type="submit" name="excluir" value="${usu.getId()}">Excluir</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="logout">Deslogar</a>
	<a href="registroDeAtividades.jsp">Registro de Atividades</a>
</body>
</html>