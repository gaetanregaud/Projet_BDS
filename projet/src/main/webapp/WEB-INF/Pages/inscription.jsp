<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset= utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<link rel="stylesheet" href="CSS/inscription.css" type="text/css">
		<link rel="stylesheet" href="CSS/header.css" type="text/css">
		<script src="js/jquery.js"></script>
		<script src="js/inscription.js"></script>
		<title>BDS HEI</title>
	</head>
	<body>
		<header>
			<c:set var="pageSelectionnee" value="connecter" scope="request" />
			<%@ include file="header.jsp"%>
		</header>
		<section>
			<h2>Page d'inscription</h2>
			<p>Pour pouvoir vous inscrire sur le site du BDS HEI, vous devez vous inscrire avec votre adresse mail "@hei.fr"!<p>
			<div id="formulaire">
				<form method="post" action="inscription">
					<input type="hidden" id="type" name="type" value="ajouterEtudiant"/>
					<label for="identifiant">Identifiant HEI : </label>
					<input type="text" id="identifiant" name="identifiant" required>
					<span id="sidentifiant"></span><br/>
					<label for="psswd1">Mot de passe : </label>
					<input type="password" id="psswd1" name="psswd1" required>
					<span id="spsswd1"></span><br/>
					<label for="psswd2">Confirmation mot de passe : </label>
					<input type="password" id="psswd2" name="psswd2" required>
					<span id="spsswd2"></span><br/>
					<label for="nom">Nom : </label>
					<input type="text" id="nom" name="nom" required><br/>
					<label for="prenom">Prénom : </label>
					<input type="text" id="prenom" name="prenom" required><br/>
					<label for="classe">Classe : </label>
					<input type="tel" id="classe" name="classe" required><br/>
					<label for="tel">Téléphone : </label>
					<input type="text" id="tel" name="tel" required><br/>
					<label for="email">Email : </label>
					<input type="email" id="email" name="email" placeholder="admin.admin@hei.fr" required/>
					<span id="semail"></span><br/>
					<input type="submit" id="creer" value="valider"/>
				</form>
			</div>
		</section>
	</body>
</html>