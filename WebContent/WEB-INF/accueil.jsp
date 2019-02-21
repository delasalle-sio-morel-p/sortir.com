<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<jsp:include page="/WEB-INF/template/head.jsp"></jsp:include>
<body>
 <!-- Header -->
<jsp:include page="/WEB-INF/template/header.jsp"></jsp:include>
 <!-- Main -->
<div class="container">
		<h3 class="mt-5">Filtrer les sorties</h3>
		<form method="post" action="">
			<label for="site-select">Site:</label> <select id="site-select">
				<option value="site1">ST Herblain</option>
				<option value="site2">Rennes</option>
				<option value="site3">Nantes</option>
			</select> <label for="nomSortie">Nom sortie: </label><input id="nomSortie"></input>
			<label for="dateDebut">Entre :</label> <input type="datetime-local" id="dateDebut"
				name="dateDebut">
				<label for="dateFin">et :</label> <input type="datetime-local" id="dateFin"
				name="dateFin">
				
			
				

			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</form>
	</div>

  <!-- Footer -->
  <jsp:include page="/WEB-INF/template/footer.jsp"></jsp:include>
</body>
</html>
