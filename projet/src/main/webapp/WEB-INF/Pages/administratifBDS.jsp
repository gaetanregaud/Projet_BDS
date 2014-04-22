<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
		<%@ include file="header.jsp" %>
    </header>
    <section id="administratifBDS">
    	<c:if test="${user_id != null}">
    		<aside id="barremenuBDS">
			<c:set var="pageSelectionnee" value="connecter" scope="request" />
			<%@ include file="barremenuBDS.jsp" %>
    		</aside>
    		<h3>Administratif</h3>
    		<div id="sports_vp">
    			<fieldset>
    				<legend>Liste des étudiants n'étant pas à jour</legend>
    				<div id="listeSport_Vp">
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
	    						<c:forEach var="etudiant" items="${listeEtudiantNotOK}">
	    							<tr>
			    						<td><span class="info" id="${etudiant.identifiant}">${etudiant.nom} ${etudiant.prenom}</span></td>
			    						<td></td>
			    						<td><c:if test="${etudiant.isCotisation() == true}"><span class="oui">Oui</span></c:if><c:if test="${etudiant.isCotisation() == false}"><span class="non">Non</span></c:if></td>
			    						<td><c:if test="${etudiant.isCertificat() == true}"><span class="oui">Oui</span></c:if><c:if test="${etudiant.isCertificat() == false}"><span class="non">Non</span></c:if></td>
	    							</tr>
	    						</c:forEach>
	    					</tbody>
	    				</table>
    				</div>
    				<div>
    					<c:forEach var="etudiant" items="${listeEtudiantNotOK}">
    						<div class="infoEtudiant" id="info${etudiant.identifiant}">
    							<h5>${etudiant.identifiant}</h5>
    							<p>
    								Nom : ${etudiant.nom}</br>
    								Prénom : ${etudiant.prenom}</br>
    								Sport : </br>
    								Tel : ${etudiant.telephone}</br>
    								Mail : ${etudiant.mail}
    							</p>
    						</div>
    					</c:forEach>
    				</div>
    			</fieldset>
    		</div>
    	</c:if>
    </section>
</body>
</html>