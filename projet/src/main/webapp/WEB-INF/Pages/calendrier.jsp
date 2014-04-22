<%@page import="bds.devweb.model.Challenge"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.GregorianCalendar"%>

<link rel="stylesheet" href="CSS/calendrier.css" type="text/css">

<script src="js/jquery.js"></script>
<script src="js/calendrier.js"></script>

<div id="calendrier">
	<%-- Tableau de Septembre à Décembre --%>
   	<c:forEach begin="8" end="11" var="m">
   		<div class="month <c:if test="${aujourdhui.getMonth() == m ? voir : ''}"/>" id="${m}">
    		<table>
    			<c:if test="${m == 8}"><caption><button class="moins" value ="${m}"><</button>Septembre<button class="plus" value ="${m + 1}">></button></caption></c:if>
    			<c:if test="${m == 9}"><caption><button class="moins" value ="${m-1}"><</button>Octobre<button class="plus" value ="${m + 1}">></button></caption></c:if>
    			<c:if test="${m == 10}"><caption><button class="moins" value ="${m-1}"><</button>Novembre<button class="plus" value ="${m + 1}">></button></caption></c:if>
    			<c:if test="${m == 11}"><caption><button class="moins" value ="${m-1}"><</button>Décembre<button class="plus" value ="0">></button></caption></c:if>
    			<thead>
    				<tr>
    					<th>Lun</th>
    					<th>Mar</th>
    					<th>Mer</th>
	    				<th>Jeu</th>
	    				<th>Ven</th>
	    				<th>Sam</th>
	    				<th>Dim</th>
    				</tr>
    			</thead>	
    			<tbody>
    				<tr>
   				 		<c:forEach var="calendrier" items="${calendriers}">
 							<c:if test="${calendrier.date.getMonth() == m}">
 								<c:if test="${calendrier.date.getDate() == 1}">
  								<c:if test="${calendrier.date.getDay() == 0}"><td colspan="6" class="padding"></td></c:if>
  								<c:if test="${calendrier.date.getDay() != 1 && calendrier.date.getDay() != 0 }"><td colspan="${calendrier.date.getDay() - 1}" class="padding"></td></c:if>
 								</c:if>
 								<td class="day" title="<fmt:formatDate value="${calendrier.date}" pattern="dd-MM-YYYY"/>">
 									<div class="events">
 										<ul>
 											<c:forEach var="challenge" items="${challenges}">
 												<c:if test="${challenge.date_challenge.getYear() == calendrier.date.getYear() && challenge.date_challenge.getMonth() == calendrier.date.getMonth() && challenge.date_challenge.getDate() == calendrier.date.getDate()}">
														<li>*</li>
													</c:if>
									</c:forEach>
 										</ul>
 									</div>
 									<div class="relative"><fmt:formatDate value="${calendrier.date}" pattern="dd"/></div>
 									<div class="jour ${mois == m && jour == calendrier.date.getDate() ? 'voir' : ''}" id="<fmt:formatDate value="${calendrier.date}" pattern="dd-MM-YYYY"/>">
 										<h1><fmt:formatDate value="${calendrier.date}" pattern="dd MMMM YYYY"/></h1>
 										<c:forEach var="challenge" items="${challenges}">
 											<c:if test="${challenge.date_challenge.getYear() == calendrier.date.getYear() && challenge.date_challenge.getMonth() == calendrier.date.getMonth() && challenge.date_challenge.getDate() == calendrier.date.getDate()}">
 												<div class="event">
 													<h4>Challenge : ${challenge.nom_challenge}<button class="voirInfo" value="${challenge.id_challenge}">Info</button></h4>
 													<div class="info" id="${challenge.id_challenge}">
 														<ul>
 															<li>Lieu : ${challenge.adresse.nom}</br>
 															<dd>${challenge.adresse.num} ${challenge.adresse.rue} </br>
 																${challenge.adresse.cp} ${challenge.adresse.ville} </br>
 																${challenge.adresse.pays}</dd>
 															</li>
 															<li>Heure : <fmt:formatDate value="${challenge.heure_challenge}" pattern="HH'h'mm"/></li>
 															<li><p>${challenge.description_challenge}</p></li>
 														</ul>
 														<form method="post" action="participer">
 															<input type="hidden" class="id_challenge" name="id_challenge" value="${challenge.id_challenge}"/>
 															<c:if test="${user_id != null && !user_type.equals('vp') && !user_type.equals('bds')}"><input type="submit" class="inscription" value="S'inscrire"/></c:if>
 														</form>
 													</div>
 												</div>
 											</c:if>
 										</c:forEach>
 									</div>
 								</td>
 								<c:if test="${calendrier.date.getDay() == 0}">
 									</tr><tr>
 								</c:if>
 							</c:if>
 						</c:forEach>
    				</tr>
    			</tbody>
    		</table>
    	</div>
    </c:forEach>
   	<%-- Calendrier de Janvier à Juin --%>
 	<c:forEach begin="0" end="5" var="m">
 		<div class="month ${mois == m ? 'voir' : ''}" id="${m}">
 			<table>
   		<c:if test="${m == 0}"><caption><button class="moins" value ="11"><</button>Janvier<button class="plus" value ="${m + 1}">></button></caption></c:if>
   		<c:if test="${m == 1}"><caption><button class="moins" value ="${m-1}"><</button>Février<button class="plus" value ="${m + 1}">></button></caption></c:if>
   		<c:if test="${m == 2}"><caption><button class="moins" value ="${m-1}"><</button>Mars<button class="plus" value ="${m + 1}">></button></caption></c:if>
   		<c:if test="${m == 3}"><caption><button class="moins" value ="${m-1}"><</button>Avril<button class="plus" value ="${m + 1}">></button></caption></c:if>
   		<c:if test="${m == 4}"><caption><button class="moins" value ="${m-1}"><</button>Mai<button class="plus" value ="${m + 1}">></button></caption></c:if>
   		<c:if test="${m == 5}"><caption><button class="moins" value ="${m-1}"><</button>Juin<button class="plus" value ="${m}">></button></caption></c:if>
   		<thead>
   			<tr>
   				<th>Lun</th>
   				<th>Mar</th>
   				<th>Mer</th>
   				<th>Jeu</th>
   				<th>Ven</th>
   				<th>Sam</th>
   				<th>Dim</th>
   			</tr>
 				</thead>	
 				<tbody>
 					<tr>
 						<c:forEach var="calendrier" items="${calendriers}">
 							<c:if test="${calendrier.date.getMonth() == m}">
 								<c:if test="${calendrier.date.getDate() == 1}">
  								<c:if test="${calendrier.date.getDay() == 0}"><td colspan="6" class="padding"></td></c:if>
  								<c:if test="${calendrier.date.getDay() != 1 && calendrier.date.getDay() != 0 }"><td colspan="${calendrier.date.getDay() - 1}" class="padding"></td></c:if>
 								</c:if>
 								<td class="day" title="<fmt:formatDate value="${calendrier.date}" pattern="dd-MM-YYYY"/>">
 									<div class="events">
 										<ul>
 											<c:forEach var="challenge" items="${challenges}">
 												<c:if test="${challenge.date_challenge.getYear() == calendrier.date.getYear() && challenge.date_challenge.getMonth() == calendrier.date.getMonth() && challenge.date_challenge.getDate() == calendrier.date.getDate()}">
														<li>*</li>
													</c:if>
											</c:forEach>
		 								</ul>
 									</div>
 									<div class="relative"><fmt:formatDate value="${calendrier.date}" pattern="dd"/></div>
 									<div class="jour ${mois == m && jour == calendrier.date.getDate() ? 'voir' : ''}" id="<fmt:formatDate value="${calendrier.date}" pattern="dd-MM-YYYY"/>">
 										<h1><fmt:formatDate value="${calendrier.date}" pattern="dd MMMM YYYY"/></h1>
 										<c:forEach var="challenge" items="${challenges}">
 											<c:if test="${challenge.date_challenge.getYear() == calendrier.date.getYear() && challenge.date_challenge.getMonth() == calendrier.date.getMonth() && challenge.date_challenge.getDate() == calendrier.date.getDate()}">
 												<div class="event">
 													<h4>Challenge : ${challenge.nom_challenge}<button class="voirInfo" value="${challenge.id_challenge}">Info</button></h4>
 													<div class="info" id="${challenge.id_challenge}">
 														<ul>
 															<li>Lieu : ${challenge.adresse.nom}</br>
 															<dd>${challenge.adresse.num} ${challenge.adresse.rue} </br>
 																${challenge.adresse.cp} ${challenge.adresse.ville} </br>
 																${challenge.adresse.pays}</dd>
 															</li>
 															<li>Heure : <fmt:formatDate value="${challenge.heure_challenge}" pattern="HH'h'mm"/></li>
 															<li><p>${challenge.description_challenge}</p></li>
 														</ul>
 														<form method="post" action="participer">
 															<input type="hidden" class="id_challenge" name="id_challenge" value="${challenge.id_challenge}"/>
 															<c:if test="${user_id != null && !user_type.equals('vp') && !user_type.equals('bds')}"><input type="submit" class="inscription" value="S'inscrire"/></c:if>
 														</form>
 													</div>
 												</div>
 											</c:if>
 										</c:forEach>
 									</div>
 								</td>
 								<c:if test="${calendrier.date.getDay() == 0}">
 									</tr><tr>
 								</c:if>
 							</c:if>
 						</c:forEach>
 					</tr>
 				</tbody>
 			</table>
 		</div>
 	</c:forEach>
</div>