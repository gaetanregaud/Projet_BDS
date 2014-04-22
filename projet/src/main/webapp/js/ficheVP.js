$(document).ready(function() {
	
	// Table Participant
	$("#table_partic").hide();
	
	$("#montrer_partic").click(function(){
		$("#table_partic").show();
	});
	$("#cacher_partic").click(function(){
		$("#table_partic").hide();
	});
	
	// Table Seance
	$("#table_seance").hide();
	$(".byid").hide();
	$(".bynum").hide();
	
	$("#montrer_seance").click(function(){
		$("#table_seance").show();
	});
	
	$("#cacher_seance").click(function(){
		$("#table_seance").hide();
	});
	
	$("#choisirNum").click(function(){
		var num = $("#choix_seanceNum").val();
		$(".bynum").hide();
		$(".byid").hide();
		if(num == 0){
			$(".byid").hide();
			$(".bynum").show();
		}
		else{
			$(".numero"+num).show();
		}
	});
	
	$("#choisirNom").click(function(){
		$(".byid").hide();
		$(".bynum").hide();
		var nom = $("#choix_seanceNom").val();
		if(nom == "0"){
			$(".byid").show();
			$(".bynum").hide();
		}
		else{
			$(".id"+nom).show();
		}
	});
	
	//Ajouter Séance
	$("#newSeance").hide();
	
	$("#ajouterSeance").click(function(){
		$("#table_seance").hide();
		$("#newSeance").show();
	});
	
	$("#retourSeance").click(function(){
		$("#table_seance").show();
		$("#newSeance").hide();
	});
	
	
});