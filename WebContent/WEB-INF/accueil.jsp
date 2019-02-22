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
		<h3 class="mt-5">Filtrer les sorties</h3>
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
		</form>
		<div class="row p-2 m-0">
			<c:choose>
				<c:when test="${listeSorties.size()>0}">
					<table class="table">
						<thead class="thead-dark">
							<tr>
								<th scope="col">#</th>
								<th scope="col">First</th>
								<th scope="col">Last</th>
								<th scope="col">Handle</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">1</th>
								<td>Mark</td>
								<td>Otto</td>
								<td>@mdo</td>
							</tr>
							<tr>
								<th scope="row">2</th>
								<td>Jacob</td>
								<td>Thornton</td>
								<td>@fat</td>
							</tr>
							<tr>
								<th scope="row">3</th>
								<td>Larry</td>
								<td>the Bird</td>
								<td>@twitter</td>
							</tr>
						</tbody>
					</table>
					<c:forEach var="sortie" items="${listeSorties}">

						<div class="col-lg-3 col-md-6 col-sm-12 mb-3">
							<div class="card">
								<img class="card-img-top"
									src="${pageContext.request.contextPath}/images/${sortie.urlPhoto}"
									alt="Card image cap">
								<div class="card-body text-center">
									<h5 class="card-title">${sortie.nom}</h5>
									<c:if test="${sortie.idEtat.libelle == 'Créée'}">
										<h6 class="card-title">
											<span class="badge badge-dark pt-2 pb-2 pl-5 pr-5">${sortie.idEtat.libelle}</span>
										</h6>
									</c:if>
									<c:if test="${sortie.idEtat.libelle == 'Ouverte'}">
										<h6 class="card-title">
											<span class="badge badge-success pt-2 pb-2 pl-5 pr-5">${sortie.idEtat.libelle}</span>
										</h6>
									</c:if>
									<c:if test="${sortie.idEtat.libelle == 'Annulée'}">
										<h6 class="card-title">
											<span class="badge badge-danger pt-2 pb-2 pl-5 pr-5">${sortie.idEtat.libelle}</span>
										</h6>
									</c:if>
									<h6 class="card-title">
										<i class="fas fa-map-marker-alt"></i> ${sortie.idLieu.nom}
									</h6>
									<h6 class="card-title">
										<i class="fas fa-calendar-alt"></i>
										<fmt:formatDate value="${sortie.dateHeureDebut}"
											pattern="dd-MM-yyyy" />
										<i class="fas fa-clock"></i>
										<fmt:formatDate value="${sortie.dateHeureDebut}"
											pattern="HH:mm" />
									</h6>
									<h6>
										<i class="fas fa-calendar-times"></i>
										<fmt:formatDate value="${sortie.dateHeureFin}"
											pattern="dd-MM-yyyy" />
									</h6>

									<h6 class="card-title">
										<i class="fas fa-hourglass-end"></i> ${sortie.duree} minutes
									</h6>

									<h6 class="card-title">
										<i class="fas fa-users"></i> ? / ${sortie.nbParticipantMax}
									</h6>
									<h6 class="card-title">
										<a
											href="${pageContext.request.contextPath}/organisateur?pseudo=${sortie.organisateur.pseudo}"
											class="text-primary"><i class="fas fa-user-cog"></i>
											${sortie.organisateur.pseudo}</a>
									</h6>
									<p class="card-text">${sortie.description}</p>
									<a
										href="${pageContext.request.contextPath}/sortie?idSortie=${sortie.idSortie}"
										class="btn btn-outline-dark" title="Voir la sortie"><i
										class="fas fa-eye"></i></a>
									<c:if
										test="${sortie.idEtat.idEtat != 3 && sortie.idEtat.idEtat != 1}">
										<a href="#" class="btn btn-dark"
											title="S'inscrire ŕ la sortie"><i class="fas fa-plus"></i></a>
									</c:if>
									<c:if
										test="${participantEnCours.pseudo == sortie.organisateur.pseudo || participantEnCours.administrateur}">
										<a
											href="${pageContext.request.contextPath}/editerSortie?idSortie=${sortie.idSortie}"
											class="btn btn-sortir" title="modifier la sortie"><i
											class="fas fa-edit"></i></a>
										<a
											href="${pageContext.request.contextPath}/annulerSortie?idSortie=${sortie.idSortie}"
											class="btn btn-danger" title="Annuler la sortie"><i
											class="fas fa-eraser"></i></a>
									</c:if>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p>Pas de sorties disponibles actuellement.</p>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<!-- Footer -->
	<jsp:include page="/WEB-INF/template/footer.jsp"></jsp:include>
</body>
</html>
