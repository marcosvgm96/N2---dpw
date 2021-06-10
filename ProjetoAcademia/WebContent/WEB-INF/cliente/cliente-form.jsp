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
<title>Novo Usuário</title>
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
			<c:if test="${ user == null }">
				<h5 class="card-header">Adição de Usuário</h5>
				<form action="insert" method="post">
			</c:if>
			<div class="card-body">
				<c:if test="${ user != null }">
					<input type="hidden" name="id" value="<c:out value='${ id }' />" />
				</c:if>
				<div class="mb-3">
					<label for="name" class="form-label">Usuário</label> <input
						type="text" class="form-control" name="nome"
						value="<c:out value='${ nome }' />" required/>
				</div>
				<div class="mb-3">
					<label for="email" class="form-label">Matricula</label> <input
						type="text" class="form-control" name="matricula"
						value="<c:out value='${ matricula }' />" required/>
				</div>
				<div class="mb-3">
					<label for="email" class="form-label">CEP</label> <input
						type="text" class="form-control" name="cep"
						value="<c:out value='${ cep }' />" required/>
				</div>
				<div class="mb-3">
					<label for="email" class="form-label">Cidade</label> <input
						type="text" class="form-control" name="cidade"
						value="<c:out value='${ cidade }' />" required/>
				</div>
				<div class="mb-3">
					<label for="email" class="form-label">Estado</label> <input
						type="text" class="form-control" name="estado"
						value="<c:out value='${ estado }' />" required/>
				</div>
				<div class="mb-3">
					<label for="email" class="form-label">Bairro</label> <input
						type="text" class="form-control" name="bairro"
						value="<c:out value='${ bairro }' />" required/>
				</div>
				<div class="mb-3">
					<label for="email" class="form-label">Logradouro</label> <input
						type="text" class="form-control" name="logradouro"
						value="<c:out value='${ logradouro }' />" required/>
				</div>
				<div class="mb-3">
					<label for="email" class="form-label">Numero</label> <input
						type="text" class="form-control" name="numero"
						value="<c:out value='${ numero }' />" required/>
				</div>
				<div class="mb-3">
					<label for="email" class="form-label">Codigo da Modalidade</label>
					<input type="text" class="form-control" name="modalidadeId"
						value="<c:out value='${ modalidadeId }' />" required/>
				</div>

				<button type="submit" class="btn btn-success">Salvar</button>
			</div>


		</div>
	</div>
</body>
</html>