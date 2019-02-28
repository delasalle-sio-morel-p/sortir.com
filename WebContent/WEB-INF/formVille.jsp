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
	<!-- Main -->
	<div class="container">
		<div class="myform form">

			<!-- Page Content -->
			<h2 class="m-5">${title} une ville</h2>

			<form method="post" class="form-horizontal offset-sm-1 col-sm-10"
				action="<%=request.getContextPath()%>/${path}">
				<div class="form-group">
					<label for="nom" class="mr-sm-2">Nom</label><input id="nom"
						type="text" name="nom" class="form-control mb-2 mr-sm-2"
						size="55" value="${ville.nom}" />
				</div>
				<div class="form-group">
					<label for="prenom" class="mr-sm-2">Code Postal</label><input
						id="codePostal" type="number" name="codePostal" maxlength="5"
						class="form-control mb-2 mr-sm-2" value="${ville.codePostal}" />
				</div>

				<div class="form-group">
					<input for="idVille" type="hidden" name="idVille"
						class="form-contrl mb-2 mr-sm-2" value="${ville.idVille}" />
				</div>


				<input type="submit" value="Valider" class="btn btn-success mb-2" />
				<a href="<%=request.getContextPath()%>/villes"
					class="btn btn-info mb-2">Annuler</a>

			</form>
		</div>
	</div>
	<!-- Footer -->
	<jsp:include page="/WEB-INF/template/footer.jsp"></jsp:include>
</body>
</html>