<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset= utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="CSS/connexion.css" type="text/css">
<link rel="stylesheet" href="CSS/header.css" type="text/css">
<script src="js/jquery.js"></script>
<script src="js/ficheVP.js"></script>
<title>BDS HEI</title>
</head>
<body>
	<header>
		<c:set var="pageSelectionnee" value="connecter" scope="request" />
		<%@ include file="header.jsp"%>
	</header>
	<section>
		<c:if test="${user_id == null}">
			<div id="connexion">
				<form method="post" action="validation">
					<fieldset>
						<legend>Connexion</legend>
						<input type="hidden" id="code" name="code" value="${code}"/>
						<label for="identifiant">Identifiant HEI</label> <input
							type="text" id="identifiant" name="identifiant" required /> <br />
						<label for="password">Mot de passe</label> <input type="password"
							id="password" name="password" required /> <br />
						<input type="submit" value="Connexion" class="sansLabel" />
					</fieldset>
				</form>
			</div>
		</c:if>
	</section>
</body>
</html>