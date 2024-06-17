<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script src="assets/js/color-modes.js"></script>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.122.0">
<title>IF Goiano</title>

<link rel="canonical"
	href="https://getbootstrap.com/docs/5.3/examples/sign-in/">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">

<link href="assets/dist/css/bootstrap.min.css" rel="stylesheet">



<!-- Custom styles for this template -->
<link href="sign-in.css" rel="stylesheet">
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">
	<%
		if (session.getAttribute("usuario") == null) {
			request.setAttribute("mensagem", "Faça login para acessar essa página!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	%>
	<main class="form-signin w-100 m-auto">
		<form action="editar" method="post">
			<h1 class="h3 mb-3 fw-normal">Editar Usuário</h1>
			
			<input type="hidden" name="id" value="${usuario.getId()}">
			
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInput" name="nome" value="${usuario.getNome()}"> 
				<label for="floatingInput">Nome</label>
			</div>
			<div class="form-floating">
				<input type="email" class="form-control" id="floatingInput" name="email" value="${usuario.getEmail()}"> 
				<label for="floatingInput">Endereço de email</label>
			</div>
			<div class="form-floating">
				<input type="password" class="form-control my-0" id="floatingPassword" name="senha" placeholder="" required> 
				<label for="floatingPassword">Senha</label>
			</div>
			<div class="form-floating">
				<input type="password" class="form-control my-0" id="floatingPassword" name="senha2" placeholder="" required> 
				<label for="floatingPassword">Confirmar senha</label>
			</div>

			<button class="btn btn-primary w-100 py-2" type="submit">Salvar mudanças</button>
			
			<c:if test="${not empty mensagem}">
				<hr>
				<div class="alert alert-danger" role="alert">
					<span>${mensagem}</span>
				</div>
			</c:if>
		</form>
		<br>
		<a href="listaUsuarios.jsp" class="btn btn-primary">Voltar</a>
	</main>
	<script src="assets/dist/js/bootstrap.bundle.min.js"></script>

</body>
<body>

</body>
</html>