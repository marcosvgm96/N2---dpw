<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0"
	crossorigin="anonymous">
<title>Listagem de enderecos</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">N2</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
				aria-controls="navbarNavAltMarkup" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
				<div class="navbar-nav">
					<a class="nav-link active" aria-current="page"
						href="<%=request.getContextPath()%>/clientes">Clientes</a>
				</div>
			</div>
			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
				<div class="navbar-nav">
					<a class="nav-link active" aria-current="page"
						href="<%=request.getContextPath()%>/enderecos">Enderecos</a>
				</div>
			</div>
			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
				<div class="navbar-nav">
					<a class="nav-link active" aria-current="page"
						href="<%=request.getContextPath()%>/modalidades">Modalidades</a>
				</div>
			</div>
		</div>
	</nav>
	<div class="container">
		<h3 class="text-center">Listagem de Enderecos</h3>
		<hr>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">Codigo</th>
					<th scope="col">CEP</th>
					<th scope="col">Cidade</th>
					<th scope="col">Estado</th>
					<th scope="col">Bairro</th>
					<th scope="col">Logradouro</th>
					<th scope="col">Numero</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="endereco" items="${ listaEnderecos }">
					<tr>
						<td><c:out value="${ endereco.id }"></c:out></td>
						<td><c:out value="${ endereco.cep }"></c:out></td>
						<td><c:out value="${ endereco.cidade }"></c:out></td>
						<td><c:out value="${ endereco.estado }"></c:out></td>
						<td><c:out value="${ endereco.bairro }"></c:out></td>
						<td><c:out value="${ endereco.logradouro }"></c:out></td>
						<td><c:out value="${ endereco.numero }"></c:out></td>

						<td><a
							href="<%=request.getContextPath()%>/enderecos/edit?id=<c:out value='${ endereco.id }'/>"
							class="btn btn-primary">Editar</a> <a
							href="<%=request.getContextPath()%>/enderecos/delete?id=<c:out value='${ endereco.id }'/>"
							class="btn btn-danger">Excluir</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>