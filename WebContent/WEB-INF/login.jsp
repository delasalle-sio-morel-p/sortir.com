<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/template/head.jsp"></jsp:include>
<body>
	<!-- Header -->
	<jsp:include page="/WEB-INF/template/header.jsp"></jsp:include>
	<div class="container">
		<c:if test="${param.message != null}">
			<div class="col-md-8 col-md-offset-2 mb-2 alert alert-danger"
				role="alert">
				<p style="text-align: center">
					<strong>${param.message}</strong>
				</p>
			</div>
			<br>
		</c:if>
		<div class="row">
			<div class="col-md-5 mx-auto">
				<div id="first">
					<div class="myform form ">
						<div class="logo mb-3">
							<div class="col-md-12 text-center">
								<h1>Login</h1>
							</div>
						</div>
						<form action="login" method="post" name="login">
							<div class="form-group">
								<label for="login">Email address</label> <input type="text"
									name="login" class="form-control" id="login"
									aria-describedby="emailHelp" placeholder="Email ou Pseudo"
									value="${login}">
							</div>
							<div class="form-group">
								<label for="password">Password</label> <input type="password"
									name="password" id="password" class="form-control"
									aria-describedby="emailHelp" placeholder="Password">
							</div>
							<div class="row align-items-center remember">
								<input type="checkbox" name="remember" value="ON"> Se
								rappeler de moi
							</div>
							<div class="col-md-12 text-center ">
								<button type="submit"
									class=" btn btn-block mybtn btn-primary tx-tfm">Login</button>
							</div>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/template/footer.jsp"></jsp:include>
</body>
</html>