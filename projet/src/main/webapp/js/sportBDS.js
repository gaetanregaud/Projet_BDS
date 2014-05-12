$(document).ready(function() {
	
	$("#sports_vp").show();
	$(".ok").hide();
	
	$(".info").click(function(){
		$(".infoVP").hide();
		var id = this.id;
		$("#info"+id).show();
	});
	
	$("#liste_sport").click(function(){
		$(".infoVP").hide();
		$("#newSport").hide();
		$("#newEquipe").hide();
		$("#suprEquipe").hide();
		$("#suprSport").hide();
		$("#modifVP").hide();
		$("#sports_vp").show();
	});
	
	$("#ajou_sport").click(function(){
		$(".infoVP").hide();
		$("#sports_vp").hide();
		$("#newEquipe").hide();
		$("#suprEquipe").hide();
		$("#suprSport").hide();
		$("#modifVP").hide();
		$("#newSport").show();
	});
	
	$("#ajou_equipe").click(function(){
		$(".infoVP").hide();
		$("#sports_vp").hide();
		$("#newSport").hide();
		$("#suprEquipe").hide();
		$("#suprSport").hide();
		$("#modifVP").hide();
		$("#newEquipe").show();
	});
	
	$("#supr_equipe").click(function(){
		$(".infoVP").hide();
		$("#sports_vp").hide();
		$("#newSport").hide();
		$("#suprSport").hide();
		$("#modifVP").hide();
		$("#suprEquipe").show();
		$("#newEquipe").hide();
	});
	
	$("#supr_sport").click(function(){
		$(".infoVP").hide();
		$("#sports_vp").hide();
		$("#newSport").hide();
		$("#suprSport").show();
		$("#suprEquipe").hide();
		$("#modifVP").hide();
		$("#newEquipe").hide();
	});
	
	$("#modif_sport").click(function(){
		$(".infoVP").hide();
		$("#sports_vp").hide();
		$("#newSport").hide();
		$("#suprSport").hide();
		$("#suprEquipe").hide();
		$("#modifVP").show();
		$("#newEquipe").hide();
	});
	
	$("#plus").click(function(){
		$("#sports").hide();
		$("#ajousport").show();
	});
	
	$("#Snewvp").keyup(function(){
		$.ajax({
			url:"sportbds",
			type:"POST",
			data:{
				type:"verifIdentifiant",
				id_etudiant:$("#Snewvp").val()
			}
		}).done(function(data){
			if(data == 'oui'){
				$("#Soui").show();
				$("#Snon").hide();
				$("#Sok").show();
			}
			else{
				$("#Snon").show();
				$("#Soui").hide();
				$("#Sok").hide();
			}
		});
	});
	
	$("#Enewvp").keyup(function(){
		$.ajax({
			url:"sportbds",
			type:"POST",
			data:{
				type:"verifIdentifiant",
				id_etudiant:$("#Enewvp").val()
			}
		}).done(function(data){
			if(data == 'oui'){
				$("#Eoui").show();
				$("#Enon").hide();
				$("#Eok").show();
			}
			else{
				$("#Enon").show();
				$("#Eoui").hide();
				$("#Eok").hide();
			}
		});
	});
	
});