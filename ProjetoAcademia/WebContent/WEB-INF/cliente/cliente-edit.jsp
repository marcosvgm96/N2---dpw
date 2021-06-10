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
<title>Formulario de Edicao</title>
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
	<div class="container col-md-5">
		<div class="card">
			<c:if test="${ cliente != null }">
				<h5 class="card-header">Edição de Usuário</h5>
				<form action="update" method="post">
			</c:if>
			<div class="card-body">
				<c:if test="${ cliente != null }">
					<input type="hidden" name="id"
						value="<c:out value='${ cliente.id }' />" />
				</c:if>
				<div class="mb-3">
					<label for="name" class="form-label">Usuário</label> <input
						type="text" class="form-control" name="nome"
						value="<c:out value='${ cliente.nome }' />" required/>
				</div>
				<div class="mb-3">
					<label for="name" class="form-label">Matricula</label> <input
						type="text" class="form-control" name="matricula"
						value="<c:out value='${ cliente.matricula }' />" required/>
				</div>
				<div class="mb-3">
					<label for="email" class="form-label">enderecoId</label> <input
						type="text" class="form-control" name="enderecoId"
						value="<c:out value='${ cliente.enderecoId }' />" required/>
				</div>
				<div class="mb-3">
					<label for="email" class="form-label">Codigo da Modalidade</label>
					<input type="text" class="form-control" name="modalidadeId"
						value="<c:out value='${ cliente.modalidadeId }' />" required/>
				</div>

				<button type="submit" class="btn btn-success">Salvar</button>
			</div>
		</div>
	</div>
</body>
</html>