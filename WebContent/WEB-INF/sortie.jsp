<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="fr">
<jsp:include page="/WEB-INF/template/head.jsp"></jsp:include>
<body>
	<!-- Header -->
	<jsp:include page="/WEB-INF/template/header.jsp"></jsp:include>
	<div class="container">
		<div class="myform form">
			<div class="row ">
				<c:if test="${sortie.etat.libelle == 'Ouverte'}">
					<h2 class="m-5">Modifier Sortie</h2>
				</c:if>
				<c:if test="${sortie.etat.libelle == 'Annulée'}">
					<h2 class="m-5">Annuler Sortie</h2>
				</c:if>
				<!-- Page Content -->
				<c:if test="${erreurMDP != null }">
					<div class="col-md-6 mb-2 offset-md-1 alert alert-danger"
						role="alert">
						<strong>${erreurMDP}</strong>
					</div>
				</c:if>
				<form method="post" class="offset-sm-1 col-sm-10"
					action="<%=request.getContextPath()%>/details?idSortie=${sortie.idSortie}">
					<div class="form-group row">
						<div class="form-group col-md-6">
							<label for="nomSortie" class="mr-sm-2">Nom de la sortie :</label><input
								id="nomSortie" type="text" name="nomSortie"
								class="form-control mb-2 mr-sm-2" value="${sortie.nom}" />
						</div>
						<!-- 						<div class="form-group col-md-6"> -->
						<!-- 							<label for="villeOrga" class="mr-sm-2">Ville -->
						<!-- 								organisatrice :</label><input id="villeOrga" type="text" -->
						<!-- 								name="villeOrga" class="form-control mb-2 mr-sm-2" -->
						<%-- 								value="${sortie.ville}" /> --%>
						<!-- 						</div> -->
					</div>
					<div class="form-group row">
						<div class="form-group col-md-6">
							<label for="dateDebut">Date et heure de la sortie :</label>
							<fmt:formatDate value="${sortie.dateHeureDebut}"
								pattern="dd-MM-yyyy hh:mm" />
						</div>
						<div class="form-group col-md-6">
							<label for="ville-select">Ville:</label> <select
								id="ville-select" name="ville-select">
								<c:forEach var="c" items="${listeVilles}">
									<option value="${c.idVille}">${c.nom}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group row">
						<div class="form-group col-md-6">
							<label for="dateFin">Date limite d'inscription :</label>
							<fmt:formatDate value="${sortie.dateHeureFin}"
								pattern="dd-MM-yyyy hh:ss" />
						</div>
						<div class="form-group col-md-6">
							<label for="lieu-select">Lieu :</label> <select id="lieu-select"
								name="lieu-select">
								<c:forEach var="c" items="${listeLieux}">
									<option value="${c.idLieu}">${c.nom}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label for="nbrePlaces" class="mr-sm-2">Nombre de places :</label><input
							id="nbrePlaces" type="text" name="nbrePlaces"
							class="form-control mb-2 mr-sm-2"
							value="${sortie.nbParticipantMax}" />
					</div>
					<div class="form-group col-md-6">
						<label for="rue" class="mr-sm-2">Rue :</label>
						<c:forEach var="c" items="${listeLieux}">
							<c:if test="${c.idLieu == sortie.lieu.idLieu}">
								<input id="rue" type="text" name="rue"
									class="form-control mb-2 mr-sm-2" value="${sortie.lieu.rue}" />
							</c:if>
						</c:forEach>
					</div>
					<div class="form-group col-md-6">
						<label for="duree" class="mr-sm-2">Durée :</label><input
							id="duree" type="text" name="duree"
							class="form-control mb-2 mr-sm-2" value="${sortie.duree}" />
					</div>
					<div class="form-group col-md-6">
						<label for="CP" class="mr-sm-2">Code postal :</label>
						<c:forEach var="c" items="${listeLieux}">
							<c:if test="${c.idLieu == sortie.lieu.idLieu}">
								<c:forEach var="v" items="${listeVilles}">
									<c:if test="${v.idVille == c.ville.idVille}">
										<input id="CP" type="text" name="CP"
											class="form-control mb-2 mr-sm-2" value="${v.codePostal}" />
									</c:if>
								</c:forEach>
							</c:if>
						</c:forEach>
					</div>
					<div class="form-group col-md-6">
						<label for="desciption" class="mr-sm-2">Description :</label>
						<textarea id="description" name="description"
							class="form-control mb-2 mr-sm-2">${sortie.description}</textarea>
					</div>
					<div class="form-group col-md-6 pull-right">
						<label for="latitude" class="mr-sm-2">Latitude</label>
						<c:forEach var="c" items="${listeLieux}">
							<c:if test="${c.idLieu == sortie.lieu.idLieu}">
								<input id="latitude" type="text" name="latitude"
									class="form-control mb-2 mr-sm-2" value="${c.latitude}" />
							</c:if>
						</c:forEach>
					</div>
					<div class="form-group col-md-6 pull-right">
						<label for="longitude" class="mr-sm-2">Longitude</label>
						<c:forEach var="c" items="${listeLieux}">
							<c:if test="${c.idLieu == sortie.lieu.idLieu}">
								<input id="longitude" type="text" name="longitude"
									class="form-control mb-2 mr-sm-2" value="${c.longitude}" />
							</c:if>
						</c:forEach>
					</div>
					<c:choose>
						<c:when test="${listeParticipant.size()>0}">
							<table class="table">
								<thead class="thead-light">
									<tr>
										<th scope="col">Pseudo</th>
										<th scope="col">Nom</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="participant" items="${listeParticipant}">
										<tr>
											<td><h6 class="card-title">${participant.pseudo}</h6></td>
											<td><h6 class="card-title">${participant.nom}</h6></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:when>
						<c:otherwise>
							<table class="table">
								<thead class="thead-light">
									<tr>
										<th scope="col">Pseudo</th>
										<th scope="col">Nom</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><h6 class="card-title">Pas de participants
												inscrits actuellement.</h6></td>
										<td><h6 class="card-title"></h6></td>
									</tr>
								</tbody>
							</table>
						</c:otherwise>
					</c:choose>
					<input type="submit" value="Valider" class="btn btn-success mb-2" />
					<a href="<%=request.getContextPath()%>/accueil"
						class="btn btn-info mb-2">Annuler</a>

				</form>
			</div>
		</div>
	</div>
	<!-- Footer -->
	<%-- 	<jsp:include page="/WEB-INF/template/footer.jsp"></jsp:include> --%>
</body>
</html>