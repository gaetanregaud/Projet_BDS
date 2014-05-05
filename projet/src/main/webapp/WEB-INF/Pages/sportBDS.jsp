<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset= utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1,safari">
<link rel="stylesheet" href="CSS/sportBDS.css" type="text/css">
<link rel="stylesheet" href="CSS/header.css" type="text/css">
<link rel="stylesheet" href="CSS/barremenuBDS.css" type="text/css">
<script src="js/jquery.js"></script>
<script src="js/sportBDS.js"></script>
<title>BDS HEI</title>
</head>
<body>
	<header>
		<c:set var="pageSelectionnee" value="connecter" scope="request" />
		<%@ include file="header.jsp"%>
	</header>
	<section id="sportBDS">
		<c:if test="${user_id != null}">
			<aside id="barremenuBDS">
				<c:set var="pageSelectionnee" value="connecter" scope="request" />
				<%@ include file="barremenuBDS.jsp"%>
			</aside>
			<h3>Sports</h3>
			<div id="sports_vp">
				<fieldset>
					<legend>Liste des Equipes de Sport</legend>
					<div id="listeSport_Vp">
						<table>
							<thead>
								<tr>
									<th>Sport</th>
									<th>Equipe</th>
									<th>VP</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="listevp" items="${listevps}">
									<tr>
										<td>${listevp.equipesport.nom_sport}</td>
										<td>${listevp.equipesport.nom_equipeSport}</td>
										<td><span class="info" id="${listevp.identifiant}">${listevp.nom}
												${listevp.prenom}</span></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div>
						<c:forEach var="listevp" items="${listevps}">
							<div class="infoVP" id="info${listevp.identifiant}">
								<h5>${listevp.identifiant}</h5>
								<p>
									Nom : ${listevp.nom}</br> Prénom : ${listevp.prenom}</br> Sport :
									${listevp.equipesport.nom_sport}</br> Tel : ${listevp.telephone}</br>
									Mail : ${listevp.mail}
								</p>
							</div>
						</c:forEach>
					</div>
				</fieldset>
			</div>
			<div id="newSport">
				<fieldset>
					<legend>Nouveau Sport</legend>
					<form method="post" action="sportbds">
						<input type="hidden" name="type" value="newSport" /> 
						<labe for="id_sport">Id du sport</label>
						<input type="text" class="id_sport" name="id_sport" />(ex: rugb)</br> 
						<label for="nom_sport">Nom du sport</label> 
						<input type="text" class="nom_sport" name="nom_sport" /></br> 
						<label for="id_equipeSport">Id de l'équipe</label> 
						<input type="text" class="id_equipeSport" name="id_equipeSport" />(ex: rugb_e1)</br> 
						<label for="nom_equipeSport">Nom de l'équipe</label> 
						<input type="text" class="nom_equipeSport" name="nom_equipeSport" />(ex : Equipe 1)</br>
						<label for="newvp">VP :</label> 
						<input type="text" id="newvp" name="newvp" /> 
						<span class="oui"><img src="IMAGE/main/oui_icone.jpg" width="15px" height="15px" /></span> 
						<span class="non"><img src="IMAGE/main/non_icone.jpg" width="15px" height="15px" /></span> 
						<input type="Submit" value="Valider" class="ok" />
					</form>
				</fieldset>
			</div>
			<div id="newEquipe">
				<fieldset>
					<legend>Nouvelle Equipe</legend>
					<form method="post" action="sportbds">
						<input type="hidden" name="type" value="newEquipe" /> <label
							for="id_sport">Sport</label> <select class="id_sport"
							name="id_sport">
							<c:forEach var="sport" items="${listeSports}">
								<option value="${sport.id_sport}">${sport.nom_sport}</option>
							</c:forEach>
						</select></br> <label for="id_equipeSport">Id de l'équipe</label> <input
							type="text" class="id_equipeSport" name="id_equipeSport" />(ex:
						rugb_e1)</br> <label for="nom_equipeSport">Nom de l'équipe</label> <input
							type="text" class="nom_equipeSport" name="nom_equipeSport" />(ex
						: Equipe 1)</br> <label for="vp">VP :</label> <input type="text"
							class="vp" name="vp" /> <span class="oui"><img
							src="IMAGE/main/oui_icone.jpg" width="15px" height="15px" /></span> <span
							class="non"><img src="IMAGE/main/non_icone.jpg"
							width="15px" height="15px" /></span> <input type="submit"
							value="Valider" class="ok" />
					</form>
				</fieldset>
			</div>
			<div id="suprEquipe">
				<fieldset>
					<legend>Supprimer une équipe</legend>
					<form method="post" action="sportbds">
						<input type="hidden" name="type" value="suprEquipe" /> <label
							for="id_equipeSport">Sport</label> <select id="id_equipeSport"
							name="id_equipeSport">
							<c:forEach var="sport" items="${listeSports}">
								<optgroup label="${sport.nom_sport}">
									<c:forEach var="equipeSport" items="${listeEquipeSport}">
										<c:if test="${sport.id_sport == equipeSport.id_sport}">
											<option value="${equipeSport.id_equipeSport}">${sport.nom_sport}
												(${equipeSport.nom_equipeSport})</option>
										</c:if>
									</c:forEach>
								</optgroup>
							</c:forEach>
						</select></br> <input type="submit" title="Valider" value="Supprimer" />
					</form>
				</fieldset>
			</div>
			<div id="suprSport">
				<fieldset>
					<legend>Supprimer un sport</legend>
					<form method="post" action="sportbds">
						<input type="hidden" name="type" value="suprSport" /> <label
							for="id_sport">Sport</label> <select id="id_sport"
							name="id_sport">
							<c:forEach var="sport" items="${listeSports}">
								<option value="${sport.id_sport}">${sport.nom_sport}</option>
							</c:forEach>
						</select></br> <input type="submit" title="Valider" value="Supprimer" />
					</form>
				</fieldset>
			</div>
			<div id="modifVP">
				<fieldset>
					<legend>Modifier un VP</legend>
					<form method="post" action="sportbds">
						<input type="hidden" name="type" value="modifVP" /> <label
							for="modifequipesport">Sport</label> <select
							id="modifequipesport" name="modifequipesport">
							<c:forEach var="sport" items="${listeSports}">
								<optgroup label="${sport.nom_sport}">
									<c:forEach var="equipeSport" items="${listeEquipeSport}">
										<c:if test="${sport.id_sport == equipeSport.id_sport}">
											<option value="${equipeSport.id_equipeSport}">${sport.nom_sport}
												(${equipeSport.nom_equipeSport})</option>
										</c:if>
									</c:forEach>
								</optgroup>
							</c:forEach>
						</select></br> <label for="vp">Nouveau VP :</label> <input type="text" id="vp"
							name="vp" /></br> <input type="submit" title="Modifier"
							value="Modifier" />
					</form>
				</fieldset>
			</div>
		</c:if>
	</section>
</body>
</html>