<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/template/head.jsp"></jsp:include>
<body>
	<!-- Header -->
	<jsp:include page="/WEB-INF/template/header.jsp"></jsp:include>
	<div class="login row">
		<div class="login-bg"></div>
		<div class="form ">

			<div class="site-title col-md-4">
			</div>

			<c:if test="${param.message != null}">
				<div class="col-md-6 mb-2 alert alert-danger" role="alert">
					<strong>${param.message}</strong>
				</div>
			</c:if>

			<form method="post" action="login" class="col-md-8">
				<input type="text" placeholder="Email ou Pseudo" name="login"
					id="login" value="${login}"> <input type="password"
					placeholder="Password" name="password" id="password">
				<div class="form-check pt-3">
					<input class="form-check-input" type="checkbox" name="remember"
						value="ON" id="defaultCheck1"> <label
						class="form-check-label" for="defaultCheck1"> Se rappeler
						de moi</label>
				</div>
				<input type="submit" value="Login"><br />

			</form>
		</div>
	</div>
	<jsp:include page="/WEB-INF/template/footer.jsp"></jsp:include>
</body>
</html>