$(document).ready(function() {
	
	
	// fieldset pratiqué noté
	$("#table_sportnote").hide();
	$("#table_seance").hide();
	
	$("#montrer_pratnote").click(function(){
		$("#table_sportnote").show();
		$("#table_seance").hide();
	});
	$("#cacher_pratnote").click(function(){
		$("#table_sportnote").hide();
		$("#table_seance").hide();
	});
	
	$("#afficher_seance").click(function(){
		$("#table_sportnote").hide();
		$("#table_seance").show();
	});
	
	$("#cacher_seance").click(function(){
		$("#table_sportnote").show();
		$("#table_seance").hide();
	});
	
	// fieldset Autre Pratiqué
	$("#table_autrepratique").hide();
	
	
	$("#montrer_autreprat").click(function(){
		$("#table_autrepratique").show();
	});
	
	$("#cacher_autreprat").click(function(){
		$("#table_autrepratique").hide();
	});
	
	// filedset Challenge
	$("#table_challenge").hide();
	$("#inscriptionChallenge").hide();
	
	$("#montrer_challenge").click(function(){
		$("#table_challenge").show();
		$("#inscriptionChallenge").hide();
	});
	
	$("#cacher_challenge").click(function(){
		$("#table_challenge").hide();
		$("#inscriptionChallenge").hide();
	});
	
	$("#inscrire").click(function(){
		$("#table_challenge").hide();
		$("#inscriptionChallenge").show();
	});
	
});