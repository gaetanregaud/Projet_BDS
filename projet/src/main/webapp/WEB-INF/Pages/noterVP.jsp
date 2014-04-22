<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset= utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <link rel="stylesheet" href="CSS/noterVP.css" type="text/css">
        <script src="js/jquery.js"></script>
		<script src="js/noterVP.js"></script>
        <title>BDS HEI</title>
</head>
<body>
	    <section id="notationvp">
            <div id="titre-h3">
    			<h2>Evaluation VP</h2>
    		</div>
    		<div id="infoVP">
    			<fieldset>
    				<c:forEach var="pratiquer" items="${pratiquers}">
    					<c:if test="${pratiquer.note == 1}">
    				<legend>Information VP</legend>
    				<p>Identifiant : ${pratiquer.vp.identifiant}</p>
    				<p>Nom : ${pratiquer.vp.nom}</p>
    				<p>Prénom : ${pratiquer.vp.prenom}</p>
    				</c:if>
    				</c:forEach>
    			</fieldset>
    		</div>
    		<div id="evalutation">
    			<fieldset>
    				<legend>Evaluation</legend>
    				<p>Qualité d'organisation et de gestion :</p>
    				<ul>
    					<li><label for="qog">Propose de bonnes conditions d'entraînement :</label>
    						<input type="number" class="value" id="qog" name="qog" value="0" min="0" max="2" required="required"/>
    						/2
    					</li>
    					<li><label for="ga">Gère l'administratif (cotisatio, CM, prise de présence) :</label>
    						<input type="number" class="value" id="ga" name="ga" value="0" min="0" max="2" required="required"/>
    						/2
    					</li>
    					<li><label for="ti">Transmets les informations (Challenges, critères de notation) :</label>
    						<input type="number" class="value" id="ti" name="ti" value="0" min="0" max="2" required="required"/>
    						/2
    					</li>
    				</ul>
    				<p>Qualité d'animation :</p>
    				<ul>
    					<li><label for="line2.1">Crée une ambiance au seuin de l'AS :</label>
    						<input type="number" id="line2.1" name="line2.1" value="0" min="0" max="2" required="required"/>
    						/2
    					</li>
    					<li><label for="line2.2">Met en place des sorties, des repas :</label>
    						<input type="number" id="line2.2" name="line2.2" value="0" min="0" max="2" required="required"/>
    						/2
    					</li>
    					<li><label for="line2.3">Propose des entraînements construits, des matchs amicaux ou de repésentations:</label>
    						<input type="number" id="line2.3" name="line2.3" value="0" min="0" max="2" required="required"/>
    						/2
    					</li>
    				</ul>
    				<p>Qualité de savoir être :</p>
    				<ul>
    					<li><label for="line3.1">Communication :</label>
    						<input type="number" id="line3.1" name="line3.1" value="0" min="0" max="2" required="required"/>
    						/2
    					</li>
    					<li><label for="line3.2">Attention aux membres :</label>
    						<input type="number" id="line3.2" name="line3.2" value="0" min="0" max="2" required="required"/>
    						/2
    					</li>
    					<li><label for="line3.3">Présence / Ponctualité :</label>
    						<input type="number" id="line3.3" name="line3.3" value="0" min="0" max="2" required="required"/>
    						/2
    					</li>
    					<li><label for="line3.4">Aura :</label>
    						<input type="number" id="line3.4" name="line3.4" value="0" min="0" max="2" required="required"/>
    						/2
    					</li>
    				</ul>
    				<label for="resultat">Note :</label>
    				<input type="number" id="resultat" name="resultat">
    			</fieldset>
    		</div>
    		<input type="submit" id="valider" value="Valider"/>
    		<input type="button" id="Annuler" value="Annuler"/>
    </section>
</body>
</html>