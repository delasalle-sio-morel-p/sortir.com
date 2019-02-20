<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<jsp:include page="/WEB-INF/template/head.jsp"></jsp:include>
<body>
	<!-- Header -->
	<jsp:include page="/WEB-INF/template/header.jsp"></jsp:include>
	<!-- Page Content -->
	<h2 class="m-5">Mon profil</h2>

	<c:if test="${erreurMDP != null }">
		<div class="col-md-6 mb-2 offset-md-1 alert alert-danger" role="alert">
			<strong>${erreurMDP}</strong>
		</div>
	</c:if>



	<form method="post" class="form-horizontal offset-sm-1 col-sm-10"
		action="<%=request.getContextPath()%>/updateProfil">
		<div class="form-group">
			<label for="nom" class="mr-sm-2">Nom</label><input id="nom"
				type="text" name="nom" class="form-control mb-2 mr-sm-2"
				value="${participantEnCours.nom}" />
		</div>
		<div class="form-group">
			<label for="prenom" class="mr-sm-2">Prénom</label><input id="prenom"
				type="text" name="prenom" class="form-control mb-2 mr-sm-2"
				value="${participantEnCours.prenom}" />
		</div>
		<div class="form-group">
			<label for="pseudo" class="mr-sm-2">Pseudo</label><input id="pseudo"
				type="text" name="pseudo" class="form-control mb-2 mr-sm-2"
				value="${participantEnCours.pseudo}" />
		</div>
		<div class="form-group">
			<label for="password" class="mr-sm-2">Mot de passe</label><input
				id="password" type="password" name="password"
				class="form-control mb-2 mr-sm-2" />
		</div>
		<div class="form-group">
			<label for="passwordVerif" class="mr-sm-2">Confirmer votre
				mot de passe</label><input id="passwordVerif" type="password"
				name="passwordVerif" class="form-control mb-2 mr-sm-2" />
		</div>
		<div class="form-group">
			<label for="telephone" class="mr-sm-2">Téléphone</label><input
				id="telephone" type="text" name="telephone"
				class="form-control mb-2 mr-sm-2"
				value="${participantEnCours.telephone}" />
		</div>
		<div class="form-group">
			<label for="email" class="mr-sm-2">Email</label><input id="email"
				type="email" name="email" class="form-control mb-2 mr-sm-2"
				value="${participantEnCours.email}" />
		</div>

		<div class="form-group">
			<label for="idSite" class="mr-sm-2">Site de rattachement</label> <select
				id="idSite" name="idSite" class="form-control mb-2 mr-sm-2">
				<c:forEach var="site" items="${listeSites}">
					<option value="${site.idSite}">${site.nom}</option>
				</c:forEach>
			</select>
		</div>

		<input type="submit" value="Valider" class="btn btn-success mb-2" />
		<a href="<%=request.getContextPath()%>/profil"
			class="btn btn-info mb-2">Annuler</a>

	</form>
	<!-- Footer -->
	<jsp:include page="/WEB-INF/template/footer.jsp"></jsp:include>
</body>
</html>