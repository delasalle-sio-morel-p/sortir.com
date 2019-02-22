<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<ul class="navbar-nav">
		<li class="nav-item active"><a class="nav-link" href="accueil">Sortir!</a>
		</li>
		<li class="nav-item"><a class="nav-link" href="#">Villes</a></li>
		<li class="nav-item"><a class="nav-link" href="#">Sites</a></li>

	</ul>
	<ul class="navbar-nav ml-auto">
		<li class="nav-item"><a class="nav-link" href="profil"><i
				class="fa fa-user" aria-hidden="true"></i> Mon profil </a></li>

		<c:if test="${empty currentSessionParticipant}">
			<li><a class="nav-link" href="login"><i
					class="fa fa-sign-in-alt" aria-hidden="true"></i> Se connecter</a></li>
		</c:if>
		<c:if test="${!empty currentSessionParticipant}">
			<li><a class="nav-link" href="logout"><i
					class="fa fa-sign-out-alt" aria-hidden="true"></i> Se déconnecter</a></li>
		</c:if>
	</ul>
</nav>
