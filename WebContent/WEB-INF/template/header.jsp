<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="accueil">Sortir!</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="#">Villes</a></li>
			<li><a href="#">Sites</a></li>
			<li><a href="profil">Mon profil</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="profil"><span class="glyphicon glyphicon-user"></span>
					S'inscrire</a></li>
			<c:if test="${empty currentSessionParticipant}">
				<li><a href="login"><span
						class="glyphicon glyphicon-log-in"></span> Se connecter</a></li>
			</c:if>
			<c:if test="${!empty currentSessionParticipant}">
				<li><a href="logout"><span
						class="glyphicon glyphicon-log-out"></span> Se déconnecter</a></li>
			</c:if>
		</ul>
	</div>
</nav>
