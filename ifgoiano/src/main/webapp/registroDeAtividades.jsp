<%@page import="br.edu.ifgoiano.repositorio.AtividadesRepositorio"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<link href="tables.css" rel="stylesheet">
</head>
<body>
	<%
		if (session.getAttribute("usuario") == null) {
			request.setAttribute("mensagem", "Faça login para acessar essa página!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else{
			AtividadesRepositorio atividadeRespositorio = new AtividadesRepositorio();
			request.setAttribute("atividades", atividadeRespositorio.listAtividades());
		}
	%>
	<table class="table">
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
	<a href="listaUsuarios.jsp" class="btn btn-primary">Voltar</a>
	
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>