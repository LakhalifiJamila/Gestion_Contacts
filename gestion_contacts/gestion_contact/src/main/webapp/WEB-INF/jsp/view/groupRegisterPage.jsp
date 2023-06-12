<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Group</title>

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
						<li class="nav-item"><a class="nav-link"
							
							href="${pageContext.request.contextPath}/">Home</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/searchContact">Search Contact</a></li>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page"
							href="${pageContext.request.contextPath}/createGroup">New Group</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/Groups">Groups</a></li>
						

					</ul>
				</div>
			</div>
		</nav>





		<div>
			<h3>Créer un Groupe</h3>
		</div>
		
		<div>

			<c:if test="${infoMsg!=null}">
				<div class="alert alert-success" role="alert">${infoMsg}</div>
			</c:if>
			<c:if test="${errorMsg!=null}">
				<div class="alert alert-danger" role="alert">${errorMsg}</div>
			</c:if>

			<f:form action="/addGroup" method="POST">
		
					<div class="row">
							<div class="col">
								<label>Nom du groupe</label>
								<input type="text" class="form-control" name="groupeNom"
									placeholder="famille, amis, ..." required/>
							</div>
					</div><br>
		
					<div class="row">
							<div class="col">
								<legend class="col-form-label col-sm-2 pt-0">Liste des Contacts</legend>
								
								<div class="form-check">
									<c:forEach items="${listContacts}" var="c">
											<input class="form-check-input" type="checkbox" value="${c.id}" name="contactIds">
											
											<c:out value="${c.nom} ${c.prenom}" /><br>
											<c:out value="Adresse: ${c.adresse}" /><br><br>
											
									</c:forEach>
								</div>
								
		
		
							</div>
							
					</div>
					
					<div style="text-align: right">
							<button type="submit" class="btn btn-primary">Ajouter Groupe</button>
							
					</div>
		
			</f:form>
		</div>
	</div>

</body>
</html>