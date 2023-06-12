<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<title>Registration Form</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">

<style>
form {
	margin-bottom: 60px;
	margin-top: 10px;
	padding: 10px;
}

h3 {
	margin-top: 20px;
}
</style>


</head>
<body>
<div class="container">

		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container-fluid">

				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page"
							href="${pageContext.request.contextPath}/">Home</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/searchContact">Search Contact</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/createGroup">New Group</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/Groups">Groups</a></li>
					</ul>
				</div>
			</div>
		</nav>






		<div>
			<h3>Enregistrer Contact</h3>
		</div>
		<div>

			<c:if test="${infoMsg!=null}">
				<div class="alert alert-success" role="alert">${infoMsg}</div>
			</c:if>
			<c:if test="${errorMsg!=null}">
				<div class="alert alert-danger" role="alert">${errorMsg}</div>
			</c:if>



			<f:form action="${action}" method="POST" modelAttribute="contactModel">

				<div class="row">
					<div class="col">
						<label>Nom</label>
						<f:input path="nom" type="text" class="form-control"
							placeholder="Nom" />
						<f:errors path="nom" class="text-danger" />
					</div>

					<div class="col">
						<label>Prénom</label>
						<f:input path="prenom" type="text" class="form-control"
							placeholder="Prénom" />
						<f:errors path="prenom" class="text-danger" />
					</div>
				</div>


				<div class="row">
					<div class="col">
						<label>Téléphone 1</label>
						<f:input path="telephone1" type="text" class="form-control"
							placeholder="Numéro personnel" />
						<f:errors path="telephone1" class="text-danger" />
					</div>

					<div class="col">
						<label>Téléphone 2</label>
						<f:input path="telephone2" type="text" class="form-control"
							placeholder="Numéro professionnel" />
						<f:errors path="telephone2" class="text-danger" />
					</div>
				</div>


				<div class="row">
					<div class="col">
						<label>Email personnel</label>
						<f:input path="emailPersonnel" class="form-control"
							placeholder="Email personnel" />
						<f:errors path="emailPersonnel" class="text-danger" />
					</div>
					
					<div class="col">
						<label>Email professionnel</label>
						<f:input path="emailProfessionnel" class="form-control"
							placeholder="Email professionnel" />
						<f:errors path="emailProfessionnel" class="text-danger" />
					</div>
				</div>

				<div class="row">
				
					<div class="col">
						<label>Adresse</label>
						<f:input path="adresse" type="text" class="form-control"
							placeholder="Adresse" />
						<f:errors path="adresse" class="text-danger" />
					</div>
				
					<div class="col">
						<legend class="col-form-label col-sm-2 pt-0">Genre</legend>
						<div class="form-check">
							<f:radiobutton path="genre" class="form-check-input"
								value="Femme" />
							<label class="form-check-label">Femme </label>
						</div>
						<div class="form-check">
							<f:radiobutton path="genre" class="form-check-input"
								value="Mâle " />
							<label class="form-check-label">Mâle </label>
						</div>
						<f:errors path="genre" class="text-danger" />
					</div>
				</div>
				
				
				
				
				
				<div style="text-align: right">
					<button type="submit" class="btn btn-primary">Enregistrer</button>
					
				</div>

			</f:form>
		</div>	
		
		
		<f:form action="/listOrder" method="POST" >
			<h3>Liste de Contacts</h3>
			<div style="text-align: right" class="row">
						
						<div class="col">
							<!--<label>Ordre</label> html select -->
							<select class="form-control" name="order">
								<option value="1" label="ascendant" />
								<option value="2" label="descendant" />
					
							</select>
						</div>
			</div><br>
			<div style="text-align: right">
					<button class="btn btn-primary" type="submit">Ordonner</button>
			</div>
			
		</f:form>
		
	


	<table class="table">
		<thead>
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Nom</th>
				<th scope="col">Prenom</th>
				<th scope="col">Telephone 1</th>
				<th scope="col">Telephone 2</th>
				<th scope="col">Adresse</th>
				<th scope="col">Email Personnel</th>
				<th scope="col">Email Professionnel</th>
				<th scope="col">Genre</th>
				<th scope="col">Action</th>
				
			</tr>
		</thead>
		<c:forEach items="${listContacts}" var="c">
			<tr>
				<td><c:out value="${c.id}" /></td>
				<td><c:out value="${c.nom}" /></td>
				<td><c:out value="${c.prenom}" /></td>
				<td><c:out value="${c.telephone1}" /></td>
				<td><c:out value="${c.telephone2}" /></td>
				<td><c:out value="${c.adresse}" /></td>
				<td><c:out value="${c.emailPersonnel}" /></td>
				<td><c:out value="${c.emailProfessionnel}" /></td>
				<td><c:out value="${c.genre}" /></td>
				<td><a href="/chargerForm?id=${c.id}" class="btn btn-outline-primary">Modifier</a>
				<a href="/supprimer?id=${c.id}" class="btn btn-outline-danger">Supprimer</a></td>

			</tr>

		</c:forEach> 

	</table>
</div>
</body>
</html>