<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset= utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <link rel="stylesheet" href="CSS/equipeSport.css" type="text/css">
        <link rel="stylesheet" href="CSS/header.css" type="text/css">
        <title>BDS HEI</title>
</head>
<body>
	<header>
		<c:set var="pageSelectionnee" value="sports" scope="request" />
		<%@ include file="header.jsp" %>
    </header>
    <section>
    	<h2>AS ${equipeSportVp.equipesport.nom_sport} (${equipeSportVp.equipesport.nom_equipeSport})</h2>
    	<div id="fiche_vp">
    		<c:if test="${user_id != null}">
   				<h4>Information VP</h4>
				<div id="info_vp">
					<p>Identifiant : ${equipeSportVp.id_etudiant}</p>
					<p>Nom : ${equipeSportVp.nom}</p>
					<p>Prénom : ${equipeSportVp.prenom}</p>
					<p>Tél : ${equipeSportVp.telephone}</p>
					<p>Mail : ${equipeSportVp.mail}</p>
				</div>
				<div id="photo_vp">
   					<c:if test="${equipeSportVp.photo.isEmpty()}">
	                	<img src="IMAGE/photo/etudiant/admin.jpg" width="180px" height="180px">
	                </c:if>
	                <c:if test="${!equipeSportVp.photo.isEmpty()}">
	                	<img src="IMAGE/photo/etudiant/${equipeSportVp.photo}.jpg" width="180px" height="180px">
	                </c:if>
				</div>
 			</c:if>
    	</div>
    	<div id="presentation_equipesport">
   			<h4>Présentation de l'Association Sportive</h4>
   			<p id="presentation">${equipeSportVp.equipesport.description_equipeSport}<p>
   			<c:if test="${user_id == equipeSportVp.id_etudiant && user_type.equals('vp')}">
   				<input type="button"value="modifier"/>
   			</c:if>
    	</div>
    </section>
</body>
</html>