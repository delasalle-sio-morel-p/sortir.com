<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en" class="h-100">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<title>Sortir!</title>
<link rel="canonical"
	href="https://getbootstrap.com/docs/4.3/examples/sticky-footer-navbar/">

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

.topnav-right {
	float: right;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
<!-- Custom styles for this template -->
<link href="sticky-footer-navbar.css" rel="stylesheet">
</head>
<body class="d-flex flex-column h-100">
	<header>
		<!-- Fixed navbar -->
		<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
			<a class="navbar-brand" href="#">Sortir.com</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarCollapse" aria-controls="navbarCollapse"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarCollapse">
				<ul class="navbar-nav topnav-right">
					<li class="nav-item"><a class="nav-link" href="#">Villes</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Sites</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Mon
							Profil</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Se
							déconnecter</a></li>
				</ul>
			</div>
		</nav>
	</header>

	<!-- Begin page content -->
	<main role="main" class="flex-shrink-0">
	<div class="container">
		<h1 class="mt-5">Filtrer les sorties</h1>
		<form method="post" action="">
			<label for="pet-select">Site:</label> <select id="site-select">
				<option value="site1">ST Herblain</option>
				<option value="site2">Rennes</option>
				<option value="site3">Nantes</option>
			</select> <label for="nomSortie">Nom sortie: </label><input id="nomSortie"></input>
			<label for="dateDebut">Entre :</label> <input type="date" id="dateDebut"
				name="dateDebut">
				<label for="dateFin">et :</label> <input type="date" id="dateFin"
				name="dateFin">

			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</form>
	</div>
	</main>

	<footer class="footer mt-auto py-3">
		<div class="container">
			<span class="text-muted">Place sticky footer content here.</span>
		</div>
	</footer>

	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
</body>
</html>