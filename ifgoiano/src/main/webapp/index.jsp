<%@page import="br.edu.ifgoiano.repositorio.UsuarioRepositorio"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<script src="assets/js/color-modes.js"></script>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.122.0">
<title>IF Goiano</title>
<link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/sign-in/">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">
<link href="assets/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="sign-in.css" rel="stylesheet">

</head>

<body class="d-flex align-items-center py-4 bg-body-tertiary">
	<%
		if (session.getAttribute("usuario") != null) {
			UsuarioRepositorio repositorio = new UsuarioRepositorio();
			request.setAttribute("usuarios", repositorio.listUsuarios());
			request.getRequestDispatcher("listaUsuarios.jsp").forward(request, response);
		}
	%>
	<main class="form-signin w-100 m-auto">
		<form action="signIn" method="post">
			<h1 class="h3 mb-3 fw-normal">Please sign in</h1>
			
			<div class="form-floating">
				<input type="text" class="form-control" id="floatingInput" name="name" placeholder=""> 
				<label for="floatingInput">Name</label>
			</div>

			<div class="form-floating">
				<input type="email" class="form-control" id="floatingInput" name="email" placeholder=""> 
				<label for="floatingInput">Email address</label>
			</div>
			<div class="form-floating">
				<input type="password" class="form-control" id="floatingPassword" name="password" placeholder=""> 
				<label for="floatingPassword">Password</label>
			</div>

			<button class="btn btn-primary w-100 py-2" type="submit">Sign in</button>
			
			<c:if test="${not empty mensagem}">
				<div class="alert alert-danger" role="alert">
					${mensagem}
				</div>
			</c:if>
			
			<div>Já possui uma conta? <a href="login.jsp">Faça login</a></div>
		</form>
	</main>
	<script src="assets/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>