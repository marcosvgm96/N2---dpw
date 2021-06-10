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
<title>Listagem de clientes</title>
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
		<h3 class="text-center">Listagem de Clientes</h3>
		<hr>
		<div class="container text-left">
			<a class="btn btn-success"
				href="<%=request.getContextPath()%>/clientes/new">Adicionar</a>
		</div>
		<br>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">Codigo</th>
					<th scope="col">Nome</th>
					<th scope="col">Matricula</th>
					<th scope="col">cep</th>
					<th scope="col">cidade</th>
					<th scope="col">estado</th>
					<th scope="col">bairro</th>
					<th scope="col">logradouro</th>
					<th scope="col">numero</th>
					<th scope="col">cod. modalidade</th>
					<th scope="col">modalidade</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="cliente" items="${ listaClientes }">
					<tr>
						<td><c:out value="${ cliente.id }"></c:out></td>
						<td><c:out value="${ cliente.nome }"></c:out></td>
						<td><c:out value="${ cliente.matricula }"></c:out></td>
						<td><c:out value="${ cliente.endereco.cep }"></c:out></td>
						<td><c:out value="${ cliente.endereco.cidade }"></c:out></td>
						<td><c:out value="${ cliente.endereco.estado }"></c:out></td>
						<td><c:out value="${ cliente.endereco.bairro }"></c:out></td>
						<td><c:out value="${ cliente.endereco.logradouro }"></c:out></td>
						<td><c:out value="${ cliente.endereco.numero }"></c:out></td>
						<td><c:out value="${ cliente.modalidade.codigoModalidade }"></c:out></td>
						<td><c:out value="${ cliente.modalidade.descricao }"></c:out></td>
						<td><a
							href="<%=request.getContextPath()%>/clientes/edit?id=<c:out value='${ cliente.id }'/>"
							class="btn btn-primary">Editar</a> <a
							href="<%=request.getContextPath()%>/clientes/delete?id=<c:out value='${ cliente.id }'/>"
							class="btn btn-danger">Excluir</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>