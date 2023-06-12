<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Contact</title>
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
						<li class="nav-item"><a class="nav-link active"
							aria-current="page"
							href="${pageContext.request.contextPath}/searchContact">Search Contact</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/createGroup">New Group</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/Groups">Groups</a></li>
					</ul>
				</div>
			</div>
		</nav>
	
	
	
		<div class="container" style="padding: 20px;">
            <form method="POST" action="/rechercher">
                <div id ="child1">
                    <h3>Rechercher Contact</h3>
                    <select class="form-control" name="rechercherContactSelect">
                        
                        <option value="Tous">Tous</option>
                        <option value="Nom">Nom</option>
                        <option value="Numero">Numéro de téléphone</option>
                        
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
		<thead >
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
			</tr>

		</c:forEach> 

	</table>
	
</div>
</body>
</html>