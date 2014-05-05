<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset= utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1,safari">
<link rel="stylesheet" href="CSS/administratifBDS.css" type="text/css">
<link rel="stylesheet" href="CSS/header.css" type="text/css">
<link rel="stylesheet" href="CSS/barremenuBDS.css" type="text/css">
<script src="js/jquery.js"></script>
<script src="js/administratifBDS.js"></script>
<title>BDS HEI</title>
</head>
<body>
	<header>
		<c:set var="pageSelectionnee" value="connecter" scope="request" />
		<%@ include file="header.jsp"%>
	</header>
	<section id="administratifBDS">
		<c:if test="${user_id != null}">
			<aside id="barremenuBDS">
				<c:set var="pageSelectionnee" value="connecter" scope="request" />
				<%@ include file="barremenuBDS.jsp"%>
			</aside>
			<h3>Administratif</h3>
			<div id="admini_etudiantNotOK">
				<fieldset>
					<legend>Liste des étudiants n'étant pas à jour</legend>
					<div id="admini_listeEtudiantNotOK">
						<table>
							<thead>
								<tr>
									<th>Nom Prénom</th>
									<th>Sport</th>
									<th>Cotisation</th>
									<th>Certificat</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="etudiant" items="${listeEtudiant}">
									<c:if test="${etudiant.isCotisation() == false || etudiant.isCertificat() == false}">
										<tr>
											<td><span class="info" id="${etudiant.identifiant}">${etudiant.nom}
													${etudiant.prenom}</span></td>
											<td></td>
											<td><c:if test="${etudiant.isCotisation() == true}">
													<span class="oui">Oui</span>
												</c:if>
												<c:if test="${etudiant.isCotisation() == false}">
													<span class="non">Non</span>
												</c:if></td>
											<td><c:if test="${etudiant.isCertificat() == true}">
													<span class="oui">Oui</span>
												</c:if>
												<c:if test="${etudiant.isCertificat() == false}">
													<span class="non">Non</span>
												</c:if></td>
										</tr>
									</c:if>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div>
						<c:forEach var="etudiant" items="${listeEtudiant}">
							<div class="infoEtudiant" id="info${etudiant.identifiant}">
								<h5>${etudiant.identifiant}</h5>
								<p>
									Nom : ${etudiant.nom}<br /> Prénom : ${etudiant.prenom}<br />
									Sport : <br /> Tel : ${etudiant.telephone}<br /> Mail :
									${etudiant.mail}
								</p>
							</div>
						</c:forEach>
					</div>
				</fieldset>
			</div>
			<div id="recherche">
				<div id="champs_recherche">
					<fieldset>
						<legend>Recherche Etudiant(e)</legend>
						<form>
							<label for="recherche_etudiant">Rechercher un(e)
								étudiant(e) : </label> <input type="search" id="recherche_etudiant"
								name="recherche_etudiant" placeholder="h10048" /> <input
								type="button" id="btn_rechercheEtudiant" value="Rechercher" /><br />
						</form>
					</fieldset>
				</div>
				<div id="resultat_recherche">
					<fieldset>
						<legend>Info Etudiant </legend>
						<div id="rech_infoEtudiant">
							<form>
								<p>
									Identifiant : <span id="rech_id"></span>
								</p>
								<p>
									Nom : <span id="rech_nom"></span>
								</p>
								<p>
									Prénom : <span id="rech_prenom"></span>
								</p>
								<p>
									Tél : <span id="rech_tel"></span>
								</p>
								<p>
									Mail : <span id="rech_mail"></span>
								</p>
								<p>
									Classe : <span id="rech_classe"></span>
								</p>
								<label for="rech_cotis">Cotisation : </label> <select
									id="rech_cotis" name="rech_cotis">
									<option value="oui">Oui</option>
									<option value="non">Non</option>
								</select><br />
								<br /> <label for="rech_certif">Cotisation : </label> <select
									id="rech_certif" name="rech_certif">
									<option value="oui">Oui</option>
									<option value="non">Non</option>
								</select><br />
								<br /> <label for="rech_licence">Licence : </label> <input
									type="text" id="rech_licence" name="rech_licence" /><br />
								<br /> <input type="hidden" id="post_id" /> <input type="button"
									id="btn_modi_infoEtudiant" value="Modifier" />
							</form>
						</div>
						<div id="rech_photoEtudiant">
							<img src="" id="rech_photo" width="250px" height="250px" />
						</div>
					</fieldset>
				</div>
			</div>
		</c:if>
	</section>
</body>
</html>