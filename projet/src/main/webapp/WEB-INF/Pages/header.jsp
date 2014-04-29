<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div id="header">
	<div id="logoBDS">
		<img src="IMAGE/main/logobds.png">
	</div>
	<div id="titreBDS">
		<h1>| Le site du BDS HEI LILLE</h1>
	</div>
	<div id="barremenu">
		<ul id="listemenu">
			<li id="listemenu-item-accueil"
				class="${pageSelectionnee == 'index' ? 'active' : ''}"><a
				href="accueil">Accueil</a></li>
			<li id="listemenu-item-bds">BDS
				<ul>
					<li id="listesubmenu-item-challenges">Challenges</li>
					<li id="listesubmenu-item-seg">SEG</li>
					<li id="listesubmenu-item-telethon">Téléthon</li>
					<li>Trophée Sportif</li>
					<li>Ligne BDS</li>
				</ul>
			</li>
			<li id="listemenu-item-commission">Commissions
				<ul>
					<li>CitaRun</li>
					<li>HEI Motor's</li>
					<li>Jumping HEI</li>
					<li>Pôle Voile</li>
				</ul>
			</li>
			<li id="listemenu-item-sports"
				class="${pageSelectionnee == 'sports' ? 'active' : ''}">Sports
				<ul>
					<c:forEach var="sport" items="${listeSports}">
						<li>${sport.nom_sport}
							<ul>
								<c:forEach var="equipeSport" items="${listeEuqipeSport}">
									<c:if test="${equipeSport.id_sport == sport.id_sport}">
										<li><a
											href="equipesport?id=${equipeSport.id_equipeSport}">${equipeSport.nom_sport}
												(${equipeSport.nom_equipeSport})</a></li>
									</c:if>
								</c:forEach>
							</ul>
						</li>
					</c:forEach>
				</ul>
			</li>
			<li class="${pageSelectionnee == 'equipe' ? 'active' : ''}"><a
				href="equipe">L'équipe</a></li>
			<li class="${pageSelectionnee == 'connecter' ? 'active' : ''}">
				<c:if test="${user_id == null}">
					<a href="connexion">Se connecter</a>
				</c:if> <c:if test="${user_id != null}">
					<c:if test="${user_type.equals('etudiant')}">
						<a href="ficheetudiant">${user_nom} ${user_prenom}</a>
					</c:if>
					<c:if test="${user_type.equals('vp')}">
						<a href="fichevp">VP : ${user_nom} ${user_prenom}</a>
					</c:if>
					<c:if test="${user_type.equals('bds')}">
						<a href="accueilbds">${user_nom}</a>
					</c:if>
					<ul>
						<li><a href="deconnexion">Déconnexion</a></li>
					</ul>
				</c:if>
			</li>
		</ul>

	</div>
</div>