<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset= utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <link rel="stylesheet" href="CSS/ficheEtudiant.css" type="text/css">
        <link rel="stylesheet" href="CSS/header.css" type="text/css">
        <script src="js/jquery.js"></script>
		<script src="js/ficheEtudiant.js"></script>
        <title>BDS HEI</title>
</head>
<body>
	<header>
		<c:set var="pageSelectionnee" value="connecter" scope="request" />
		<%@ include file="header.jsp" %>
    </header>
    <section id="informationetudiant">
    	<c:if test="${user_id != null}">
    		<div id="photo_id">
    			<c:if test="${etudiant.photo.isEmpty()}">
                <img src="IMAGE/photo/etudiant/admin.jpg" width="250px" height="250px">
                </c:if>
                <c:if test="${!etudiant.photo.isEmpty()}">
                <img src="IMAGE/photo/etudiant/${etudiant.photo}.jpg" width="250px" height="250px">
                </c:if>
            </div>
            <div id="titre-h3">
    			<h2>Bienvenue ${user_prenom} ${user_nom}</h2>
    		</div>
    		<div id="information">
    			<fieldset>
    				<legend>Informations</legend>
    				<p>Classe : ${etudiant.classe}</p>
    				<p>Téléphone : ${etudiant.telephone}</p>
    				<p>Mail : ${etudiant.mail}</p>
    				<p>Cotisation : <c:if test="${etudiant.isCotisation() == true}"><span class="oui">Oui</span></c:if><c:if test="${etudiant.isCotisation() == false}"><span class="non">Non</span></c:if>,   
    				   Certificat: <c:if test="${etudiant.isCertificat() == true}"><span class="oui">Oui</span></c:if><c:if test="${etudiant.isCertificat() == false}"><span class="non">Non</span></c:if>
    				</p>
    			</fieldset>
    		</div>
    		<div id="pratiquer_note">
    			<fieldset>
    				<legend>Sport Noté (<span class="btn"><a id="montrer_pratnote">montrer</a>/<a id="cacher_pratnote">cacher</a></span>)</legend>
    				<div id="table_sportnote">
    				<table>
    					<thead>
    						<tr>
    							<th>Sport</th>
    							<th>VP</th>
    							<th>Note</th>
    						</tr>
    					</thead>
    					<tbody>
    						<c:forEach var="pratiquer" items="${pratiquers}">
    						<c:if test="${pratiquer.note == 1}">
    						<tr>
    							<td>${pratiquer.equipesport.nom_sport} (${pratiquer.equipesport.nom_equipeSport})</td>
    							<td>${pratiquer.vp.prenom} ${pratiquer.vp.nom}</td>
    							<td><c:if test="${pratiquer.note == 1}"><span class="oui">Oui</span></c:if><c:if test="${pratiquer.note == 0}"><span class="non">Non</span></c:if><c:if test="${pratiquer.note == -1}"><span class="">NI</span></c:if></td>
    						</tr>
    						</c:if>
    						</c:forEach>
    					</tbody>
    				</table>
    				<br/>
    				<input type="button" id="afficher_seance" value="Afficher séance"/>
    				<a href="notervp" target="_bank"><input type="button" id="notervp" value="Noter VP"/></a>
    				</div>
    				<div id="table_seance">
    				<table>
    					<thead>
    						<tr>
    							<th>Séance</th>
    							<th>Date</th>
    							<th>Présence</th>
    						</tr>
    					</thead>
    					<tbody>
    						<c:forEach var="seance" items="${seances}">
    						<tr>
    							<td>${seance.id_seance}</td>
    							<td><fmt:formatDate value="${seance.date_seance}" pattern="dd MMMM yyy"/></td>
    							<td><c:if test="${seance.presence_seance == 1}"><span class="oui">Oui</span></c:if><c:if test="${seance.presence_seance == 0}"><span class="non">Non</span></c:if></td>
    						</tr>
    						</c:forEach>
    					</tbody>
    				</table>
    				<br/>
    				<input type="button" id="cacher_seance" value="Cacher séance"/>
    				</div>
    			</fieldset>
    		</div>
    		<br/>
    		<div id="autre_pratique">
    			<fieldset>
    				<legend>Autre(s) Sport(s) Pratiqué(s) (<span class="btn"><a id="montrer_autreprat">montrer</a>/<a id="cacher_autreprat">cacher</a></span>)</legend>
    				<div id="table_autrepratique">
    				<table>
    					<thead>
    						<tr>
    							<th>Sport</th>
    							<th>VP</th>
    							<th>Note</th>
    						</tr>
    					</thead>
    					<tbody>
    						<c:forEach var="pratiquer" items="${pratiquers}">
    						<c:if test="${pratiquer.note!= 1}">
    						<tr>
    							<td>${pratiquer.equipesport.nom_sport} (${pratiquer.equipesport.nom_equipeSport})</td>
    							<td>${pratiquer.vp.prenom} ${pratiquer.vp.nom}</td>
    							<td><c:if test="${pratiquer.note == 1}"><span class="oui">Oui</span></c:if><c:if test="${pratiquer.note == 0}"><span class="non">Non</span></c:if><c:if test="${pratiquer.note == -1}"><span class="">NI</span></c:if></td>
    						</tr>
    						</c:if>
    						</c:forEach>
    					</tbody>
    				</table>
    				</div>
    			</fieldset>
    		</div>
    		<br/>
    		<div id="challengeDo">
    			<fieldset>
    				<legend>Challenge(s) (<span class="btn"><a id="montrer_challenge">montrer</a>/<a id="cacher_challenge">cacher</a></span>)</legend>
    				<div id="table_challenge">
    				<table>
    					<thead>
    						<tr>
    							<th>Date</th>
    							<th>Nom</th>
    							<th>Presence</th>
    						</tr>
    					</thead>
    					<tbody>
    						<c:forEach var="participation" items="${participations}">
    						<tr>
    							<td><fmt:formatDate value="${participation.challenge.date_challenge}" pattern="dd/MM/YYYY"/></td>
    							<td>${participation.challenge.nom_challenge}</td>
    							<td><c:if test="${participation.presence == 1}"><span class="oui">Oui</span></c:if><c:if test="${participation.presence == 0}"><span class="non">Non</span></c:if><c:if test="${participation.presence == -1}"><span class="">NI</span></c:if></td>
    						</tr>
    						</c:forEach>
    					</tbody>
    				</table>
    				<br/>
    				<input type="button" class="btnChallenge" id="inscrire" value="S'inscrire à un challenge"/>
    				</div>
    				<div id="inscriptionChallenge">
    					<%@ include file="calendrier.jsp" %>
    				</div>
    			</fieldset>
    		</div>
    	</c:if>
    </section>
</body>
</html>