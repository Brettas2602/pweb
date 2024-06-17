<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="br.edu.ifgoiano.entidade.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="br.edu.ifgoiano.repositorio.UsuarioRepositorio"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<link href="tables.css" rel="stylesheet">
</head>
<body>
	<%
		if (session.getAttribute("usuario") == null) {
			request.setAttribute("mensagem", "Faça login para acessar essa página!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else if (request.getAttribute("usuarios") == null){
			UsuarioRepositorio repositorio = new UsuarioRepositorio();
			request.setAttribute("usuarios", repositorio.listUsuarios());
		}
	%>
	<table class="table">
		<thead>
			<tr>
				<th class="title">ID</th>
				<th class="title">Nome</th>
				<th class="title">Email</th>
				<th colspan="2"></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<form action="pesquisar">
					<td></td>
					<td><input type="text" name="pesquisaNome"
						class="form-control" placeholder="Pesquisar Nome"></td>
					<td><input type="text" name="pesquisaEmail"
						class="form-control" placeholder="Pesquisar Email"></td>
					<td><button type="submit" class="btn btn-primary">Pesquisar</button></td>
				</form>
			</tr>
			<c:forEach items="${usuarios}" var="usu">
				<tr>
					<td>${usu.getId()}</td>
					<td>${usu.getNome()}</td>
					<td>${usu.getEmail()}</td>
					<td>
						<form action="editar" method="get">
							<button type="submit" name="editar" class="btn btn-secondary"
								value="${usu.getId()}">Editar</button>
						</form>
					</td>
					<td>
						<form action="excluir" method="get"
							onSubmit="return confirm('Tem certeza que deseja excluir esse usuário?')">
							<button type="submit" name="excluir" class="btn btn-danger"
								value="${usu.getId()}">Excluir</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="logout" class="btn btn-danger">Deslogar</a>
	<a href="registroDeAtividades.jsp" class="btn btn-info">Registro de
		Atividades</a>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
</body>
</html>