<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset= utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="CSS/ficheVP.css" type="text/css">
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
		<c:if test="${user_id != null && user_type.equals('vp')}">
			<div id="photo_id">
				<c:if test="${vp.photo.isEmpty()}">
					<img src="IMAGE/photo/etudiant/admin.jpg" width="250px"
						height="250px">
				</c:if>
				<c:if test="${!vp.photo.isEmpty()}">
					<img src="IMAGE/photo/etudiant/${vp.photo}.jpg" width="250px"
						height="250px">
				</c:if>
			</div>
			<div id="titre-h3">
				<h2>
					Fiche VP : ${vp.equipesport.nom_sport}
					<c:if test="${equipesport.size() !=1}">(${vp.equipesport.nom_equipeSport})</c:if>
				</h2>
			</div>
			<div id="information">
				<fieldset>
					<legend>Informations</legend>
					<p>VP : ${vp.prenom} ${vp.nom}</p>
					<p>Téléphone : ${vp.telephone}</p>
					<p>Mail : ${vp.mail}</p>
				</fieldset>
			</div>
			<div id="participant">
				<fieldset>
					<legend>
						Participant(s) (<span class="btn"><a id="montrer_partic">montrer</a>/<a
							id="cacher_partic">cacher</a></span>)
					</legend>
					<div id="table_partic">
						<table>
							<thead>
								<tr>
									<th>Identifiant</th>
									<th>Nom</th>
									<th>Prénom</th>
									<th>Classe</th>
									<th>Téléphone</th>
									<th>Mail</th>
									<th>Cotisation</th>
									<th>Certificat</th>
									<th>Note</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="pratiquant" items="${pratiquants}">
									<tr>
										<td>${pratiquant.etudiant.identifiant}</td>
										<td>${pratiquant.etudiant.nom}</td>
										<td>${pratiquant.etudiant.prenom}</td>
										<td>${pratiquant.etudiant.classe}</td>
										<td>${pratiquant.etudiant.telephone}</td>
										<td>${pratiquant.etudiant.mail}</td>
										<td><c:if test="${pratiquant.etudiant.isCotisation()}">
												<span class="oui">Oui</span>
											</c:if>
											<c:if test="${!pratiquant.etudiant.isCotisation()}">
												<span class="non">Non</span>
											</c:if></td>
										<td><c:if test="${pratiquant.etudiant.isCertificat()}">
												<span class="oui">Oui</span>
											</c:if>
											<c:if test="${!pratiquant.etudiant.isCertificat()}">
												<span class="non">Non</span>
											</c:if></td>
										<td><c:if test="${pratiquant.note == 1}">
												<span class="oui">Oui</span>
											</c:if>
											<c:if test="${pratiquant.note == 0}">
												<span class="non">Non</span>
											</c:if>
											<c:if test="${pratiquant.note == -1}">
												<span class="">NI</span>
											</c:if></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</fieldset>
			</div>
			<div id="seance">
				<fieldset>
					<legend>
						Séance(s) (<span class="btn"><a id="montrer_seance">montrer</a>/<a
							id="cacher_seance">cacher</a></span>)
					</legend>
					<div id="table_seance">
						<p>
							Trier par : <br /> <label for="choix_seanceNum">Séance :</label>
							<select name="choix_seanceNum" id="choix_seanceNum">
								<option></option>
								<c:forEach var="numseance" items="${numseances}">
									<option value="${numseance.id_seance}">${numseance.id_seance}</option>
								</c:forEach>
								<option value="0">tout</option>
							</select> <input type="button" id="choisirNum" Value="Valider" /> <br /> <label
								for="choix_seanceNom">Nom : </label> <select
								name="choix_seanceNom" id="choix_seanceNom">
								<option></option>
								<c:forEach var="pratiquantnote" items="${pratiquantsnote}">
									<option value="${pratiquantnote.etudiant.identifiant}">${pratiquantnote.etudiant.prenom}
										${pratiquantnote.etudiant.nom}</option>
								</c:forEach>
								<option value="0">tout</option>
							</select> <input type="button" id="choisirNom" Value="Valider" />
						</p>
						<table>
							<thead>
								<tr>
									<th>Séance</th>
									<th>Date</th>
									<th>Identifiant</th>
									<th>Nom</th>
									<th>Prénom</th>
									<th>Présence</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="seancebynum" items="${seancesbynum}">
									<tr class="numero${seancebynum.id_seance} bynum">
										<td>${seancebynum.id_seance}</td>
										<td><fmt:formatDate value="${seancebynum.date_seance}"
												pattern="dd MMMM yyy" /></td>
										<td>${seancebynum.etudiant.identifiant}</td>
										<td>${seancebynum.etudiant.nom}</td>
										<td>${seancebynum.etudiant.prenom}</td>
										<td><c:if test="${seancebynum.presence_seance == 1}">
												<span class="oui">Oui</span>
											</c:if>
											<c:if test="${seancebynum.presence_seance == 0}">
												<span class="non">Non</span>
											</c:if></td>
									</tr>
								</c:forEach>
								<c:forEach var="seancebyid" items="${seancesbyid}">
									<tr class="id${seancebyid.id_etudiant} byid">
										<td>${seancebyid.id_seance}</td>
										<td><fmt:formatDate value="${seancebyid.date_seance}"
												pattern="dd MMMM yyy" /></td>
										<td>${seancebyid.etudiant.identifiant}</td>
										<td>${seancebyid.etudiant.nom}</td>
										<td>${seancebyid.etudiant.prenom}</td>
										<td><c:if test="${seancebyid.presence_seance == 1}">
												<span class="oui">Oui</span>
											</c:if>
											<c:if test="${seancebyid.presence_seance == 0}">
												<span class="non">Non</span>
											</c:if></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<br /> <input type="button" class="btnSeance" id="ajouterSeance"
							value="Ajouter une séance" />
					</div>
					<div id="newSeance">
						<h3>Ajouter une séance</h3>
						<form method="POST" action="ajouterseance">
							<label for="numseance">Séance n° : </label> <input type="text"
								name="numseance" id="numseance" /><br /> <label for="date">Date
								: </label> <input type="date" name="date" id="date" /><br />
							<table>
								<thead>
									<tr>
										<th>Identifiant</th>
										<th>Nom</th>
										<th>Prénom</th>
										<th>Présence</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="pratiquant" items="${pratiquants}">
										<c:if test="${pratiquant.note == '1'}">
											<tr>
												<td>${pratiquant.etudiant.identifiant}</td>
												<td>${pratiquant.etudiant.nom}</td>
												<td>${pratiquant.etudiant.prenom}</td>
												<td><select id="presence"
													name="presence_${pratiquant.etudiant.identifiant}">
														<option class="oui" value="1">Oui</option>
														<option class="non" value="0">Non</option>
												</select></td>
											</tr>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
							<br />
							<div class="btnSeance">
								<input type="button" id="retourSeance" value="Retour" /> <input
									type="submit" id="validerSeance" value="Valider" />
							</div>
						</form>
					</div>
				</fieldset>
			</div>
		</c:if>
	</section>
</body>
</html>