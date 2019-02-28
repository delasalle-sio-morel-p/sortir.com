<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<!-- Page Content -->
				<h2 class="m-5">Créer Sortie</h2>

				<c:if test="${erreurMDP != null }">
					<div class="col-md-6 mb-2 offset-md-1 alert alert-danger"
						role="alert">
						<strong>${erreurMDP}</strong>
					</div>
				</c:if>
				<form method="post" class="offset-sm-1 col-sm-10"
					action="<%=request.getContextPath()%>/nouvelleSortie">
					<div class="form-group row">
						<div class="form-group col-md-6">
							<label for="nomSortie" class="mr-sm-2">Nom de la sortie :</label><input
								id="nomSortie" type="text" name="nomSortie"
								class="form-control mb-2 mr-sm-2" />
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
							<input type="datetime-local" id="dateDebut" name="dateDebut">
						</div>
						<div class="form-group col-md-6">
							<label for="ville-select">Ville:</label> <select
								id="ville-select">
								<c:forEach var="c" items="${listeVilles}">
									<option value="${c.idVille}">${c.nom}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group row">
						<div class="form-group col-md-6">
							<label for="dateFin">Date limite d'inscription :</label>
							<input type="datetime-local" id="dateFin" name="dateFin">
						</div>
						<div class="form-group col-md-6">
							<label for="lieu-select">Lieu :</label> <select id="lieu-select">
								<c:forEach var="c" items="${listeLieux}">
									<option value="${c.idLieu}">${c.nom}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group col-md-6">
						<label for="nbrePlaces" class="mr-sm-2">Nombre de places :</label><input
							id="nbrePlaces" type="text" name="nbrePlaces"
							class="form-control mb-2 mr-sm-2" />
					</div>
					<div class="form-group col-md-6">
						<label for="rue" class="mr-sm-2">Rue :</label> <input id="rue"
							type="text" name="rue" class="form-control mb-2 mr-sm-2"/>
					</div>
					<div class="form-group col-md-6">
						<label for="duree" class="mr-sm-2">Durée :</label><input
							id="duree" type="text" name="duree"
							class="form-control mb-2 mr-sm-2" />
					</div>
					<div class="form-group col-md-6">
						<label for="CP" class="mr-sm-2">Code postal :</label> <input
							id="CP" type="text" name="CP" class="form-control mb-2 mr-sm-2" />
					</div>
					<div class="form-group col-md-6">
						<label for="desciption" class="mr-sm-2">Description :</label>
						<textarea id="description" name="description"
							class="form-control mb-2 mr-sm-2"></textarea>
					</div>
					<div class="form-group col-md-6 pull-right">
						<label for="latitude" class="mr-sm-2">Latitude</label> <input
							id="latitude" type="text" name="latitude"
							class="form-control mb-2 mr-sm-2"  />

					</div>
					<div class="form-group col-md-6 pull-right">
						<label for="longitude" class="mr-sm-2">Longitude</label> <input
							id="longitude" type="text" name="longitude"
							class="form-control mb-2 mr-sm-2" />

					</div>
					<input type="submit" value="Valider" class="btn btn-success mb-2" />
					<a href="<%=request.getContextPath()%>/accueil"
						class="btn btn-info mb-2">Annuler</a>
				</form>
			</div>
		</div>
	</div>
</body>
</html>