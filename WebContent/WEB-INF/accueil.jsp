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
			<h2 class="mt-5">Filtrer les sorties</h2>
			<div class="row p-2 m-0">
				<form method="post" action="">
					<label for="site-select">Site:</label> <select id="site-select">
						<c:forEach var="c" items="${listeSites}">
							<option value="${c.idSite}">${c.nom}</option>
						</c:forEach>
					</select> <label for="nomSortie">Nom sortie: </label><input id="nomSortie"></input>
					<label for="dateDebut">Entre :</label> <input type="datetime-local"
						id="dateDebut" name="dateDebut"> <label for="dateFin">et
						:</label> <input type="datetime-local" id="dateFin" name="dateFin">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
					<!-- 					<div class="input-group-prepend"> -->
					<!-- 						<span class="input-group-text cyan lighten-2" id="basic-text1"><i -->
					<!-- 							class="fas fa-search text-white" aria-hidden="true"></i></span> <input -->
					<!-- 							class="form-control my-0 py-1" type="text" placeholder="Search" -->
					<!-- 							aria-label="Search"> -->
					<!-- 					</div> -->
				</form>
			</div>
			<div class="row p-2 m-0">
				<c:choose>
					<c:when test="${listeSorties.size()>0}">
						<table class="table">
							<thead class="thead-light">
								<tr>
									<th scope="col">Nom de la sortie</th>
									<th scope="col">Date de la sortie</th>
									<th scope="col">Clôture</th>
									<th scope="col">Inscrits/places</th>
									<th scope="col">Etat</th>
									<th scope="col">Organisateur</th>
									<th scope="col">Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="sortie" items="${listeSorties}">
									<tr>
										<td><h6 class="card-title">${sortie.nom}</h6></td>
										<td><h6 class="card-title">
												<i class="fas fa-calendar-alt"></i>
												<fmt:formatDate value="${sortie.dateHeureDebut}"
													pattern="dd-MM-yyyy" />
												<i class="fas fa-clock"></i>
												<fmt:formatDate value="${sortie.dateHeureDebut}"
													pattern="HH:mm" />
											</h6></td>
										<td><h6 class="card-title">
												<i class="fas fa-calendar-alt"></i>
												<fmt:formatDate value="${sortie.dateHeureFin}"
													pattern="dd-MM-yyyy" />
												<i class="fas fa-clock"></i>
												<fmt:formatDate value="${sortie.dateHeureFin}"
													pattern="HH:mm" />
											</h6></td>
										<td><h6 class="card-title">
												<c:set var="count" value="0" scope="page" />
												<i class="fas fa-users"></i> ${count} /
												${sortie.nbParticipantMax}
											</h6></td>
										<td><c:if test="${sortie.etat.libelle == 'Créée'}">
												<h6 class="card-title">
													<span class="badge badge-dark pt-2 pb-2 pl-5 pr-5">${sortie.etat.libelle}</span>
												</h6>
											</c:if> <c:if test="${sortie.etat.libelle == 'Ouverte'}">
												<h6 class="card-title">
													<span class="badge badge-success pt-2 pb-2 pl-5 pr-5">${sortie.etat.libelle}</span>
												</h6>
											</c:if> <c:if test="${sortie.etat.libelle == 'Annulée'}">
												<h6 class="card-title">
													<span class="badge badge-danger pt-2 pb-2 pl-5 pr-5">${sortie.etat.libelle}</span>
												</h6>
											</c:if></td>
										<td><h6 class="card-title">
												<i class="fas fa-user-cog"></i>
												${sortie.organisateur.pseudo}
											</h6></td>
										<td>
<!-- 										<a -->
<%-- 											href="${pageContext.request.contextPath}/details?idSortie=${sortie.idSortie}" --%>
<!-- 											title="Voir la sortie"><i class="fas fa-eye"></i></a>  -->
											<c:if
												test="${sortie.etat.idEtat != 3 && sortie.etat.idEtat != 1}">
												<a
													href="${pageContext.request.contextPath}/inscription?idSortie=${sortie.idSortie}"
													title="S'inscrire à la sortie"><i class="fas fa-plus"></i></a>
												<c:forEach var="inscription" items="${listeInscriptions}">
													<c:if
														test="${sortie.idSortie == inscription.idSortie.idSortie && participantEnCours.getIdparticipant() == inscription.idParticipant.getIdparticipant()}">
														<a href="#" title="S'inscrire à la sortie"><i
															class="fas fa-minus"></i></a>
													</c:if>
												</c:forEach>

											</c:if> <c:if
												test="${participantEnCours.pseudo == sortie.organisateur.pseudo || participantEnCours.administrateur}">
												<a
													href="${pageContext.request.contextPath}/details?idSortie=${sortie.idSortie}"
													title="modifier la sortie"><i class="fas fa-edit"></i></a>
												<a
													href="${pageContext.request.contextPath}/details?idSortie=${sortie.idSortie}"
													title="Annuler la sortie"><i class="fas fa-eraser"></i></a>
											</c:if></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					<c:otherwise>
						<p>Pas de sorties disponibles actuellement.</p>
					</c:otherwise>
				</c:choose>
			</div>
			<h2 class="mt-5">Créer une sortie</h2>
			<a href="<%=request.getContextPath()%>/nouvelleSortie"
				title="ajouter une sortie" class="sortir"><span
				class="fa-stack fa-xs"> <i class="fas fa-circle fa-stack-2x"></i>
					<i class="fas fa-plus fa-stack-1x fa-inverse"></i>
			</span></a>
		</div>
	</div>
	<!-- Footer -->
	<jsp:include page="/WEB-INF/template/footer.jsp"></jsp:include>
</body>
</html>
