<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Groups Page</title>
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
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/createGroup">New Group</a></li>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page"
							href="${pageContext.request.contextPath}/Groups">Groups</a></li>
					</ul>
				</div>
			</div>
		</nav>

			<c:if test="${infoMsg!=null}">
				<div class="alert alert-success" role="alert">${infoMsg}</div>
			</c:if>
			<c:if test="${errorMsg!=null}">
				<div class="alert alert-danger" role="alert">${errorMsg}</div>
			</c:if>


		<div class="container" style="padding: 20px;">
            <form method="POST" action="/rechercherGroupe">
                <div id ="child1">
                    <h3>Rechercher Groupe</h3>
                    <select class="form-control" name="rechercherGroupeSelect">
                        
                        <option value="Tous">Tous</option>
                        <option value="Nom">Nom</option>
              
                        
                    </select>
                </div><br><br>

                <div >
                    
                    <input type="text" value="search" name="search"  class="form-control" placeholder="search" required>
                </div>

                <br>
                <button type="submit" class="btn btn-primary">Rechercher</button>
            </form>
        </div><br>


		<table class="table">
			<thead>
				<tr>
					
					<th scope="col">Nom</th>
					
					<th scope="col"></th>
					
				</tr>
			</thead>
			<c:forEach items="${listGroups}" var="g">
				<tr>
					
					<td><c:out value="${g.nom}" /></td>
					
					<td style="text-align: right"><a href="/afficherGroupe?id=${g.id}" class="btn btn-outline-primary" >Afficher</a>
					<a href="/modifierGroupe?id=${g.id}" class="btn btn-outline-warning">Modifier</a>
					<a href="/supprimerGroupe?id=${g.id}" class="btn btn-outline-danger">Supprimer</a></td>
	
				</tr>
	
			</c:forEach> 
	
		</table>
	</div>
</body>
</html>