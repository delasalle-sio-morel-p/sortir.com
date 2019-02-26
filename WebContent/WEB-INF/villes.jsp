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
	<!-- Page Content -->
	<div class="container">
		<div class="myform form">
			<h2 class="m-5 text-uppercase">
				Les Villes <a href="<%=request.getContextPath()%>/nouvelleVille"
					title="ajouter une ville" class="sortir"><span
					class="fa-stack fa-xs"> <i class="fas fa-circle fa-stack-2x"></i>
						<i class="fas fa-plus fa-stack-1x fa-inverse"></i>
				</span></a>
			</h2>


			<div class="row m-0">
				<div class="col-md-6 offset-md-3">
					<table class="table">
						<thead class="thead-light">
							<tr>
								<th scope="col">Nom</th>
								<th scope="col">Code postal</th>
								<th scope="col">Modifier</th>
								<th scope="col">Supprimer</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="ville" items="${listeVilles}">
								<tr>
									<th>${ville.nom}</th>
									<td>${ville.codePostal}</td>

									<td width="5%"><a
										href="${pageContext.request.contextPath}/editerVille?idVille=${ville.idVille}"
										class="btn btn-lg btn-sortir" title="modifier la ville"><i
											class="fas fa-edit"></i></a></td>
									<td width="5%"><a
										href="${pageContext.request.contextPath}/supprimerVille?idVille=${ville.idVille}"
										class="btn btn-lg btn-danger" title="supprimer la ville"><i
											class="fas fa-eraser"></i></a></td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- Footer -->
	<jsp:include page="/WEB-INF/template/footer.jsp"></jsp:include>
</body>
</html>