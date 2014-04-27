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
    					<li><label for="note1">Propose de bonnes conditions d'entraînement :</label>
    						<input type="number" class="value" id="note1" name="note1" value="0" min="0" max="2" required="required"/>
    						/2<span id="snote1"></span>
    					</li>
    					<li><label for="note2">Gère l'administratif (cotisatio, CM, prise de présence) :</label>
    						<input type="number" class="value" id="note2" name="note2" value="0" min="0" max="2" required="required"/>
    						/2<span id="snote2"></span>
    					</li>
    					<li><label for="note3">Transmets les informations (Challenges, critères de notation) :</label>
    						<input type="number" class="value" id="note3" name="note3" value="0" min="0" max="2" required="required"/>
    						/2<span id="snote3"></span>
    					</li>
    				</ul>
    				<p>Qualité d'animation :</p>
    				<ul>
    					<li><label for="note4">Crée une ambiance au seuin de l'AS :</label>
    						<input type="number" class="value" id="note4" name="note4" value="0" min="0" max="2" required="required"/>
    						/2<span id="snote4"></span>
    					</li>
    					<li><label for="note5">Met en place des sorties, des repas :</label>
    						<input type="number" class="value" id="note5" name="note5" value="0" min="0" max="2" required="required"/>
    						/2<span id="snote5"></span>
    					</li>
    					<li><label for="note6">Propose des entraînements construits, des matchs amicaux ou de repésentations:</label>
    						<input type="number" class="value" id="note6" name="note6" value="0" min="0" max="2" required="required"/>
    						/2<span id="snote6"></span>
    					</li>
    				</ul>
    				<p>Qualité de savoir être :</p>
    				<ul>
    					<li><label for="note7">Communication :</label>
    						<input type="number" class="value" id="note7" name="note7" value="0" min="0" max="2" required="required"/>
    						/2<span id="snote7"></span>
    					</li>
    					<li><label for="note8">Attention aux membres :</label>
    						<input type="number" class="value" id="note8" name="note8" value="0" min="0" max="2" required="required"/>
    						/2<span id="snote8"></span>
    					</li>
    					<li><label for="note9">Présence / Ponctualité :</label>
    						<input type="number" class="value" id="note9" name="note9" value="0" min="0" max="2" required="required"/>
    						/2<span id="snote9"></span>
    					</li>
    					<li><label for="note10">Aura :</label>
    						<input type="number" class="value" id="note10" name="note10" value="0" min="0" max="2" required="required"/>
    						/2<span id="snote10"></span>
    					</li>
    				</ul>
    				<label for="resultat">Note :</label>
    				<input type="number" id="resultat" name="resultat"><span id="sresultat"></span>
    			</fieldset>
    		</div>
    		<input type="button" id="valider" value="Valider"/>
    		<input type="button" id="Annuler" value="Annuler"/>
    </section>
</body>
</html>