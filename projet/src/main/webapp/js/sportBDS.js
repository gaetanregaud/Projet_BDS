$(document).ready(function() {
	
	$("#sports_vp").show();
	
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
	
	$("#vp").keyup(function(){
		$.ajax({
			url:"rechercheridetudiant",
			type:"POST",
			data:{vp:$("#vp").val()
			}
		}).done(function(data){
			if(data == 'oui'){
				$("#oui").show();
				$("#non").hide();
				$("#ok").show();
			}
			else{
				$("#non").show();
				$("#oui").hide();
				$("#ok").hide();
			}
		});
	});
	
});